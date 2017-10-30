<%-- 
    Document   : villains
    Created on : Oct 23, 2017, 1:58:38 PM
    Author     : kvnzi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Villains</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body background="${pageContext.request.contextPath}/img/redness.jpg">
        <div class="container-fluid">
            <center><h1 style="color:red">Vile Individuals Lawlessly Loathing Anyone in Nearby(proximity)</h1></center>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/" style="color:red">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hero" style="color:red">Hero</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/villain" style="color:red">Villain</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location" style="color:red">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization" style="color:red">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting" style="color:red">Sightings</a></li>
                </ul>    
            </div>
            <div class="col-md-7" style="background:rgba(255,255,255,.7)">
                <h2>Villains</h2>
                <table id="heroTable" class="table table-hover">
                    <tr>
                        <th width="40%">Hero Name</th>
                        <th width="30%">Power(s)</th>
                        <th width="30%">Description</th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                    </tr>
                    <c:forEach var="currentHero" items="${heroList}">
                        <c:if test="${!currentHero.isHero}">
                            <tr>
                                <td>
                                    <c:out value="${currentHero.name}"/>
                                </td>
                                <td>
                                    <c:forEach var="currentPower" items="${currentHero.powers}">
                                        <c:out value="${currentPower.name}"/>
                                    </c:forEach>
                                    <!--maybe make it to where when you click on the hero you go to a descriuption page with a list of thier powers?-->
                                </td>
                                <td>
                                    <c:out value="${currentHero.championDesc}"/>
                                </td>
                                <td>
                                    <a href="displayEditVillain?villainId=${currentHero.championID}" style="color:red">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteVillain?villainId=${currentHero.championID}" style="color:red">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:if>                
                    </c:forEach>
                </table>
                <!-- dynamicly load villains here-->
            </div>
            <div class="col-md-5" style="background:rgba(255,255,255,.7)">
                <!--add new villain here-->
                <h2>Add New Villain</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createVillain">
                    <div class="form-group">
                        <label for="add-villian-name" class="col-md-4 control-label">Villain Name:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="villainName" placeholder="Villain Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-villain-desc" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="villainDesc" placeholder="Description"/>
                        </div>
                    </div>
                    <div>
                        <label for="add-villain-powers" class="col-md-4 control-label">Powers:</label>
                        <div class="col-md-8">
                            <select required name="powersToAdd" multiple>
                                <c:forEach var="currentPower" items="${powerList}">
                                    <option value="${currentPower.powerID}"> ${currentPower.name}</option><br/>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6">
                        </div>
                        <div class="col-md-6">
                            <label for="add-power"><a href="${pageContext.request.contextPath}/newPower" style="color:red">Create New Power</a></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Villain"/>
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
