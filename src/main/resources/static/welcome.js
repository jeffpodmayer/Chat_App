const addUser = document.getElementById("addUser");

addUser.addEventListener("click", function () {
  const username = document.getElementById("username").value;
  console.log(username);
  sessionStorage.setItem("username", username);
  //   window.location.href = "/channels";
});

// document.addEventListener("DOMContentLoaded", function () {});
