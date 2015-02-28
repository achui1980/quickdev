<!DOCTYPE html>
<html>
	<head>
		<@include_page path="/WEB-INF/jsps/common/import-common.jsp" />
		<script src='${ctx}/js/custom.js' type="text/javascript" charset="utf-8"></script>
		<title>Button ('button')</title>
	</head>
<body>
	<script>
webix.ui({
	view:'gridex',
	domain:'${domain}',
	searchFields:[
		<#list columnInfoMap?keys as key>
			<#if columnInfoMap[key].searchable>
				{view:'text',name:'${key}',op:"${columnInfoMap[key].op}",enableSearch:true,placeholder:'${key}'},
			</#if>
		</#list>
	],
	grid:{
		columns:[
			<#list columnInfoMap?keys as key>
				<#if columnInfoMap[key].display>
					{id:'${key}', header:'${key}', editor:'text', width:100},
				</#if>
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