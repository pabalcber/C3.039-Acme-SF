
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="client.progressLog.list.label.recordId" path="recordId" width="25%"/>
	<acme:list-column code="client.progressLog.list.label.completeness" path="completeness" width="25%"/>
	<acme:list-column code="client.progressLog.list.label.registrationMoment" path="registrationMoment" width="25%"/>
	</acme:list>		
	
<acme:button test="${showCreate}" code="client.progressLog.list.button.create" action="/client/progress-log/create?masterId=${masterId}"/>
