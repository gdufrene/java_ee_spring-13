<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="fr.eservice.todo.Task"
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ma liste de choses à faire</title>
</head>
<body>
<h1>Todo liste</h1>
<%
Task[] tasks = req.getAttribute("tasks");

if ( tasks == null || tasks.length == 0 ) { %>
Aucune tâche n'est enregistré.
<% } else { %>

<%-- 
  /*--------------- Liste des tâches -----------------*/
--%>
<b>TODO: Lister les tâches</b>

<pre>
  "Titre de la tache" à réaliser avant le "xx/xx/xxxx"
  "Description de la tache"
  ...
  <a href="xxx">Cliquer ici</a> pour noter cette tâche terminée.
  <a href="xxx">Cliquer ici</a> pour supprimer cette tâche terminée.
</pre>

<% } %>

<%-- 
  /*--------------- Formulaire d'ajout d'une tâche -----------------*/
--%>
<form name="" method="">
  <input type="hidden" name="action" value="">
  
  Titre :<br/>
  <input type="" name="" value="">
  
  Description :
  <textarea name=""></textarea>
  
  Date Limite de réalisation :
  <input type="" name="" value=""></input>
  
</form>

</body>
</html>