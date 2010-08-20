/*
 * File: MyViewport.ui.js Date: Fri Jul 16 2010 12:08:02 GMT+0800 (¥x¥_¼Ð·Ç®É¶¡)
 * 
 * This file was generated by Ext Designer version xds-1.0.2.2.
 * http://www.extjs.com/products/designer/
 * 
 * This file will be auto-generated each and everytime you export.
 * 
 * Do NOT hand edit this file.
 */

MyViewportUi = Ext.extend(Ext.Viewport, {
			layout : 'border',
			initComponent : function() {
				this.items = [new ConditionForm(), {
							xtype : 'grid',
							id:'DataForm',
							title : 'DataForm',
							store : 'MyStore',
							region : 'center',
							columns : [{
										xtype : 'gridcolumn',
										dataIndex : 'item_order_id',
										header : '摩根得標編號',
										sortable : true,
										width : 100
									},{
										xtype : 'gridcolumn',
										dataIndex : 'agnet_account',
										header : '下標帳號',
										sortable : true,
										width : 100
									}, {
										xtype : 'gridcolumn',
										dataIndex : 'item_id',
										header : '日雅ID',
										sortable : true,
										width : 100
									}, {
										xtype : 'gridcolumn',
										dataIndex : 'item_name',
										header : '商品名稱',
										sortable : true,
										width : 100,
										align : 'right'
									},

									{
										xtype : 'gridcolumn',
										dataIndex : 'won_date',
										header : '得標日',
										sortable : true,
										width : 100
									}]
						}];
				MyViewportUi.superclass.initComponent.call(this);
			}
		});

ConditionForm = Ext.extend(Ext.form.FormPanel, {
			title : 'TopForm',
			region : 'north',
			height : 300,
			id : 'conditionForm',
			initComponent : function() {
				this.items = [{
							xtype : 'datefield',
							format : 'Y-m-d',
							name : 'START_DATE',
							fieldLabel : '開始日期'
						}, {
							xtype : 'datefield',
							format : 'Y-m-d',
							name : 'END_DATE',
							fieldLabel : '結束日期'
						}, {
							xtype : 'textfield',
							name : 'KEYWORD',
							fieldLabel : '其他條件'
						}];
				this.bbar = {
					xtype : 'toolbar',
					items : [{
								xtype : 'button',
								text : '搜尋',
								handler : query
							}]
				};
				ConditionForm.superclass.initComponent.call(this);
			}
		});
