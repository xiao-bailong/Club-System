var locations = ["index2.html","myclub2.html"];//"myclub.html",
var index = document.getElementById("jump-index");
// var myclub = document.getElementById("jump-myClub");
var myclub2 = document.getElementById("jump-myClubTest");
var logout = document.getElementById("logout");
index.onclick = function() {
    window.location.href = locations[0];
}
// myclub.onclick = function() {
//     window.location.href = locations[1];
// }
myclub2.onclick = function() {
    window.location.href = locations[1];
}
logout.onclick = function() {
    window.location.href = "login.html";
}
