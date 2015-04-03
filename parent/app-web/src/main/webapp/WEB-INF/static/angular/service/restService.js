'use strict';

/**
 * @ngdoc service
 * @name todoApp.myService
 * @description
 * # myService
 * Service in the todoApp.
 */
define(['static/js/app'],function(app){
	var injectParams = ['$http','config'];
	var restService = function($http,config){
		var restAjax = function(url,params,headers,method){
	     	headers = 
	     		headers == null ? { 'Content-Type':'application/json'} : headers;
	     	var promise = $http({
	     		method:method,
	     		url:config.APP.HOST + url,
	     		headers:headers,
	     		data:JSON.stringify(params)
	     	});
			return promise;
		};
		var find = function(url,params,method,headers){
			return restAjax(url,params,headers,method);
		};
		
		var get = function(url,params,headers){
			return restAjax(url,params,headers,'GET');
		};

		var create = function(url,params,headers){
			return restAjax(url,params,headers,'POST');
		};

		var update = function(url,params,headers){
			return restAjax(url,params,headers,'PUT');
		};

		var remove = function(url,params,headers){
			return restAjax(url,params,headers,'DELETE');
		};
		var echo = function(){
			console.log('echo service');
		};
		return {
			create:create,
			update:update,
			remove:remove,
			find:find,
			get:get,
			restAjax:restAjax,
			echo:echo
		};
	};
	restService.$inject = injectParams;

	app.factory('restService', restService);
	
});
