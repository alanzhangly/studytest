var Permission = new function() {
	return {
		perms : new Array(),
		createPermissionElement : function(permission, element) {
			var hasExist = false;
			for (var i = 0; i < this.perms.length; i++) {
				if (this.perms[i].perm == permission) {
					hasExist = true;
				}
			}
			// 不存在权限时访问后台
			if (!hasExist) {
				$.ajax({
					"type" : "GET",
					async : false,
					"url" : ctx + "/checkPermission.json?permission="+permission+"&now=" + new Date().getTime(),
					"success" : function(data) {
						if (data.code == "1") {
							Permission.perms.push({
								perm : permission,
								hasPerm : true
							});
						} else {
							Permission.perms.push({
								perm : permission,
								hasPerm : false
							});
						}
					},
					"error" : function(data) {
						//
					}
				});
			}
			var hasThisPerm = false;
			for (var i = 0; i < this.perms.length; i++) {
				if (this.perms[i].perm == permission && this.perms[i].hasPerm == true) {
					hasThisPerm = true;
				}
			}
			if (hasThisPerm) {
				return element;
			} else {
				return "";
			}
		}

	};
}();