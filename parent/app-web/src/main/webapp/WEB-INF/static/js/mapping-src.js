var app = app || {};
app.mapping = app.mapping || {};
app.mapping.src = app.mapping.src || {};
app.mapping.mid = app.mapping.mid || {};
app.mapping.dist = app.mapping.dist || {};


app.mapping.src = (function(){
	var setting = {
		async: {
			enable: true,
			url:$ctx+"/rest/hello/xml2tree",
			type:'get'
		},
		edit: {
			drag: {
				isCopy:true,
				isMove:false
			},
			enable: true,
			showRemoveBtn: true,
			showRenameBtn: false
		},
		data: {
			simpleData: {
				enable: false
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop,
			onClick:onClick
		},
		view: {
			fontCss: getFontCss
		}
	};
	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		return targetNode ? targetNode.drop !== false : true;
	}
	function onClick(event, treeId, treeNode){
		console.log('%o,%o',treeId,treeNode);
		if(treeId !== 'app-tree') return;
		$('#nodeName').html(treeNode.name);
		var znode = [];
		if(!$.isEmptyObject(treeNode.attrs)){
			for(var o in treeNode.attrs){
				var node = {};
				node['name'] = o +'-'+ treeNode.attrs[o];
				node['leaf'] = true;
				znode.push(node);
			}
		}
		$.fn.zTree.init($("#app-attr"), {},znode);
	}
	var nodeList = [];
	var searchFn = function(treeId,search){
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		if(zTree){
			//zTree.expandAll(false);
			updateNodes(treeId,false);
			nodeList = zTree.getNodesByFilter(_searchFilter,false,null,{name:search});
			nodeList.forEach(function(node){
				node.highlight = true;
				if(node.isParent !== true){
					zTree.expandNode(node.getParentNode(),true,true,true);
				}else{
					zTree.expandNode(node,true,true,true);
				}
				zTree.updateNode(node);
			});
		}
	};
	function getFontCss(treeId, treeNode) {
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
	function updateNodes(treeId,highlight) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		for( var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = highlight;
			zTree.updateNode(nodeList[i]);
		}
	}
	var _searchFilter = function(node,invokeParam){
		var name = node.name;
		var names = name.split(' - ');
		if(names && names.length > 0){
			 name = names[0];
		}
		var searchText = invokeParam.name;
		return searchText == name? true:false;
	};
	return {
		initApp : function(id){
			$("#search").on('click',function(){
				var search = $('#searchInput').val();
				if(search != ''){
					searchFn('app-tree',search);
				}
			});
			$(".expand-tree").on('click',function(){
				var treeId = $(this).attr('ref');
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				zTree.expandAll(true);
			});
			$(".collapse-tree").on('click',function(){
				var treeId = $(this).attr('ref');
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				zTree.expandAll(false);
			});
			$.fn.zTree.init($("#"+id), setting);
		}
	};
})();

$(document).ready(function () {
	app.mapping.src.initApp('app-tree');
});