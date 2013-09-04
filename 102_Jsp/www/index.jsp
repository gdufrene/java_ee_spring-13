<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Locale,java.util.Date,java.text.SimpleDateFormat"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
<% SimpleDateFormat f = new SimpleDateFormat( "EEEEE d MMMMM y - kk:mm", Locale.FRANCE ); %>
<%= f.format( new Date() ) %>
    </body>
</html>