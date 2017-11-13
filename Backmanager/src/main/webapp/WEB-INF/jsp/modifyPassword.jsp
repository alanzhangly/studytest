<%@ page language="java" import="hl.backstage.web.permission.AccountUtil" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="needModifyPassword" value="<%=AccountUtil.getNeedModifyPassword()%>" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh">
<!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<title>后台管理系统 | 修改密码</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
	<link href="${ctx}/assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
	<link href="${ctx}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${ctx}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="${ctx}/assets/css/animate.min.css" rel="stylesheet" />
	<link href="${ctx}/assets/css/style.min.css" rel="stylesheet" />
	<link href="${ctx}/assets/css/style-responsive.min.css" rel="stylesheet" />
	<link href="${ctx}/assets/css/theme/default.css" rel="stylesheet" id="theme" />
	<!-- ================== END BASE CSS STYLE ================== -->
	<script type="text/javascript">
		var ctx = '<%=request.getContextPath() %>';
	</script>
</head>
<body>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<div class="login-cover">
	    <div class="login-cover-image">
	    	<img src="${ctx}/assets/img/login-bg/bg-2.jpg" data-id="login-cover-image" alt="" /></div>
	    <div class="login-cover-bg"></div>
	</div>
	<!-- begin #page-container -->
	<div id="page-container" class="fade">
	    <!-- begin login -->
        <div class="login login-v2" data-pageload-addclass="animated flipInX">
            <!-- begin brand -->
            <div class="login-header">
                <div class="brand">
                    <span class="logo"></span> 后台管理系统
                </div>
            </div>
            <!-- end brand -->
            <div class="login-content">
            	<div class="text-danger" id="errorMsgDiv">
					<span id="errorMsg">${needModifyPassword}</span>
				</div>
                <form action="#" method="POST" class="margin-bottom-0">
                    <div class="form-group m-b-20">
                        <input type="password" class="form-control input-lg" placeholder="请输入原登录密码" id="oldPassword" name="oldPassword" onpaste="return false" autocomplete="off"/>
                    </div>
                    <div class="form-group m-b-20">
                        <input type="password" class="form-control input-lg" placeholder="请输入新登录密码" id="newPassword" name="newPassword" onpaste="return false" autocomplete="off"/>
                    </div>
                    <div class="form-group m-b-20">
                        <input type="password" class="form-control input-lg" placeholder="请再次输入新登录密码" id="newPassword2" name="newPassword2" onpaste="return false" autocomplete="off"/>
                    </div>
                    <div class="login-buttons">
                        <button type="submit" class="btn btn-success btn-block btn-lg">提交</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- end login -->
        
	</div>
	<!-- end page container -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="${ctx}/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="${ctx}/assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="${ctx}/assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
		<script src="${ctx}/assets/crossbrowserjs/html5shiv.js"></script>
		<script src="${ctx}/assets/crossbrowserjs/respond.min.js"></script>
		<script src="${ctx}/assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="${ctx}/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${ctx}/assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="${ctx}/assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script src="${ctx}/assets/plugins/jquery-validate/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/jquery-validate/jquery.validate-extend.js" type="text/javascript"></script>
	<script src="${ctx}/assets/plugins/jquery-validate/messages_cn.js" type="text/javascript" ></script>
	<script>
		$(document).ready(function() {
			App.init();
			$('form.margin-bottom-0').validate({
				rules : {
					oldPassword : {
						required : true
					},
					newPassword : {
						required : true
					},
					newPassword2 : {
						required : true,
						equalTo : '#newPassword'
					}
				},
				messages : {
					oldPassword : {
						required : "请输入原登录密码"
					},
					newPassword : {
						required : "请输入新登录密码"
					},
					newPassword2 : {
						required : "请再次输入新登录密码",
						equalTo : "两次输入新密码不一致"
					}
				},
				submitHandler : function(form) {
					var postData = {};
					postData.oldPassword = $("#oldPassword").val();
					postData.newPassword = $("#newPassword").val();
					var postUrl = ctx + "/modifyPassword.json";
					$.ajax({
						"dataType" : 'json',
						"type" : "POST",
						"url" : postUrl,
						"data" : postData,
						"success" : function(result) {
							if (result.code == "1") {
								window.location.href = ctx + "/index.html";
							} else {
								// 错误信息展示
								$("#errorMsg").html(result.message);
								$("#errorMsgDiv").show();
							}
						}
					});
				}
			});
		});
	</script>
	 
</body>
</html>