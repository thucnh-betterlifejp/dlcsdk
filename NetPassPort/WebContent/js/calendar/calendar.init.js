/*
 * File: xds_index.js Date: Fri Jul 16 2010 12:08:02 GMT+0800 (¥x¥_¼Ð·Ç®É¶¡)
 * 
 * This file was generated by Ext Designer version xds-1.0.2.2.
 * http://www.extjs.com/products/designer/
 * 
 * This file will be auto-generated each and everytime you export.
 * 
 * Do NOT hand edit this file.
 */
var appId = "26b782eb04abbd54efba0dcf854b158d";

Ext.onReady(function() {

			var myViewportUi = new MyViewportUi({
						renderTo : Ext.getBody()
					});
			myViewportUi.show();
			Ext.getCmp('BidList_unreadList').on('rowdblclick',
					function(grid, rowIndex, e) {
						var id=Ext.getCmp('BidList_unreadList').getStore().getAt(rowIndex).get("item_order_id");
						window.open("./iteminfo.jsp?item_order_id="+id);
					});
			Ext.getCmp('BidList_readList').on('rowdblclick',
					function(grid, rowIndex, e) {
						Ext.getCmp('BidList_readList').getStore().getAt(rowIndex).get("item_order_id");
						window.open("./iteminfo.jsp?item_order_id="+id);
					});
			initData(Ext.util.Format.date(new Date(), "Y-m"));
			Ext.getCmp('calendarPanel').setDate(new Date());
			
		});