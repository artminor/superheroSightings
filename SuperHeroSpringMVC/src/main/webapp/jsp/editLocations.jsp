<%-- 
    Document   : editLocations
    Created on : Dec 23, 2018, 6:30:16 PM
    Author     : Jun
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
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
                background-image: url("${pageContext.request.contextPath}/img/b5.jpg");
            }
        </style>        
    </head>
    <body>
        <div class="container">
            <h1>Edit Location</h1>
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
                </ul>    
                                           <div class="nav navbar-right">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <p>Hello : ${pageContext.request.userPrincipal.name}
                            | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                        </p>
                    </c:if>     
                </div>
            </div>
   
            <sf:form class="form-horizontal" role="form" modelAttribute="location"
                     action="editLocation" method="POST">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-name"
                                  path="name" placeholder="Name"/>
                        <sf:errors path="name" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-latitude"
                                  path="latitude" placeholder="00.00"/>
                        <sf:errors path="latitude" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-longitude"
                                  path="longitude" placeholder="00.00"/>
                        <sf:errors path="longitude" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="street" class="col-md-4 control-label">Street:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" 
                                  path="street" placeHolder="street"/>
                        <sf:errors path="street" cssClass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="city" class="col-md-4 control-label">City:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" 
                                  path="city" placeHolder="city"/>
                        <sf:errors path="city" cssClass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="state" class="col-md-4 control-label">State:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" 
                                  path="state" placeHolder="state"/>
                        <sf:errors path="state" cssClass="error"></sf:errors>
                        </div>
                    </div>  

                    <div class="form-group">
                        <label for="zip" class="col-md-4 control-label">Zip:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" 
                                  path="zip" placeHolder="zip"/>
                        <sf:errors path="zip" cssClass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-4 control-label">Description</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" 
                                  path="description" placeHolder="description"/>
                        <sf:errors path="description" cssClass="error"></sf:errors>
                        </div>
                    </div>  

                <sf:hidden path="locationId"/>



                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update"/>
                    </div>
                </div>
            </sf:form>                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>