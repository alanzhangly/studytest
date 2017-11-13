<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="zh">
    <head>
        <script type="text/javascript">
            var ctx = '<%=request.getContextPath() %>';
        </script>
    </head>
    <body>
        <h2>Hello World!</h2>
        <a href="/user/add">lalala</a>
    </body>

</html>
