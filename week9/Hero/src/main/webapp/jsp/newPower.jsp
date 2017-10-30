<%-- 
    Document   : newPower
    Created on : Oct 26, 2017, 9:47:46 AM
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
        <title>Powers</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/img/hunger.jpg" style="background-size: cover">
        <div class="container-fluid">
            <h1>Create a Power</h1>
        </div>
        <div class="col-md-7" style="background:rgba(255,255,255,.7)">
                <h2>Powers</h2>
                <table id="heroTable" class="table table-hover">
                    <tr>
                        <th width="40%">Name</th>
                        <th width="30%">Description</th>
                        <th width="15%"></th>
                    </tr>
                    <c:forEach var="currentPower" items="${powerList}">                        
                            <tr>
                                <td>
                                    <c:out value="${currentPower.name}"/>
                                </td>
                                <td>
                                    <c:out value="${currentPower.powerDesc}"/>
                                </td>
                                <td>
                                    <a href="deletePower?powerId=${currentPower.powerID}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                    </c:forEach>
                </table>
                <!-- dynamicly load heros here-->
            </div>
            <div class="col-md-5" style="background:rgba(255,255,255,.7)">
                <!--add new hero here-->
                <h2>Add New Power</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createPower">
                    <div class="form-group">
                        <label for="add-power-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="powerName" placeholder="Flying"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-power-desc" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="powerDesc" placeholder="Ability to leave the ground for an extended period"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Power"/>
                        </div>
                    </div>
                </form><br><br>
                <a class="col-md-offset-6" href="${pageContext.request.contextPath}/hero">Return to Heroes</a><br/><br/>
                <a class="col-md-offset-6" href="${pageContext.request.contextPath}/villain">Return to Villains</a>
            </div>   
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
