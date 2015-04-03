define(['static/js/app'],function(app){
	var config = {
		APP: {
			PORT: '8090',
	        NAMESPACE: 'rest/service',
	        CONTEXT:'app-web',
	        HOST : 'http://localhost:8090/app-web/rest/service'
		}	
	};
	
	app.value('config',config);
});