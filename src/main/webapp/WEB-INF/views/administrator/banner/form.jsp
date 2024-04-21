
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-moment code="administrator.banner.form.label.moment" path="moment"/>	
	<acme:input-moment code="administrator.banner.form.label.displayStartMoment" path="displayStartMoment"/>
	<acme:input-moment code="administrator.banner.form.label.displayEndMoment" path="displayEndMoment"/>
	<acme:input-textbox code="administrator.banner.form.label.slogan" path="slogan"/>
	<acme:input-url code="administrator.banner.form.label.target" path="target"/>
	<acme:input-url code="administrator.banner.form.label.picture" path="picture"/>
	
	<jstl:choose>	 
		<jstl:when test="${_command == 'show'}">
			<acme:input-checkbox code="administrator.banner.form.label.confirmation" path="confirmation"/>
			<acme:submit code="administrator.banner.form.button.delete" action="/administrator/banner/delete"/>
			<acme:submit code="administrator.banner.form.button.update" action="/administrator/banner/update"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:input-checkbox code="administrator.banner.form.label.confirmation" path="confirmation"/>
			<acme:submit code="administrator.banner.form.button.delete" action="/administrator/banner/delete"/>
			<acme:submit code="administrator.banner.form.button.update" action="/administrator/banner/update"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:input-checkbox code="administrator.banner.form.label.confirmation" path="confirmation"/>
			<acme:submit code="administrator.banner.form.button.create" action="/administrator/banner/create"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>
