<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日曆</title>
<script type="text/javascript" >
<%
if (session.getAttribute("USER_ID")==null){
	System.out.println("session.isNew()..."+session.isNew());
%>
	location='nonPrivilege.jsp';
<%
}
%>
</script>
<link rel="stylesheet" type="text/css" href="resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="resources/mogan/mogan.css" />
<link rel="stylesheet" type="text/css" href="resources/mogan/grid.css" />

<script type="text/javascript" src="js/ext-base.js"></script>
<script type="text/javascript" src="js/ext-all.js"></script>
<script type="text/javascript" src="js/ext-lang-zh_TW.js"></script>
<script type="text/javascript" src="js/ext-mogan.js"></script>


<script type="text/javascript"	src="js/calendar/calendar.fucntion.js"></script>
<script type="text/javascript"	src="js/calendar/calendar.ui.js"></script>
<script type="text/javascript"	src="js/calendar/calendar.store.js"></script>

<script type="text/javascript"	src="js/calendar/calendar.init.js"></script>

</head>
<body>

</body>
</html>