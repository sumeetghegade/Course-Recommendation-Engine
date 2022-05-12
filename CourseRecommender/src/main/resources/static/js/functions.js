// functions to be used throughout the project

// navigating to login page
// https://stackoverflow.com/questions/16562577/how-can-i-make-a-button-redirect-my-page-to-another-page
function goLogin() {
    location.href="/v1/login";
    console.log("clicked login");
}

// navigating to signup page
function goSignup() {
    location.href="/v1/signup";
    console.log("clicked signup");
}