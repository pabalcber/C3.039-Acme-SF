<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="manager.userStory.form.label.title" path="title"/>
	<acme:input-textbox code="manager.userStory.form.label.description" path="description"/>
	<acme:input-textbox code="manager.userStory.form.label.estimatedCost" path="estimatedCost"/>
	<acme:input-textbox code="manager.userStory.form.label.priority" path="priority"/>
	<acme:input-textbox code="manager.userStory.form.label.project" path="project"/>
	<acme:input-textbox code="manager.userStory.form.label.acceptanceCriteria" path="acceptanceCriteria"/>
	<acme:input-textbox code="manager.userStory.form.label.optionalLink" path="optionalLink"/>

	<jstl:choose>	 
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:button code="manager.userStory.form.button.projects" action="/manager/userStory/list?masterId=${id}"/>	
			<acme:button code="manager.userStory.form.button.update" action="/manager/userStory/update"/>
			<acme:button code="manager.userStory.form.button.delete" action="/manager/userStory/delete"/>			
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="manager.userStory.form.button.update" action="/manager/userStory/update"/>
			<acme:submit code="manager.userStory.form.button.delete" action="/manager/userStory/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="manager.userStory.form.button.create" action="/manager/userStory/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
