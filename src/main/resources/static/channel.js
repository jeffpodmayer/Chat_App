document.addEventListener("DOMContentLoaded", function () {
  const messageForm = document.getElementById("messageForm")

  if (messageForm) {
    messageForm.addEventListener("submit", function (event) {
      event.preventDefault()
      const messageContent = document.getElementById("messageContent").value
      const channelId = document.getElementById("channelId").value

      fetch(`/api/channels/${channelId}/messages`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ content: messageContent }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok")
          }
          return response.json();
        })
        .then((data) => {
          console.log(`Sending message: ${messageContent}`)
          document.getElementById("messageContent").value = ""
        })
        .catch((error) => {
          console.error("Error sending message: ", error)
        })
    })
  }

  const createChannelForm = document.getElementById("createChannelForm")

  if (createChannelForm) {
    createChannelForm.addEventListener("submit", function (event) {
      event.preventDefault()
      const channelName = document.getElementById("channelName").value

      fetch("/api/channels/createChannel", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ channelName: channelName }),
      })
      .then(response => {
        if (!response.ok) {
          throw new Error("Network response was not ok")
        }
        return response.json() 
      })
        .then((data) => {
          console.log("Channel created:", channelName)
          document.getElementById("channelName").value = ""

          const channelList = document.getElementById("channelList")
          const newChannelElement = document.createElement("li")
          newChannelElement.textContent = data.name
        })
        .catch((error) => {
          console.error("Error creating channel: ", error)
        })
    })
  }
})
