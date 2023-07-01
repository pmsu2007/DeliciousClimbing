const openButton = document.querySelector(".profile_edit");
const closeButton = document.querySelector(".close-button");
const checkButton = document.querySelectorAll(".passwordCheck");
const profileInfoButton = document.querySelector("#update-info");
const profileImgButton = document.querySelector("#update-img");
const passwordButton = document.querySelector("#update-password");
const deleteUserButton = document.querySelector("#delete-user");

// 회원 정보 수정에 대한 클릭 이벤트 처리
profileInfoButton.addEventListener("click", (e) => {
  e.stopPropagation();
  document.querySelector(".modal-update-info").style.display = "block";
  document.querySelector(".modal-update-img").style.display = "none";
  document.querySelector(".modal-update-password").style.display = "none";
  document.querySelector(".modal-delete-user").style.display = "none";
});

// 프로필 이미지 수정에 대한 클릭 이벤트 처리
profileImgButton.addEventListener("click", (e) => {
  e.stopPropagation();
  document.querySelector(".modal-update-img").style.display = "block";
  document.querySelector(".modal-update-info").style.display = "none";
  document.querySelector(".modal-update-password").style.display = "none";
  document.querySelector(".modal-delete-user").style.display = "none";
});

// 비밀번호 변경에 대한 클릭 이벤트 처리
passwordButton.addEventListener("click", (e) => {
  e.stopPropagation();
  document.querySelector(".modal-update-img").style.display = "none";
  document.querySelector(".modal-update-info").style.display = "none";
  document.querySelector(".modal-update-password").style.display = "block";
  document.querySelector(".modal-delete-user").style.display = "none";
});

// 회원 탈퇴에 대한 클릭 이벤트 처리
deleteUserButton.addEventListener("click", (e) => {
  e.stopPropagation();
  document.querySelector(".modal-update-img").style.display = "none";
  document.querySelector(".modal-update-info").style.display = "none";
  document.querySelector(".modal-update-password").style.display = "none";
  document.querySelector(".modal-delete-user").style.display = "block";
});

// 열기 버튼에 대한 클릭 이벤트 처리
openButton.addEventListener("click", (e) => {
  e.stopPropagation();
  const modalId = openButton.getAttribute("data-modal");
  const modal = document.querySelector(modalId);
  modal.style.visibility = "visible";
});

// 닫기 버튼에 대한 클릭 이벤트 처리

closeButton.addEventListener("click", (e) => {
  e.stopPropagation();
  const modal = closeButton.parentElement.parentElement;
  console.log(modal);
  modal.style.visibility = "hidden";
});

// 체크 버튼에 대한 클릭 이벤트 처리
checkButton.forEach((button) => {
  button.addEventListener("click", () => {
    const modal = button.parentElement.parentElement;
    modal.style.display = "none";
  });
});

$(function () {
  areaSelectMaker("select[name=addressSiDo]");
});

