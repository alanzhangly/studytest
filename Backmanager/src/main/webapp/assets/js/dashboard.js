var blue = "#348fe2", blueLight = "#5da5e8", blueDark = "#1993E4", aqua = "#49b6d6", aquaLight = "#6dc5de", aquaDark = "#3a92ab", green = "#00acac", greenLight = "#33bdbd", greenDark = "#008a8a", orange = "#f59c1a", orangeLight = "#f7b048", orangeDark = "#c47d15", dark = "#2d353c", grey = "#b6c2c9", purple = "#727cb6", purpleLight = "#8e96c5", purpleDark = "#5b6392", red = "#ff5b57";
var handleInteractiveChart = function() {
	"use strict";
	function e(e, t, n) {
		$('<div id="tooltip" class="flot-tooltip">' + n + "</div>").css({
			top : t - 45,
			left : e - 55
		}).appendTo("body").fadeIn(200)
	}
	if ($("#interactive-chart").length !== 0) {
		var t = [ [ 11964728e5, 30 ], [ 11971548e5, 44 ], [ 11972412e5, 55 ],
				[ 1197414e6, 71 ], [ 1197846e6, 65 ], [ 11979324e5, 87 ],
				[ 11985372e5, 67 ], [ 11986236e5, 90 ], [ 11992284e5, 110 ],
				[ 11993148e5, 130 ], [ 11994876e5, 100 ], [ 11999196e5, 105 ],
				[ 12000924e5, 110 ], [ 12001788e5, 120 ], [ 12006108e5, 130 ],
				[ 120087e7, 135 ], [ 1201302e6, 145 ], [ 12015612e5, 132 ],
				[ 12019932e5, 123 ], [ 12020796e5, 135 ], [ 12022524e5, 150 ],
				[ 12026844e5, 145 ], [ 12029436e5, 133 ], [ 12036212e5, 145 ] ];
		var n = [ [ 11964728e5, 5 ], [ 11972412e5, 6 ], [ 1197414e6, 10 ],
				[ 1197846e6, 12 ], [ 11980188e5, 18 ], [ 11985372e5, 20 ],
				[ 11987964e5, 25 ], [ 11992284e5, 23 ], [ 11994876e5, 24 ],
				[ 11999196e5, 25 ], [ 12001788e5, 18 ], [ 12006108e5, 30 ],
				[ 120087e7, 25 ], [ 1201302e6, 25 ], [ 12015612e5, 30 ],
				[ 12020796e5, 27 ], [ 12022524e5, 20 ], [ 12026844e5, 18 ],
				[ 12029436e5, 31 ], [ 1203462e6, 23 ], [ 12036212e5, 30 ] ];
		$.plot($("#interactive-chart"), [ {
			data : t,
			label : "Page Views",
			color : purple,
			lines : {
				show : true,
				fill : false,
				lineWidth : 1.5
			},
			points : {
				show : false,
				radius : 5,
				fillColor : "#fff"
			},
			shadowSize : 0
		}, {
			data : n,
			label : "Visitors",
			color : green,
			lines : {
				show : true,
				fill : false,
				lineWidth : 1.5,
				fillColor : ""
			},
			points : {
				show : false,
				radius : 3,
				fillColor : "#fff"
			},
			shadowSize : 0
		} ], {
			xaxis : {
				mode : "time",
				ticks : 15,
				tickDecimals : 0,
				tickColor : "rgba(0,0,0,0.2)"
			},
			yaxis : {
				tickColor : "rgba(0,0,0,0.2)",
				ticks : 5
			},
			grid : {
				hoverable : true,
				clickable : true,
				tickColor : "rgba(0,0,0,0.2)",
				borderWidth : 1,
				backgroundColor : "#fafafa",
				borderColor : "#ddd"
			},
			legend : {
				labelBoxBorderColor : "#ddd",
				margin : 10,
				noColumns : 1,
				show : true
			}
		});
		var r = null;
		$("#interactive-chart").bind("plothover", function(t, n, i) {
			$("#x").text(n.x.toFixed(2));
			$("#y").text(n.y.toFixed(2));
			if (i) {
				if (r !== i.dataIndex) {
					r = i.dataIndex;
					$("#tooltip").remove();
					var s = i.datapoint[1].toFixed(2);
					var o = i.series.label + " " + s;
					e(i.pageX, i.pageY, o)
				}
			} else {
				$("#tooltip").remove();
				r = null
			}
			t.preventDefault()
		})
	}
};
var handleDashboardDatepicker = function() {
	"use strict";
	$("#datepicker-inline").datepicker({
		todayHighlight : true
	})
};
var handleDashboardTodolist = function() {
	"use strict";
	$("[data-click=todolist]").click(function() {
		var e = $(this).closest("li");
		if ($(e).hasClass("active")) {
			$(e).removeClass("active")
		} else {
			$(e).addClass("active")
		}
	})
};

var Dashboard = function() {
	"use strict";
	return {
		init : function() {
			handleInteractiveChart();
			/*handleDashboardTodolist();*/
			handleDashboardDatepicker()
		}
	}
}()