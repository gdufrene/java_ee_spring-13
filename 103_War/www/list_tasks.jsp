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
Task[] tasks = (Task[]) request.getAttribute("tasks");

if ( tasks == null || tasks.length == 0 ) { %>
<i>Aucune tâche n'est enregistré</i>.<br/>
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
  
  <p>
  Titre :<br/>
  <input type="" name="" value=""><br/>
  </p>
  
  <p>
  Description :<br/>
  <textarea name=""></textarea>
  </p>
  
  <p>
  Date Limite de réalisation :<br/>
  <input type="" name="" value=""></input>
  </p>
  
  <input type="submit" value="Créer la tâche"/>
</form>

</body>
</html>