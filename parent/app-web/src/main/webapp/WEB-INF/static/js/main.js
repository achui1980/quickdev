require.config({
    baseUrl: '',
    urlArgs: 'v=1.0'
});

require(
	['static/js/custom',
	 'static/js/app',
	],
	function(){
		angular.bootstrap(document, ['webixApp']);
	}
);