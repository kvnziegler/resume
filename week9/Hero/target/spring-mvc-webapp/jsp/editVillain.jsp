<%-- 
    Document   : editVillain
    Created on : Oct 26, 2017, 9:46:51 AM
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
        <title>Edit Villain</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/img/villain.jpg" style="background-size: cover">
        <div class="container-fluid">
            <h1>Edit Villain</h1>
            <hr/>
            <form class="form-horizontal" 
                  role="form" method="POST" 
                  action="editVillain" style="background:rgba(255,255,255,.7)">
                <hidden>
                    <div class="form-group">
                        <label for="add-hero-id" class="col-md-4 control-label">Hero ID:</label>
                        <div class="col-md-8">
                            <input readonly="readonly" type="text" class="form-control" name="heroID" placeholder="Hero ID" value="${champion.championID}"/>
                        </div>
                    </div>
                </hidden>
                <div class="form-group">
                    <label for="add-hero-name" class="col-md-4 control-label">Hero Name:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="heroName" placeholder="Hero Name" value="${champion.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-hero-desc" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="heroDesc" placeholder="Description" value="${champion.championDesc}"/>
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
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Villain"/>
                    </div>
                </div>
            </form><br/><br/>
            <div class='col-md-offset-6'>
                    <a href="${pageContext.request.contextPath}/villain" style="color:white">Back to Villains</a>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
