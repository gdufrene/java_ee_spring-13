<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/views/partials/header.jsp" />


<form:form class="form-horizontal" commandName="form" modelAttribute="person">

<div class="control-group">
  <label class="control-label" for="name">First Name</label>
  <div class="controls">
  <form:input path="name" placeholder="Your name"/>
  </div>
</div>

<div class="control-group">
  <label class="control-label" for="gender">Gender</label>
  <div class="controls">
  <form:input path="gender" placeholder="Gender"/>
  </div>
</div>

<div class="control-group">
  <div class="controls">
  <label class="checkbox">
  <input type="checkbox"> Remember me
  </label>
  <button type="submit" class="btn">Sign in</button>
  </div>
</div>

</form:form>

<jsp:include page="/views/partials/footer.jsp" />