// functions to be used throughout the project

// navigating to login page
// https://stackoverflow.com/questions/16562577/how-can-i-make-a-button-redirect-my-page-to-another-page
function goLogin() {
    location.href="/v1/login";
    console.log("clicked login from index page");
}

// navigating to signup page
function goSignup() {
    location.href="/v1/signup";
    console.log("clicked signup from index page");
}

// for when clicking login on login page
function login() {
    location.reload();
    location.href='/v1/home/' + document.getElementById('username').value;
    console.log("clicked login from login page");
}