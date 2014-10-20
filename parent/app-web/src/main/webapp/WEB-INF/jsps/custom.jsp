<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsps/common/import-common.jspf"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="${pageContext.request.contextPath}/js/custom.js" type="text/javascript" charset="utf-8"></script>
		<title>Button ('button')</title>
	</head>
<body>
	
<script>

var form1 = {
	view:"form", width:250, scroll:false,
	elements:[
		{ view:"button", label:"Standard" },
		{ view:"mybutton",item:'button' },
	]
};

webix.attachEvent("onBeforeAjax", function(mode, url, data, request){
	console.log("url:"+url);
	if(url.indexOf('op') > -1){
    	//request.setRequestHeader("Content-type","application/json");
	}
});
webix.ui({
	view:'gridex',
	domain:'SysUser',
	grid:{
		/*
		columns:[
			{ id:"rank",	header:"#", 		width:50,	sort:"int",	css:"rank"},
			{ id:"title",	header:"Film title",width:200,	sort:"string"},
			{ id:"year",	header:"Released" , width:80,	sort:"int"},
			{ id:"votes",	header:"Votes", 	width:100,	sort:"int"}
		],
		select:"row",
		autowidth:false,
		data:big_film_set
		*/
		columns:[
			{id:'id', header:'id', editor:'text', width:50, sort:'int'},
			{id:'username', header:'username', editor:'text',width:100, sort:'string'},
			{id:'password', header:'password',editor:'text', width:100, sort:'string'},
		],
		editable:true,
		select:"row",
		multiselect:true,
		editaction:'dblclick',
		autowidth:false,
		url:"querypost->"+$ctx+"/rest/service/myuserService/list",
		save:{
			url:"jsonrest->"+$ctx+"/rest/service/myuserService/op/SysUser",
			autoupdate:false
		},
		on:{
			'onItemClick':function(){
				console.log('dddd');
			}
		}
	}
});		
</script>
</body>
</html>