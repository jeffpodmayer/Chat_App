const username = sessionStorage.getItem("username");
const userIdInput = document.getElementById("userId");
const userId = userIdInput.value;
const channelIdInput = document.getElementById("channelId");
const channelId = channelIdInput.value;
const channelNameInput = document.getElementById("channelName");
const channelName = channelNameInput.value;
const messagesContainer = document.getElementById("messagesContainer");

console.log(username);
console.log(channelId);
console.log(userId);


//added below from 42-52 to fetch all messages for the channel
document.addEventListener("DOMContentLoaded", function () {
  const channelId = document.getElementById("channelId").value;
  fetch(`/api/messages/channels/${channelId}/messages`)
    .then((response) => response.json())
    .then((messages) => {
      messages.forEach((message) => {
        renderMessage(message);
      });
    })
    .catch((error) => console.error("Failed to load messages:", error));
});


// if (messageForm) {
const btnSendMessage = document.getElementById("sendMessage");
btnSendMessage.addEventListener("click", function () {
  // event.preventDefault();
  const messageContent = document.getElementById("messageContent").value;
  // console.log("Message Input:", messageContent);
  let message = {
    user: {
      username: username,
      id: userId,
    },
    content: messageContent,
    channel: {
      id: channelId,
      name: channelName,
    },
  };
  console.log("Message Output:", message);
  fetch(`/api/messages/createMessage/${channelId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(message),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      console.log(response);
      return response.json();
    })
    .then((data) => {
      console.log("New message:", data);
      renderMessage(data);
      document.getElementById("messageContent").value = "";
    });
  // Now able to update the UI or perform any other operations with the new message data
});

function fetchMessages() {
  fetch(`/api/messages/channels/${channelId}`)
    .then((response) => response.json())
    .then((Messages) => {
      messagesContainer.innerHTML = ''; 
      messages.forEach(message => {
        renderMessage(message);
      });
    })
    .catch((error) => console.error("Failed to load messages:", error));
}

function renderMessage(data) {
  const div = document.createElement("div");
  //added line 97
  const username = data.username ? data.username : "Unknown user"; // Default to 'Unknown user' if user data is missing
  // const messageHTML = `<p>${data.user.username} : ${data.content}</p>`;
  const messageHTML = `<p>${username} : ${data.content}</p>`;

  console.log(messageHTML);
  div.innerHTML = messageHTML;
  messagesContainer.appendChild(div);
}
// setInterval(fetchMessages, 5000);

