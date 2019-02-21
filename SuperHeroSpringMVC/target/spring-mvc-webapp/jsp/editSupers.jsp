<%-- 
    Document   : editSupers
    Created on : Dec 25, 2018, 5:18:59 PM
    Author     : Jun
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
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
                background-image: url("${pageContext.request.contextPath}/img/b1.jpg");
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Edit Superhero / Villain</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index">
                            Home
                        </a>
                    </li>
                    <li role="presentation"
                        class="active">
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
                    <li role="presentation">
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

            <sf:form class="form-horizontal" role="form" modelAttribute="hero"
                     action="editSuper" method="POST">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-name" 
                                  path="name"/>
                        <sf:errors path="name" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-description" class="col-md-4 control-label">Description:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-description"
                                  path="description" placeholder="Description"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>
                        </div>
                    </div>


                    <div class="form-group" name="isHero">
                        <label for="add-isHero" class="col-md-4 control-label">Hero/Villain:</label>
                        <div class="col-md-8">
                            <div class="col-md-6">
                            <sf:radiobutton class="form-control" name="isHero" path="isHero" value="true"/>Hero<br>
                        </div>
                        <div class="col-md-6">
                            <sf:radiobutton class="form-control" name="isHero" path="isHero" value="false"/>Villain<br>
                        </div>
                        <sf:errors path="isHero" cssclass="error"></sf:errors> 
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="add-power" class="col-md-4 control-label">Super Power:</label>
                        <div class="col-md-8">
                        <sf:select multiple="true" path="powers" class="form-control">
                            <option value="" disabled class="form-control">--Select your power(s)</option>
                            <sf:options items="${powerList}" itemLabel="name" itemValue="powerId"/>
                            <sf:errors path="powers" cssclass="error"></sf:errors>
                        </sf:select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-organization" class="col-md-4 control-label">Organization:</label>
                    <div class="col-md-8">
                        <sf:select multiple="true"  path="orgs" class="form-control">
                            <option value="" disabled class="form-control">--Select your organization(s)</option>
                            <sf:options items="${organizationList}" itemLabel="name" itemValue="organizationId"/>
                            <sf:errors path="orgs" cssclass="error"></sf:errors>
                        </sf:select>
                    </div>
                </div>


                <sf:hidden path="superId"/>

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