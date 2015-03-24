webix.ready(function(){
	webix.ui({
		id:'framework',
		type:'line',
		rows:[
		   {template:'header',height:50},
		   {
				multi:true,
				type:'line',
				cols:[
					{
						header:"Menu",
						width:250,
						body:{
							multi:false,
							type:'line',
							rows:[
								{ header:"menu1ws1", body:"content3 1"},
								{ header:"menu15", body:"content 5"}
							]
						}
					},
					{view:"resizer"},
					{body:'content'}
				]
		   },
		   {template:'footer',height:50},
		],
		
	});
});