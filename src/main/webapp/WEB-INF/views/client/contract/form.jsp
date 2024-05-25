
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>	

	<jstl:choose>	 
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:input-textbox code="client.contract.form.label.code" path="code" placeholder = "AAA-000"/>
			<acme:input-select code="client.contract.form.label.project" path="project" choices="${projects}"/>
			<acme:input-textbox code="client.contract.form.label.providerName" path="providerName"/>
			<acme:input-textbox code="client.contract.form.label.customerName" path="customerName"/>
			<acme:input-textarea code="client.contract.form.label.goals" path="goals"/>
			<acme:input-money code="client.contract.form.label.budget" path="budget"/>
			<acme:button code="client.contract.form.button.progress-log" action="/client/progress-log/list?masterId=${id}"/>			
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:input-textbox code="client.contract.form.label.code" path="code" readonly = "true" placeholder = "AAA-000"/>
			<acme:input-select code="client.contract.form.label.project" path="project" readonly = "true" choices="${projects}"/>
			<acme:input-textbox code="client.contract.form.label.providerName" path="providerName"/>
			<acme:input-textbox code="client.contract.form.label.customerName" path="customerName"/>
			<acme:input-textarea code="client.contract.form.label.goals" path="goals"/>
			<acme:input-money code="client.contract.form.label.budget" path="budget"/>
			<acme:button code="client.contract.form.button.progress-log" action="/client/progress-log/list?masterId=${id}"/>
			<acme:submit code="client.contract.form.button.update" action="/client/contract/update"/>
			<acme:submit code="client.contract.form.button.delete" action="/client/contract/delete"/>
			<acme:submit code="client.contract.form.button.publish" action="/client/contract/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:input-textbox code="client.contract.form.label.code" path="code" placeholder = "AAA-000"/>
			<acme:input-select code="client.contract.form.label.project" path="project" choices="${projects}"/>
			<acme:input-textbox code="client.contract.form.label.providerName" path="providerName"/>
			<acme:input-textbox code="client.contract.form.label.customerName" path="customerName"/>
			<acme:input-textarea code="client.contract.form.label.goals" path="goals"/>
			<acme:input-money code="client.contract.form.label.budget" path="budget"/>
			<acme:submit code="client.contract.form.button.create" action="/client/contract/create"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>
