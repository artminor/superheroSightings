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
                background-image: url("${pageContext.request.contextPath}/img/bghome.jpg");
            }



            /* Always set the map height explicitly to define the size of the div
             * element that contains the map. */
            /*            #map {
                            height: 70%;
                            width: 100%;
                            margin: auto;
                        }*/
            /* Optional: Makes the sample page fill the window. */
            html, body {
                height:100%;
                margin: 0;
                padding: 0;
            }

        </style>


    </head>




    <body>


        <div id="map" style="width: 100%; height: 100%;  overflow: auto;" >
            <div id="map-canvas"></div>
        </div>



        <div class="container" style="margin-top: 25px;">


            <h1>Super Hero Sightings</h1> 

            <hr>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" 
                        class="active">
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










            <div class="col-md-12">

                <p>
                    SUPER SUPER SUPER ME WILL GET THIS DONE YAY.
                </p>
                <p>
                    With the rising popularity of superhero movies there has been a heightened awareness of superheroes in our midst.  The frequency of superhero (and supervillain) sightings is increasing at an alarming rate... hence why we have this web app...
                </p>


                <!--        <div id="map" style="width: 100%; height: 100%; position: absolute;">
                            <div id="map-canvas"></div>
                        </div>-->


                <h2>Latest Sightings</h2>


                <table id="sightingTable" class="table table-hover">
                    <tr>
                        <th width="30%">Date</th>
                        <th width="30%">Hero/Villain</th>
                        <th width="30%">Location</th>
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
                        </tr>
                    </c:forEach>
                </table>        

            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


        <script>
            function initMap() {
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 4
                });

                var bounds = new google.maps.LatLngBounds();
            <c:forEach var="currentsighting" items="${newSightingList}" varStatus="status">
                var myLatLng = new google.maps.LatLng(<c:out value="${currentsighting.location.latitude}"/>,
                <c:out value="${currentsighting.location.longitude}"/>);
                var marker = new google.maps.Marker({
                    position: myLatLng,
                    map: map,
                    title: 'Hero Sightings'});
                bounds.extend(myLatLng);
            </c:forEach>
                map.fitBounds(bounds);
            }

            $(document).ready(function () {
                initMap();
            });
        </script>


        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD3c_5CWPerhrgaxc9oUTGBs0VvtmJeS-E&callback=initMap"
        async defer></script>


    </body>
</html>

