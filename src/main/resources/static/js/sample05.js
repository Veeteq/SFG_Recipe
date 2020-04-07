function loadCategories(id) {
  console.log('loadCategories' + id);
  $.ajax({
	  type: 'GET',
      url: '/categories/all',
      success: function(data) {
    	  $('#category').html('');
    	  for (i = 0; i < data.length; i++) {
    		  $('<option>', {
    		      value: data[i].id,
    		      text: data[i].name
    		  }).appendTo($('#category'));
    		  $("#category").val(id).attr('selected','selected');
    	  }
      }
  });
  $('<option>', {
      value: '654',
      text: 'Bla'
  }).appendTo($('#category'));
}

$(document).ready(function() {
  $('#sidebarCollapse').on('click', function () {
      $('#sidebar').toggleClass('active');
  });
});

$(document).ready(function($) {
  $("#count, #price").on("keydown keyup", function() {
    sum();
  });
  
  $('.newCategoryBtn, .table .editCategoryBtn').on('click', function(event){
	  console.log("Edit category");
	  event.preventDefault();
	  
	  var href = $(this).attr('href');
	  $.get(href, function(category, status){
		  $('.category-form #id').val(category.id);
		  $('.category-form #name').val(category.name);		  
		  $("#categoryType").val(category.categoryType).attr('selected','selected');
	  });
	  
	  $('.category-form #categoryFormModal').modal();
  });
  
  $('.newUserBtn, .table .editUserBtn').on('click', function(event){
	  console.log("Edit user");
	  event.preventDefault();
	  
	  var href = $(this).attr('href');
	  $.get(href, function(user, status){
		  $('.user-form #id').val(user.id);
		  $('.user-form #name').val(user.name);
	  });
	  
	  $('.user-form #userFormModal').modal();
  });

  $('.newItemBtn, .table .editItemBtn').on('click', function(event){
	  console.log("Edit item");
	  event.preventDefault();
	  
	  var text = $(this).text();
	  
	  var href = $(this).attr('href');
	  $.get(href, function(item, status){
		  if(text=='Edit') {
		      loadCategories(item.category.id);
		      $('.item-form #id').val(item.id);
		      $('.item-form #name').val(item.name);
		  } else {
			  loadCategories();
		      $('.item-form #id').val('');
		      $('.item-form #name').val('');
		  }
	  });
	  
	  $('.item-form #itemFormModal').modal();
  });
  
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

$(function() {
	$("#comment-input").autocomplete({
		source: "expenseCommentAutocomplete",
	    minLength: 3
	});
});