var areaSelectMaker = function (target) {
  if (target == null || $(target).length == 0) {
    console.warn("Unkwon Area Tag");
    return;
  }

  var area = {
    "서울특별시": [
      {
        "sigungu": "종로구",
        "code": 1111000000
      },
      {
        "sigungu": "중구",
        "code": 1114000000
      },
      {
        "sigungu": "용산구",
        "code": 1117000000
      },
      {
        "sigungu": "성동구",
        "code": 1120000000
      },
      {
        "sigungu": "광진구",
        "code": 1121500000
      },
      {
        "sigungu": "동대문구",
        "code": 1123000000
      },
      {
        "sigungu": "중랑구",
        "code": 1126000000
      },
      {
        "sigungu": "성북구",
        "code": 1129000000
      },
      {
        "sigungu": "강북구",
        "code": 1130500000
      },
      {
        "sigungu": "도봉구",
        "code": 1132000000
      },
      {
        "sigungu": "노원구",
        "code": 1135000000
      },
      {
        "sigungu": "은평구",
        "code": 1138000000
      },
      {
        "sigungu": "서대문구",
        "code": 1141000000
      },
      {
        "sigungu": "마포구",
        "code": 1144000000
      },
      {
        "sigungu": "양천구",
        "code": 1147000000
      },
      {
        "sigungu": "강서구",
        "code": 1150000000
      },
      {
        "sigungu": "구로구",
        "code": 1153000000
      },
      {
        "sigungu": "금천구",
        "code": 1154500000
      },
      {
        "sigungu": "영등포구",
        "code": 1156000000
      },
      {
        "sigungu": "동작구",
        "code": 1159000000
      },
      {
        "sigungu": "관악구",
        "code": 1162000000
      },
      {
        "sigungu": "서초구",
        "code": 1165000000
      },
      {
        "sigungu": "강남구",
        "code": 1168000000
      },
      {
        "sigungu": "송파구",
        "code": 1171000000
      },
      {
        "sigungu": "강동구",
        "code": 1174000000
      }],
      "부산광역시": [
      {
        "sigungu": "중구",
        "code": 2611000000
      },
      {
        "sigungu": "서구",
        "code": 2614000000
      },
      {
        "sigungu": "동구",
        "code": 2617000000
      },
      {
        "sigungu": "영도구",
        "code": 2620000000
      },
      {
        "sigungu": "부산진구",
        "code": 2623000000
      },
      {
        "sigungu": "동래구",
        "code": 2626000000
      },
      {
        "sigungu": "남구",
        "code": 2629000000
      },
      {
        "sigungu": "북구",
        "code": 2632000000
      },
      {
        "sigungu": "해운대구",
        "code": 2635000000
      },
      {
        "sigungu": "사하구",
        "code": 2638000000
      },
      {
        "sigungu": "금정구",
        "code": 2641000000
      },
      {
        "sigungu": "강서구",
        "code": 2644000000
      },
      {
        "sigungu": "연제구",
        "code": 2647000000
      },
      {
        "sigungu": "수영구",
        "code": 2650000000
      },
      {
        "sigungu": "사상구",
        "code": 2653000000
      },
      {
        "sigungu": "기장군",
        "code": 2671000000
      }],
      "대구광역시": [
      {
        "sigungu": "중구",
        "code": 2711000000
      },
      {
        "sigungu": "동구",
        "code": 2714000000
      },
      {
        "sigungu": "서구",
        "code": 2717000000
      },
      {
        "sigungu": "남구",
        "code": 2720000000
      },
      {
        "sigungu": "북구",
        "code": 2723000000
      },
      {
        "sigungu": "수성구",
        "code": 2726000000
      },
      {
        "sigungu": "달서구",
        "code": 2729000000
      },
      {
        "sigungu": "달성군",
        "code": 2771000000
      }],
      "인천광역시":[
      {
        "sigungu": "중구",
        "code": 2811000000
      },
      {
        "sigungu": "동구",
        "code": 2814000000
      },
      {
        "sigungu": "미추홀구",
        "code": 2817700000
      },
      {
        "sigungu": "연수구",
        "code": 2818500000
      },
      {
        "sigungu": "남동구",
        "code": 2820000000
      },
      {
        "sigungu": "부평구",
        "code": 2823700000
      },
      {
        "sigungu": "계양구",
        "code": 2824500000
      },
      {
        "sigungu": "서구",
        "code": 2826000000
      },
      {
        "sigungu": "강화군",
        "code": 2871000000
      },
      {
        "sigungu": "옹진군",
        "code": 2872000000
      }],
      "광주광역시":[
      {
        "sigungu": "동구",
        "code": 2911000000
      },
      {
        "sigungu": "서구",
        "code": 2914000000
      },
      {
        "sigungu": "남구",
        "code": 2915500000
      },
      {
        "sigungu": "북구",
        "code": 2917000000
      },
      {
        "sigungu": "광산구",
        "code": 2920000000
      }],
      "대전광역시":[
      {
        "sigungu": "동구",
        "code": 3011000000
      },
      {
        "sigungu": "중구",
        "code": 3014000000
      },
      {
        "sigungu": "서구",
        "code": 3017000000
      },
      {
        "sigungu": "유성구",
        "code": 3020000000
      },
      {
        "sigungu": "대덕구",
        "code": 3023000000
      }],
      "울산광역시":[
      {
        "sigungu": "중구",
        "code": 3111000000
      },
      {
        "sigungu": "남구",
        "code": 3114000000
      },
      {
        "sigungu": "동구",
        "code": 3117000000
      },
      {
        "sigungu": "북구",
        "code": 3120000000
      },
      {
        "sigungu": "울주군",
        "code": 3171000000
      }],
      "세종특별자치시": [
      {
        "sigungu": "세종시",
        "code": 3611000000
      }],
      "경기도":[
      {
        "sigungu": "수원시",
        "code": 4111000000
      },
      {
        "sigungu": "성남시",
        "code": 4113000000
      },
      {
        "sigungu": "의정부시",
        "code": 4115000000
      },
      {
        "sigungu": "안양시",
        "code": 4117000000
      },
      {
        "sigungu": "부천시",
        "code": 4119000000
      },
      {
        "sigungu": "광명시",
        "code": 4121000000
      },
      {
        "sigungu": "평택시",
        "code": 4122000000
      },
      {
        "sigungu": "동두천시",
        "code": 4125000000
      },
      {
        "sigungu": "안산시",
        "code": 4127000000
      },
      {
        "sigungu": "고양시",
        "code": 4128000000
      },
      {
        "sigungu": "과천시",
        "code": 4129000000
      },
      {
        "sigungu": "구리시",
        "code": 4131000000
      },
      {
        "sigungu": "남양주시",
        "code": 4136000000
      },
      {
        "sigungu": "오산시",
        "code": 4137000000
      },
      {
        "sigungu": "시흥시",
        "code": 4139000000
      },
      {
        "sigungu": "군포시",
        "code": 4141000000
      },
      {
        "sigungu": "의왕시",
        "code": 4143000000
      },
      {
        "sigungu": "하남시",
        "code": 4145000000
      },
      {
        "sigungu": "용인시",
        "code": 4146000000
      },
      {
        "sigungu": "파주시",
        "code": 4148000000
      },
      {
        "sigungu": "이천시",
        "code": 4150000000
      },
      {
        "sigungu": "안성시",
        "code": 4155000000
      },
      {
        "sigungu": "김포시",
        "code": 4157000000
      },
      {
        "sigungu": "화성시",
        "code": 4159000000
      },
      {
        "sigungu": "광주시",
        "code": 4161000000
      },
      {
        "sigungu": "양주시",
        "code": 4163000000
      },
      {
        "sigungu": "포천시",
        "code": 4165000000
      },
      {
        "sigungu": "여주시",
        "code": 4167000000
      },
      {
        "sigungu": "연천군",
        "code": 4180000000
      },
      {
        "sigungu": "가평군",
        "code": 4182000000
      },
      {
        "sigungu": "양평군",
        "code": 4183000000
      }],
      "강원도":[
      {
        "sigungu": "춘천시",
        "code": 4211000000
      },
      {
        "sigungu": "원주시",
        "code": 4213000000
      },
      {
        "sigungu": "강릉시",
        "code": 4215000000
      },
      {
        "sigungu": "동해시",
        "code": 4217000000
      },
      {
        "sigungu": "태백시",
        "code": 4219000000
      },
      {
        "sigungu": "속초시",
        "code": 4221000000
      },
      {
        "sigungu": "삼척시",
        "code": 4223000000
      },
      {
        "sigungu": "홍천군",
        "code": 4272000000
      },
      {
        "sigungu": "횡성군",
        "code": 4273000000
      },
      {
        "sigungu": "영월군",
        "code": 4275000000
      },
      {
        "sigungu": "평창군",
        "code": 4276000000
      },
      {
        "sigungu": "정선군",
        "code": 4277000000
      },
      {
        "sigungu": "철원군",
        "code": 4278000000
      },
      {
        "sigungu": "화천군",
        "code": 4279000000
      },
      {
        "sigungu": "양구군",
        "code": 4280000000
      },
      {
        "sigungu": "인제군",
        "code": 4281000000
      },
      {
        "sigungu": "고성군",
        "code": 4282000000
      },
      {
        "sigungu": "양양군",
        "code": 4283000000
      }],
      "충청북도":[
      {
        "sigungu": "청주시",
        "code": 4311000000
      },
      {
        "sigungu": "충주시",
        "code": 4313000000
      },
      {
        "sigungu": "제천시",
        "code": 4315000000
      },
      {
        "sigungu": "보은군",
        "code": 4372000000
      },
      {
        "sigungu": "옥천군",
        "code": 4373000000
      },
      {
        "sigungu": "영동군",
        "code": 4374000000
      },
      {
        "sigungu": "증평군",
        "code": 4374500000
      },
      {
        "sigungu": "진천군",
        "code": 4375000000
      },
      {
        "sigungu": "괴산군",
        "code": 4376000000
      },
      {
        "sigungu": "음성군",
        "code": 4377000000
      },
      {
        "sigungu": "단양군",
        "code": 4380000000
      }],
      "충청남도": [
      {
        "sigungu": "천안시",
        "code": 4413000000
      },
      {
        "sigungu": "공주시",
        "code": 4415000000
      },
      {
        "sigungu": "보령시",
        "code": 4418000000
      },
      {
        "sigungu": "아산시",
        "code": 4420000000
      },
      {
        "sigungu": "서산시",
        "code": 4421000000
      },
      {
        "sigungu": "논산시",
        "code": 4423000000
      },
      {
        "sigungu": "계룡시",
        "code": 4425000000
      },
      {
        "sigungu": "당진시",
        "code": 4427000000
      },
      {
        "sigungu": "금산군",
        "code": 4471000000
      },
      {
        "sigungu": "부여군",
        "code": 4476000000
      },
      {
        "sigungu": "서천군",
        "code": 4477000000
      },
      {
        "sigungu": "청양군",
        "code": 4479000000
      },
      {
        "sigungu": "홍성군",
        "code": 4480000000
      },
      {
        "sigungu": "예산군",
        "code": 4481000000
      },
      {
        "sigungu": "태안군",
        "code": 4482500000
      }],
      "전라북도":[
      {
        "sigungu": "전주시",
        "code": 4511000000
      },
      {
        "sigungu": "군산시",
        "code": 4513000000
      },
      {
        "sigungu": "익산시",
        "code": 4514000000
      },
      {
        "sigungu": "정읍시",
        "code": 4518000000
      },
      {
        "sigungu": "남원시",
        "code": 4519000000
      },
      {
        "sigungu": "김제시",
        "code": 4521000000
      },
      {
        "sigungu": "완주군",
        "code": 4571000000
      },
      {
        "sigungu": "진안군",
        "code": 4572000000
      },
      {
        "sigungu": "무주군",
        "code": 4573000000
      },
      {
        "sigungu": "장수군",
        "code": 4574000000
      },
      {
        "sigungu": "임실군",
        "code": 4575000000
      },
      {
        "sigungu": "순창군",
        "code": 4577000000
      },
      {
        "sigungu": "고창군",
        "code": 4579000000
      },
      {
        "sigungu": "부안군",
        "code": 4580000000
      }],
      "전라남도": [
      {
        "sigungu": "목포시",
        "code": 4611000000
      },
      {
        "sigungu": "여수시",
        "code": 4613000000
      },
      {
        "sigungu": "순천시",
        "code": 4615000000
      },
      {
        "sigungu": "나주시",
        "code": 4617000000
      },
      {
        "sigungu": "광양시",
        "code": 4623000000
      },
      {
        "sigungu": "담양군",
        "code": 4671000000
      },
      {
        "sigungu": "곡성군",
        "code": 4672000000
      },
      {
        "sigungu": "구례군",
        "code": 4673000000
      },
      {
        "sigungu": "고흥군",
        "code": 4677000000
      },
      {
        "sigungu": "보성군",
        "code": 4678000000
      },
      {
        "sigungu": "화순군",
        "code": 4679000000
      },
      {
        "sigungu": "장흥군",
        "code": 4680000000
      },
      {
        "sigungu": "강진군",
        "code": 4681000000
      },
      {
        "sigungu": "해남군",
        "code": 4682000000
      },
      {
        "sigungu": "영암군",
        "code": 4683000000
      },
      {
        "sigungu": "무안군",
        "code": 4684000000
      },
      {
        "sigungu": "함평군",
        "code": 4686000000
      },
      {
        "sigungu": "영광군",
        "code": 4687000000
      },
      {
        "sigungu": "장성군",
        "code": 4688000000
      },
      {
        "sigungu": "완도군",
        "code": 4689000000
      },
      {
        "sigungu": "진도군",
        "code": 4690000000
      },
      {
        "sigungu": "신안군",
        "code": 4691000000
      }],
      "경상북도": [
      {
        "sigungu": "포항시",
        "code": 4711000000
      },
      {
        "sigungu": "경주시",
        "code": 4713000000
      },
      {
        "sigungu": "김천시",
        "code": 4715000000
      },
      {
        "sigungu": "안동시",
        "code": 4717000000
      },
      {
        "sigungu": "구미시",
        "code": 4719000000
      },
      {
        "sigungu": "영주시",
        "code": 4721000000
      },
      {
        "sigungu": "영천시",
        "code": 4723000000
      },
      {
        "sigungu": "상주시",
        "code": 4725000000
      },
      {
        "sigungu": "문경시",
        "code": 4728000000
      },
      {
        "sigungu": "경산시",
        "code": 4729000000
      },
      {
        "sigungu": "군위군",
        "code": 4772000000
      },
      {
        "sigungu": "의성군",
        "code": 4773000000
      },
      {
        "sigungu": "청송군",
        "code": 4775000000
      },
      {
        "sigungu": "영양군",
        "code": 4776000000
      },
      {
        "sigungu": "영덕군",
        "code": 4777000000
      },
      {
        "sigungu": "청도군",
        "code": 4782000000
      },
      {
        "sigungu": "고령군",
        "code": 4783000000
      },
      {
        "sigungu": "성주군",
        "code": 4784000000
      },
      {
        "sigungu": "칠곡군",
        "code": 4785000000
      },
      {
        "sigungu": "예천군",
        "code": 4790000000
      },
      {
        "sigungu": "봉화군",
        "code": 4792000000
      },
      {
        "sigungu": "울진군",
        "code": 4793000000
      },
      {
        "sigungu": "울릉군",
        "code": 4794000000
      }],
      "경상남도": [
      {
        "sigungu": "창원시",
        "code": 4812000000
      },
      {
        "sigungu": "진주시",
        "code": 4817000000
      },
      {
        "sigungu": "통영시",
        "code": 4822000000
      },
      {
        "sigungu": "사천시",
        "code": 4824000000
      },
      {
        "sigungu": "김해시",
        "code": 4825000000
      },
      {
        "sigungu": "밀양시",
        "code": 4827000000
      },
      {
        "sigungu": "거제시",
        "code": 4831000000
      },
      {
        "sigungu": "양산시",
        "code": 4833000000
      },
      {
        "sigungu": "의령군",
        "code": 4872000000
      },
      {
        "sigungu": "함안군",
        "code": 4873000000
      },
      {
        "sigungu": "창녕군",
        "code": 4874000000
      },
      {
        "sigungu": "고성군",
        "code": 4882000000
      },
      {
        "sigungu": "남해군",
        "code": 4884000000
      },
      {
        "sigungu": "하동군",
        "code": 4885000000
      },
      {
        "sigungu": "산청군",
        "code": 4886000000
      },
      {
        "sigungu": "함양군",
        "code": 4887000000
      },
      {
        "sigungu": "거창군",
        "code": 4888000000
      },
      {
        "sigungu": "합천군",
        "code": 4889000000
      }],
      "제주특별자치도":[
      {
        "sigungu": "제주시",
        "code": 5011000000
      },
      {
        "sigungu": "서귀포시",
        "code": 5013000000
      }
    ]
  };

  for (i = 0; i < $(target).length; i++) {
    (function (z) {
      var a1 = $(target).eq(z);
      var a2 = a1.next();

      //초기화
      init(a1, true);

      //시도 생성
      var areaKeys1 = Object.keys(area);
      areaKeys1.forEach(function (Region) {
        a1.append("<option value=" + Region + ">" + Region + "</option>");
      });

      //시군구 이벤트
      $(a1).on("change", function () {
        init($(this), false);
        var region = $(this).val();
        area[region].forEach(function (Do) {
          a2.append("<option value=" + Do.code + ">" + Do.sigungu + "</option>");
        });
      });
    })(i);

    function init(t, first) {
      first ? t.empty().append("<option value=''>선택</option>") : "";
      t.next().empty().append("<option value=''>선택</option>");
    }
  }
};

