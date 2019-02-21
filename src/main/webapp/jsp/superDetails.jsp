<%-- 
    Document   : SuperDetails
    Created on : Dec 25, 2018, 10:29:26 AM
    Author     : Jun
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>--%>
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
                background-image: url("${pageContext.request.contextPath}/img/c1.jpg");
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>SuperHeroes Details</h1>
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

            <p>
                Name: <c:out value="${hero.name}"/>
            </p>
            <p>
                Description: <c:out value="${hero.description}"/>
            </p>
            <p>
                Hero/Villain: <c:out value="${isHero}"/>
            </p>

            <table class="col-md-4 table table-hover">
                <tr>
                    <th width="25%">Power</th>
                </tr>

                <c:forEach items="${powerList}" var="power">
                    <tr>
                        <td>
                            <p> <a href="displayPowerDetails?powerId=${power.powerId}"> <c:out value="${power.name}"/></a></p>
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <table class="col-md-4 table table-hover" >
                <tr>
                    <th width="25%">Organization</th>
                </tr>
                <c:forEach items="${organizationList}" var="organization">
                    <tr>
                        <td>
                            <p><a href="displayOrgDetails?organizationId=${organization.organizationId}"><c:out value="${organization.name}"/></a></p>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>