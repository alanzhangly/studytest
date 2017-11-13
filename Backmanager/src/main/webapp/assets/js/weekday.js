	// 选中星期
	function setweek(tag){
		if(tag == '0'){
			for(var i = 1; i < 8; i++){
				$("#week"+i).attr("class","btn btn-sm btn-default");
				$("#weekstr").val("");
			}
		} else {
			var str = $("#weekstr").val();
			if($("#week" + tag).attr("class")=="btn btn-sm btn-default"){
				str += tag+",";
				$("#week"+tag).attr("class","btn btn-sm btn-danger");
			}else if($("#week"+tag).attr("class")=="btn btn-sm btn-danger"){
				$("#week"+tag).attr("class","btn btn-sm btn-default");
				str = str.replace(tag + ",", "");
			}
			$("#weekstr").val(str);
		}
	}
	//星期只选一天
	function setOneweek(tag){
		if(tag == '0'){
			for(var i = 1; i < 8; i++){
				$("#week"+i).attr("class","btn btn-sm btn-default");
				$("#weekstr").val("");
			}
		} else {
			for(var i = 1; i < 8; i++){
				$("#week"+i).attr("class","btn btn-sm btn-default");
				$("#weekstr").val("");
			}
			var str = $("#weekstr").val();
			if($("#week" + tag).attr("class") == "btn btn-sm btn-default"){
				str += tag+",";
				$("#week"+tag).attr("class","btn btn-sm btn-danger");
			} else if($("#week"+tag).attr("class") == "btn btn-sm btn-danger"){
				$("#week"+tag).attr("class","btn btn-sm btn-default");
				str = str.replace(tag + ",","");
			}
			$("#weekstr").val(str);
		}
	}
	// 选中天
	function setday(tag){
		if(tag=='0'){//全清
			for(var i = 1; i < 32; i++){
				$("#day"+i).attr("class", "btn btn-sm btn-default");
				$("#daystr").val("");
			}
		} else if(tag == "99"){//全选
			var str = "";
			for(var i=1;i<32;i++){
				$("#day"+i).attr("class", "btn btn-sm btn-danger");
				str +=i+",";
			} 
			$("#daystr").val(str);
		} else if(tag =="88"){//反选
			var str = "";
			for(var i = 1; i < 32; i++){
				if($("#day"+i).attr("class") == "btn btn-sm btn-default"){
					$("#day"+i).attr("class", "btn btn-sm btn-danger");
					str += i + ",";
				} else if($("#day"+i).attr("class") == "btn btn-sm btn-danger"){
					$("#day"+i).attr("class", "btn btn-sm btn-default");
					str = str.replace(i + ",", "");
				}
			} 
			$("#daystr").val(str);
		} else {
			var str = $("#daystr").val();
			if($("#day"+tag).attr("class") == "btn btn-sm btn-default"){
				str += tag+",";
				$("#day"+tag).attr("class","btn btn-sm btn-danger");
			} else if($("#day"+tag).attr("class") == "btn btn-sm btn-danger"){
				str = str.replace(tag + ",", "");
				$("#day"+tag).attr("class","btn btn-sm btn-default");
			}
			$("#daystr").val(str);
		}
	} 