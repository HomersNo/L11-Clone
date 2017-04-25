<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:set var="full" value="font-color:grey" />
<jstl:set var="Inminent" value="font-weight:bold" />
<jstl:set var="passed" value="font-color:red" />

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="events" requestURI="event/actor/list.do" id="row">
	<security:authentication property="principal" var ="loggedactor"/>
	
	<spring:message code="event.title" var="titleHeader" />
	<spring:message code="event.description" var="descriptionHeader" />
	<spring:message code="event.moment" var="momentHeader" />
	<spring:message code="event.numberSeat" var="numberSeatHeader" />
	<spring:message code="event.picture" var="pictureHeader" />
	
	<jstl:choose>
		<jstl:when test="${row.registered.size() == row.numberSeat }">
			<display:column property="title" title="${titleHeader}" sortable="true" style="${full}"/>
			<display:column property="description" title="${descriptionHeader}" sortable="true" style="${full}" />
			<display:column property="moment" title="${momentHeader}" sortable="true" style="${full}" />
			<display:column property="numberSeat" title="${numberSeatHeader}" sortable="true" style="${full}" />
			<display:column property="picture" title="${pictureHeader}" sortable="true" style="${full}" />
		</jstl:when>
		<jstl:otherwise>
			<jsp:useBean id="dateValue" class="java.util.Date" />
			<jstl:set var="now" value="${dateValue.time}" />
			<jstl:set var="oneMonth" value="${dateValue.time + 2628000000}"/>
			<jstl:choose>
				<jstl:when test="${row.moment.time > now && row.moment.time < oneMonth}">
					<display:column property="title" title="${titleHeader}" sortable="true" style="${Inminent}"/>
					<display:column property="description" title="${descriptionHeader}" sortable="true" style="${Inminent}" />
					<display:column property="moment" title="${momentHeader}" sortable="true" style="${Inminent}" />
					<display:column property="numberSeat" title="${numberSeatHeader}" sortable="true" style="${Inminent}" />
					<display:column property="picture" title="${pictureHeader}" sortable="true" style="${Inminent}" />
				</jstl:when>
				<jstl:when test="${row.moment.time < now}">
					<display:column property="title" title="${titleHeader}" sortable="true" style="${passed}"/>
					<display:column property="description" title="${descriptionHeader}" sortable="true" style="${passed}" />
					<display:column property="moment" title="${momentHeader}" sortable="true" style="${passed}" />
					<display:column property="numberSeat" title="${numberSeatHeader}" sortable="true" style="${passed}" />
					<display:column property="picture" title="${pictureHeader}" sortable="true" style="${passed}" />
				</jstl:when>
				<jstl:otherwise>
					<display:column property="title" title="${titleHeader}" sortable="true"/>
					<display:column property="description" title="${descriptionHeader}" sortable="true"/>
					<display:column property="moment" title="${momentHeader}" sortable="true"/>
					<display:column property="numberSeat" title="${numberSeatHeader}" sortable="true"/>
					<display:column property="picture" title="${pictureHeader}" sortable="true"/>
				</jstl:otherwise>
			</jstl:choose>
		</jstl:otherwise>
	</jstl:choose>
	
	
	<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${loggedactor.id == row.organiser.userAccount.id}">
		<spring:message code="event.edit" var="editHeader" />
		<display:column title="${editHeader}">
			<a href="event/manager/edit.do?eventId=${row.id}"><spring:message code="event.edit" /> </a>
		</display:column>
	
	
		<spring:message code="event.delete" var="deleteHeader" />
		<display:column title="${deleteHeader}">
			<a href="event/manager/delete.do?eventId=${row.id}"><spring:message code="event.delete" /> </a>
		</display:column>
	</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('CHORBI')">
		<spring:message code="event.register" var="registerHeader" />
		<display:column title="${registerHeader}">
		<jstl:if test="${row.registered.size() < row.numberSeat}">
			<a href="event/chorbi/register.do?eventId=${row.id}">
			<jstl:set var="registered" value="false" />
			<jstl:forEach var="item" items="${row.registered}">
				<jstl:if test="${item.userAccount.id == loggedactor.id}">
					<jstl:set var="registered" value="true" />
				</jstl:if>
			</jstl:forEach>
			<jstl:choose>
				<jstl:when test="${registered}">
					<spring:message code="event.unregister" />
				</jstl:when>
				<jstl:otherwise>	
					<spring:message code="event.register" />				
				</jstl:otherwise>
			</jstl:choose>
			 </a>
		</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>

<security:authorize access="hasRole('MANAGER')">
	<input type="button" name="create"
		value="<spring:message code="event.create" />"
		onclick="location.href = ('event/manager/create.do');" />
</security:authorize>
