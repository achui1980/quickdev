<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsps/common/import-common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="${pageContext.request.contextPath}/static/vendor/require.js" data-main="static/js/main" type="text/javascript" charset="utf-8"></script>
		<title>Button ('button')</title>
	</head>
<body>
	<a href='#/require'>require</a>
	<div data-ng-view id="ng-view"></div>
</body>
</html>