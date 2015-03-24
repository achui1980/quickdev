webix.protoUI({
	name:'gridex',
	defaults:{
		tbarBtnWidth:75,
		useDefaultBtn:true,
		tbarControls:[],
		searchFields:[],
		//if grid are in edit mode,this value should be set false
		usePreLoad:false,
		/*
		 * grid:edit in grid, form:edit in popup window.
		 * */
		editMode:"form"
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
		this._searchbar = new webix.ui.searchbar({
			grid:self.config.id,
			domain:self.config.domain,
			cols:self.config.searchFields,
			elementsConfig:{
				width:130
			}
		});
		this._initGrid();
		this._initTbar(this._grid);
		_layout.addView(this._tbar);
		if(this.config.searchFields.length > 0)
			_layout.addView(this._searchbar);
		_layout.addView(this._grid);
		_layout.addView(this._pager);
		_layout.adjust();
		var dp = webix.dp(this._grid);
		dp.attachEvent('onAfterSync', function(id, text, data,loader){ 
			if(self._searchbar)
				self._searchbar.search();
			else{
				self.reload();
			}
			dp.reset();
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
		var pagerCfg = {master:false, size: 8, group: 10};
		if(this.config.pager){
			webix.extend(pageCfg,this.config.pager);
		}
		this._pager = new webix.ui.pager(pagerCfg);
	},
	_initGrid:function(){
		var gridCfg = {
			pager:this._pager.config.id,
			params:this._searchbar.getSearchObj(),
			//loadahead:16,
			datafetch:this._pager.config.size
		};
		if(this.config.usePreLoad){
			gridCfg["loadahead"] = this._pager.config.size * 2;
		}
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
		if(this.config.editMode == "grid")
			this._grid.add(obj);
		else if(this.config.editMode == 'form'){
			this._showWin(this._grid);
		}
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
		if(this.config.editMode == 'form'){
			this._showWin(this._grid,true);
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
		//this._searchbar.search();
		var dp = webix.dp(this._grid);
		dp.send();
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
		this._grid.refresh();
	},
	_initEditForm:function(grid){
		var columns = grid.config.columns;
		var self = this;
		var form = {
			view:'form',
			id:webix.uid(),
			elements:[],
			scroll:true,
			elementsConfig:{
				labelPosition:"top"
			}
		};
		for(var i=0; i<columns.length; i++){
			form.elements.push({
				view:"text",
				name:columns[i].id,
				label:columns[i].header[0].text
			});
		}
		var submitFunc = function(){
			var parentForm = this.getFormView();
			console.log(parentForm.getValues());
			var obj = parentForm.getValues();
			if(self.callEvent("onBeforSubmit",[obj,self])){
				if(!self._isUpdate)
					grid.add(obj,0);
				else{
					var sel = grid.getSelectedId();
					grid.updateItem(sel.row,obj);
				}
				self.onDefaultSave();
			}
		};
		form.elements.push({ view:"button", value: "Submit",click:submitFunc});
		return form;
	},
	_showWin:function(grid,update){
		this._isUpdate = update;
		var form = this._initEditForm(grid);
		var win = webix.ui({
			view:"window",
			height:500,width:400,
			position:'center',
			move:true,
		    head:{
				view:"toolbar", margin:-4, cols:[
				    {view:'label',label:'Update'},
					{ view:"icon", icon:"times-circle", css:"alter",
						click:function(){win.close();}}
				]
			},
			body:webix.copy(form)
		});
		win.show();
		if(update){
			$$(form.id).bind(grid);
		}
	}
},webix.ui.view,webix.EventSystem);



webix.proxy.jsonrest = {
	$proxy:true,
    saveAll:function(view, updates, dp, callback){
    	callback.error = this.processError;
    	var url = this.source;
		url += url.charAt(url.length-1) == "/" ? "" : "/";
		var data = {};
		var ids = [];
		for (var i = 0; i < updates.length; i++) {
			var action = updates[i];
			ids.push(action.id);
			var mode = action.operation;
			if(mode == 'insert') {
				delete action.data.id;
				delete action.data.password;
			}
			if(data[mode]){
				data[mode].push(action.data);
			}else{
				data[mode] = [action.data];
			}
		}
		for(o in data){
			if(o == "update"){
				webix.ajax().header({
					'Content-Type':'application/json'
				}).put(url, JSON.stringify(data[o]), callback);
			} 
			if(o == "delete") {
				webix.ajax().header({
					'Content-Type':'application/json'
				}).del(url, JSON.stringify(data[o]), callback);
			}
			if(o == "insert") {
				webix.ajax().header({
					'Content-Type':'application/json'
				}).post(url, JSON.stringify(data[o]), callback);
			}
		}
    },
    processError:function(text, data, loader){
    	var response = data.json();
		if (response.hasErrors === true){
			webix.confirm({
				text:response.errorMessage,
				title:'Error Message',
				type:"alert-error",
				ok:'see detail',
				callback:function(result){
					if(!result) return;
					webix.alert({
						title:"Detail",
						text: response.detailErrorMessage,
						type:"alert-error",
						width:600
					});
				}
			});
		}
    },
    result:function(state, view, dp, text, data, loader){
		data = data.json();
		//return dp._processError(null, text, data, loader);
	}
};
webix.proxy.querypost = {
	$proxy:true,
	load:function(view, callback,details){
		var param = null;
		if(view.config.params){
			param = view.config.params;
		}
		if(view.params){
			param = view.params;
		}
		param = param || {};
		if(!param["page"]){
			param["page"] = 0;
		}
		if(details){
			param["page"] = view.getPage();
		}
		param["pageCount"] = view.getPager().config.size;
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
		var self = this;
		var iconCfg = {view:"icon", icon:"search" ,width:30};
		var icon = new webix.ui.icon(iconCfg);
		icon.attachEvent('onItemClick',function(){
			self.search();
		});
		this.addView(icon);
	},
	getSearchObj:function(){
		var searchObj = {
			domain:this.config.domain,
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
		gridEx.reload();
	}
},webix.ui.toolbar);

webix.UIManager.addHotKey("enter", function(view){
  if(view.getParentView().name == 'searchbar'){
	  var searchBar = view.getParentView();
	  searchBar.search();
	  console.log('heloop:%o',view);
  }else{
	  console.log('no:%o',view);
  }
}, "text");

webix.debug = false;
