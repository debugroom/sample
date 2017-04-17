
var test = $("#space")
var test2 = $("[id$='test-xss']");
$.each(test2, function(i, val){
	var test4 = $(val).val();
});

$("#space").html($("[id$='test-xss']").val() + 'jqeury');
$("#space").text($("[id$='test-xss']").val() + 'jqeury-text');
