<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>



<acme:form>
	<acme:input-integer code="sponsor.dashboard.label.totalTrainingModules" path="totalTrainingModules" placeholder=""/>
	<acme:input-integer code="sponsor.dashboard.label.totalTrainingSessions" path="totalTrainingSessions" placeholder=""/>
		
	<acme:input-double code="sponsor.dashboard.label.averageTimeTrainingModule" path="averageTimeTrainingModule" placeholder=""/>
	<acme:input-double code="sponsor.dashboard.label.deviationTimeTrainingModule" path="deviationTimeTrainingModule" placeholder=""/>
	<acme:input-double code="sponsor.dashboard.label.maxTimeTrainingModule" path="maxTimeTrainingModule" placeholder=""/>
	<acme:input-double code="sponsor.dashboard.label.minTimeTrainingModule" path="minTimeTrainingModule" placeholder=""/>

</acme:form>