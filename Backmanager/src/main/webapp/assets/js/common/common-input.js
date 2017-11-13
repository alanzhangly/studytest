var InputValidate = function () {
	return {
		//只能输入数字
		numeral : function(selector) {
			//jQuery(selector).css("imeMode", "disabled");
			jQuery("body").on("keypress", selector, function(e) {
				
				var code = (e.keyCode ? e.keyCode : e.which);  //兼容火狐 IE  
				if(e.ctrlKey){return true;}//firefox ctrl+v会响应keypess事件
				if(!$.support.msie&&(e.keyCode==0x8)) {
					//火狐下不能使用退格键
			        return ;
				}
				return (code >= 48 && code<= 57) || code==9;
			}).on("blur", selector, function() {
				if (this.value.lastIndexOf(".") == (this.value.length - 1)) {
					this.value = this.value.substr(0, this.value.length - 1);
				} else if (isNaN(this.value)) {
					this.value = "";
				}
			}).on("paste", selector, function() {
				var s = clipboardData.getData('text');
				if (!/\D/.test(s));
				this.value = s.replace(/^0*/, '');
				return false;
			}).on("dragenter", selector, function() {
				return false;
			}).on("keyup", selector, function() {
//				if (/(^0+)/.test(this.value)) {
//					this.value = this.value.replace(/^0*/, '');
//				}
				if (/[^\d\.]/g.test(this.value)) {
					this.value = this.value.replace(/[^\d\.]/g,'');
				}
			});
		},
		moneyInput : function(selector) {
			//jQuery(selector).css("imeMode", "disabled");
			jQuery("body").on("keypress", selector, function(e) {
				var code = (e.keyCode ? e.keyCode : e.which);  //兼容火狐 IE  
				if(e.ctrlKey){return true;}//firefox ctrl+v会响应keypess事件
				if (code==46) {
					//限制第一位不能是小数点
					if (jQuery(this).val()=="")
						return false;
					//限制只能有一个.
					if (jQuery(this).val().indexOf(".")>0)
						return false;
				}
				if(!$.support.msie&&(e.keyCode==0x8))  {
					//火狐下不能使用退格键
			        return ;
				}
				return code == 46 || (code >= 48 && code<= 57) || code==9;      
			}).on("dragenter", selector, function() {
				//禁止拖拽
				return false;
			}).on("paste", selector, function() {
				return false;
			}).on("keyup", selector, function() {
				this.value = this.value.replace(/[^\d\.]/g,'');
			});
		}
	};
}();
