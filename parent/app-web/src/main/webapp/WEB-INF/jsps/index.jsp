<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsps/common/import-common.jspf"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<h2>Hello World! Hello ${name}</h2>
<div id="listA"></div>
<div id="page">
	<div id="next"></div>
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
		select:"cell",
		datafetch:10,
		loadahead:2,
		pager:{
			//template:"{common.first()} {common.prev()} {common.pages()} {common.next()} {common.last()}",
			container:"page",
			size:1,
			group:2
		},
		url:"user/list"
	});
	var button  = new webix.ui({
		container:"next",
		view:"button",
		value:'next',
		click:next
	});
	
	function next(){
		console.log(grid.getPage());
		grid.clearAll();
		grid.loadNext(2,grid.getPage());
		grid.setPage(grid.getPage()+1);
	}
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
