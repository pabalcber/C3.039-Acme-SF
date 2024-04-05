
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="client.progressLog.form.label.recordId" path="recordId"/>
	<acme:input-textbox code="client.progressLog.form.label.completeness" path="completeness"/>	
	<acme:input-textbox code="client.progressLog.form.label.comment" path="comment"/>
	<acme:input-textarea code="client.progressLog.form.label.registrationMoment" path="registrationMoment"/>
	<acme:input-url code="client.progressLog.form.label.responsiblePerson" path="responsiblePerson"/>
	<acme:input-textbox code="client.progressLog.form.label.contract" path="contract"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="client.progressLog.form.button.update" action="/client/progressLog/update"/>
			<acme:submit code="client.duty.form.button.delete" action="/client/progressLog/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="client.progressLog.form.button.create" action="/client/progress-log/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>
