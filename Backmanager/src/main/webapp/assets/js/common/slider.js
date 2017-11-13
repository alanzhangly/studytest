var Slider = new Object();
Slider.createMenu = function(arr,wrap) {
	var oWrap = jQuery(wrap);
 	var oUrl;
	for (var i = 0; i < arr.length; i++) {
		var oLi = jQuery('<li menuId="'+arr[i].id+'" parentId="'+arr[i].parentId+'"></li>');
    	oUrl = arr[i].url || 'javascript:;';
    	oUrl = oUrl=="/"?'javascript:;':oUrl;
    	if (arr[i].hasChild==1) {
    		oLi.addClass("has-sub");
			jQuery(oLi).append('<a href="' + oUrl + '"><b class="caret pull-right"></b><i class="fa fa-th"></i><span>' + arr[i].name + '</span></a>');
        	//jQuery(oLi).append('<a href="' + oUrl + '"><span class="title" name="menuTitle">' + arr[i].name + '</span><span class="arrow"></span></a>');
			Slider.createSubMenu(arr[i].children, oLi);
    	} else {
    		jQuery(oLi).append('<a href="' + ctx + oUrl + '"><i class="fa fa-signal"></i> <span>' + arr[i].name + '</span></a>');
    		//jQuery(oLi).append('<a href="' + ctx + arr[i].url + '"><span class="title" id="menuTitle">' + arr[i].name + '</span></a>');
      	};//'/' + 
     	jQuery(oWrap).after(oLi);
  	}
};
//构建子菜单函数
Slider.createSubMenu = function(sArr, oLi) {
	var self = arguments.callee;
	var sUl = jQuery('<ul class="sub-menu"></ul>');
	var sUrl;
	for (var j = (sArr.length-1); j >=0 ; j--) {
		var sLi = jQuery('<li menuId="'+sArr[j].id+'" parentId="'+sArr[j].parentId+'"></li>');
       	sUrl = sArr[j].url || 'javascript:;';
       	sUrl = sUrl=="/"?'javascript:;':sUrl;
     	if (sArr[j].hasChild==1) {
     		sLi.addClass("has-sub");
     		jQuery(sLi).append('<a href="' + sUrl + '"><b class="caret pull-right"></b>' + sArr[j].name + '</a>');
           	//jQuery(sLi).append('<a href="' + sUrl + '">' + sArr[j].name + '</span><span class="arrow"></span></a>');
         	self(sArr[j].children, sLi);
      	} else {//'/' + 
           	jQuery(sLi).append('<a href="' + ctx + sUrl + '">' + sArr[j].name + '</a>');
       	}
      	jQuery(sUl).append(sLi);
   	}
	jQuery(oLi).append(sUl);
};
