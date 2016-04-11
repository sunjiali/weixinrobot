var $window = $(window), gardenCtx, gardenCanvas, $garden, garden;
var clientWidth = $(window).width();
var clientHeight = $(window).height();
$(function() {
	$loveHeart = $("#loveHeart");
	var a = $loveHeart.width() / 2;
	var b = $loveHeart.height() / 2 - 55;
	$garden = $("#garden");
	gardenCanvas = $garden[0];
	gardenCanvas.width = $("#loveHeart").width();
	gardenCanvas.height = $("#loveHeart").height();
	gardenCtx = gardenCanvas.getContext("2d");
	gardenCtx.globalCompositeOperation = "lighter";
	garden = new Garden(gardenCtx, gardenCanvas);
	$("#content").css("width", $loveHeart.width() + $("#code").width());
	$("#content").css("height",
			Math.max($loveHeart.height(), $("#code").height()));
	$("#content").css("margin-top",
			Math.max(($window.height() - $("#content").height()) / 2, 10));
	$("#content").css("margin-left",
			Math.max(($window.width() - $("#content").width()) / 2, 10));
	setInterval(function() {
		garden.render()
	}, Garden.options.growSpeed)
});
$(window).resize(function() {
	var b = $(window).width();
	var a = $(window).height();
	if (b != clientWidth && a != clientHeight) {
		location.replace(location)
	}
});
function getHeartPoint(c) {
	var b = c / Math.PI;
	var a = 19.5 * (16 * Math.pow(Math.sin(b), 3));
	var d = -20
			* (13 * Math.cos(b) - 5 * Math.cos(2 * b) - 2 * Math.cos(3 * b) - Math
					.cos(4 * b));
	return new Array(offsetX + a, offsetY + d)
}
function startHeartAnimation() {
	var c = 50;
	var d = 10;
	var b = new Array();
	var a = setInterval(function() {
		var h = getHeartPoint(d);
		var e = true;
		for ( var f = 0; f < b.length; f++) {
			var g = b[f];
			var j = Math.sqrt(Math.pow(g[0] - h[0], 2)
					+ Math.pow(g[1] - h[1], 2));
			if (j < Garden.options.bloomRadius.max * 1.3) {
				e = false;
				break
			}
		}
		if (e) {
			b.push(h);
			garden.createRandomBloom(h[0], h[1])
		}
		if (d >= 30) {
			clearInterval(a);
			showMessages()
		} else {
			d += 0.2
		}
	}, c)
}
(function(a) {
	a.fn.typewriter = function() {
		this.each(function() {
			var d = a(this), c = d.html(), b = 0;
			d.html("");
			var e = setInterval(function() {
				var f = c.substr(b, 1);
				if (f == "<") {
					b = c.indexOf(">", b) + 1
				} else {
					b++
				}
				d.html(c.substring(0, b) + (b & 1 ? "_" : ""));
				if (b >= c.length) {
					clearInterval(e)
				}
			}, 75)
		});
		return this
	}
})(jQuery);
startclock() 
var timerID = null;   
var timerRunning = false;  
function timeElapse(c) {
	
	/*
	var e = Date();
	var f = (Date.parse(e) - Date.parse(c)) / 1000;
	var g = Math.floor(f / (3600 * 24));
	f = f % (3600 * 24);
	var b = Math.floor(f / 3600);
	if (b < 10) {
		b = "0" + b
	}
	f = f % 3600;
	var d = Math.floor(f / 60);
	if (d < 10) {
		d = "0" + d
	}
	f = f % 60;
	if (f < 10) {
		f = "0" + f
	}*/
	
	Today = new Date();   
	var NowHour = Today.getHours();   
	var NowMinute = Today.getMinutes();   
	var NowMonth = Today.getMonth();   
	var NowDate = Today.getDate();   
	var NowYear = Today.getYear();   
	var NowSecond = Today.getSeconds();   
	if (NowYear <2000)   
	NowYear=1900+NowYear;   
	Today = null;   
	Hourleft = 23 - NowHour   ;
	Minuteleft = 59 - NowMinute   ;
	Secondleft = 59 - NowSecond   ;
	Yearleft = 2017 - NowYear  ; 
	Monthleft = 12 - NowMonth - 1 ;
	Dateleft = 5 - NowDate   ;
	if (Secondleft<0)   
	{   
	Secondleft=60+Secondleft;   
	Minuteleft=Minuteleft-1;   
	}   
	if (Minuteleft<0)   
	{    
	Minuteleft=60+Minuteleft;   
	Hourleft=Hourleft-1;   
	}   
	if (Hourleft<0)   
	{   
	Hourleft=24+Hourleft;   
	Dateleft=Dateleft-1;   
	}   
	if (Dateleft<0)   
	{   
	Dateleft=31+Dateleft;   
	Monthleft=Monthleft-1;   
	}   
	if (Monthleft<0)   
	{   
	Monthleft=12+Monthleft;   
	Yearleft=Yearleft-1;   
	}   
	
	var a ='<span class="digit">' + Yearleft+ '</span> years<span class="digit">' + Dateleft + '</span> days <span class="digit">'
	+ Hourleft + '</span> hours <span class="digit">' + Minuteleft
	+ '</span> minutes <span class="digit">' + Secondleft + "</span> seconds";
$("#elapseClock").html(a)
	
	/*var a = '<span class="digit">' + g + '</span> days <span class="digit">'
			+ b + '</span> hours <span class="digit">' + d
			+ '</span> minutes <span class="digit">' + f + "</span> seconds";
	$("#elapseClock").html(a)*/
}

function startclock () {   
	stopclock();   
	timeElapse();   
	} 
function showMessages() {
	$("#messages").fadeIn(5000, function() {
		showLoveU()
	})
}
function adjustWordsPosition() {
	$("#words").css("position", "absolute");
	$("#words").css("top", $("#garden").position().top + 195);
	$("#words").css("left", $("#garden").position().left + 70)
}
function adjustCodePosition() {
	$("#code").css("margin-top",
			($("#garden").height() - $("#code").height()) / 2)
}
function showLoveU() {
	$("#loveu").fadeIn(3000)
};