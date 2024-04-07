<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.contract.form.label.code" path="code"/>	
	<acme:input-textbox code="authenticated.contract.form.label.providerName" path="providerName"/>
	<acme:input-textbox code="authenticated.contract.form.label.customerName" path="customerName"/>
	<acme:input-money code="authenticated.contract.form.label.budget" path="budget"/>
	<acme:input-textbox code="authenticated.contract.form.label.project" path="project"/>
	
	<jstl:if test="${_command == 'show'}">
	<acme:button code="authenticated.contract.form.button.progress-log" action="/authenticated/progress-log/list?masterId=${id}"/>
</jstl:if>
	
	
	
</acme:form>
