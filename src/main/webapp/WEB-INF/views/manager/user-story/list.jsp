<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="manager.userStory.list.label.title" path="title" width="30%"/>
	<acme:list-column code="manager.userStory.list.label.description" path="description" width="40%"/>
	<acme:list-column code="manager.userStory.list.label.estimatedCost" path="estimatedCost" width="10%"/>
	<acme:list-column code="manager.userStory.list.label.priority" path="priority" width="10%"/>
	<acme:list-column code="manager.userStory.list.label.project" path="project" width="10%"/>
</acme:list>
	<acme:button code="manager.userStory.list.button.create" action="/manager/user-story/create"/>