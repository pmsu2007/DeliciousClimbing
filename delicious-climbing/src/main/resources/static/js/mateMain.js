// $(".joiningBtn").on('click',function(e) {
//     $(e.target).css({'background-color':'gray'});
//     $(".joiningBtn").on('click',function(e) {
//         $(e.target).css({'background-color':'white'});
//     })
// });

function change_btn(e) {
  var btns = document.querySelectorAll(".button");
  btns.forEach(function (btn, i) {
    if (e.currentTarget == btn) {
      btn.classList.add("active");
    } else {
      btn.classList.remove("active");
    }
  });
  console.log(e.currentTarget);
}


window.addEventListener('scroll', function() {
  if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
      createCard();
  }
});


//무한 스크롤로 카드형 디자인 생성하는 함수
// var count = 8;
// window.onscroll = function(e) {
//   console.log(window.innerHeight , window.scrollY,document.body.offsetHeight)
//   if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) { 
//     setTimeout(function(){
//       var addContent = document.createElement("div");
//       addContent.classList.add("box")
//       addContent.innerHTML = `<p>${++count}번째 블록</p>`
//       document.querySelector('section').appendChild(addContent);
//     }, 100)  
  
//   }
//   if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) { 
//     setTimeout(function(){
//       var addContent = document.createElement("div");
//       addContent.classList.add("box")
//       addContent.innerHTML = `<p>${++count}번째 블록</p>`
//       document.querySelector('section').appendChild(addContent);
//     }, 100)  
  
//   }
//   if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) { 
//     setTimeout(function(){
//       var addContent = document.createElement("div");
//       addContent.classList.add("box")
//       addContent.innerHTML = `<p>${++count}번째 블록</p>`
//       document.querySelector('section').appendChild(addContent);
//     }, 100)  
  
//   }
//   if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) { 
//     setTimeout(function(){
//       var addContent = document.createElement("div");
//       addContent.classList.add("box")
//       addContent.innerHTML = `<p>${++count}번째 블록</p>`
//       document.querySelector('section').appendChild(addContent);
//     }, 100)  
  
//   }
// }

function goToPage(pageNumber) {
// 해당 페이지로 이동하는 로직을 작성합니다.
// 예를 들어, 서버에 Ajax 요청을 보내거나, 클라이언트 측 스크립트로 페이지 내용을 변경할 수 있습니다.
// 이 예시에서는 페이지 번호만 출력하도록 작성되었습니다.
// document.getElementById("content").innerHTML = "현재 페이지: " + pageNumber;

// 현재 페이지를 표시하기 위해 active 클래스를 설정합니다.
var pageLinks = document.getElementsByClassName("page-link");
for (var i = 0; i < pageLinks.length; i++) {
  pageLinks[i].classList.remove("active");
}
pageLinks[pageNumber - 1].classList.add("active");
}

// 페이지 링크를 가져옵니다.
var pageLinks = document.getElementsByClassName("page-link");

// 페이지 링크에 이벤트 핸들러를 추가합니다.
for (var i = 0; i < pageLinks.length; i++) {
pageLinks[i].addEventListener("click", function() {
  // 페이지 번호를 가져옵니다.
  var pageNumber = this.innerHTML;

  // 해당 페이지로 이동하는 함수를 호출합니다.
  goToPage(pageNumber);
});
}


//modal창
document.addEventListener('DOMContentLoaded', function() {
var modalTrigger = document.querySelector('.box');
var modal = document.getElementById('myModal');
var closeBtn = document.querySelector('.close');

// 모달 열기
modalTrigger.addEventListener('click', function() {
  modal.style.display = 'block';
});

// 모달 닫기
closeBtn.addEventListener('click', function() {
  modal.style.display = 'none';
});

// 모달 외부 클릭 시 닫기
window.addEventListener('click', function(event) {
  if (event.target == modal) {
    modal.style.display = 'none';
  }
});
});