<%-- 
    Document   : editOrganization
    Created on : Oct 26, 2017, 9:47:17 AM
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
        <title>Edit Organization</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/img/HQ.jpg" style="background-size: cover">
        <div class="container-fluid">
            <h1 style="color:white">Edit Organization</h1>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="editOrganization" style="background:rgba(255,255,255,.7)">
                <hidden>
                    <div class="form-group">
                        <label for="add-organization-id" class="col-md-4 control-label">Organization ID:</label>
                        <div class="col-md-8">
                            <input readonly="readonly" type="text" class="form-control" name="organizationID" placeholder="organization ID" value="${organization.organizationID}"/>
                        </div>
                    </div>
                </hidden>
                <div class="form-group">
                    <label for="add-org-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <input required type="text" class="form-control" name="orgName" placeholder="Name" value="${organization.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org-desc" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <input required type="text" class="form-control" name="orgDesc" placeholder="Description" value="${organization.orgDesc}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org-heroes" class="col-md-4 control-label">Heroes/Villains:</label>
                    <div class="col-md-8">
                        <select required name="championsToAdd" multiple>
                            <c:forEach var="currentHero" items="${heroList}">
                                <option value="${currentHero.championID}"> ${currentHero.name}<br/>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org-locations" class="col-md-4 control-label">Locations:</label>
                    <div class="col-md-8">
                        <select required name="locationToAdd" multiple>
                            <c:forEach var="currentLoc" items="${locList}">
                                <option value="${currentLoc.locationId}">${currentLoc.name}</option><br/>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org-contacts" class="col-md-4 control-label">Contacts:</label>
                    <div class="col-md-8">
                        <select required name="contactsToAdd" multiple>
                            <c:forEach var="currentCont" items="${contList}">
                                <option value="${currentCont.contactId}">${currentCont.email}</option><br/>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Organization"/>
                    </div>
                </div>
            </form><br/><br/>
            <div class='col-md-offset-6'>
                    <a href="${pageContext.request.contextPath}/organization" style="color:white">Back to Locations</a>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
