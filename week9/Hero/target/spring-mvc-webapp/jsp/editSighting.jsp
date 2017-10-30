<%-- 
    Document   : editSighting
    Created on : Oct 26, 2017, 9:47:30 AM
    Author     : kvnzi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Edit Sighting</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/img/striking.jpg" style="background-size: cover">
        <div class="container-fluid">
            <h1>Edit Sighting</h1>
            <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="editSighting" style="background:rgba(255,255,255,.7)">
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
                            <input required type="date" class="form-control" name="sightDate" placeholder="yyyy/mm/dd" value="${sighting.sightDate}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-sight-desc" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="sightDesc" placeholder="Description" value="${sighting.sightDesc}"/>
                        </div>
                    </div>
                    <hidden>
                    <div class="form-group">
                        <label for="add-sighting-id" class="col-md-4 control-label">Sighting ID:</label>
                        <div class="col-md-8">
                            <input readonly="readonly" type="text" class="form-control" name="sightingID" placeholder="organization ID" value="${sighting.sightingId}"/>
                        </div>
                    </div>
                </hidden>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Update Sighting"/>
                            </div>
                        </div>
                </form><br/><br/>
            <div class='col-md-offset-6'>
                    <a href="${pageContext.request.contextPath}/sighting">Back to Sightings</a>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
