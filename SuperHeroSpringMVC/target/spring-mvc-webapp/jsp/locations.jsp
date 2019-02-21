<%-- 
    Document   : locations
    Created on : Dec 26, 2018, 2:44:48 PM
    Author     : Jun
--%>

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
                background-image: url("${pageContext.request.contextPath}/img/r5.jpg");
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Superheroes & Villains</h1>
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
                    <li role="presentation" 
                        class="active">
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">
                            Locations
                        </a>
                    </li>
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
                    <h2>Existing Locations</h2>
                    <table id="location" class="table table-hover">
                        <tr>
                            <th width="15%">Name</th>
                            <th width="25%">Address</th>
                            <th width="25%">Description</th>
                                <sec:authorize access="hasRole('ROLE_SITEADMIN')">
                                <th width="25%">Options</th>
                                </sec:authorize>

                        </tr>
                        <c:forEach var="currentLocation" items="${locationList}">
                            <tr>
                                <td>
                                    <a href="displayLocationDetails?locationId=${currentLocation.locationId}">
                                        <c:out value="${currentLocation.name}"/> 
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.street}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.description}"/>
                                </td>
                                <sec:authorize access="hasRole('ROLE_SITEADMIN')">
                                    <td>
                                        <a href="displayEditLocationForm?locationId=${currentLocation.locationId}">
                                            <button type="button" class="btn btn-success">Edit</button>
                                        </a>
                                        <a href="deleteLocation?locationId=${currentLocation.locationId}">
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
                        <h2>Add Location</h2>
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createLocation">
                            <div class="form-group">
                                <label for="add-name" class="col-md-4 control-label">Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="name" placeholder="Location Name" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="latitude" placeholder="Latitude" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="longitude" placeholder="Longitude"required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-street" class="col-md-4 control-label">Street:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="street" placeholder="Street"required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-city" class="col-md-4 control-label">City:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="city" placeholder="City"required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-state" class="col-md-4 control-label">State:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="state" placeholder="State"required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="zip" placeholder="Zip"required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="description" placeholder="Description" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Create Location"/>
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