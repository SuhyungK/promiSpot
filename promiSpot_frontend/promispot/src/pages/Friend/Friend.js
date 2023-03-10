import React, { useEffect, useState } from "react";
import BasicHeader2 from "../../components/Header/BasicHeader2";
// import SelectBar from "../../components/SelectBar/SelectBar";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import Tabs from "@mui/material/Tabs";
import axios from "axios";
import PropTypes from "prop-types";
import { ImSearch } from "react-icons/im";
import { useSelector } from "react-redux";
import MiniButton from "../../components/Buttons/MiniButton";
import TabBar from "../../components/TabBar/TabBar";
import { SERVER_URL } from "../../constants/constants";
import "../scss/Friend.scss";
import FriendList from "./FriendList";
import FriendRequestReceive from "./FriendRequestReceive";
import FriendRequestSend from "./FriendRequestSend";

function TabPanel(props) {
  const { children, value, index, ...other } = props;
  const [inputData, setInputData] = useState("");

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <div>{children}</div>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

export default function Friend(props) {
  const [value, setValue] = useState(0);
  const [valid, setValid] = useState(false);
  const [toWho, setToWho] = useState([]);

  const isValid = () => {
    setValid(!valid);
  };

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  // input 값 변할 때마다 axios 요청
  const [inputData, setInputData] = useState("");
  const changeInputData = (e) => {
    setInputData(e.target.value);
  };

  useEffect(() => {
    if (inputData && inputData !== "") {
      getFriendSearchResult(inputData);
    } else {
      setFriendSearchResult([]);
    }
  }, [inputData]);

  const [friendSearchResult, setFriendSearchResult] = useState([]);

  /** 변경된 inputData 값으로 친구 검색 결과 목록 가져오기 */
  const getFriendSearchResult = async (memberInfo) => {
    try {
      const response = await axios({
        method: "GET",
        url: `${SERVER_URL}/friend/search/${memberSeq}/${memberInfo}`,
      });
      if (response.data === "fail") {
        setFriendSearchResult([]);
      } else {
        /** 친구 검색 결과 리스트 */
        setFriendSearchResult(response.data);
        /* 내가 요청한 친구 목록 배열 만들기 */
        const toWhoFriend = response.data.map((friend) => {
          if (friend.isSend === 1) return friend.memberSeq;
        });
        /* toWho 배열에 내가 요청한 친구 목록에서 memberSeq만 담은 배열 넣기 */
        setToWho(toWhoFriend.filter((friendSeq) => friendSeq));
      }
    } catch (err) {
      setFriendSearchResult([]);
      console.log(err);
    }
  };

  /* 친구 요청 보내기
  내 친구 목록/받은 요청/보낸 요청에 다 memberSeq props로 넘겨주기 */
  const memberSeq = useSelector((state) => state?.user?.info?.memberSeq);

  const sendFriendRequest = async (friendRequestMember) => {
    try {
      // 요청 보낸 친구 목록에 이미 존재한다면 => 그냥 존재하는 거...
      if (toWho.includes(friendRequestMember)) {
        // /*  요청 취소하면서 목록에서 제거
        // 요청 보낸 친구 목록에 있는 번호과 현재 누른 버튼의 친구 번호가 일치하는 경우 제외 */
        // const newWho = toWho.filter((who) => {
        //   return who !== friendRequestMember;
        // });
        // setToWho(newWho);
        // 요청 보낸 친구 목록에 존재하지 않는다면 => 요청
      } else {
        const response = await axios({
          method: "POST",
          url: `${SERVER_URL}/friend/request`,
          data: {
            memberSeq,
            friendRequestMember,
          },
        });

        const newWho = [...toWho, friendRequestMember];
        setToWho(newWho);
      }
    } catch (err) {
      console.log(err);
    }
  };

  /* 검색결과창 닫기 */
  const closeSearchResult = () => {
    setInputData("");
    setFriendSearchResult([]);
  };

  /* 친구 검색 결과 리스트 값이 변할 때마다 실행되는 함수 */
  useEffect(() => {}, [friendSearchResult]);

  return (
    <>
      <div className="d-flex flex-column justify-content-center w-100 h-100">
        <div className="d-flex flex-column justify-content-center align-items-center"></div>
      </div>
      <BasicHeader2 text="친구 목록" />

      {/* <SearchBar /> */}
      <div className="friend-wrapper">
        <div className="friend-search-wrapper">
          {/* <SearchBar GetAxiosQuery={null}/> */}
          <div className="search">
            <input
              type="text"
              className="search__input"
              placeholder="아이디 또는 전화번호를 입력하세요"
              onChange={changeInputData}
              value={inputData}
            />
            <div className="search__icon">
              <ImSearch />
            </div>
          </div>
        </div>
      </div>
      <Box sx={{ width: "100%" }}>
        {friendSearchResult.length === 0 ? (
          <>
            <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
              <Tabs
                value={value}
                onChange={handleChange}
                textColor="secondary"
                indicatorColor="secondary"
                aria-label="basic tabs example"
                centered
              >
                <Tab label="내 친구" {...a11yProps(0)} />
                <Tab label="받은 요청" {...a11yProps(1)} />
                <Tab label="보낸 요청" {...a11yProps(2)} />
              </Tabs>
            </Box>
            <TabPanel value={value} index={0}>
              <div className="friend-list">
                <FriendList memberSeq={memberSeq} />
              </div>
            </TabPanel>
            <TabPanel value={value} index={1}>
              <div className="friend-request-receive">
                <FriendRequestReceive memberSeq={memberSeq} />
              </div>
            </TabPanel>
            <TabPanel value={value} index={2}>
              <div className="friend-request-send">
                <FriendRequestSend memberSeq={memberSeq} />
              </div>
            </TabPanel>
          </>
        ) : (
          <>
            <Box
              sx={{
                borderBottom: 1,
                borderColor: "divider",
                position: "relative",
              }}
            >
              {/* <Tabs className="friend-search-result-list" textColor="secondary" indicatorColor="secondary" aria-label="basic tabs example">
                <p>검색결과</p>
                <p>X</p>
              </Tabs> */}
              <div className="friend-search-result-list">
                <div className="friend-search-result-title">검색결과</div>
                <div
                  className="friend-search-result-icon"
                  onClick={closeSearchResult}
                >
                  ✖
                </div>
              </div>
            </Box>
            {/* <TabPanel> */}
            {friendSearchResult.map((friend, idx) => {
              return (
                <div key={idx} className="friend-myfriend-wrapper">
                  <div className="friend-myfriend-profile-info-wrapper">
                    <div className="friend-myfriend-profile-info-img-wrapper">
                      <div className="friend-myfriend-profile-info-img">
                        <img src={friend.memberImgPath} width="40px" />
                      </div>
                    </div>
                    <div className="friend-myfriend-profile-info-name-wrapper">
                      <div className="friend-myfriend-profile-info-nickname-wrapper">
                        {friend.memberNick}
                      </div>
                      <div className="friend-myfriend-profile-info-id-wrapper">
                        {friend.memberId}
                      </div>
                    </div>
                    <div className="friend-myfriend-profile-info-button-wrapper">
                      {/* 1. div 요청됨 : 친구가 아니지만 요청을 보냄 */}
                      {/* 2. button 요청 : 친구가 아니고 요청을 보낸 적이 없음 */}
                      {/* 3. null : 친구임 */}

                      {/* 친구가 아니고 */}
                      {!friend.isFriend ? (
                        // 요청을 보낸 적이 없고
                        !friend.isSend && !toWho.includes(friend.memberSeq) ? (
                          // 요청을 받은 적도 없으면 >> 요청 버튼
                          !friend.isReceive ? (
                            <div
                              className="friend-myfriend-profile-info-button"
                              onClick={() =>
                                sendFriendRequest(friend.memberSeq)
                              }
                            >
                              <MiniButton text="요청" />
                            </div>
                          ) : (
                            // 요청을 받은 적이 있으면 >> 수락/거절
                            <div>
                              <button>수락/거절</button>
                            </div>
                          )
                        ) : (
                          // 요청을 보낸 적이 있으면 >> 요청됨
                          <div>요청됨</div>
                        )
                      ) : // 이미 친구
                      null}
                    </div>
                  </div>
                </div>
              );
            })}
            {/* </TabPanel> */}
          </>
        )}
      </Box>
      <TabBar />
    </>

    //     <div className="d-flex flex-column justify-content-center w-100 h-100">

    // <div className="d-flex flex-column justify-content-center align-items-center">
    // </div>
    // </div>
    //     <BasicHeader2 text="친구 목록"/>
    //     <div className="friend-wrapper">
    //       <div className="friend-search-wrapper">

    //     <div className="search">
    //       <input type="text" className="search__input" placeholder="Search..." onClick={isValid}/>
    //       <div className="search__icon">
    //         <ImSearch/>
    //       </div>
    //     </div></div>
    //       {/* <Link to = '/friend/list'>
    //         <button onClick={() => setVisible1(!visible1)}>친구 리스트</button>
    //       </Link>
    //       {visible1 && <FriendList/>}
    //       <Link to = '/friend/respond'>
    //         <button onClick={() => setVisible2(!visible2)}>받은 요청</button>
    //       </Link>
    //       {visible2 && <FriendRequestReceive/>}
    //       <Link to = '/friend/send'>
    //         <button onClick={() => setVisible3(!visible3)}>보낸 요청</button>
    //        </Link>
    //       {visible3 && <FriendRequestSend/>}
    //       <div className="friend-content-wrapper">
    //         {<FriendList/> */}
    //       </div>
    //       <div>
    //         {
    //           valid === false
    //           ? <Box sx={{ width: '100%' }}>
    //           <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
    //             <Tabs value={value} onChange={handleChange} textColor="secondary" indicatorColor="secondary" aria-label="basic tabs example" centered>
    //               <Tab label="내 친구" {...a11yProps(0)} />
    //               <Tab label="받은 요청" {...a11yProps(1)} />
    //               <Tab label="보낸 요청" {...a11yProps(2)} />
    //             </Tabs>
    //           </Box>
    //           <TabPanel value={value} index={0}>
    //             <FriendList/>
    //           </TabPanel>
    //           <TabPanel value={value} index={1}>
    //             <FriendRequestReceive/>
    //           </TabPanel>
    //           <TabPanel value={value} index={2}>
    //             <FriendRequestSend/>
    //           </TabPanel>
    //         </Box>
    //         : <Box sx={{ width: '100%' }}>
    //         <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
    //           <Tabs value={value} onChange={handleChange} textColor="secondary" indicatorColor="secondary" aria-label="basic tabs example" centered>
    //             <Tab label="검색 결과" {...a11yProps(0)} />
    //           </Tabs>
    //         </Box>
    //         <TabPanel value={value} index={0}>
    //     <div className='profile-info-wrapper'>
    //       <div className='profile-info-img-wrapper'>
    //         <div className='profile-info-img'>
    //         <img src={require("../../img/IU_Profile.jpg")} width="40px"/></div>
    //       </div>
    //       <div className='profile-info-name-wrapper'>
    //         <div className='profile-info-nickname-wrapper'>닉네임</div>
    //         <div className='profile-info-id-wrapper'>아이디</div>
    //       </div>
    //       <div className='profile-info-button-wrapper'>
    //         <div className='profile-info-button'><button onClick={isValid}>요청</button></div>

    //       </div>
    //     </div>
    //         </TabPanel>
    //       </Box>
    //         }
    //     </div>
  );
}
