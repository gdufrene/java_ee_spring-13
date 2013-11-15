<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form commandName="form" modelAttribute="person">
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td>Gender:</td>
            <td><form:input path="gender" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Save Changes" />
            </td>
        </tr>
    </table>
</form:form>