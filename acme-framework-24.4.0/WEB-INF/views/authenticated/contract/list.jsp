
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.contract.list.label.code" path="code" width="25%"/>
	<acme:list-column code="authenticated.contract.list.label.providerName" path="providerName" width="25%"/>
	<acme:list-column code="authenticated.contract.list.label.customerName" path="customerName" width="25%"/>
	<acme:list-column code="authenticated.contract.list.label.budget" path="budget" width="20%"/>	
	<acme:list-column code="authenticated.contract.list.label.project" path="project" width="10%"/>	
</acme:list>	


