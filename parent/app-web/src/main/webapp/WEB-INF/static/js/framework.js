webix.ready(function(){
	webix.ui({
		id:'framework',
		rows:[
		   {template:"row 1", height:50},
		   {
			   cols:[
			      {template:"col 1", width:150},
			      {
                      view:"resizer"
                  },
			      {template:"col 2"},
			   ]
		   },
		   {template:"row 3", height:40 },
		]
	});
});