<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="client.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.dashboard.form.label.total-progress-logs-below-25-percent"/>
		</th>
		<td>
			<acme:print value="${totalProgressLogsBelow25Percent}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.dashboard.form.label.total-progress-logs-25-to-50-percent"/>
		</th>
		<td>
			<acme:print value="${totalProgressLogs25To50Percent}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.dashboard.form.label.total-progress-logs-50-to-75-percent;"/>
		</th>
		<td>
			<acme:print value="${totalProgressLogs50To75Percent;}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.dashboard.form.label.total-progress-logs-above-75-percent"/>
		</th>
		<td>
			<acme:print value="${totalProgressLogsAbove75Percent}"/>
		</td>
	</tr>	
</table>

<acme:return/>
