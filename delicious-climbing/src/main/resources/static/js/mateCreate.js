function count(type)  {
    // 결과를 표시할 element
    const resultElement = document.getElementById('result');
    
    // 현재 화면에 표시된 값
    let number = resultElement.innerText;
    
    // 더하기/빼기
    if(parseInt(number)>=0){
        if(type === 'plus') {
        number = parseInt(number) + 1;
        }else if(type === 'minus')  {
        number = parseInt(number) - 1;
    }
    }
    
    // 결과 출력
    if(parseInt(number)>=0){
    resultElement.innerText = number;
    }
}

//오늘 이후 날짜 선택하는 함수
var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); // 0부터 시작하므로 1을 더해줌
var yyyy = today.getFullYear();

var minDate = yyyy + '-' + mm + '-' + dd;

// HTML 요소에 min 속성 설정
document.getElementById('date-input').min = String(minDate);



//어디 입력안했는지 알려주기
function validateForm() {

    var date = document.getElementById("date-input").value;
    var mountain = document.getElementById("mountain1").value;
    
    if (date === "" || mountain === "") {
        alert("날짜를 모두 입력해주세요.");
        return false; // 폼 제출을 중단합니다.
    }
}