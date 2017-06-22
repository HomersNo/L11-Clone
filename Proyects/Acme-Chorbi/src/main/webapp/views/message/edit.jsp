<%--
 * edit.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="chirp/chorbi/edit.do" modelAttribute="chirpAttach">

	<form:hidden path="sender"/>
	<form:hidden path="folder"/>
	<form:hidden path="moment"/>
	<form:hidden path="attachments"/>
	


	<acme:textbox code="message.title" path="subject"/>
	
	<acme:textbox code="message.body" path="text"/>
	
	<form:label path="recipient">
		<spring:message code="message.recipient" />:
	</form:label>
	<form:select id="actors" path="recipient">
		<form:option value="0" label="----"/>
		<jstl:forEach items="${actors}" var="actor">
			<form:option value="${actor.id}" label="${actor.surname}, ${actor.name}" />
		</jstl:forEach>
	</form:select>
	<form:errors cssClass="error" path="recipient" />
	<br /><br /><br />
	
	<acme:textbox  code="chirp.attachment.add" path="attachment"/>
		
		<input type="submit" name="attach"
		value="<spring:message code="chirp.attachment.add" />" />&nbsp; 
		<br/>
		<jstl:if test="${urlError != null}">
			<br />
			<span class="message"><spring:message code="${urlError}" /></span>
		</jstl:if>	
	<br />
	

	<input type="submit" name="save"
		value="<spring:message code="message.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="location.href = ('folder/chorbi/list.do');" />
	<br />

	

</form:form>
	
	<ul>
	<jstl:forEach var="row" varStatus="i" items="${chirpAttach.attachments}">
	
		<li><a href="${row}"><jstl:out value="${row}" /></a>
	
    </jstl:forEach>
    </ul>
