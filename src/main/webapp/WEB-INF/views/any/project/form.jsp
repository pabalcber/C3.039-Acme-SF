<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="any.project.form.label.code" path="code"/>
	<acme:input-textbox code="any.project.form.label.title" path="title"/>
	<acme:input-textbox code="any.project.form.label.abstractPj" path="abstractPj"/>
	<acme:input-checkbox code="any.project.form.label.fatalErrors" path="fatalErrors"/>
	<acme:input-money code="any.project.form.label.cost" path="cost"/>
	<acme:input-url code="any.project.form.label.optionalLink" path="optionalLink"/>

	<jstl:choose>	 
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:button code="any.project.form.button.projects" action="/any/project/list?masterId=${id}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
