<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsps/common/import-common.jspf"%>
<html>
<body>
<h2>Hello World! Hello ${name}</h2>
<div id="listA"></div>
<script type="text/javascript">
webix.ui({
	container:"listA",
	weekHeader:true,
	date:new Date(2012,3,16),
	view:"calendar",
	events:webix.Date.isHoliday,
	timepicker:true
});
</script>
</body>
</html>
