$(document).ready(function() {
  $('#login').click(
    function(e) {
      e.preventDefault();
      console.log("hey");
      $.ajax({
        type: "POST",
        url: "/login",
        data: {
          username: $('#inputEmail').val(),
          password: $('#inputPassword').val()
        },
        success: function(data) {
          if (data.success) {
            window.location = "/";
          } else {
            alert("Password errada!");
          }
        }
      });
    });
});