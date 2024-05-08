
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.risk.form.label.reference" path="reference"/>	
	<acme:input-integer code="authenticated.risk.form.label.impact" path="impact"/>	
	<acme:input-textarea code="authenticated.risk.form.label.description" path="description"/>
	<acme:input-double code="authenticated.risk.form.label.probability" path="probability"/>
	<acme:input-double code="authenticated.risk.form.label.value" path="value"/>
	<acme:input-url code="authenticated.risk.form.label.optionalLink" path="optionalLink"/>
	<acme:input-textbox code="authenticated.risk.form.label.project" path="project"/>
	<acme:input-textbox code="authenticated.risk.form.label.admin" path="admin"/>
	
</acme:form>
