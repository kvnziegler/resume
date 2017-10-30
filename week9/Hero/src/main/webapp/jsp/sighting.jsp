<%-- 
    Document   : sighting
    Created on : Oct 23, 2017, 1:59:20 PM
    Author     : kvnzi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body background="${pageContext.request.contextPath}/img/technology.jpg">
        <div class="container-fluid">
            <center><h1>Hero Education Relationship Organization</h1></center>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hero">Hero</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/villain">Villain</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                </ul>    
            </div>
            <div class="col-md-7" style="background:rgba(255,255,255,.7)">
                <h2>Sightings</h2>
                  <table id="heroTable" class="table table-hover">
                        <tr>
                            <th width="25%">Description</th>
                            <th width="25%">Date</th>
                            <th width="25%">Heros</th>
                            <th width="25%">Location</th>

                        </tr>
                        <c:forEach var="currentSight" items="${sightList}">
                            <tr>
                                <td>
                                        <c:out value="${currentSight.sightDesc}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSight.sightDate}"/>
                                </td>
                                <td>
                                    <c:forEach var="currentHero" items="${currentSight.heroes}">
                                    <c:out value="${currentHero.name}"/>
                                </c:forEach>
                                </td>
                                <td>
                                    <c:out value="${currentSight.location.name}"/>
                                </td>
                                <td>
                                    <a href="displayEditSighting?SightingId=${currentSight.sightingId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSighting?SightingId=${currentSight.sightingId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                <!-- dynamicly load sightings here-->
            </div>
            <div class="col-md-5" style="background:rgba(255,255,255,.7)">
                <!--add new sighting here-->
                <h2>Add a New Sighting</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createSighting">
                    <div class="form-group">
                        <label for="add-sighting-location" class="col-md-4 control-label">Location:</label>
                        <div class="col-md-8">
                            <c:forEach var="currentLocation" items="${locList}">
                                <input required type="radio" name="location" value="${currentLocation.locationId}"> ${currentLocation.name}<br>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-sighting-heros" class="col-md-4 control-label">Heroes:</label>
                        <div class="col-md-8">
                            <select required name="herosToAdd" multiple>
                                <c:forEach var="currentHero" items="${heroList}">
                                    <option value="${currentHero.championID}"> ${currentHero.name}</option><br/>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-sight-date" class="col-md-4 control-label">Date:</label>
                        <div class="col-md-8">
                            <input required type="date" class="form-control" name="sightDate" placeholder="yyyy/mm/dd"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-sight-desc" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="sightDesc" placeholder="Description"/>
                        </div>
                    </div>
                    
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                </form>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
