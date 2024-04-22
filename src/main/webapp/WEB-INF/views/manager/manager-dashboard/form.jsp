<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="manager.dashboard.form.title.user-stories-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.must-user-stories"/>
		</th>
		<td>
			<acme:print value="${mustUserStories}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.should-user-stories"/>
		</th>
		<td>
			<acme:print value="${shouldUserStories}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.could-user-stories"/>
		</th>
		<td>
			<acme:print value="${couldUserStories}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.wont-user-stories"/>
		</th>
		<td>
			<acme:print value="${wontUserStories}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.average-estimated-cost"/>
		</th>
		<td>
			<acme:print value="${averageEstimatedCost}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.deviation-estimated-cost"/>
		</th>
		<td>
			<acme:print value="${deviationEstimatedCost}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.minimum-estimated-cost"/>
		</th>
		<td>
			<acme:print value="${minimumEstimatedCost}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.maximum-estimated-cost"/>
		</th>
		<td>
			<acme:print value="${maximumEstimatedCost}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="manager.dashboard.form.title.project-indicators"/>
</h2>

<table class="table table-sm">	
	<tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.average-project-cost-eur"/>
		</th>
		<td>
			<acme:print value="${averageProjectCostsEUR}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.average-project-cost-gbp"/>
		</th>
		<td>
			<acme:print value="${averageProjectCostsGBP}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.average-project-cost-usd"/>
		</th>
		<td>
			<acme:print value="${averageProjectCostsUSD}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.deviation-project-cost-eur"/>
		</th>
		<td>
			<acme:print value="${deviationProjectCostsEUR}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.deviation-project-cost-gbp"/>
		</th>
		<td>
			<acme:print value="${deviationProjectCostsGBP}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.deviation-project-cost-usd"/>
		</th>
		<td>
			<acme:print value="${deviationProjectCostsUSD}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.minimum-project-cost-eur"/>
		</th>
		<td>
			<acme:print value="${minimumProjectCostsEUR}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.minimum-project-cost-gbp"/>
		</th>
		<td>
			<acme:print value="${minimumProjectCostsGBP}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.minimum-project-cost-usd"/>
		</th>
		<td>
			<acme:print value="${minimumProjectCostsUSD}"/>
		</td><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.maximum-project-cost-eur"/>
		</th>
		<td>
			<acme:print value="${maximumProjectCostsEUR}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.maximum-project-cost-gbp"/>
		</th>
		<td>
			<acme:print value="${maximumProjectCostsGBP}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="manager.dashboard.form.label.maximum-project-cost-usd"/>
		</th>
		<td>
			<acme:print value="${maximumProjectCostsUSD}"/>
		</td>
	</tr>
</table>


<div>
	<canvas id="canvas"></canvas>
</div>

<acme:return/>

