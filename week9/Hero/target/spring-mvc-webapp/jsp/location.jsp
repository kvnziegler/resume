<%-- 
    Document   : location
    Created on : Oct 23, 2017, 1:58:51 PM
    Author     : kvnzi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Locations</title>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/location">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                </ul>    
            </div>
            <div class="col-md-7" style="background:rgba(255,255,255,.7)">
                <h2>Locations</h2>
                <table id="LocationTable" class="table table-hover">
                    <tr>
                        <th width="25%">Name</th>
                        <th width="25%">Address</th>
                        <th width="25%">Longitude</th>
                        <th width="25%">Latitude</th>
                    </tr>
                    <c:forEach var="currentLoc" items="${locList}">
                        <tr>
                            <td>
                                <c:out value="${currentLoc.name}"/>
                            </td>
                            <td>
                                <c:out value="${currentLoc.address}"/>
                            </td>
                            <td>
                                <c:out value="${currentLoc.longitutde}"/>
                            </td>
                            <td>
                                <c:out value="${currentLoc.lattitude}"/>
                            </td>
                            <td>
                                <a href="displayEditLocation?locationId=${currentLoc.locationId}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteLocation?locationId=${currentLoc.locationId}">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <!-- dynamicly load locations here-->
            </div>
            <div class="col-md-5" style="background:rgba(255,255,255,.7)">
                <!--add new location here-->
                <h2>Add a Location</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createLocation">
                    <div class="form-group">
                        <label for="add-location-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="locationName" placeholder="Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-address" class="col-md-4 control-label">Address</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="address" placeholder="Address"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-desc" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="description" placeholder="Description"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-longitude" class="col-md-4 control-label">Longitude</label>
                        <div class="col-md-8">
                            <input required type="number" class="form-control" name="longitude" placeholder=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-latitude" class="col-md-4 control-label">Latitude</label>
                        <div class="col-md-8">
                            <input required type="number" class="form-control" name="latitude" placeholder=""/>
                        </div>
                    </div><br/>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input required type="submit" class="btn btn-default" value="Create Location"/>
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
