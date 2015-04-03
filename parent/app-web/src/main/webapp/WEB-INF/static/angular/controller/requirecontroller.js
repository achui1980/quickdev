define(['static/js/app'],function(app){
	var injectParams = ['$scope','config','restService','$routeParams'];
	var ctrl = function($scope,config,restService,$routeParams){
		console.log($routeParams.domain);
		var domain = $routeParams.domain;
		var url = '/dbinfo/'+domain;
		restService.get(url)
			.then(function(result){
				console.log(result.data.data);
				var metaInfoMap = result.data.data.columnInfoMap;
				var cols = [], searchs = [];
				for(var column in metaInfoMap){
					var metaInfo = metaInfoMap[column];
					if(metaInfo.display){
						var col = {
							id: column,
							header:column,
							editor:'text',
							width:100,
						};
						cols.push(col);
					}
					if(metaInfo.searchable){
						var search = {
							view:'text',
							name:column,
							op:metaInfo.op,
							enableSearch:true,
							placeholder:column
						};
						searchs.push(search);
					};
				};
				$scope.config = {
			    		view:'gridex',
			    		domain:domain,
			    		searchFields:searchs,
			    		grid:{
			    			columns : cols,
			    			editable:true,
			    			select:"row",
			    			multiselect:true,
			    			editaction:'dblclick',
			    			autowidth:false,
			    			url:"querypost->"+config.APP.HOST+"/"+result.data.data.serviceName+"/list",
			    			save:{
			    				url:"jsonrest->"+config.APP.HOST+"/"+result.data.data.serviceName+"/op/"+domain,
			    				autoupdate:false
			    			},
			    			on:{
			    				'onItemClick':function(){
			    					console.log('dddd');
			    				}
			    			}
			    		}
			    };
			});
	    //config object
	};
	 ctrl.$inject = injectParams;

	 app.register.controller('RequireCtrl', ctrl);
});