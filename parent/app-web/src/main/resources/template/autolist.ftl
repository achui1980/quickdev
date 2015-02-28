<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="/app-web/static/vendor/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"> 
		<link rel="stylesheet" href="/app-web/static/vendor/bootstrap/css/bootstrap.css" type="text/css"> 
		<link rel="stylesheet" href="/app-web/static/vendor/bootstrap/css/context.bootstrap.css" type="text/css"> 
		<link rel="stylesheet" href="/app-web/static/css/common.css" type="text/css"> 
		<script src="/app-web/static/vendor/jquery-2.1.3.js" type="text/javascript"></script>  
		<script src="/app-web/static/vendor/ztree/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>  
		<script src="/app-web/static/vendor/bootstrap/js/bootstrap.js" type="text/javascript"></script>  
		<script src="/app-web/static/vendor/bootstrap/js/context.js" type="text/javascript"></script>  
		<script>
		var $ctx = '/app-web';
		</script>
		<link rel="stylesheet" href="/app-web/static/vendor/webix/webix.css" type="text/css"> 
		<script src="/app-web/static/vendor/webix/webix_debug.js" type="text/javascript"></script>  
		<script src='/app-web/js/custom.js' type="text/javascript" charset="utf-8"></script>
		<title>Button ('button')</title>
	</head>
<body>
	
<script>
webix.ui({
	view:'gridex',
	domain:'${domain}',
	grid:{
		columns:[
			<#list columnInfoList as columnInfo>
				{id:'${columnInfo.name}', header:'${columnInfo.name}', editor:'text', width:100},
			</#list>
		],
		editable:true,
		select:"row",
		multiselect:true,
		editaction:'dblclick',
		autowidth:false,
		url:"querypost->"+$ctx+"/rest/service/${serviceName}/list",
		save:{
			url:"jsonrest->"+$ctx+"/rest/service/${serviceName}/op/${domain}",
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