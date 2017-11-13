var UITree = function () {
	var tree;

    var getPath = function(_inst, _node) {
        if (_node.parent=="#") {
        		
        } else {
        	var current = _inst.get_node(_inst.get_parent(_node));
        	UITree.path = current.text + ">" + UITree.path;
        	getPath(_inst, current);
        }
    };
    var handleSample = function (_treeId, _result, _flag) {
    	var checkbox = _flag?"checkbox":"";
        $('#'+_treeId).jstree({
            'plugins': ["wholerow", "types", checkbox],
            'core': {
                "themes" : {
                    "responsive": false
                },    
                'data': _result
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-warning icon-lg"
                }
            }
        }).bind("changed.jstree", function(e, data){
        	var inst = data.instance;
        	if (data.node) {
        		UITree.selectedId = [];
        		if (!_flag) {
        			UITree.childrenId = [];
        			var selectChildren = data.node.children;
        			for (var i=0;i<selectChildren.length;i++) {
            			if (selectChildren[i].indexOf(_treeId)==0) {
            				UITree.childrenId.push(selectChildren[i].substr(_treeId.length+1));
            			} else {
            				UITree.childrenId.push(selectChildren[i]);
            			}
            		}
        		}
        		if (inst.is_selected(data.node)) {
            		//选中
            		UITree.isSelected = true;
            	} else {
            		//取消选中
            		UITree.isSelected = false;
            	}
        		var temp = data.selected;
        		for (var i=0;i<temp.length;i++) {
        			if (temp[i].indexOf(_treeId)==0) {
        				UITree.selectedId.push(temp[i].substr(_treeId.length+1));
        			} else {
        				UITree.selectedId.push(temp[i]);
        			}
        		}
            	UITree.selectedText = data.node.text;
            	UITree.parentId = data.node.parent;
            	if (data.node.parent!="#") {
            		if (data.node.parent.indexOf(_treeId)==0) {
            			UITree.parentId = data.node.parent.substr(_treeId.length+1);
            		} else {
            			UITree.parentId = data.node.parent;
            		}
            	}
            	UITree.parentText = inst.get_node(inst.get_parent(data.node)).text;
            	UITree.path = data.node.text;
            	getPath(inst,data.node);
            	for (var i=0;i<UITree.dataArr.length;i++) {
            		var item = UITree.dataArr[i];
            		if (_treeId==item._treeId) {
            			item._clickfunc.call();
            			break;
            		}
            	}
        	}
        });
    };

    return {
        //main function to initiate the module
    	isSelected : false,
    	selectedId : [],
    	clickId : [],
    	childrenId : [],
    	selectedText : "",
    	parentId : "",
    	parentText : "",
    	path : "",
    	dataArr : [],
        init: function (_treeId, _result, _flag) {
            handleSample(_treeId, _result, _flag);
            UITree.setListener(_treeId);
        },
        setListener : function(_treeId) {
        	jQuery('#'+_treeId + "_expand").bind("click", function() {
        		jQuery('#'+_treeId).jstree("open_all");
        	});
        	jQuery('#'+_treeId + "_collapse").bind("click", function() {
        		jQuery('#'+_treeId).jstree("close_all");
        	});
        },
        click : function () {
        },
        initTree : function(_param) {
        	var data = {
        		_postUrl : _param.url,
                _checkBox : _param.checkbox,
                _clickfunc : _param.click,
                _treeId : _param.id,
            	_disable : _param.disable?_param.disable:false
        	};
        	var itemExist = false;
        	for (var i=0;i<UITree.dataArr.length;i++) {
        		var item = UITree.dataArr[i];
        		if (_param.id==item._treeId) {
        			itemExist = true;
        			break;
        		}
        	}
        	if (!itemExist) {
        		UITree.dataArr.push(data);
        	}
        	jQuery.ajax({
                type: "GET",
                async: false,
                url: _param.url,
                data : "treeId="+_param.id+"&disable="+_param.disable,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (result) {
                	UITree.init(_param.id, result, _param.checkbox);
    			   	UITree.click = function() {
    			   		_param.click.call();
    			   	};
                },
                error: function (err) {
                    alert(err);
                }
            });
        },
        refresh : function(_refreshId) {
        	var selected = jQuery('#'+_refreshId).jstree('get_selected');
        	for (var i=0;i<UITree.dataArr.length;i++) {
        		var item = UITree.dataArr[i];
        		if (item._treeId==_refreshId) {
        			var param = {};
                	param.url = item._postUrl;
                	param.checkbox = item._checkBox;
                	param.click = item._clickfunc;
                	param.id = _refreshId;
                	param._disable = item._disable;
                	jQuery("#"+_refreshId).jstree().destroy();
                	UITree.initTree(param);
                	jQuery('#'+_refreshId).jstree('select_node', selected);
        		}
        	}
        	
        },
        setSelected : function(_param) {
        	jQuery('#'+_param.treeId).jstree("deselect_all");
        	if (_param.ids) {
        		var idArr = _param.ids.split(",");
            	for (var i=0;i<idArr.length;i++) {
            		var node = jQuery('#'+_param.treeId).jstree('get_node', idArr[i]);
            		if (node.id) {
            			jQuery('#'+_param.treeId).jstree('select_node', idArr[i]);
            		} else {
            			jQuery('#'+_param.treeId).jstree('select_node', _param.treeId+"_"+idArr[i]);
            		}
            	}
        	}
        },
        getUndetermined : function(_treeId){
        	var result = [];
        	var selectedNodes = jQuery('#'+_treeId).jstree('get_selected');
        	for(var index in selectedNodes){
        		var parentIds = [];
        		var currentId = selectedNodes[index];
        		while(true){
        			var parentId = jQuery('#'+_treeId).jstree('get_parent',currentId);
        			if(parentId == "#"){
        				break;
        			}
        			parentIds.push(parentId);
        			currentId = parentId;
        		}
        		for(var i in parentIds){
        			var id = parentIds[i];
        			var node = jQuery('#'+_treeId).jstree('get_node',id);
//        			var undetermined = jQuery('#'+_treeId).jstree().is_undetermined(node)
        			if(node&&!node.state.selected){
        				if (id.indexOf(_treeId)==0) {
        					if(result.indexOf(id.substr(_treeId.length+1))==-1){
        						result.push(id.substr(_treeId.length+1));
        					}
                		} else {
                			if(result.indexOf(id)==-1){
                				result.push(id);
        					}
                		}
        			}
        		}
        	}
        	return result;
        },
        getSelected : function(_treeId) {
        	var selected = jQuery('#'+_treeId).jstree('get_selected');
        	var result = [];
        	for (var i=0;i<selected.length;i++) {
        		if (selected[i].indexOf(_treeId)==0) {
        			result.push(selected[i].substr(_treeId.length+1));
        		} else {
        			result.push(selected[i]);
        		}
        	}
        	return result;
        },
        getSelectedChildren:function(_treeId){
        	var selected = jQuery('#'+_treeId).jstree('get_selected');
        	var result = [];
        	for (var i=0;i<selected.length;i++) {
        		var node=jQuery('#'+_treeId).jstree('get_node', selected[i]);
        		if(node&&node.children&&node.children.length==0){
        			if (selected[i].indexOf(_treeId)==0) {
        				result.push(selected[i].substr(_treeId.length+1));
        			} else {
        				result.push(selected[i]);
        			}
        		}
        	}
        	return result;
        },
        getSelectedName : function(_treeId) {
        	var _selectedNames = "";
        	var _selectedIds = jQuery('#'+_treeId).jstree('get_selected');
        	if (_selectedIds && _selectedIds.length>0) {
        		for (var i=0;i<_selectedIds.length;i++) {
        			var tt = jQuery('#'+_treeId).jstree('get_node', _selectedIds[i]);
        			_selectedNames = _selectedNames + tt.text+",";
        		}
        		_selectedNames = _selectedNames.substr(0, _selectedNames.length-1);
        	}
        	return _selectedNames;
        },
        searchByName : function(_treeId,_condition) {
        	jQuery('#'+_treeId).jstree("open_all");
        	var _ids = "";
        	jQuery("#"+_treeId).find("li").each(function() {
        		var id = jQuery(this).attr("id");
        		var node = jQuery('#'+_treeId).jstree('get_node', id);
        		var liText = node.text;
        		if (liText.indexOf(_condition)>=0) {
        			if (id.indexOf(_treeId)==0) {
        				_ids = _ids + id.substr(_treeId.length+1) + ",";
        			} else {
        				_ids = _ids + id + ",";
        			}
        		}
        	});
        	if (_ids) {
        		_ids = _ids.substr(0,_ids.length-1);
        		var param = {
        			treeId : _treeId,
        			ids	   : _ids
        		};
        		UITree.setSelected(param);
        	} else {
        		BootstrapDialog.show({
    	            title: '信息',
    	            message: '未找到相关分类',
    	            buttons: [{
    	                label: '确定',
    	                action: function(dialog) {
    	                    dialog.close();
    	                }
    	            }]
    	        });
        	}
        	jQuery('#'+_treeId).jstree("close_all");
        	var _selectedIds = jQuery('#'+_treeId).jstree('get_selected');
        	if (_selectedIds && _selectedIds.length>0) {
        		for (var i=0;i<_selectedIds.length;i++) {
        			var node = jQuery('#'+_treeId).jstree('get_node', _selectedIds[i]);
        			if (node.type=="file" && node.parent!="#") {
        				jQuery('#'+_treeId).jstree('open_node', node.parent);
        			} else {
        				jQuery('#'+_treeId).jstree('open_node', _selectedIds[i]);
        			}
        		}
        	}
        }
    };

}();