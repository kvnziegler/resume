<%-- 
    Document   : editLocation
    Created on : Oct 26, 2017, 9:47:03 AM
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
        <title>Edit Location</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/img/location.jpg" style="background-size: cover">
        <div class="container-fluid">
            <h1>Edit Location</h1>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="editLocation" style="background:rgba(255,255,255,.7)">
                <hidden>
                    <div class="form-group">
                        <label for="add-location-id" class="col-md-4 control-label">Location ID:</label>
                        <div class="col-md-8">
                            <input readonly="readonly" type="text" class="form-control" name="locationID" placeholder="Location ID" value="${location.locationId}"/>
                        </div>
                    </div>
                </hidden>
                <div class="form-group">
                    <label for="add-locationo-name" class="col-md-4 control-label">Location Name:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="locationName" placeholder="Location Name" value="${location.name}"/>
                    </div>
                </div>
                    <div class="form-group">
                        <label for="add-location-address" class="col-md-4 control-label">Address</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="address" placeholder="Address" value="${location.address}"/>
                        </div>
                    </div>
                <div class="form-group">
                    <label for="addlocation-desc" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="locationDesc" placeholder="Description" value="${location.locationDesc}"/>
                    </div>
                </div>
                <div class="form-group">
                        <label for="add-location-longitude" class="col-md-4 control-label">Longitude</label>
                        <div class="col-md-8">
                            <input required type="number" class="form-control" name="longitude" placeholder="" value="${location.longitutde}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-latitude" class="col-md-4 control-label">Latitude</label>
                        <div class="col-md-8">
                            <input required type="number" class="form-control" name="latitude" value="${location.lattitude}"/>
                        </div><br/><br/><br/>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Location" />
                    </div>
                </div>
            </form><br/><br/>
            <div class='col-md-offset-6'>
                    <a href="${pageContext.request.contextPath}/location">Back to Locations</a>
            </div>
        </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
