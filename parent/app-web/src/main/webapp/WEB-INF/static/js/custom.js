webix.protoUI({
	name:'gridex',
	defaults:{
		tbarBtnWidth:75,
		useDefaultBtn:true,
		tbarControls:[]
	},
	$init:function(config){
		this.$ready.push(this._init_gridex);
	},
	_init_gridex:function(){
		webix.extend(this.config,this.defaults);
		var layout = {view:'layout',type:'line',
			rows:[]
		};
		var self =  this;
		var _layout = webix.ui(layout);
		this._initPager();
		this._initGrid();
		this._initTbar(this._grid);
		this._searchbar = new webix.ui.searchbar({
			grid:self.config.id,
			domain:self.config.domain,
			cols:[
			   {view:'text',name:'username',op:"like",enableSearch:true,placeholder:'username'},
			],
			elementsConfig:{
				width:130
			}
		});
		this._grid.params = this._searchbar.getSearchObj();
		_layout.addView(this._tbar);
		_layout.addView(this._searchbar);
		_layout.addView(this._grid);
		_layout.addView(this._pager);
		_layout.adjust();
		var dp = webix.dp(this._grid);
		dp.attachEvent('onAfterSync', function(id, text, data,loader){ 
			if(self._searchbar)
				self._searchbar.search();
			else
				self.reload();
		});
	},
	_initTbar:function(grid){
	
		if(this.config.tbar){
			this._tbar = new webix.ui.toolbar(this.config.tbar);
		}else{
			this._tbar = new webix.ui.toolbar({
				cols:[]
			});
		}
		var tbar = this._tbar;
		var self = this;
		//re-index button position
		webix.toArray(this._tbar.getChildViews()).each(function(view){
			self.config.tbarControls.push(view.config);
			self._tbar.removeView(view.config.id);
		});
		var buttons = [
			{view:'button',label:'Add',eventname:'Add',width:this.config.tbarBtnWidth},
			{view:'button',label:'Mod',eventname:'Mod',width:this.config.tbarBtnWidth},
			{view:'button',label:'Del',eventname:'Del',width:this.config.tbarBtnWidth},
			{view:'button',label:'Save',eventname:'Save',width:this.config.tbarBtnWidth},
		];
		if(this.config.useDefaultBtn){
			buttons = buttons.concat(this.config.tbarControls);
		}else{
			buttons = this.config.tbarControls;
		}
		webix.toArray(buttons).each(function(btnCfg){
			var button = new webix.ui.button(btnCfg);
			var eventName = button.config.eventname;
			//add default event for default button
			if(eventName && !self.hasEvent('on'+eventName)){
				try{
					self.attachEvent('on'+eventName,webix.toFunctor('onDefault'+eventName,self));
				}catch(e){
					console.log(e);
				}
			}
			button.attachEvent('onItemClick',function(){
				if(self.callEvent('onBefore'+eventName,[grid])){
					self.callEvent('on'+eventName,[grid]);
				}
			});
			tbar.addView(button);
		});
		delete buttons;
	},
	_initPager:function(){
		var pagerCfg = {master:false, size: 15, group: 10};
		if(this.config.pager){
			webix.extend(pageCfg,this.config.pager);
		}
		this._pager = new webix.ui.pager(pagerCfg);
	},
	_initGrid:function(){
		var gridCfg = {
			pager:this._pager.config.id
		};
		if(!this.config.grid){
			webix.assert_error("missing grid attribute");
		}
		webix.extend(gridCfg,this.config.grid);
		this._grid = new webix.ui.datatable(gridCfg);
		this._grid.attachEvent('onBeforeLoad',function(){
			 this.showOverlay("Loading...");
		});
		this._grid.attachEvent('onAfterLoad',function(){
			this.hideOverlay();
		});
		
	},
	onDefaultAdd:function(grid){
		var obj = {
				"id":null,
			    "username":"achui",
			    "password":"1000",
			    "new":false
			};
		this._grid.add(obj);
		webix.message({
			text:'TODO:Add event,'+grid,
			expire:1000
		});
	},
	onDefaultMod:function(grid){
		var rows = this.getSelectedRows(grid);
		if(rows.length == 0){
			webix.alert({
				text: "Please select a record!",
				type:"alert-error"
			});
			return;
		}
		webix.message({
			text:'TODO:Mod event,'+rows[0],
			expire:1000
		});
	},
	onDefaultDel:function(grid){
		var rows = this.getSelectedRows(grid);
		if(rows.length == 0){
			webix.alert({
				text: "Please select at least one record!",
				type:"alert-error"
			});
			return;
		}
		grid.remove(grid.getSelectedId(true));
		webix.message({
			text:'TODO:Del event,'+grid,
			expire:1000
		});
	},
	onDefaultSave:function(){
		this._searchbar.search();
		//var dp = webix.dp(this._grid);
		//dp.send();
	},
	getSelectedRows:function(grid){
		var ids = webix.toArray(grid.getSelectedId(true));
		var rows = webix.toArray();
		ids.each(function(obj){
			rows.insertAt(grid.getItem(obj.id));
		});
		return rows;
	},
	getGrid:function(){
		return this._grid;
	},
	getTbar:function(){
		return this._tbar;
	},
	reload:function(){
		this._grid.clearAll();
		this._grid.load(this._grid.config.url);
	}
},webix.ui.view,webix.EventSystem);

webix.proxy.jsonrest = {
	init:function(){
        webix.extend(this, webix.proxy.rest);
    },
    save:function(view, update, dp, callback){
		var url = this.source;
		url += url.charAt(url.length-1) == "/" ? "" : "/";
		var mode = update.operation;

		var data = update.data;
		if (mode == "insert") delete data.id;

		//call rest URI
		if (mode == "update"){
			webix.ajax().header({
				'Content-Type':'application/json'
			}).put(url + data.id, JSON.stringify(data), callback);
		} else if (mode == "delete") {
			webix.ajax().header({
				'Content-Type':'application/json'
			}).del(url + data.id, JSON.stringify(data), callback);
		} else {
			webix.ajax().header({
				'Content-Type':'application/json'
			}).post(url, JSON.stringify(data), callback);
		}
	}
};
webix.proxy.querypost = {
	$proxy:true,
	load:function(view, callback){
		var param = view.params || {};
		webix.ajax().bind(view).header({
			'Content-Type':'application/json'
		}).post(this.source, JSON.stringify(param), callback);
	}
};

webix.protoUI({
	name:'searchbar',
	$init:function(config){
		this.$ready.push(this._init_search);
	},
	_init_search:function(){
		this.addView( {view:"icon", icon:"search" ,width:30});
	},
	getSearchObj:function(){
		var self = this;
		var searchObj = {
			domain:"SysUser",
			params:[]
		};
		webix.toArray(this.getChildViews()).each(function(view){
			var enableSearch = view.config.enableSearch;
			if(enableSearch){
				var name = view.config.name;
				var param = {};
				param["value"] = view.getValue();
				param["name"] = name;
				param["op"] = view.config.op;
				searchObj.params.push(param);
			}
		});
		return searchObj;
	},
	search:function(){
		var gridEx = webix.$$(this.config.grid);
		gridEx.getGrid().params = this.getSearchObj();
		console.log(this.getSearchObj());
		gridEx.reload();
	}
},webix.ui.toolbar);

