  // 메시지 전송
  function sendMessage() {
    var chatInput = document.getElementById("chatInput");
    var chatContainer = document.getElementById("chatContainer");

    var message = chatInput.value;
    if (message) {
      var newMessage = document.createElement("div");
      newMessage.textContent = message;
      chatContainer.appendChild(newMessage);

      chatInput.value = ""; // 입력창 초기화
    }
  }

  // 파일 선택 이벤트 처리
document.getElementById("fileInput").addEventListener("change", handleFileSelect, false);

// 파일 선택 핸들러
function handleFileSelect(event) {
  var files = event.target.files; // 선택한 파일들의 정보를 가져옵니다.
  
  // 선택한 각 파일에 대해서 처리를 수행합니다.
  for (var i = 0, length = files.length; i < length; i++) {
    var file = files[i];

    // 파일 정보를 활용하여 원하는 동작을 수행합니다.
    console.log("Selected file:", file.name, "Size:", file.size, "bytes");
    // 파일을 전송하거나, 미리보기 등의 기능을 구현할 수 있습니다.
  }
}

function showSelectedFileName() {
  var fileInput = document.getElementById('fileInput');
  var selectedFile = fileInput.files[0];
  var selectedFileName = document.getElementById('selectedFileName');
  
  if (selectedFile) {
    selectedFileName.innerHTML = "선택된 파일: " + selectedFile.name;
  } else {
    selectedFileName.innerHTML = "";
  }
}


var liItems = document.querySelectorAll(".people-list li");
var activeItem = null;

liItems.forEach(function(item) {
  item.addEventListener("click", function() {
    if (activeItem !== null) {
      activeItem.classList.remove("active"); /* 이전 활성 아이템에서 효과 제거 */
    }
    
    item.classList.add("active"); /* 클릭한 아이템에 효과 추가 */
    activeItem = item; /* 현재 활성 아이템 설정 */
  });
});

  // 엔터 키 처리
  function handleKeyDown(event) {
    if (event.keyCode === 13) { // 13은 엔터 키의 키 코드입니다
      event.preventDefault(); // 폼의 기본 제출 동작을 방지합니다
      sendMessage(); // 메시지 전송 함수를 호출합니다
      scrollToBottom();
    }
  }

    // 스크롤을 아래로 내리는 함수
    function scrollToBottom() {
      var chatContainer = document.getElementById('chatContainer');
      chatContainer.scrollTop = chatContainer.scrollHeight;
    }


    const button = document.querySelector("#emoji_btn");
    const picker = new EmojiButton({
      position: 'bottom-start'
    });
    
    button.addEventListener('click', () => {
      picker.togglePicker(button);
    });
    
    picker.on('emoji', emoji => {
      const text_box = document.querySelector('#chatInput');
      text_box.value += emoji;
    });

