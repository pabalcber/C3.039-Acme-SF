
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="client.progressLog.form.label.recordId" path="recordId" placeholder = "PG-AA-0000"/>
	<acme:input-double code="client.progressLog.form.label.completeness" path="completeness" placeholder = "0.1"/>	
	<acme:input-textarea code="client.progressLog.form.label.comment" path="comment"/>
	<acme:input-textbox code="client.progressLog.form.label.responsiblePerson" path="responsiblePerson"/>
	<acme:input-textbox code="client.progressLog.form.label.contract" path="contract"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')  && draftMode == true }">
			<acme:submit code="client.progressLog.form.button.update" action="/client/progress-log/update"/>
			<acme:submit code="client.progressLog.form.button.delete" action="/client/progress-log/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="client.progressLog.form.button.create" action="/client/progress-log/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>
