document.addEventListener("DOMContentLoaded", function () {

    //displaying messages if the password is too short or the passwords do not match
    let passwordInput = $('#pass1');
    passwordInput.on('keyup', function () {
        if (passwordInput.val() !== '' && passwordInput.val().length < 8) {
            $('#message1').html('Minimalna długość hasła to 8 znaków').css('color', 'red');
        } else
            $('#message1').html('');
    });

    $('#pass2').on('keyup', function () {
        if (passwordInput.val().length >= 8) {
            if (passwordInput.val() === $('#pass2').val()) {
                $('#message').html('');
            } else
                $('#message').html('Wpisane hasła nie są jednakowe').css('color', 'red');
        } else
            $('#message').html('');
    });

});

function passwordValidation() {
    var pass1 = document.getElementById("pass1").value;
    var pass2 = document.getElementById("pass2").value;
    var ok = true;

    //protection from submitting form with not matching passwords
    if (pass1 !== pass2) {
        alert("Wpisane hasła nie są jednakowe!");
        document.getElementById("pass1").style.borderColor = "#E34234";
        document.getElementById("pass2").style.borderColor = "#E34234";
        ok = false;
    }

    //protection from submitting form with password shorter than 8 characters
    if (pass1.length < 8) {
        alert("Minimalna długość hasła to 8 znaków");
        document.getElementById("pass1").style.borderColor = "#E34234";
        ok = false;
    }

    return ok;
}