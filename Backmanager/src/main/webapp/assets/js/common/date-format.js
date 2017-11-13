Date.prototype.format = function (format) {    
    var o = {    
        "M+": this.getMonth() + 1,    
        "d+": this.getDate(),    
        "h+": this.getHours(),    
        "m+": this.getMinutes(),    
        "s+": this.getSeconds(),    
        "q+": Math.floor((this.getMonth() + 3) / 3),    
        "S": this.getMilliseconds()    
    };
    if (/(y+)/.test(format)) {    
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));    
    }    
    for (var k in o) {    
        if (new RegExp("(" + k + ")").test(format)) {    
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));    
        }    
    }    
    return format;    
};

Date.prototype.diff = function(date){
	return (parseInt(this.getTime() - date.getTime())/(24 * 60 * 60 * 1000))+1;
};

//计算两个日期的间隔天数 
function getDays(strDateStart,strDateEnd){
   var strSeparator = "-"; //日期分隔符
   var oDate1;
   var oDate2;
   var iDays;
   oDate1= strDateStart.split(strSeparator);
   oDate2= strDateEnd.split(strSeparator);
   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
   iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数
   return iDays ;
}

/**
 * 格式化柜子状态
 */
function fmtsStatus(ostatus){
	
	if(ostatus){
		if(ostatus=="0"){
			return messages.public_storage_status_un_used;
		}
		if(ostatus=="1"){
			return messages.public_storage_status_in_used;
		}
		if(ostatus=="2"){
			return messages.public_storage_status_owe;
		}
		if(ostatus=="3"){
			return messages.public_storage_status_new_orders;
		}
		if(ostatus=="4"){
			return messages.public_storage_status_eliminate_ark;
		}
		if(ostatus=="5"){
			return messages.public_storage_status_renewal_order;
		}
		if(ostatus=="6"){
			return messages.public_storage_status_locked;
		}
		if(ostatus=="7"){
			return messages.public_storage_status_due_to;
		}
	}
	return messages.public_storage_status_un_used;
}

function digitalFormatted (tText) {
    
	try {
    	
    	tReg = "^[0-9]{1,20}\.[0-9]{1}|[0-9]{1,20}$";
    	
        if(tText == "" || tText == "undefined" || tText == null) { return true; }
        
        var regX = new RegExp(tReg, "ig");
        if (regX.test(tText)) {
            return true;
        } else {
            return false;
        }
        
    } catch (e) {
        return false;
    }
}

var I18n = new function(key) {
	return {
		getI18n:function(key){
			return '<fmt:message key="'+key+'"></fmt:message>';
		}
	};
}();