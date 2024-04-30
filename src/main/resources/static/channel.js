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
// const btnSendMessage = document.getElementById('sendMessage');
// console.log(username)
// console.log(channelId)
// document.addEventListener("DOMContentLoaded", function () {
//   const messageForm = document.getElementById("messageForm");
//   let channelId = document.getElementById("channelId")
//     ? document.getElementById("channelId").value
//     : null; // Assuming you have an element with ID 'channelId' holding the channel ID.

//   function fetchMessages(channelId) {
//     fetch(`/api/messages/channels/${channelId}`)
//       .then((response) => response.json())
//       .then((messages) => {
//         const container = document.getElementById("messagesContainer");
//         container.innerHTML = "";
//         messages.forEach((message) => {
//           const messageElement = document.createElement("p");
//           messageElement.textContent = message.content;
//           container.appendChild(messageElement);
//         });
//       })
//       .catch((error) => console.error("Failed to load messages:", error));
//   }

//   if (channelId) {
//     fetchMessages(channelId);
//   }

//   //for debugging
//   document.addEventListener("DOMContentLoaded", function () {
//     console.log("DOM fully loaded and parsed")
//     const messageForm = document.getElementById("messageForm");
//     console.log("Message Form:", messageForm)

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
    });
      // Now able to update the UI or perform any other operations with the new message data
    });

    function renderMessage(data) {
      const div = document.createElement("div");
        const messageHTML = `<p>${data.user.username} : ${data.}</p>`

        console.log(messageHTML);
        div.innerHTML=messageHTML;
        messagesContainer.appendChild(div);
    }
    //   document.getElementById("messageContent").value = "";
    //   fetchMessages(channelId);
    // })
    //     .catch((error) => {
      //       console.error("Error posting message: ", error);
      //     });
      // });
      // )}
      
      //   const createChannelForm = document.getElementById("createChannelForm");
      
//   if (createChannelForm) {
//     createChannelForm.addEventListener("submit", function (event) {
//       event.preventDefault();
//       const channelName = document.getElementById("channelName").value;

//       fetch("/api/channels/createChannel", {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify({ channelName: channelName }),
//       })
//         .then((response) => {
//           if (!response.ok) {
//             throw new Error("Network response was not ok");
//           }
//           return response.json();
//         })
//         .then((data) => {
//           console.log("Channel created:", channelName);
//           document.getElementById("channelName").value = "";

//           const channelList = document.getElementById("channelList");
//           const newChannelElement = document.createElement("li");
//           newChannelElement.textContent = data.name;
//           channelList.appendChild(newChannelElement);
//         })
//         .catch((error) => {
//           console.error("Error creating channel: ", error);
//         });
//     });
//   }
// });
