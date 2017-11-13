/*
Template Name: Color Admin Responsive Admin Template
Author: Sean Ngu
Author URL: http://www.sean-theme.com/pixel-admin/
Version: 1.0
 */
var handlePageLoaderView = function() {
	"use strict";
	/* <!-- begin #page-loader --> */
	$("body")
			.prepend(
					'<div id="page-loader" class="fade in"><span class="spinner"></span></div>');
	/* <!-- end #page-loader --> */
};
var handleSlimScroll = function() {
	"use strict";
	$("[data-scrollbar=true]").each(function() {
		var e = $(this).attr("data-height");
		e = !e ? $(this).height() : e;
		$(this).slimScroll({
			height : e,
			alwaysVisible : true
		});
	});
};
var handleSidebarMenu = function() {
	"use strict";
	$(".sidebar .nav > .has-sub > a").click(function() {
		var e = $(this).next(".sub-menu");
		var t = ".sidebar .nav > li.has-sub > .sub-menu";
		$(t).not(e).slideUp(250);
		$(e).slideToggle(250)
	});
	$(".sidebar .nav > .has-sub .sub-menu li.has-sub > a").click(function() {
		var e = $(this).next(".sub-menu");
		$(e).slideToggle(250)
	})
};
var handleMobileSidebarToggle = function() {
	$(".sidebar").click(function(e) {
		e.stopPropagation()
	});
	$(document).click(function(e) {
		if (!e.isPropagationStopped()) {
			if ($("#page-container").hasClass("sidebar-toggled")) {
				$("#page-container").removeClass("sidebar-toggled")
			}
		}
	});
	$("[data-click=sidebar-toggled]").click(function(e) {
		e.stopPropagation();
		var t = "sidebar-toggled";
		var n = "#page-container";
		if ($(n).hasClass(t)) {
			$(n).removeClass(t)
		} else {
			$(n).addClass(t)
		}
	})
};
var handleSidebarMinifyView = function() {
	/*
	 * <!-- begin sidebar minify button -->
	 */
	$("ul#sidebar-nav")
			.append(
					'<li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>');
	/*
	 * <!-- end sidebar minify button -->
	 */
}
var handleSidebarMinify = function() {
	$("[data-click=sidebar-minify]").click(function(e) {
		e.preventDefault();
		var t = "sidebar-minified";
		var n = "#page-container";
		if ($(n).hasClass(t)) {
			$(n).removeClass(t)
		} else {
			$(n).addClass(t)
		}
		$(window).trigger("resize")
	})
};
var handlePageContentView = function() {
	"use strict";
	$.when($("#page-loader").addClass("hide")).done(function() {
		$("#page-container").addClass("in")
	})
};
var handlePanelAction = function() {
	"use strict";
	$("[data-click=panel-remove]").hover(function() {
		$(this).tooltip({
			title : "Remove",
			placement : "bottom",
			trigger : "hover",
			container : "body"
		});
		$(this).tooltip("show")
	});
	$("[data-click=panel-remove]").click(function(e) {
		e.preventDefault();
		$(this).closest(".panel").remove()
	});
	$("[data-click=panel-collapse]").hover(function() {
		$(this).tooltip({
			title : "折叠 /展开",
			placement : "bottom",
			trigger : "hover",
			container : "body"
		});
		$(this).tooltip("show")
	});
	$("[data-click=panel-collapse]").click(function(e) {
		e.preventDefault();
		$(this).closest(".panel").find(".panel-body").slideToggle()
	});
	$("[data-click=panel-reload]").hover(function() {
		$(this).tooltip({
			title : "刷新",
			placement : "bottom",
			trigger : "hover",
			container : "body"
		});
		$(this).tooltip("show")
	});
	$("[data-click=panel-reload]")
			.click(
					function(e) {
						e.preventDefault();
						var t = $(this).closest(".panel");
						if (!$(t).hasClass("panel-loading")) {
							var n = $(t).find(".panel-body");
							var r = '<div class="panel-loader"><span class="spinner-small"></span></div>';
							$(t).addClass("panel-loading");
							$(n).prepend(r);
							setTimeout(function() {
								$(t).removeClass("panel-loading");
								$(t).find(".panel-loader").remove()
							}, 2e3)
						}
					});
	$("[data-click=panel-expand]").hover(function() {
		$(this).tooltip({
			title : "展开/缩小",
			placement : "bottom",
			trigger : "hover",
			container : "body"
		});
		$(this).tooltip("show")
	});
	$("[data-click=panel-expand]").click(
			function(e) {
				e.preventDefault();
				var t = $(this).closest(".panel");
				if ($("body").hasClass("panel-expand")
						&& $(t).hasClass("panel-expand")) {
					$("body, .panel").removeClass("panel-expand");
					$(".panel").removeAttr("style");
					$("[class*=col]").sortable("enable")
				} else {
					$("body").addClass("panel-expand");
					$(this).closest(".panel").addClass("panel-expand");
					$("[class*=col]").sortable("disable")
				}
				$(window).trigger("resize")
			})
};
var handleAjaxStartStop = function() {
	$(document).ajaxStart(function() {
		$("#global_isloading").css("display","");
	});
	$(document).ajaxStop(function() {
		$("#global_isloading").css("display", "none");
	});
}
var handleDraggablePanel = function() {
	"use strict";
	var e = "[class*=col]";
	var t = ".panel-heading";
	var n = ".row > [class*=col]";
	$(e).sortable({
		handle : t,
		connectWith : n
	})
};
var handelTooltipPopoverActivation = function() {
	"use strict";
	$("[data-toggle=tooltip]").tooltip();
	$("[data-toggle=popover]").popover()
};
var handleScrollToTopButton = function() {
	"use strict";
	$(document).scroll(function() {
		var e = $(document).scrollTop();
		if (e >= 200) {
			$("[data-click=scroll-top]").addClass("in")
		} else {
			$("[data-click=scroll-top]").removeClass("in")
		}
	});
	$("[data-click=scroll-top]").click(function(e) {
		e.preventDefault();
		$("html, body").animate({
			scrollTop : $("body").offset().top
		}, 500)
	})
};
var handleFooterView = function() {
	"use strict";
	/* <!-- begin #footer --> */
	$("div.content")
			.after(
					'<div id="footer" class="footer">'
							+ 'Copyright © 2015-2016 www.zeepson.com Inc. All rights reserved <br>'
							+'北京智普信科技股份有限公司 版权所有 京ICP备09017907'
							+ '</div>');
	/* <!-- end #footer --> */
};
var handleScrollToTopInit = function() {
	"use strict";
	/*
	 * <!-- begin scroll to top btn -->
	 */
	$("div.content")
			.after(
					'<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>');
	/*
	 * <!-- end scroll to top btn -->
	 */
};
var handleFancybox = function() {
	if (!jQuery.fancybox) {
		return;
	}

	if (jQuery(".fancybox-button").size() > 0) {
		jQuery(".fancybox-button").fancybox({
			groupAttr : 'data-rel',
			prevEffect : 'none',
			nextEffect : 'none',
			closeBtn : true,
			helpers : {
				title : {
					type : 'inside'
				}
			}
		});
	}
};
var App = function() {
	"use strict";
	return {
		init : function() {
			handlePageLoaderView();
			handleSlimScroll();
			handleSidebarMenu();
			handleMobileSidebarToggle();
			handleSidebarMinifyView();
			handleSidebarMinify();
			handlePageContentView();
			handlePanelAction();
			handleDraggablePanel();
			handelTooltipPopoverActivation();
			handleFooterView();
			handleScrollToTopInit();
			handleScrollToTopButton();
			handleAjaxStartStop();
		},
		initFancybox : function() {
			handleFancybox();
		}
	}
}()