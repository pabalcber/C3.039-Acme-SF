<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.banner-start-time" path="bannerStartTime" width="20%"/>
	<acme:list-column code="administrator.banner.list.label.banner-end-time" path="bannerEndTime" width="20%"/>
	<acme:list-column code="administrator.banner.list.label.slogan" path="slogan" width="20%"/>
</acme:list>

<acme:button code="administrator.banner.list.button.create" action="/administrator/banner/create"/>