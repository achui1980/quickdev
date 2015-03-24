define(['app'],function(app){
	app.controller('webixCtrl',function($scope){
	    //config object
	    $scope.config = {
	    		view:'gridex',
	    		domain:'SysUser',
	    		grid:{
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
	    			url:"querypost->"+$ctx+"/rest/service/userService/list",
	    			save:{
	    				url:"jsonrest->"+$ctx+"/rest/service/userService/op/SysUser",
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
});