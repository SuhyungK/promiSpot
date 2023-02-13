import React from "react";
import { useNavigate, useLocation } from "react-router-dom";
import BasicHeader from "../../components/Header/BasicHeader3";
import getDetail from "./GetDetail";
import { setPlace } from "../../Redux/reducer/map";
import { useDispatch } from "react-redux";
import GetDetail from "./GetDetail";
import axios from "axios";
import { SERVER_URL } from "../../constants/constants";

export default function PlaceDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const place = location.state;
  const dispatch = useDispatch();

  console.log(place);
  /* 장소 '등록하기' 버튼 누르면 지도에 등록하면서 약속 장소 후보로 등록 */
  // 등록하기를 누르면 DB에 저장 
  const registerPlaceToMap = async () => {
    dispatch(setPlace(place));

    console.log(place);

    // 장소등록 -> 장소아이디를 받아온다 -> 약속장소후보 테이블에 저장한다. 
    const sendData = {
      placeId: place.id,
      placeName: place.place_name,
      placeCategoryName: place.category_name,
      placeCategoryGroupCode: place.category_group_code,
      placeCategoryGroupName: place.category_group_name,
      placePhone: place.phone,
      placeRoadAddressName: place.road_address_name,
      placeAddressName: place.address_name,
      placeX: place.x,
      placeY: place.y,
      placeUrl: "",
      placeImgUrl: ""
    }

    try { 
      const response = await axios({
        url: "http://localhost:9090/api/place/insert",
        method: "POST",
        // baseURL: SERVER_URL,
        data: sendData
      });
      console.log("장소등록 후 응답보기 : ")
      console.log(response.placeId);
    } catch (err) {
      console.log(err);
    }








    // store.dispatch({
    //   type: "REGISTER_PLACE_TO_MAP",
    //   mapCenterPosition: {
    //     x: parseFloat(place.x),
    //     y: parseFloat(place.y),
    //   },
    // });
  };

  return (
    <div className="place-modal-wrapper">
      <div>
        <BasicHeader text={place.place_name} />
      </div>
      <div>
        <GetDetail place={place} />
        <button
          onClick={registerPlaceToMap}
          style={{
            position: "absolute",
            bottom: "5vh",
            right: 0,
            backgroundColor: "white",
            border: "1px solid #c4c4c4",
            marginRight: "1rem",
            marginBottom: "1rem",
            padding: "0.5rem 1rem",
            borderRadius: "15px",
          }}
        >
          등록하기
        </button>
      </div>
    </div>
  );
}
