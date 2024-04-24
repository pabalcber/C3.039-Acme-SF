<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="authenticated.client.form.label.degree" path="degree"/>
	<acme:input-textbox code="authenticated.client.form.label.overview" path="overview"/>
	<acme:input-textbox code="authenticated.client.form.label.certificationsList" path="certificationsList"/>
	<acme:input-url code="authenticated.client.form.label.optionalLink" path="optionalLink"/>

	<acme:submit test="${_command == 'create'}" code="authenticated.manager.form.button.create"
action="/authenticated/manager/create"/>
</acme:form>
