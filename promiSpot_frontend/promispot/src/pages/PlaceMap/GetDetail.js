import axios from "axios";
import { useState, useEffect } from "react";
import BeatLoader from "react-spinners/BeatLoader";

export default function GetDetail(props) {
  var address_name, category_name, phone, place_name, place_url, road_address_name;

  console.log("여기는 props 이다");
  console.log(props);

  if (props.place.address_name) {
    console.log("1");
    var place = props.place;
    address_name = place.address_name;
    category_name = place.category_name;
    phone = place.phone;
    place_name = place.place_name;
    // place_url = place.place_url
    // road_addres_name = place.placeRoadAddressName
  } else if (props.place.memberSeq) {
    let votePlace = props.place;
    address_name = votePlace.placeAddressName;
    category_name = votePlace.placeCategoryName;
    phone = votePlace.placePhone;
    place_name = votePlace.placeName;
    // place_url = votePlace.placeUrl
    // road_address_name = votePlace.placeRoadAddressName
  }

  console.log("category_name", category_name);
  // console.log("props", props)
  /* axios 관련 처리 */

  const catergoryList = category_name.split(" > ");
  console.log("2");
  const [Img, setData] = useState(undefined);
  const [Star, setStar] = useState(undefined);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  // const placeUrl = "https://place.map.kakao.com/314463146"

  const sendData = async () => {
    try {
      setLoading(true);
      const response = await axios.post("http://i8a109.p.ssafy.io/crawling", {
        placeUrl: place_url,
      });
      setData(response.data.placeImg);
      setStar(response.data.placeStar);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    sendData();
  }, []);

  return (
    <div style={{ margin: "0 auto", width: "85%" }}>
      <div style={{ marginBottom: "0.6rem" }}>
        {catergoryList.map((category, idx) => {
          return (
            <div
              style={{
                border: "1px solid #c4c4c4",
                display: "inline-block",
                padding: "1.5vw 1.8vw",
                margin: "1vw",
                borderRadius: "0.9rem",
                fontSize: "0.8rem",
              }}
            >
              {category}
            </div>
          );
        })}
      </div>
      <div style={{ display: "grid", gridTemplateColumns: "3.5fr 8.5fr" }}>
        {!loading ? (
          <div style={{}}>
            <img
              style={{ borderRadius: "3vw", width: "20vw", height: "20vw" }}
              src={Img}
              alt="img"
            />
          </div>
        ) : (
          <div style={{ width: "100%", textAlign: "center" }}>
            <h2>로딩중 ... </h2>
            <BeatLoader color="#36d7b7" />
          </div>
        )}
        <div style={{}}>
          <div>{address_name}</div>
          <div>{phone}</div>
          <div>평점 : {Star}</div>
        </div>
      </div>

      {/* <button
        // onClick={registerPlaceToMap}
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
      </button> */}
    </div>
  );
}
