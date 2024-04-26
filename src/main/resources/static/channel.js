const username=sessionStorage.getItem('username');
const channelIdInput = document.getElementById('channelId');
const channelId = channelIdInput.value;

const btnSendMessage = document.getElementById('sendMessage');
console.log(username)
console.log(channelId)
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
    btnSendMessage.addEventListener("click", function (event) {
      event.preventDefault();
      const messageContent = document.getElementById("messageContent").value;
      // console.log("Message Input:", messageContent);
      let message = {
        user: {
          username: username
        },
        content: messageContent,
        channel: channelId

      };
      console.log("Message Output:", message);
    });
      fetch(`/api/messages/createMessage/${channelId}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          message
        }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          console.log(response)
          return response.json();
        })
        // .then(() => {
        //   console.log(`Message posted: ${messageContent}`);
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
