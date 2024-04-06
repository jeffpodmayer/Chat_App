document.addEventListener("DOMContentLoaded", function () {
  const messageForm = document.getElementById("messageForm");

  messageForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const messageContent = document.getElementById("messageContent").value;
    const channelId = document.getElementById("channelId").value;

    fetch("/api/channels/${channelId}/messages", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ content: messageContent }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        console.log(`Sending message: ${messageContent}`);
        document.getElementById("messageContent").value = "";
      })
      .catch((error) => {
        console.error("Error sending message: ", error);
      });
  });
});
