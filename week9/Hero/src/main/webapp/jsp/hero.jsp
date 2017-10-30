<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Heros</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/img/atom.gif" style="background-size: cover">
        <div class="container-fluid">
            <center><h1>Hero Education Relationship Organization</h1></center>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/hero">Hero</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/villain">Villain</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                </ul>    
            </div>
            <div class="col-md-7" style="background:rgba(255,255,255,.7)">
                <h2>Heros</h2>
                <table id="heroTable" class="table table-hover">
                    <tr>
                        <th width="40%">Hero Name</th>
                        <th width="30%">Power(s)</th>
                        <th width="30%">Description</th>
                        <th width="15%"></th>
                        <th width="15%"></th>
                    </tr>
                    <c:forEach var="currentHero" items="${heroList}">
                        <c:if test="${currentHero.isHero}">
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
                                    <a href="displayEditHero?heroId=${currentHero.championID}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteHero?heroId=${currentHero.championID}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
                <!-- dynamicly load heros here-->
            </div>
            <div class="col-md-5" style="background:rgba(255,255,255,.7)">
                <!--add new hero here-->
                <h2>Add New Hero</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createHero">
                    <div class="form-group">
                        <label for="add-hero-name" class="col-md-4 control-label">Hero Name:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="heroName" placeholder="Hero Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-hero-desc" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="heroDesc" placeholder="Description"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-hero-powers" class="col-md-4 control-label">Powers:</label>
                        <div class="col-md-8">
                            <select name="powersToAdd" multiple required>
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
                            <label for="add-power"><a href="${pageContext.request.contextPath}/newPower">Create New Power</a></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Hero"/>
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

