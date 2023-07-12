// 버튼 클릭 시 호출되는 함수
function fnCalCount(action, button) {
    var input = button.parentNode.querySelector('input[name="pop_out"]');
    var value = parseInt(input.value);

    if (action === 'p') {
        value += 1;
    } else if (action === 'm') {
        value -= 1;
    }

    input.value = value;
}

//오늘 이후 날짜 선택하는 함수
var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); // 0부터 시작하므로 1을 더해줌
var yyyy = today.getFullYear();

var minDate = yyyy + '-' + mm + '-' + dd;

// HTML 요소에 min 속성 설정
document.getElementById('date-input').min = minDate;



//어디 입력안했는지 알려주기
function validateForm() {

    var date = document.getElementById("date-input").value;
    var mountain = document.getElementById("mountain1").value;
    
    if (date === "" || mountain === "") {
        alert("날짜를 모두 입력해주세요.");
        return false; // 폼 제출을 중단합니다.
    }
}