
/**
 * 调用接口查询內域是否存在此邮箱帐号
 * @returns
 */

function checkEmail(userEmail) {
	var result=0;
//	接口调试区，请将接口放入
	/*$.ajax({
		type : "get",
		url : "/ajax/apply.do",// 请求数据
		data: {
  			"uid":userEmail
  			},
		async: false,
		dataType : 'json',
		success : function(data) {
			if (data.code == "200") {
				result = 3;
			}else if(data.code == "202"){
				alert("活动未开始！");
				return false;
			}else if(data.code == "203"){
				alert("活动已下线！");
				return false;
			}else if(data.code == "209"){
				result = 2;
			}else if(data.code == "400"){
				result = 1;
			}else if(data.code == "601"){
				result = 5;
			}else if(data.code == "602"){
				result = 4;
			}else if(data.code == "500"){
				alert( "领取失败请稍后再试！");
				return false;
			}
		}
	});*/
	
//		result=1表示帐号不存在；result=2表示已经领过礼品；result=3表示未领过礼品；result=4表示老用户的邮箱帐号；result=5 表示未登录过邮箱大师的邮箱帐号；
		return 5;
}
/**
 * 调用接口 获取获奖列表（弹框）
 * @returns
 */

function prizeUserList() {
//	接口调试区，请将接口放入   使用时解禁ajax部分的代码
	$.ajax({
		type : "get",
		url : "/ajax/getWinners.do",// 请求数据
		contentType : "application/json",
		dataType : 'json',
		success : function(data) {
			if (data.code == "200"&&!check.isEmpty(data.content)) {
				var mxList="";
				for(var i = 0; i < data.content.list.length; i++){
					if(data.content.list[i].prizeCode==1){
						mxList+="<tr><td>"+data.content.list[i].userEmail+"</td><td>乐视电视</td></tr>";
					}
					if(data.content.list[i].prizeCode==2){
						mxList+="<tr><td>"+data.content.list[i].userEmail+"</td><td>一加手机</td></tr>";
					}
					if(data.content.list[i].prizeCode==3){
						mxList+="<tr><td>"+data.content.list[i].userEmail+"</td><td>艾吉森AGS-E6空气净化器</td></tr>";
					}
				}
				$("#mxList").html(mxList);
				$('.mask').show();
				$('.prize-list-mess').show();
			}else if(data.code == "500"){
				$("#mxList").html("");
			}
		}
	});
}