//산 선택 설정
$(function () {
  var mountain = [
      {
        "name": "가리산",
        "id": 2213100200
      },
      {
        "name": "가리왕산",
        "id": 2211050100
      },
      {
        "name": "가야산",
        "id": 2710070100
      },
      {
        "name": "가지산",
        "id": 1704060100
      },
      {
        "name": "감악산",
        "id": 2140140100
      },
      {
        "name": "강천산",
        "id": 2607102300
      },
      {
        "name": "계룡산",
        "id": 2402020400
      },
      {
        "name": "계방산",
        "id": 2215064300
      },
      {
        "name": "공작산",
        "id": 2216100300
      },
      {
        "name": "관악산",
        "id": 1105040200
      },
      {
        "name": "구병산",
        "id": 2303020400
      },
      {
        "name": "금산",
        "id": 2805090900
      },
      {
        "name": "금수산",
        "id": 2307050900
      },
      {
        "name": "금오산",
        "id": 2704390100
      },
      {
        "name": "금정산",
        "id": 2810040200
      },
      {
        "name": "깃대봉",
        "id": 2612140500
      },
      {
        "name": "남산",
        "id": 2702090600
      },
      {
        "name": "내연산",
        "id": 2724130500
      },
      {
        "name": "내장산",
        "id": 2514380500
      },
      {
        "name": "대둔산",
        "id": 2404170800
      },
      {
        "name": "대암산",
        "id": 2210050800
      },
      {
        "name": "대야산",
        "id": 2707010800
      },
      {
        "name": "덕숭산(수덕산)",
        "id": 2412050400
      },
      {
        "name": "덕유산(향적봉)",
        "id": 2505040700
      },
      {
        "name": "덕항산",
        "id": 2214180500
      },
      {
        "name": "도락산",
        "id": 2307083702
      },
      {
        "name": "도봉산(자운봉)",
        "id": 1110010100
      },
      {
        "name": "두륜산",
        "id": 2621041300
      },
      {
        "name": "두타산",
        "id": 2204151100
      },
      {
        "name": "마니산",
        "id": 1401990600
      },
      {
        "name": "마이산",
        "id": 2515021000
      },
      {
        "name": "명성산",
        "id": 2212010400
      },
      {
        "name": "명지산",
        "id": 2101021300
      },
      {
        "name": "모악산",
        "id": 2503060700
      },
      {
        "name": "무등산",
        "id": 2622132600
      },
      {
        "name": "무학산",
        "id": 2806020900
      },
      {
        "name": "미륵산",
        "id": 2816111100
      },
      {
        "name": "민주지산",
        "id": 2304021500
      },
      {
        "name": "방장산",
        "id": 2501100600
      },
      {
        "name": "방태산",
        "id": 2210011400
      },
      {
        "name": "백덕산",
        "id": 2215081700
      },
      {
        "name": "백암산",
        "id": 2617051500
      },
      {
        "name": "백운산(광양)",
        "id": 2604100800
      },
      {
        "name": "백운산(정선)",
        "id": 2215034400
      },
      {
        "name": "백운산(포천)",
        "id": 2142141300
      },
      {
        "name": "변산",
        "id": 2506112300
      },
      {
        "name": "북한산",
        "id": 1103030100
      },
      {
        "name": "비슬산",
        "id": 1303011500
      },
      {
        "name": "삼악산",
        "id": 2213142900
      },
      {
        "name": "서대산",
        "id": 2403011500
      },
      {
        "name": "선운산",
        "id": 2501121000
      },
      {
        "name": "설악산",
        "id": 2207010900
      },
      {
        "name": "성인봉",
        "id": 2717030900
      },
      {
        "name": "소백산",
        "id": 2302011300
      },
      {
        "name": "소요산",
        "id": 2112130200
      },
      {
        "name": "속리산",
        "id": 2303021300
      },
      {
        "name": "신불산",
        "id": 1704041600
      },
      {
        "name": "연화산",
        "id": 2803010100
      },
      {
        "name": "오대산",
        "id": 2215072100
      },
      {
        "name": "오봉산",
        "id": 2213103900
      },
      {
        "name": "용문산",
        "id": 2130103000
      },
      {
        "name": "용화산",
        "id": 2217011600
      },
      {
        "name": "운문산",
        "id": 2720052300
      },
      {
        "name": "운악산",
        "id": 2101062700
      },
      {
        "name": "운장산",
        "id": 2515042400
      },
      {
        "name": "월악산",
        "id": 2307083700
      },
      {
        "name": "월출산",
        "id": 2601072300
      },
      {
        "name": "유명산",
        "id": 2130093200
      },
      {
        "name": "응봉산",
        "id": 2204113300
      },
      {
        "name": "장안산",
        "id": 2511992400
      },
      {
        "name": "재약산",
        "id": 2807063600
      },
      {
        "name": "적상산",
        "id": 2505061400
      },
      {
        "name": "점봉산",
        "id": 2210012400
      },
      {
        "name": "조계산",
        "id": 2611193200
      },
      {
        "name": "주왕산",
        "id": 2721022000
      },
      {
        "name": "주흘산",
        "id": 2707083600
      },
      {
        "name": "지리산",
        "id": 2809040100
      },
      {
        "name": "지리산(통영)",
        "id": 2816132100
      },
      {
        "name": "천관산",
        "id": 2618021900
      },
      {
        "name": "천마산",
        "id": 2111091500
      },
      {
        "name": "천성산",
        "id": 2810181300
      },
      {
        "name": "천태산",
        "id": 2304053500
      },
      {
        "name": "청량산",
        "id": 2708013300
      },
      {
        "name": "추월산",
        "id": 2607102100
      },
      {
        "name": "축령산",
        "id": 2101033200
      },
      {
        "name": "치악산",
        "id": 2209132600
      },
      {
        "name": "칠갑산",
        "id": 2414022100
      },
      {
        "name": "태백산",
        "id": 2214211300
      },
      {
        "name": "태화산",
        "id": 2208064100
      },
      {
        "name": "팔공산",
        "id": 1304461200
      },
      {
        "name": "팔봉산",
        "id": 2216075500
      },
      {
        "name": "팔영산",
        "id": 2602132900
      },
      {
        "name": "한라산",
        "id": 2902194100
      },
      {
        "name": "화악산(중봉)",
        "id": 2101023600
      },
      {
        "name": "화왕산",
        "id": 2814140100
      },
      {
        "name": "황매산",
        "id": 2820020300
      },
      {
        "name": "황석산",
        "id": 2819072400
      },
      {
        "name": "황악산",
        "id": 2706133700
      },
      {
        "name": "황장산",
        "id": 2707040400
      },
      {
        "name": "희양산",
        "id": 2301072800
      }
  ]
  

  mountain.forEach((item) => {
    var option = $("<option value=" + item.id + ">" + item.name + "</option>");
    $("select[name=famousMountainId]").append(option);
  })
});


//글자수 제한
$("#searchKeyword").keyup(function (e) {
  var content = $(this).val();
  $("#textLengthCheck").val("" + content.length + " / 20"); //실시간 글자수 카운팅
  if (content.length > 20) {
    Alert("최대 20자까지 입력 가능합니다.");
    $(this).val(content.substring(0, 20));
  }
});

const newPassword = $("#newPassword").val();
const checkNewPassword = $("#check-new-password").val();
const changePasswordForm = document.querySelector("#change-password");

// 비밀번호 정규식 :
const reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;

changePasswordForm.addEventListener("submit", (e) => {
  if(!newPassword.match(reg)) {
    alert("새로운 비밀번호를 규칙에 맞게 입력해주세요")
    return false;
  } else if (newPassword.match(checkNewPassword)) {
    alert("새로운 비밀번호와 일치하지 않습니다.")
    return false;
  }
  return true;
})