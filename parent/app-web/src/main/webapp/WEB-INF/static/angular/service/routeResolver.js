'use strict';

define([],function(){
	var routeResolver = function(){
		this.$get = function(){
			return this;
		};
		
		this.routeConfig = function(){
			var viewsDirectory = 'static/angular/view/',
			controllersDirectory = 'static/angular/controller/',
				
			setBaseDir = function(viewDir,controllerDir){
				viewsDirectory = viewDir;
				controllersDirectory = controllerDir;
			},
			
			getViewsDir = function(){
				return viewsDirectory;
			},
			
			getControllersDir = function(){
				return controllersDirectory;
			};
			
			return {
				setBaseDir : setBaseDir,
				getViewsDir: getViewsDir,
				getControllersDir:getControllersDir
			};
			
		}();
		
		this.route = function(routeConfig){
			var resolve = function(baseName,path,contollerAs,secure,fileExtension){
				if(!path) path = '';
				if(!fileExtension) fileExtension = 'html';
				var routeDef = {};
				var baseFileName = baseName.charAt(0).toLowerCase() + baseName.substr(1);
				routeDef.controller = baseName + 'Ctrl';
				routeDef.templateUrl = routeConfig.getViewsDir() + path + baseFileName + '.' + fileExtension;
				if(contollerAs) routeDef.controllerAs = contollerAs;
				routeDef.secure = (secure) ? secure : false;
				routeDef.resolve = {
						load:['$q', '$rootScope', function ($q, $rootScope) {
	                        var dependencies = [routeConfig.getControllersDir() + path + baseFileName + 'controller.js'];
	                        return resolveDependencies($q, $rootScope, dependencies);
	                    }]
				};
				return routeDef;
				
			},
			resolveDependencies = function ($q, $rootScope, dependencies){
				var defer = $q.defer();
				require(dependencies, function () {
                    defer.resolve();
                    $rootScope.$apply();
                });
				return defer.promise;
			};
			return {
				resolve : resolve
			};
		}(this.routeConfig);
	};
	 var servicesApp = angular.module('routeResolverServices', []);

    //Must be a provider since it will be injected into module.config()    
    servicesApp.provider('routeResolver', routeResolver);
});