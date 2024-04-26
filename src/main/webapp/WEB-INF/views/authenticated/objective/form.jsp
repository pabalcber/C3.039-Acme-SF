
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.objective.form.label.title" path="title"/>	
	<acme:input-textbox code="authenticated.objective.form.label.priority" path="priority"/>	
	<acme:input-textarea code="authenticated.objective.form.label.description" path="description"/>
	<acme:input-textbox code="authenticated.objective.form.label.status" path="status"/>
	<acme:input-moment code="authenticated.objective.form.label.startDuration" path="startDuration"/>
	<acme:input-moment code="authenticated.objective.form.label.finishDuration" path="finishDuration"/>
	<acme:input-url code="authenticated.objective.form.label.furtherInformationLink" path="furtherInformationLink"/>
</acme:form>
