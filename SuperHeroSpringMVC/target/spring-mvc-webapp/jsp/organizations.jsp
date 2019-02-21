<%-- 
    Document   : organizations
    Created on : Jan 1, 2019, 3:30:09 PM
    Author     : Jun
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
                background-image: url("${pageContext.request.contextPath}/img/r4.jpg");
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Organizations</h1>
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
                    <li role="presentation" 
                        class="active">
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
                    <li role="presentation">
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
                    <h2>Existing Organizations</h2>
                    <table id="organizationTable" class="table table-hover">
                        <tr>
                            <th width="25%">Name</th>
                            <th width="30%">Description</th>
                                <sec:authorize access="hasRole('ROLE_SITEADMIN')">
                                <th width="25%">Options</th>
                                </sec:authorize>
                        </tr>
                        <c:forEach var="currentOrg" items="${organizationList}">
                            <tr>
                                <td>
                                    <a href="displayOrgDetails?organizationId=${currentOrg.organizationId}">
                                        <c:out value="${currentOrg.name}"/> 
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentOrg.description}"/>
                                </td>
                                <sec:authorize access="hasRole('ROLE_SITEADMIN')">
                                    <td>
                                        <a href="displayEditOrgForm?organizationId=${currentOrg.organizationId}">
                                            <button type="button" class="btn btn-success">Edit</button>
                                        </a>
                                        <a href="deleteOrg?organizationId=${currentOrg.organizationId}">
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
                <sec:authorize access="hasRole('ROLE_SITEADMIN')">
                    <div class="col-md-6">
                        <h2>Add New Organization</h2>
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createOrganization">
                            <div class="form-group">
                                <label for="add-name" class="col-md-4 control-label">Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" placeholder="Organization Name" required/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="description" placeholder="Description" required/>
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
                                <div class="col-md-offset-4 col- md-8">
                                    <input type="submit" class="btn btn-default" value="Create Organization"/>
                                </div>
                            </div>
                        </form>

                    </div> <!-- End col div -->
                </sec:authorize>
            </div> <!-- End row div -->
            <!-- Main Page Content Stop -->    
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>