const openButtons = document.querySelectorAll(".open-button");
const closeButtons = document.querySelectorAll(".close-button");
const checkButtons = document.querySelectorAll(".passwordCheck");

// 열기 버튼에 대한 클릭 이벤트 처리
openButtons.forEach((button) => {
  button.addEventListener("click", () => {
    const modalId = button.getAttribute("data-modal");
    const modal = document.querySelector(modalId);
    modal.style.display = "block";
  });
});

// 닫기 버튼에 대한 클릭 이벤트 처리
closeButtons.forEach((button) => {
  button.addEventListener("click", () => {
    const modal = button.parentElement.parentElement;
    modal.style.display = "none";
  });
});

// 체크 버튼에 대한 클릭 이벤트 처리
checkButtons.forEach((button) => {
  button.addEventListener("click", () => {
    const modal = button.parentElement.parentElement;
    modal.style.display = "none";
  });
});

$("document").ready(function () {
  var areaSelectMaker = function (target) {
    if (target == null || $(target).length == 0) {
      console.warn("Unkwon Area Tag");
      return;
    }

    var area = {
      서울특별시: [
        {
          sigungu: "종로구",
          code: 1111000000,
        },
        {
          sigungu: "중구",
          code: 1114000000,
        },
        {
          sigungu: "용산구",
          code: 1117000000,
        },
        {
          sigungu: "성동구",
          code: 1120000000,
        },
        {
          sigungu: "광진구",
          code: 1121500000,
        },
        {
          sigungu: "동대문구",
          code: 1123000000,
        },
        {
          sigungu: "중랑구",
          code: 1126000000,
        },
        {
          sigungu: "성북구",
          code: 1129000000,
        },
        {
          sigungu: "강북구",
          code: 1130500000,
        },
        {
          sigungu: "도봉구",
          code: 1132000000,
        },
        {
          sigungu: "노원구",
          code: 1135000000,
        },
        {
          sigungu: "은평구",
          code: 1138000000,
        },
        {
          sigungu: "서대문구",
          code: 1141000000,
        },
        {
          sigungu: "마포구",
          code: 1144000000,
        },
        {
          sigungu: "양천구",
          code: 1147000000,
        },
        {
          sigungu: "강서구",
          code: 1150000000,
        },
        {
          sigungu: "구로구",
          code: 1153000000,
        },
        {
          sigungu: "금천구",
          code: 1154500000,
        },
        {
          sigungu: "영등포구",
          code: 1156000000,
        },
        {
          sigungu: "동작구",
          code: 1159000000,
        },
        {
          sigungu: "관악구",
          code: 1162000000,
        },
        {
          sigungu: "서초구",
          code: 1165000000,
        },
        {
          sigungu: "강남구",
          code: 1168000000,
        },
        {
          sigungu: "송파구",
          code: 1171000000,
        },
        {
          sigungu: "강동구",
          code: 1174000000,
        },
      ],
      부산광역시: [
        {
          sigungu: "중구",
          code: 2611000000,
        },
        {
          sigungu: "서구",
          code: 2614000000,
        },
        {
          sigungu: "동구",
          code: 2617000000,
        },
        {
          sigungu: "영도구",
          code: 2620000000,
        },
        {
          sigungu: "부산진구",
          code: 2623000000,
        },
        {
          sigungu: "동래구",
          code: 2626000000,
        },
        {
          sigungu: "남구",
          code: 2629000000,
        },
        {
          sigungu: "북구",
          code: 2632000000,
        },
        {
          sigungu: "해운대구",
          code: 2635000000,
        },
        {
          sigungu: "사하구",
          code: 2638000000,
        },
        {
          sigungu: "금정구",
          code: 2641000000,
        },
        {
          sigungu: "강서구",
          code: 2644000000,
        },
        {
          sigungu: "연제구",
          code: 2647000000,
        },
        {
          sigungu: "수영구",
          code: 2650000000,
        },
        {
          sigungu: "사상구",
          code: 2653000000,
        },
        {
          sigungu: "기장군",
          code: 2671000000,
        },
      ],
      대구광역시: [
        {
          sigungu: "중구",
          code: 2711000000,
        },
        {
          sigungu: "동구",
          code: 2714000000,
        },
        {
          sigungu: "서구",
          code: 2717000000,
        },
        {
          sigungu: "남구",
          code: 2720000000,
        },
        {
          sigungu: "북구",
          code: 2723000000,
        },
        {
          sigungu: "수성구",
          code: 2726000000,
        },
        {
          sigungu: "달서구",
          code: 2729000000,
        },
        {
          sigungu: "달성군",
          code: 2771000000,
        },
      ],
      인천광역시: [
        {
          sigungu: "중구",
          code: 2811000000,
        },
        {
          sigungu: "동구",
          code: 2814000000,
        },
        {
          sigungu: "미추홀구",
          code: 2817700000,
        },
        {
          sigungu: "연수구",
          code: 2818500000,
        },
        {
          sigungu: "남동구",
          code: 2820000000,
        },
        {
          sigungu: "부평구",
          code: 2823700000,
        },
        {
          sigungu: "계양구",
          code: 2824500000,
        },
        {
          sigungu: "서구",
          code: 2826000000,
        },
        {
          sigungu: "강화군",
          code: 2871000000,
        },
        {
          sigungu: "옹진군",
          code: 2872000000,
        },
      ],
      광주광역시: [
        {
          sigungu: "동구",
          code: 2911000000,
        },
        {
          sigungu: "서구",
          code: 2914000000,
        },
        {
          sigungu: "남구",
          code: 2915500000,
        },
        {
          sigungu: "북구",
          code: 2917000000,
        },
        {
          sigungu: "광산구",
          code: 2920000000,
        },
      ],
      대전광역시: [
        {
          sigungu: "동구",
          code: 3011000000,
        },
        {
          sigungu: "중구",
          code: 3014000000,
        },
        {
          sigungu: "서구",
          code: 3017000000,
        },
        {
          sigungu: "유성구",
          code: 3020000000,
        },
        {
          sigungu: "대덕구",
          code: 3023000000,
        },
      ],
      울산광역시: [
        {
          sigungu: "중구",
          code: 3111000000,
        },
        {
          sigungu: "남구",
          code: 3114000000,
        },
        {
          sigungu: "동구",
          code: 3117000000,
        },
        {
          sigungu: "북구",
          code: 3120000000,
        },
        {
          sigungu: "울주군",
          code: 3171000000,
        },
      ],
      세종특별자치시: [
        {
          sigungu: "세종시",
          code: 3611000000,
        },
      ],
      경기도: [
        {
          sigungu: "수원시",
          code: 4111000000,
        },
        {
          sigungu: "성남시",
          code: 4113000000,
        },
        {
          sigungu: "의정부시",
          code: 4115000000,
        },
        {
          sigungu: "안양시",
          code: 4117000000,
        },
        {
          sigungu: "부천시",
          code: 4119000000,
        },
        {
          sigungu: "광명시",
          code: 4121000000,
        },
        {
          sigungu: "평택시",
          code: 4122000000,
        },
        {
          sigungu: "동두천시",
          code: 4125000000,
        },
        {
          sigungu: "안산시",
          code: 4127000000,
        },
        {
          sigungu: "고양시",
          code: 4128000000,
        },
        {
          sigungu: "과천시",
          code: 4129000000,
        },
        {
          sigungu: "구리시",
          code: 4131000000,
        },
        {
          sigungu: "남양주시",
          code: 4136000000,
        },
        {
          sigungu: "오산시",
          code: 4137000000,
        },
        {
          sigungu: "시흥시",
          code: 4139000000,
        },
        {
          sigungu: "군포시",
          code: 4141000000,
        },
        {
          sigungu: "의왕시",
          code: 4143000000,
        },
        {
          sigungu: "하남시",
          code: 4145000000,
        },
        {
          sigungu: "용인시",
          code: 4146000000,
        },
        {
          sigungu: "파주시",
          code: 4148000000,
        },
        {
          sigungu: "이천시",
          code: 4150000000,
        },
        {
          sigungu: "안성시",
          code: 4155000000,
        },
        {
          sigungu: "김포시",
          code: 4157000000,
        },
        {
          sigungu: "화성시",
          code: 4159000000,
        },
        {
          sigungu: "광주시",
          code: 4161000000,
        },
        {
          sigungu: "양주시",
          code: 4163000000,
        },
        {
          sigungu: "포천시",
          code: 4165000000,
        },
        {
          sigungu: "여주시",
          code: 4167000000,
        },
        {
          sigungu: "연천군",
          code: 4180000000,
        },
        {
          sigungu: "가평군",
          code: 4182000000,
        },
        {
          sigungu: "양평군",
          code: 4183000000,
        },
      ],
      강원도: [
        {
          sigungu: "춘천시",
          code: 4211000000,
        },
        {
          sigungu: "원주시",
          code: 4213000000,
        },
        {
          sigungu: "강릉시",
          code: 4215000000,
        },
        {
          sigungu: "동해시",
          code: 4217000000,
        },
        {
          sigungu: "태백시",
          code: 4219000000,
        },
        {
          sigungu: "속초시",
          code: 4221000000,
        },
        {
          sigungu: "삼척시",
          code: 4223000000,
        },
        {
          sigungu: "홍천군",
          code: 4272000000,
        },
        {
          sigungu: "횡성군",
          code: 4273000000,
        },
        {
          sigungu: "영월군",
          code: 4275000000,
        },
        {
          sigungu: "평창군",
          code: 4276000000,
        },
        {
          sigungu: "정선군",
          code: 4277000000,
        },
        {
          sigungu: "철원군",
          code: 4278000000,
        },
        {
          sigungu: "화천군",
          code: 4279000000,
        },
        {
          sigungu: "양구군",
          code: 4280000000,
        },
        {
          sigungu: "인제군",
          code: 4281000000,
        },
        {
          sigungu: "고성군",
          code: 4282000000,
        },
        {
          sigungu: "양양군",
          code: 4283000000,
        },
      ],
      충청북도: [
        {
          sigungu: "청주시",
          code: 4311000000,
        },
        {
          sigungu: "충주시",
          code: 4313000000,
        },
        {
          sigungu: "제천시",
          code: 4315000000,
        },
        {
          sigungu: "보은군",
          code: 4372000000,
        },
        {
          sigungu: "옥천군",
          code: 4373000000,
        },
        {
          sigungu: "영동군",
          code: 4374000000,
        },
        {
          sigungu: "증평군",
          code: 4374500000,
        },
        {
          sigungu: "진천군",
          code: 4375000000,
        },
        {
          sigungu: "괴산군",
          code: 4376000000,
        },
        {
          sigungu: "음성군",
          code: 4377000000,
        },
        {
          sigungu: "단양군",
          code: 4380000000,
        },
      ],
      충청남도: [
        {
          sigungu: "천안시",
          code: 4413000000,
        },
        {
          sigungu: "공주시",
          code: 4415000000,
        },
        {
          sigungu: "보령시",
          code: 4418000000,
        },
        {
          sigungu: "아산시",
          code: 4420000000,
        },
        {
          sigungu: "서산시",
          code: 4421000000,
        },
        {
          sigungu: "논산시",
          code: 4423000000,
        },
        {
          sigungu: "계룡시",
          code: 4425000000,
        },
        {
          sigungu: "당진시",
          code: 4427000000,
        },
        {
          sigungu: "금산군",
          code: 4471000000,
        },
        {
          sigungu: "부여군",
          code: 4476000000,
        },
        {
          sigungu: "서천군",
          code: 4477000000,
        },
        {
          sigungu: "청양군",
          code: 4479000000,
        },
        {
          sigungu: "홍성군",
          code: 4480000000,
        },
        {
          sigungu: "예산군",
          code: 4481000000,
        },
        {
          sigungu: "태안군",
          code: 4482500000,
        },
      ],
      전라북도: [
        {
          sigungu: "전주시",
          code: 4511000000,
        },
        {
          sigungu: "군산시",
          code: 4513000000,
        },
        {
          sigungu: "익산시",
          code: 4514000000,
        },
        {
          sigungu: "정읍시",
          code: 4518000000,
        },
        {
          sigungu: "남원시",
          code: 4519000000,
        },
        {
          sigungu: "김제시",
          code: 4521000000,
        },
        {
          sigungu: "완주군",
          code: 4571000000,
        },
        {
          sigungu: "진안군",
          code: 4572000000,
        },
        {
          sigungu: "무주군",
          code: 4573000000,
        },
        {
          sigungu: "장수군",
          code: 4574000000,
        },
        {
          sigungu: "임실군",
          code: 4575000000,
        },
        {
          sigungu: "순창군",
          code: 4577000000,
        },
        {
          sigungu: "고창군",
          code: 4579000000,
        },
        {
          sigungu: "부안군",
          code: 4580000000,
        },
      ],
      전라남도: [
        {
          sigungu: "목포시",
          code: 4611000000,
        },
        {
          sigungu: "여수시",
          code: 4613000000,
        },
        {
          sigungu: "순천시",
          code: 4615000000,
        },
        {
          sigungu: "나주시",
          code: 4617000000,
        },
        {
          sigungu: "광양시",
          code: 4623000000,
        },
        {
          sigungu: "담양군",
          code: 4671000000,
        },
        {
          sigungu: "곡성군",
          code: 4672000000,
        },
        {
          sigungu: "구례군",
          code: 4673000000,
        },
        {
          sigungu: "고흥군",
          code: 4677000000,
        },
        {
          sigungu: "보성군",
          code: 4678000000,
        },
        {
          sigungu: "화순군",
          code: 4679000000,
        },
        {
          sigungu: "장흥군",
          code: 4680000000,
        },
        {
          sigungu: "강진군",
          code: 4681000000,
        },
        {
          sigungu: "해남군",
          code: 4682000000,
        },
        {
          sigungu: "영암군",
          code: 4683000000,
        },
        {
          sigungu: "무안군",
          code: 4684000000,
        },
        {
          sigungu: "함평군",
          code: 4686000000,
        },
        {
          sigungu: "영광군",
          code: 4687000000,
        },
        {
          sigungu: "장성군",
          code: 4688000000,
        },
        {
          sigungu: "완도군",
          code: 4689000000,
        },
        {
          sigungu: "진도군",
          code: 4690000000,
        },
        {
          sigungu: "신안군",
          code: 4691000000,
        },
      ],
      경상북도: [
        {
          sigungu: "포항시",
          code: 4711000000,
        },
        {
          sigungu: "경주시",
          code: 4713000000,
        },
        {
          sigungu: "김천시",
          code: 4715000000,
        },
        {
          sigungu: "안동시",
          code: 4717000000,
        },
        {
          sigungu: "구미시",
          code: 4719000000,
        },
        {
          sigungu: "영주시",
          code: 4721000000,
        },
        {
          sigungu: "영천시",
          code: 4723000000,
        },
        {
          sigungu: "상주시",
          code: 4725000000,
        },
        {
          sigungu: "문경시",
          code: 4728000000,
        },
        {
          sigungu: "경산시",
          code: 4729000000,
        },
        {
          sigungu: "군위군",
          code: 4772000000,
        },
        {
          sigungu: "의성군",
          code: 4773000000,
        },
        {
          sigungu: "청송군",
          code: 4775000000,
        },
        {
          sigungu: "영양군",
          code: 4776000000,
        },
        {
          sigungu: "영덕군",
          code: 4777000000,
        },
        {
          sigungu: "청도군",
          code: 4782000000,
        },
        {
          sigungu: "고령군",
          code: 4783000000,
        },
        {
          sigungu: "성주군",
          code: 4784000000,
        },
        {
          sigungu: "칠곡군",
          code: 4785000000,
        },
        {
          sigungu: "예천군",
          code: 4790000000,
        },
        {
          sigungu: "봉화군",
          code: 4792000000,
        },
        {
          sigungu: "울진군",
          code: 4793000000,
        },
        {
          sigungu: "울릉군",
          code: 4794000000,
        },
      ],
      경상남도: [
        {
          sigungu: "창원시",
          code: 4812000000,
        },
        {
          sigungu: "진주시",
          code: 4817000000,
        },
        {
          sigungu: "통영시",
          code: 4822000000,
        },
        {
          sigungu: "사천시",
          code: 4824000000,
        },
        {
          sigungu: "김해시",
          code: 4825000000,
        },
        {
          sigungu: "밀양시",
          code: 4827000000,
        },
        {
          sigungu: "거제시",
          code: 4831000000,
        },
        {
          sigungu: "양산시",
          code: 4833000000,
        },
        {
          sigungu: "의령군",
          code: 4872000000,
        },
        {
          sigungu: "함안군",
          code: 4873000000,
        },
        {
          sigungu: "창녕군",
          code: 4874000000,
        },
        {
          sigungu: "고성군",
          code: 4882000000,
        },
        {
          sigungu: "남해군",
          code: 4884000000,
        },
        {
          sigungu: "하동군",
          code: 4885000000,
        },
        {
          sigungu: "산청군",
          code: 4886000000,
        },
        {
          sigungu: "함양군",
          code: 4887000000,
        },
        {
          sigungu: "거창군",
          code: 4888000000,
        },
        {
          sigungu: "합천군",
          code: 4889000000,
        },
      ],
      제주특별자치도: [
        {
          sigungu: "제주시",
          code: 5011000000,
        },
        {
          sigungu: "서귀포시",
          code: 5013000000,
        },
      ],
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
            a2.append(
                "<option value=" + Do.code + ">" + Do.sigungu + "</option>"
            );
          });
        });
      })(i);

      function init(t, first) {
        first ? t.empty().append("<option value=''>선택</option>") : "";
        t.next().empty().append("<option value=''>선택</option>");
      }
    }
  };

  areaSelectMaker("select[name^=sido]");
});

//넘겨 줄때 입력값으로 넘겨줘야 함

$("document").ready(function () {
  var type = [
    { name: "장비 선택", value: "" },
    {
      name: "등산복",
      value: "CLOTHES",
    },
    {
      name: "등산화",
      value: "SHOES",
    },
    {
      name: "등산스틱",
      value: "STICK",
    },
    {
      name: "기타장비",
      value: "ETC",
    },
  ];

  $("select[name^=type]").each(function () {
    $selmountain = $(this);
    $.each(type, function (idx, item) {
      $selmountain.append(
          "<option value=" + item.value + ">" + item.name + "</option>"
      );
    });
    console.log($selmountain.value);
  });

  // $("select[name^=mountain]").change(function() {
  //   var mountain = "mountain" +$("option", $(this)).index($("option:selected",$(this)));

  // })
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

function handleInputLength(el, max) {
  if (el.value.length > max) {
    el.value = el.value.substr(0, max);
  }
}