var app = app || {};
app.mapping = app.mapping || {};
app.mapping.src = app.mapping.src || {};
app.mapping.mid = app.mapping.mid || {};
app.mapping.dist = app.mapping.dist || {};


app.mapping.dist = (function(){
	var setting = {
//		async: {
//			enable: true,
//			url:$ctx+"/rest/hello/xml2tree",
//			type:'get'
//		},
		edit: {
			drag: {
				isCopy:true,
				isMove:true
			},
			enable: true,
			showRemoveBtn: true,
			showRenameBtn: true
		},
		data: {
			simpleData: {
				enable: false
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop,
			onRightClick: OnRightClick
		}
	};
	var addCount = 0,zTree = null;
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
	function OnRightClick(event, treeId, treeNode) {
		zTree.selectNode(treeNode);
	}
	function addNode(event,selector){
		var newNode = { name:"增加" + (addCount++)};
		if (zTree.getSelectedNodes()[0]) {
			zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
		} else {
			zTree.addNodes(null, newNode);
		}
	}
	function delNode(){
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length>0) {
			if (nodes[0].children && nodes[0].children.length > 0) {
				var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
				if (confirm(msg)==true){
					zTree.removeNode(nodes[0]);
				}
			} else {
				zTree.removeNode(nodes[0]);
			}
		}
	};
	var menu = {
		id:'tree-menu',
		data:[ {
            icon: 'glyphicon-plus',
            text: 'Create',
            action: addNode
        },{
            icon: 'glyphicon-trash',
            text: 'Delete',
            action: delNode
        }]
	};
	return {
		initApp : function(id){
			$.fn.zTree.init($("#"+id), setting);
			zTree = $.fn.zTree.getZTreeObj(id);
			context.init({preventDoubleContext: false});
			context.attach("#"+id, menu);
		}
	};
})();

$(document).ready(function () {
	app.mapping.dist.initApp('dist-tree');
});