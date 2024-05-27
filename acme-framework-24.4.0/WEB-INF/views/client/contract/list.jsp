
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="client.contract.list.label.code" path="code" width="25%"/>
	<acme:list-column code="client.contract.list.label.providerName" path="providerName" width="25%"/>
	<acme:list-column code="client.contract.list.label.budget" path="budget" width="25%"/>	
	<acme:list-column code="client.contract.list.label.project" path="project" width="25%"/>	
</acme:list>	
	
<jstl:if test="${_command == 'list'}">
	<acme:button code="client.contract.list.button.create" action="/client/contract/create"/>
</jstl:if>

