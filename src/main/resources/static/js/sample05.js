$(document).ready(function() {
  $('#sidebarCollapse').on('click', function () {
      $('#sidebar').toggleClass('active');
  });
});

$(document).ready(function($) {
  $("#count, #price").on("keydown keyup", function() {
    sum();
  });
  
  $('.table .eventBtn').on('click', function(event){
	  console.log("Bla");
	  $('.user-form #userFormModal').modal();
  })
});

$(function sum() {
  var count = document.getElementById('count').value;
  var price = document.getElementById('price').value;
  var total = parseFloat(count) * parseFloat(price);
  if (!isNaN(total)) {
    document.getElementById('total').value = total.toFixed(2);
  }
});     


$(function () {        
  var el = this;
  
  $("#datetimepicker1").datetimepicker({
	  inline: true,
	  useCurrent: true,
	  format: 'L',
	  format: 'YYYY-MM-DD',
	  locale: moment.locale('en', {
	        week: { dow: 1 }
	  }),
	  buttons:{
          showToday:false
      }
  });  
  $('#datetimepicker1').on("change.datetimepicker", function (e) {  
    console.log(e.date);
    var dateTxt = moment(e.date).format('YYYY-MM-DD');
    console.log(dateTxt);
    document.getElementById("datePickerValue").value=dateTxt
    if(currentDate !== dateTxt) {
      $('#datePickerForm').submit();
    }
  });  
  console.log("queryDate: " + currentDate);
  var parsedDate = $.datepicker.parseDate('yy-mm-dd', currentDate);
  console.log("parsedDate: " + parsedDate);
  $('#datetimepicker1').datetimepicker('date',moment(parsedDate, 'yyyy-mm-dd'));
});

$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});