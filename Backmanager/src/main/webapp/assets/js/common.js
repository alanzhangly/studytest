var mySwiperSlide;
var uid="";
$(document).ready(function() {
	init();
});
/**
 * 初始化数据
 */
function init() {
	// 自动识别屏幕高度 （动态适配）
	var winHeight = $(window).height();
	$('.swiper-container').css({
		"height" : winHeight
	});
	mySwiperSlide = new Swiper('.swiper-container', {
		loop : false,
		direction : 'vertical',
	});
	$(".mess-close").click(function() {
		$('.mask').hide();
		$('.mess').hide();
		$("input").val("");
	});
	$(".gotoAward").click(function() {
		uid=$('.j-uid').text();
		if(!check.isEmpty(uid)){
			$("#userEmail").val(uid);
			getGift(1);
		}else{
			$('.mask').show();
			$('.input-mess').show();
			$("input").val("");
		}
	});
	$("#getGift").click(function() {
		getGift(1);
	});
	
	$(".morebtn").click(function() {
		$(".mess-close").click();
		$('.mask').show();
		$('.input-mess').show();
	});
	$("#m-gz").click(function() {
		$('.mask').show();
		$('.gz-mess').show();
	});
	$("#m-share").click(function() {
		$('.mask').show();
		$('.share-mess').show();
	});
	$(".share-mess").click(function() {
		$(".mess-close").click();
	});
	
}

