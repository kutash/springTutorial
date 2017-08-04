
function onLoad() {

    $("#password").keyup(checkPasswordsMatch);
    $("#confirmpass").keyup(checkPasswordsMatch);

    $("#details").submit(canSubmit);
}

function canSubmit() {
    var password = $("#password").val();
    var confirmpass = $("#confirmpass").val();

    if(password != confirmpass) {
        alert("Passwords do not match!")
        return false;
    }
    else {
        return true;
    }
}

function checkPasswordsMatch() {
    var password = $("#password").val();
    var confirmpass = $("#confirmpass").val();

    if (password.length > 3 || confirmpass.length > 3) {

        if (password == confirmpass) {
            $("#matchpass").text("Passwords match.");
            $("#matchpass").addClass("valid");
            $("#matchpass").removeClass("error");
        } else {
            $("#matchpass").text("Passwords do not match.");
            $("#matchpass").addClass("error");
            $("#matchpass").removeClass("valid");
        }
    }
}

$(document).ready(onLoad);