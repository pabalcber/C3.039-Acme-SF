
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.progressLog.list.label.recordId" path="recordId" width="25%"/>
	<acme:list-column code="authenticated.progressLog.list.label.completeness" path="completeness" width="25%"/>
	<acme:list-column code="authenticated.progressLog.list.label.responsiblePerson" path="responsiblePerson" width="25%"/>
</acme:list>		


