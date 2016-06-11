$(function(){
	var location = localStorage["location"];
	$("nav ul li").click(function(){
		console.log("nav: "+ $(this).data("target"));
	  var xcoord = $(this).data("xcoord");
	  console.log(xcoord);
	  var width = $(this).data("width");
	  var target = $(this).data("target");
		//$('#tablist li').data("xcoord", xcoord);
	  $("nav div").stop().animate({marginLeft:xcoord,'width':width}, 200);
	  $(this).addClass("active");
	  $("nav ul li").not(this).removeClass("active");
	  $('.ar_wrapper').hide();
	  $('#'+target).show();
		localStorage.setItem("location", target);
		dataLoad();
	});
//})
//$(function() {

	$("div.wrapper").swiperight(function() {
		console.log("swipe right");
		var xcoord = $('nav div').css("margin-left");
		console.log(xcoord);
		if(xcoord == "720px"){
			$("nav ul li").not("#ar_feed").removeClass("active");
			$("#ar_feed").addClass("active");
			$('.ar_wrapper').hide();
			$('#ar_feed').show();
			localStorage.setItem("location", "ar_feed");
			dataLoad();
			//$('#tablist li').data("xcoord", "360px");
			$("nav div").stop().animate({marginLeft:"360px", 'width': "360px"}, 200);
		}else if (xcoord == "360px"){
			$("nav ul li").not("#ar_profile").removeClass("active");
			$("#ar_profile").addClass("active");
			$('.ar_wrapper').hide();
			$('#ar_profile').show();
			localStorage.setItem("location", "ar_profile");
			$("nav div").stop().animate({marginLeft:"0px", 'width': "360px"}, 200);
		}
	});
	$("div.wrapper").swipeleft(function() {
		console.log("swipe left");
		var xcoord = $('nav div').css("margin-left");
		console.log(xcoord);
		if(xcoord == "0px"){
			$("nav ul li").not("#ar_feed").removeClass("active");
			$("#ar_feed").addClass("active");
			$('.ar_wrapper').hide();
			$('#ar_feed').show();
			localStorage.setItem("location", "ar_feed");
			dataLoad();
			$("nav div").stop().animate({marginLeft:"360px", 'width': "360px"}, 200);
		}else if (xcoord == "360px"){
			$("nav ul li").not("#ar_notes").removeClass("active");
			$("#ar_notes").addClass("active");
			$('.ar_wrapper').hide();
			$('#ar_notes').show();
			localStorage.setItem("location", "ar_notes");
			$("nav div").stop().animate({marginLeft:"720px", 'width': "360px"}, 200);
			dataLoad();
		}

	});
	//function take() {
	//	$.each(['show', 'hide'], function (i, ev) {
	//		var el = $.fn[ev];
	//		$.fn[ev] = function () {
	//			this.trigger(ev);
	//			return el.apply(this, arguments);
	//		};
	//	});
	//};
//$("ul.tablist > li > a").on("shown", function (e) {
	$('.container').on('shown', function(e){
	var id = $(e.target).attr("href").substr(1);
	console.log("id: " , id);
	window.location.hash = id;
});

// on load of the page: switch to the currently selected tab
var hash = localStorage.getItem("location");
console.log(hash);
if (hash == "ar_feed"){
	dataLoad();
	console.log("ar_feed");
	$("nav ul li").not("#ar_feed").removeClass("active");
	$("#ar_feed").addClass("active");
	$('.ar_wrapper').hide();
	$('#ar_feed').show();
	//$('#tablist li').data("xcoord", "360px");
	$("nav div").stop().animate({marginLeft:"360px", 'width': "360px"}, 0);
}else if(hash == "ar_notes"){
	dataLoad();
	$("nav ul li").not("#ar_notes").removeClass("active");
	$("#ar_notes").addClass("active");
	$('.ar_wrapper').hide();
	$('#ar_notes').show();
	//$('#tablist li').data("xcoord", "720px");
	$("nav div").stop().animate({marginLeft:"720px", 'width': "360px"}, 0);
}
});


function dd (){window.location.href = '../views/search.html'; noteloaded = false; contloaded = false;}