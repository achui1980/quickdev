define(['static/angular/service/routeResolver'],function(){
	var app = angular.module('webixApp',['webix','ngRoute','routeResolverServices']);
	app.config(['$routeProvider', 'routeResolverProvider', '$controllerProvider',
                '$compileProvider', '$filterProvider', '$provide', '$httpProvider',
       function($routeProvider, routeResolverProvider, $controllerProvider,
               $compileProvider, $filterProvider, $provide, $httpProvider){
		 app.register =
         {
             controller: $controllerProvider.register,
             directive: $compileProvider.directive,
             filter: $filterProvider.register,
             factory: $provide.factory,
             service: $provide.service
         };
		 
		 var route = routeResolverProvider.route;
		 $routeProvider
		 	.when('/require',route.resolve('Require'));
		}         
    ]);
	return app;
});