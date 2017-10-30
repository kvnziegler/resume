<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O.</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body background="${pageContext.request.contextPath}/img/technology.jpg">
        <div class="container-fluid">
            <center><h1>Hero Education Relationship Organization</h1></center>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hero">Hero</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/villain">Villain</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                </ul>    
            </div>
            <div class="col-md-7" style="background:rgba(255,255,255,.7)">
                <h2>Recent sightings</h2>
                <table id="heroTable" class="table table-hover">
                    <tr>
                            <th width="25%">Description</th>
                            <th width="25%">Date</th>
                            <th width="25%">Heroes</th>
                            <th width="25%">Location</th>

                        </tr>
                    <c:forEach var="currentSight" items="${recentList}">
                            <tr>
                                <td>
                                    <c:out value="${currentSight.sightDesc}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSight.sightDate}"/>
                                </td>
                                <td>
                                    <c:forEach var="hero" items="${currentSight.heroes}">
                                        <c:out value="${hero.name}"/>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:out value="${currentSight.location.name}"/>
                                </td>
                            </tr>
                    </c:forEach>
                </table>
                <!-- dynamicly load the 10 most recent super sightings here-->
            </div>
            <div class="col-md-5">
                <!--place an image here for now in the future a map may be integrated here-->
                <div class="col-md-offset-2">
                <img src="${pageContext.request.contextPath}/img/hero.png" alt="hero" height="500" width="500"/>
                </div>
            </div>   
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

