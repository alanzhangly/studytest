//图片上传
function uploadImg(image,imageUrl){
	var path = $(image).val();
	if (path != '') {
		var type = (path.substr(path.lastIndexOf("."))).toLowerCase();
		if (type != ".jpg" && type != ".gif" && type != ".jpeg"
				&& type != ".png") {
			BootstrapDialog.show({
				type : BootstrapDialog.TYPE_WARNING,
				title : "警告",
				message : "您上传图片的类型不符合(.jpg|.jpeg|.gif|.png)！",
				buttons : [ {
					label : "确定",
					cssClass : 'btn btn-sm btn-primary',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
		} else {
			$("#upload_img").attr("src", loadingImagePath);
			$.ajaxFileUpload({
				fileElementId : 'image',// 文件选择框的id属性
				dataType : 'json',// 服务器返回的格式，可以是json
				data : {
					"dir" : "image"
				},
				url : ctx + '/upload/file',// 需要链接到服务器地址
				success : function(data, status) {
					if (data['code'] == 1) {
						$("#upload_img").attr("src", data.url);
						$("#upload_img").css("width", "80%");
						$(imageUrl).val(data.url);
					} else {
						$("#upload_img").attr("src", uploadImagePath);
						BootstrapDialog.show({
							type : BootstrapDialog.TYPE_WARNING,
							title : "警告",
							message : data.message,
							buttons : [ {
								label : "确定",
								cssClass : 'btn btn-sm btn-primary',
								action : function(dialogItself) {
									dialogItself.close();
								}
							} ]
						});
					}
				}
			});
		}
	}
}