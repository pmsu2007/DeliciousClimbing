const baseUrl = "http://localhost:8080"
function existsEmail() {
    const currentEmail = document.querySelector(".email").value;
    const submitBtn = document.querySelector(".ok");
    fetch(`${baseUrl}/user/exist-email/${currentEmail}`)
        .then((response) => response.json())
        .then((data) => {
            if(data === true) {
                alert("이미 존재하는 이메일입니다")
            } else {
                alert("사용 가능한 이메일입니다.")
                submitBtn.disabled = false;
            }
        });
}