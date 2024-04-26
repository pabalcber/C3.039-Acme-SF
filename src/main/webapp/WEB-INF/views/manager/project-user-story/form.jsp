<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-select code="manager.project-user-story.form.label.user-story" path="userStory" choices="${userStories}"/>
	<jstl:choose>	 
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="manager.project-user-story.form.button.create" action="/manager/project-user-story/create?masterId=${id}"/>
		</jstl:when>	
		<jstl:when test="${_command == 'delete'}">
			<acme:submit code="manager.project-user-story.form.button.delete" action="/manager/project-user-story/delete?masterId=${id}"/>
		</jstl:when>	
	</jstl:choose>
</acme:form>
