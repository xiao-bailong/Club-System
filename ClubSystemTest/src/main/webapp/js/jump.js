var locations = ["index.html","clubapproval.html","clubmanager.html","usermanager.html"];
var index = document.getElementById("jump-index");
var clubapproval = document.getElementById("jump-clubApproval");
var clubmanager = document.getElementById("jump-clubManager");
var usermanager = document.getElementById("jump-userManager");
var logout = document.getElementById("logout");
index.onclick = function() {
    window.location.href = locations[0];
}
clubapproval.onclick = function() {
    window.location.href = locations[1];
}
clubmanager.onclick = function() {
    window.location.href = locations[2];
}
usermanager.onclick = function() {
    window.location.href = locations[3];
}
logout.onclick = function() {
    window.location.href = "login.html";
}