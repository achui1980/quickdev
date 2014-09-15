<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsps/common/import-common.jspf"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>Hello World! Hello ${name}</h2>
<div id="listA" style="height:200px"></div>
<div id="page">
	<div id="next"></div>
</div>
<div id="page2">
</div>
<script type="text/javascript">
/* webix.locale.pager = {
		first: "<<",
		last: ">>",
		next: ">",
		prev: "<"
	}; */
webix.ready(function(){
	var grid = new webix.ui({
		container:"listA",
		view:'datatable',
		columns:[
		   {id:"username",header:"username", width:200},
		   {id:"password",header:"password", width:200},
		],
		datafetch:2,
		//loadahead:3,
		select:"cell",
		pager:{
			container:"page",
			size:2,
			group:5
		},
		url:"user/list"
	});
	var button  = new webix.ui({
		container:"next",
		view:"button",
		value:'next',
		click:next
	});
});
// webix.ui({
// 	container:"listA",
// 	weekHeader:true,
// 	date:new Date(2012,3,16),
// 	view:"calendar",
// 	events:webix.Date.isHoliday,
// 	timepicker:true
// });
</script>
<script>
<shiro:hasPermission name="achui:achui-1:achui-1-1:add">
var hasAdd = true;
</shiro:hasPermission>
<shiro:hasPermission name="achui:achui-1:achui-1-1:view">
var hasMod = true;
</shiro:hasPermission>
console.log("hasAdd:"+hasMod);
</script>
</body>
</html>
