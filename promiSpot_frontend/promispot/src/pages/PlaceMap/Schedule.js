import axios from "axios";
import { BsFillCalendarCheckFill } from "react-icons/bs";
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import DragDrop from '../../components/DragDrop/DragDrop';
import { useDispatch, useSelector } from "react-redux";
import { React, useEffect, useRef, useState } from "react";
import { changeRect } from "../../Redux/reducer/map";
import mapdata from "../mapdata.json";
import TabBar2 from "../../components/TabBar/TabBar2";
import { SERVER_URL } from "../../constants/constants";
import { Link, Outlet, useNavigate, useLocation } from "react-router-dom";

import '../scss/Map_Container.scss'

const { kakao } = window;

export default function Schedule() {
  // 지도 변수
  const [map, setMap] = useState(null);

  const location = useLocation();
  const [promiseSeq, setPromiseSeq] = useState();

  // 지도 그리기
  const mapscript = () => {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(37.5013, 127.0397),
      level: 2,
    };

    var map = new kakao.maps.Map(container, options);
    setMap(map);
  };

  useEffect(() => {
    mapscript()
  }, [])


  /////////////// 약속 후보 장소 /////////////////////

  // 약속 후보 장소를 불러오는 함수
  const [votePlaceList, setVotePlaceList] = useState();
  const searchVotePlaceList = async () => {
    var path = location.pathname;
    var parse = path.split("/");
    var promiseSeq = parse[2];

    const response = await axios({
      method: "GET",
      url: `${SERVER_URL}/vote/getVotePlaceList/${promiseSeq}`,
    });
    if (response.data !== "fail") {
      setVotePlaceList(response.data);
    }
  };

  // 약속 후보 마커 찍기
  const [beforeVotePlaceList, setBeforeVotePlaceList] = new useState();
  useEffect(() => {
    if (beforeVotePlaceList) {
      beforeVotePlaceList.forEach((beforeVotePlace) => {
        beforeVotePlace.setMap(null);
      });
    }

    console.log(votePlaceList);
    console.log("마커가 잘 찍히는지 확인");

    setBeforeVotePlaceList([]);

    // BeforeVotePlaceList의 데이터로 마커 찍기
    if (votePlaceList) {
      votePlaceList.forEach((votePlace) => {

        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
        var imageSize = new kakao.maps.Size(24, 35); 
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    
        // 마커 생성 및 클릭이벤트 등록 
        var marker = new kakao.maps.Marker({
          map: map,
          position: new kakao.maps.LatLng(votePlace.placeY, votePlace.placeX),
          image: markerImage,
          title: votePlace.placeId,
          placeImgUrl: votePlace.placeImgUrl,
          clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
        });

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function() {
          // 마커 위에 인포윈도우를 표시합니다
          console.log(marker.getTitle());
          console.log(votePlace.placeImgUrl);
        });

        setBeforeVotePlaceList((prev) => [...prev, marker]);
        marker.setMap(map);
      });
    }
  }, [votePlaceList]);

  // 처음 페이지가 켜졌을 때 발생하는 함수들
  useEffect(() => {
    var path = location.pathname;
    var parse = path.split("/");
    var seq = parse[2];
    setPromiseSeq(seq);
    searchVotePlaceList();
  }, []);

  // <TabBar2 />에서 검색 클릭 했을 때 true/false 값 가져오기
  const [searchOpen, setSearchOpen] = useState(false);
  const catchClickSearch = (isOpen) => {
    setSearchOpen(isOpen);
  };

  // <TabBar2 />에서 추천 클릭 했을 때 true/false 값 가져오기
  const [recommendOpen, setRecommendOpen] = useState(false);
  const catchClickRecommend = (isOpen) => {
    setRecommendOpen(isOpen);
  };

  return (
    <div id="map" style={{ width: '100vw', height: '100vh' }}>
         <div className='schedule-wrapper'>
           <div className='schedule-background-wrapper'>
  
             {/* <DndProvider backend={HTML5Backend}>
      //   <DragDrop/>
      // </DndProvider> */}
             <button className="draggable" draggable="true">🦊</button>
             <button className="draggable" draggable="true">🐸</button>
           </div>
           <div className='map-button-wrapper'>
             <button className="map-button-schedule">
               <BsFillCalendarCheckFill size="40" color="#ffffff" />
             </button>
           </div>
         </div>
         <div className="map-tab-wrapper">
        <TabBar2
          // 검색 클릭 했을 때
          catchClickSearch={catchClickSearch}
          // 추천 클릭 했을 때
          catchClickRecommend={catchClickRecommend}
        />
      </div>
    </div>
    )
  }

