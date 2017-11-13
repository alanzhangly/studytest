<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh">
<!--<![endif]-->
<head>
	<%--<%@ include file="/WEB-INF/common/taglibs.jsp"%>--%>
    <title>后台管理系统 | 404页面</title>
    <%--<%@ include file="/WEB-INF/common/meta.jsp"%>--%>
</head>
<body>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade page-sidebar-fixed page-header-fixed">
		
		<%--<%@ include file="/WEB-INF/common/header.jsp" %>--%>
        <%--<%@ include file="/WEB-INF/common/slider.jsp" %>--%>
		
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<ol class="breadcrumb pull-right">
				<li>后台管理系统</li>
				<li class="active">404页面</li>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">404页面 <small>后台管理系统</small></h1>
			<!-- end page-header -->
			
			<!-- begin row -->
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <SequenceNumber href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></SequenceNumber>
                                <SequenceNumber href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></SequenceNumber>
                                <SequenceNumber href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></SequenceNumber>
                            </div>
                            <h4 class="panel-title">404页面</h4>
                        </div>
                        <div class="panel-body">
                        	<h1>很抱歉~</h1>
                        	<h3>没有找到你所需要页面</h3>
						</div>
					</div>
                    <!-- end panel -->
			    </div>
			    <!-- end col-12 -->
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->
		
	</div>
	<!-- end page container -->
	<%--<%@ include file="/WEB-INF/common/footer.jsp"%>--%>
</body>
</html>