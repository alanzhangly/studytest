var CommonTree = new Object();
CommonTree.selectId = "";
CommonTree.selectText = "";
CommonTree.selectParentId = "";
CommonTree.topUlId = "";
CommonTree.type = "";
CommonTree.doClick = function(param){
	//alert(param);
};
CommonTree.createMenu = function(arr,wrap,param) {
	CommonTree.topUlId = wrap;
	var oWrap = jQuery("#"+wrap);
	for (var i = 0; i < arr.length; i++) {
		var oLi = jQuery('<li></li>');
    	oUrl = arr[i].url || 'javascript:void(0)';
    	if (arr[i].hasChild==1) {
        	jQuery(oLi).append('<a menuId="'+arr[i].id+'" parentId="'+arr[i].parentId+'" type="'+arr[i].type+'" class="tree-toggle closed" data-value="Bootstrap_Tree" data-toggle="branch" href="javascript:void(0);"><i class="tree-folder"></i>'+arr[i].name+'</a>');
        	CommonTree.createSubMenu(arr[i].children, oLi,param);
    	} else {
    		jQuery(oLi).append('<a menuId="'+arr[i].id+'" parentId="'+arr[i].parentId+'" type="'+arr[i].type+'" data-role="leaf" href="javascript:void(0);"><i class="tree-folder tree-menu"></i>'+arr[i].name+'</a>');
      	};
      	jQuery(oLi).children("a").eq(0).bind("click",function(){
      		CommonTree.selectId = jQuery(this).attr("menuId");
      		CommonTree.selectParentId = jQuery(this).attr("parentId");
      		CommonTree.type = jQuery(this).attr("type");
      		CommonTree.doClick(param);
      		
//      		jQuery("#"+CommonTree.topUlId).find("a").each(function(){
//      			if (jQuery(this).hasClass("tree-selected")) {
//      				jQuery(this).removeClass("tree-selected");
//      			}
//      		});
//      		jQuery(this).addClass("tree-selected");
      	});
     	jQuery(oWrap).append(oLi);
  	}
};
//构建子菜单函数
CommonTree.createSubMenu = function(sArr, oLi,param) {
	var self = arguments.callee;
	var sUl = jQuery('<ul class="branch"></ul>');
	for (var j = 0; j < sArr.length; j++) {
		var sLi = jQuery('<li></li>');
       	sUrl = sArr[j].url || 'javascript:void(0)';
     	if (sArr[j].hasChild==1) {
           	jQuery(sLi).append('<a menuId="'+sArr[j].id+'" parentId="'+sArr[j].parentId+'" type="'+sArr[j].type+'" class="tree-toggle closed" data-value="Bootstrap_Tree" data-toggle="branch" href="javascript:void(0);"><i class="tree-folder"></i>'+sArr[j].name+'</a>');
         	self(sArr[j].children, sLi);
      	} else {
           	jQuery(sLi).append('<a menuId="'+sArr[j].id+'" parentId="'+sArr[j].parentId+'" type="'+sArr[j].type+'" data-role="leaf" href="javascript:void(0);"><i class="tree-folder tree-menu"></i>'+sArr[j].name+'</a>');
       	}
     	jQuery(sLi).children("a").eq(0).bind("click",function(){
     		CommonTree.selectId = jQuery(this).attr("menuId");
     		CommonTree.selectParentId = jQuery(this).attr("parentId");
     		CommonTree.type = jQuery(this).attr("type");
     		CommonTree.doClick(param);
//      		jQuery("#"+CommonTree.topUlId).find("a").each(function(){
//      			if (jQuery(this).hasClass("tree-selected")) {
//      				jQuery(this).removeClass("tree-selected");
//      			}
//      		});
//      		jQuery(this).addClass("tree-selected");
      	});
      	jQuery(sUl).append(sLi);
   	}
	jQuery(oLi).append(sUl);
};
