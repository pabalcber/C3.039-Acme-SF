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

<table class="table">
    <thead>
        <tr>
            <th><acme:message code="manager.dashboard.form.label.currency"/></th>
            <th><acme:message code="manager.dashboard.form.label.average-project-cost"/></th>
            <th><acme:message code="manager.dashboard.form.label.deviation-project-cost"/></th>
            <th><acme:message code="manager.dashboard.form.label.minimum-project-cost"/></th>
            <th><acme:message code="manager.dashboard.form.label.maximum-project-cost"/></th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>EUR</td>
            <td><acme:print value="${averageProjectCosts['EUR']}"/></td>
            <td><acme:print value="${deviationProjectCosts['EUR']}"/></td>
            <td><acme:print value="${minimumProjectCosts['EUR']}"/></td>
            <td><acme:print value="${maximumProjectCosts['EUR']}"/></td>
        </tr>
        <tr>
            <td>GBP</td>
            <td><acme:print value="${averageProjectCosts['GBP']}"/></td>
            <td><acme:print value="${deviationProjectCosts['GBP']}"/></td>
            <td><acme:print value="${minimumProjectCosts['GBP']}"/></td>
            <td><acme:print value="${maximumProjectCosts['GBP']}"/></td>
        </tr>
        <tr>
            <td>USD</td>
            <td><acme:print value="${averageProjectCosts['USD']}"/></td>
            <td><acme:print value="${deviationProjectCosts['USD']}"/></td>
            <td><acme:print value="${minimumProjectCosts['USD']}"/></td>
            <td><acme:print value="${maximumProjectCosts['USD']}"/></td>
        </tr>
    </tbody>
</table>



<div>
	<canvas id="canvas"></canvas>
</div>

<acme:return/>

