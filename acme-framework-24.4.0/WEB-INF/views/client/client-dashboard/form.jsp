<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="client.client-dashboard.form.title.progress-logs-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.total-progress-logs-below-25-percent"/>
		</th>
		<td>
			<acme:print value="${totalProgressLogsBelow25Percent}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.total-progress-logs-25-to-50-percent"/>
		</th>
		<td>
			<acme:print value="${totalProgressLogs25To50Percent}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.total-progress-logs-50-to-75-percent"/>
		</th>
		<td>
			<acme:print value="${totalProgressLogs50To75Percent}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.total-progress-logs-above-75-percent"/>
		</th>
		<td>
			<acme:print value="${totalProgressLogsAbove75Percent}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="client.client-dashboard.form.title.contracts-budgets"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.avg-budget-eur"/>
		</th>
		<td>
			<acme:print value="${avgBudgetEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.avg-budget-gbp"/>
		</th>
		<td>
			<acme:print value="${avgBudgetGBP}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.avg-budget-usd"/>
		</th>
		<td>
			<acme:print value="${avgBudgetUSD}"/>
		</td>
	</tr>
		<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.dev-budget-eur"/>
		</th>
		<td>
			<acme:print value="${deviationBudgetEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.dev-budget-gbp"/>
		</th>
		<td>
			<acme:print value="${deviationBudgetGBP}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.dev-budget-usd"/>
		</th>
		<td>
			<acme:print value="${deviationBudgetUSD}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.min-budget-eur"/>
		</th>
		<td>
			<acme:print value="${minimumBudgetEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.min-budget-gbp"/>
		</th>
		<td>
			<acme:print value="${minimumBudgetGBP}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.min-budget-usd"/>
		</th>
		<td>
			<acme:print value="${minimumBudgetUSD}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.max-budget-eur"/>
		</th>
		<td>
			<acme:print value="${maximumBudgetEUR}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.max-budget-gbp"/>
		</th>
		<td>
			<acme:print value="${maximumBudgetGBP}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.client-dashboard.form.label.max-budget-usd"/>
		</th>
		<td>
			<acme:print value="${maximumBudgetUSD}"/>
		</td>
	</tr>
	
</table>


<div>
	<canvas id="canvas"></canvas>
</div>

<acme:return/>

