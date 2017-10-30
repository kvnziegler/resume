<%-- 
    Document   : organization
    Created on : Oct 23, 2017, 1:59:09 PM
    Author     : kvnzi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organizations</title>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                </ul>    
            </div>
            <div class="col-md-7" style="background:rgba(255,255,255,.7)">
                <h2>Organizations</h2>
                <table id="LocationTable" class="table table-hover">
                    <tr>
                        <th width="40%">Name</th>
                        <th width="37%">Super Beings</th>
                        <th width="37%">Contact</th>

                    </tr>
                    <c:forEach var="currentOrg" items="${orgList}">
                        <tr>
                            <td>
                                <c:out value="${currentOrg.name}"/>
                            </td>
                            <td>
                                <c:forEach var="currentHero" items="${currentOrg.champions}">
                                    <c:out value="${currentHero.name}"/>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="currentCont" items="${currentOrg.contacts}">
                                    <c:out value="${currentCont.email}"/>
                                </c:forEach>
                            </td>
                            <td>
                                <a href="displayEditOrganization?organizationId=${currentOrg.organizationID}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteOrganization?organizationId=${currentOrg.organizationID}">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <!-- dynamicly load organizations here-->
            </div>
            <div class="col-md-5" style="background:rgba(255,255,255,.7)">
                <!--add new organization here-->
                <h2>Add a New Organization</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createOrganization">
                    <div class="form-group">
                        <label for="add-org-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="orgName" placeholder="Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-org-desc" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="orgDesc" placeholder="Description"/>
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
                        <div class="col-md-6">
                        </div>
                        <div class="col-md-6">
                            <label for="add-power"><a href="${pageContext.request.contextPath}/newContact">Create New Contact</a></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Organization"/>
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
