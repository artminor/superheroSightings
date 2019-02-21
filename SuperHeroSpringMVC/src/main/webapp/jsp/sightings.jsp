<%-- 
    Document   : sightings
    Created on : Dec 26, 2018, 2:45:06 PM
    Author     : Jun
--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Hero Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
        <style>
            body {
                background-image: url("${pageContext.request.contextPath}/img/r2.jpg");
            }
        </style>
    </head>

    <body>

        <div class="container">
            <h1>Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySupersPage">
                            Heroes/Villains
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayPowersPage">
                            Powers
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrgsPage">
                            Organizations
                        </a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_SITEADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayLocationsPage">
                                Locations
                            </a>
                        </li>
                    </sec:authorize>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Sightings
                        </a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_SITEADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
                </ul>    
                <div class="nav navbar-right">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <p>Hello : ${pageContext.request.userPrincipal.name}
                            | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                        </p>
                    </c:if>     
                </div>
            </div>

            <!-- Main Page Content Start -->
            <!-- 
                Add a row to our container - this will hold the summary table and the new
                contact form.
            -->
            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>Existing Sightings</h2>
                    <table id="sightingTable" class="table table-hover">
                        <tr>
                            <th width="15%">Date</th>
                            <th width="10%">Hero/Villain</th>
                            <th width="20%">Location</th>
                                <sec:authorize access="hasRole('ROLE_SITEADMIN')">
                                <th width="20%">Options</th>
                                </sec:authorize>
                        </tr>
                        <c:forEach var="currentSighting" items="${newSightingList}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                        <c:out value="${currentSighting.sightingDate}"/> 
                                    </a>
                                </td>
                                <td>
                                    <a href="displaySuperDetails?superId=${currentSighting.superId}">
                                        <c:out value="${currentSighting.hero.name}"/>

                                </td>
                                <td>
                                    <a href="displayLocationDetails?locationId=${currentSighting.locationId}">
                                        <c:out value="${currentSighting.location.name}"/>

                                </td>
                                <sec:authorize access="hasRole('ROLE_SITEADMIN')">
                                    <td>
                                        <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                            <button type="button" class="btn btn-success">Edit</button>
                                        </a>
                                        <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                            <button type="button" class="btn btn-danger">Delete</button>
                                        </a>
                                    </td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                    </table>                  
                </div> <!-- End col div -->
                <!-- 
                    Add col to hold the new contact form - have it take up the other 
                    half of the row
                -->
                <div class="col-md-6">
                    <h2>Add New Sighting</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSighting">
                        <div class="form-group">
                            <label for="add-date" class="col-md-4 control-label">Date:</label>
                            <div class="col-md-8">
                                <input type="date" class="form-control" name="sightingDate"  max="2018-01-25" required/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="add-super" class="col-md-4 control-label">Hero/Villain:</label>
                            <div class="col-md-8" >
                                <select name="superId"  class="form-control" required>
                                    <option value="" disabled selected>Select your option</option>
                                    <c:forEach items="${superList}" var="hero">
                                        <option value="${hero.superId}">${hero.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location" class="col-md-4 control-label">Location:</label>
                            <div class="col-md-8" >
                                <select name="locationId"  class="form-control" required>
                                    <option value="" disabled selected>Select your option</option>
                                    <c:forEach items="${locationList}" var="location">
                                        <option value="${location.locationId}">${location.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                    </form>

                </div> <!-- End col div -->

            </div> <!-- End row div -->
            <!-- Main Page Content Stop -->    
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>