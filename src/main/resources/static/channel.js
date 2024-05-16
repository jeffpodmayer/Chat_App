const username = sessionStorage.getItem("username");
const userIdInput = document.getElementById("userId");
const userId = userIdInput.value;
const channelIdInput = document.getElementById("channelId");
const channelId = channelIdInput.value;
const channelNameInput = document.getElementById("channelName");
const channelName = channelNameInput.value;
const messagesContainer = document.getElementById("messagesContainer");
const btnSendMessage = document.getElementById("sendMessage");
const messageContentInput = document.getElementById("messageContent");
let mostRecentMessageId = 0;

// When enter is pressed this prevents the page froom submitting and instead does what the btnSendMessage event listener does
messageContentInput.addEventListener("keydown", function (event) {
  if (event.key === "Enter") {
    event.preventDefault();
    btnSendMessage.click();
  }
});

//this messsage sends and renders a message on the page, then sets the mostRecentMessageId
btnSendMessage.addEventListener("click", function () {
  const messageToSend = messageContent.value;
  let message = {
    user: {
      username: username,
      id: userId,
    },
    content: messageToSend,
    channel: {
      id: channelId,
      name: channelName,
    },
  };

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
      mostRecentMessageId = data.id; //Sets the mostRecentMessageId to that user sent
      document.getElementById("messageContent").value = "";
    });
});

function renderMessage(username, message) {
  const div = document.createElement("div");
  const messageHTML = `<p>${username} : ${message}</p>`;
  console.log(messageHTML);
  div.innerHTML = messageHTML;
  messagesContainer.appendChild(div);
}

// when a user joins a channel, this function gets all the messages related to that  channel --> called in the DOMContentLoaded eventListener
function getAllChannelMessages() {
  fetch(`/api/messages/channels/${channelId}/messages`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((messages) => {
      messages.forEach((message) => {
        renderMessage(message.user.username, message.content);
        if (message.id > mostRecentMessageId) {
          mostRecentMessageId = message.id;
        }
      });
    })
    .catch((error) => console.error("Failed to load messages:", error));
}
document.addEventListener("DOMContentLoaded", getAllChannelMessages);

// Checks for new messages --> this function is called by setInterval
async function checkForNewMessages() {
  try {
    const response = await fetch(
      `/api/messages/channels/${channelId}/messages?since=${mostRecentMessageId}`
    );
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const newMessages = await response.json();
    newMessages.forEach((message) => {
      renderMessage(message.user.username, message.content);
      if (message.id > mostRecentMessageId) {
        mostRecentMessageId = message.id;
      }
    });
  } catch (error) {
    console.error("Failed to load new messages:", error);
  }
}
setInterval(checkForNewMessages, 500);
