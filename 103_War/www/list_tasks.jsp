<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="
fr.eservice.todo.Task,
java.text.DateFormat,
java.text.SimpleDateFormat,
java.util.Date
"
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ma liste de choses à faire</title>
</head>
<style>
li {
  border-left: solid 2px #CCC;
  list-style-type: none;
  padding-left: 10px;
  background-color: #EEE;
  max-width: 500px;
  margin-bottom: 10px;
}
.taskList {
  position: absolute;
  left: 300px;
  width: 50%;
}
.deltask {
  float: left;
}
.completetask {
  float: right;
}
li.complete {
  background-color: #EFE;
}
.addTask {
  position: absolute;
  width: 250px;
  overflow: hidden;
}
</style>
<body>
<h1>Todo liste</h1>
<%
Task[] tasks = (Task[]) request.getAttribute("tasks");

if ( tasks == null || tasks.length == 0 ) { %>
<i>Aucune tâche n'est enregistrée</i>.<br/>
<% } else { %>

<%-- 
  /*--------------- Liste des tâches -----------------*/
--%>
<div class="taskList">
<b>Liste des tâches</b>
<ul>
<% 
DateFormat df = (DateFormat) request.getAttribute("dateFormatter");
if ( df == null ) df = new SimpleDateFormat();
for( Task t : tasks ) { 
%>
  <li class="<%= (t.hasBeenCompleted() ? "complete" : "todo") %>">
    <b><%= t.getTitle() %></b><br/>
    <p><%= t.getDescription() %></p>
    <% if (t.getTargetDate() != 0L) { %>
      A terminer avant le : <%= df.format( new Date(t.getTargetDate()*1000L) ) %>
    <% } %><br/>
    <div class="deltask">
      [ <a href="?action=DELTASK&tid=<%= t.getReference() %>"> Effacer </a> ]
    </div>
    <% if ( !t.hasBeenCompleted() ) { %>
    <div class="completetask">
      [ <a href="?action=COMPLETETASK&tid=<%= t.getReference() %>"> Noter comme terminée </a> ] 
    </div>
    <% } %>
    <div style="clear: both"></div>
  </li>
<% 
} // -- /for tasks/ --
%>
</ul>
</div>

<% } %>

<%-- 
  /*--------------- Formulaire d'ajout d'une tâche -----------------*/
--%>
<div class="addTask">
<form name="TaskForm" method="POST">
  <input type="hidden" name="action" value="addtask">
  
  <p>
  Titre :<br/>
  <input type="text" name="title" value=""><br/>
  </p>
  
  <p>
  Description :<br/>
  <textarea name="description"></textarea>
  </p>
  
  <p>
  Date Limite de réalisation :<br/>
  <input type="text" name="target" value=""></input>
  </p>
  
  <input type="submit" value="Créer la tâche"/>
</form>
</div>

</body>
</html>