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


//added below to fetch all messages for the channel



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
      renderMessage(data.user.username, data.content);
      document.getElementById("messageContent").value = "";
    });
  // Now able to update the UI or perform any other operations with the new message data
});

// function fetchMessages() {
//   fetch(`/api/messages/channels/${channelId}`)
//     .then((response) => response.json())
//     .then((Messages) => {
//       messagesContainer.innerHTML = ''; 
//       messages.forEach(message => {
//         renderMessage(message);
//       });
//     })
//     .catch((error) => console.error("Failed to load messages:", error));
// }

function renderMessage(username, message) {
  // console.log(data.user.username);
  const div = document.createElement("div");
  // const username = data.user.username ? data.user.username : "Unknown user"; // Default to 'Unknown user' if user data is missing
  // const messageHTML = `<p>${data.user.username} : ${data.content}</p>`;
  const messageHTML = `<p>${username} : ${message}</p>`;

  console.log(messageHTML);
  div.innerHTML = messageHTML;
  messagesContainer.appendChild(div);
}
  function getAllMessages() {
  const channelId = document.getElementById("channelId").value;
  fetch(`/api/messages/channels/${channelId}/messages`)
    .then((response) => response.json())
    .then((messages) => {
      messages.forEach((message) => {
        // if (message.user.id !== userId) {
        renderMessage(message.user.username, message.content);
        console.log(message);
        // }
      });
    })
    .catch((error) => console.error("Failed to load messages:", error));
  };

// setInterval(getAllMessages, 3000);
document.addEventListener("DOMContentLoaded", getAllMessages);