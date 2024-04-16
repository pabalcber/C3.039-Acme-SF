
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.progressLog.form.label.recordId" path="recordId"/>
	<acme:input-double code="authenticated.progressLog.form.label.completeness" path="completeness"/>	
	<acme:input-textarea code="authenticated.progressLog.form.label.comment" path="comment"/>
	<acme:input-moment code="authenticated.progressLog.form.label.registrationMoment" path="registrationMoment"/>
	<acme:input-textbox code="authenticated.progressLog.form.label.responsiblePerson" path="responsiblePerson"/>
	<acme:input-textbox code="authenticated.progressLog.form.label.contract" path="contract"/>
</acme:form>
