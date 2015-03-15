$("#new_item").on("click", function(e) {
  e.preventDefault();
  var base = {
    code: $("#cod").val(),
    name: $("#name").val(),
    unit: $("#unit").val()
  };
  var fab = {
    code: $("#cod").val(),
    name: $("#name").val(),
    unit: $("#unit").val()
  };
  console.log(base);
});