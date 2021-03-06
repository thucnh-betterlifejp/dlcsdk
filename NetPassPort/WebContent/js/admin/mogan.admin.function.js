Ext.namespace("Mogan.admin");

// 設定狀態訊息
function setStatusMsg(msg) {
	Ext.getCmp("statusMsg").setText(msg);
}

/**/
Mogan.admin.reloadModels = function() {
	setStatusMsg("Reload Models Start.");
	Ext.Ajax.request({
				url : 'AjaxPortal',
				callback : function() {
				},
				success : function(response) {
					var json = parserJSON(response.responseText);
					Ext.getCmp("gridPanelModel").store.loadData(json);
					Ext.Msg.alert("Message","讀取完成");
					setStatusMsg("LoadModelData success.");
				},
				failure : function(response) {
					setStatusMsg("LoadModelData failure.");
				},
				params : {
					APP_ID : "fccc13447039e0ebf289e4227bc8e9e6",
					ACTION : "RELOAD_MODELS",
					MODEL_NAME : "ModelService",
					RETURN_TYPE : "JSON"
				}
			});
}

/*
 * 讀取Model資料
 */
Mogan.admin.loadModelData = function() {
	setStatusMsg("LoadModelData Start.");
	Ext.Ajax.request({
				url : 'AjaxPortal',
				callback : function() {
				},
				success : function(response) {
					var json = parserJSON(response.responseText);
					Ext.getCmp("gridPanelModel").store.loadData(json);
					setStatusMsg("LoadModelData success.");
				},
				failure : function(response) {
					setStatusMsg("LoadModelData failure.");
				},
				params : {
					APP_ID : "fccc13447039e0ebf289e4227bc8e9e6",
					ACTION : "LOAD_MODEL_DATA",
					MODEL_NAME : "ModelService",
					RETURN_TYPE : "JSON"
				}
			});

}