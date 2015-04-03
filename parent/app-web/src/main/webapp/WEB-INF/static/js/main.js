require.config({
    baseUrl: '',
    urlArgs: 'v=1.0'
});

require(
	['static/js/custom',
	 'static/js/app',
	 'static/angular/service/config',
	 'static/angular/service/restService',
	],
	function(){
		angular.bootstrap(document, ['webixApp']);
	}
);