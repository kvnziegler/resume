<%-- 
    Document   : newContact
    Created on : Oct 26, 2017, 9:47:58 AM
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
        <title>Contacts</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/img/batphone.gif" style="background-size: cover">
        <div class="container-fluid">
            <h1 style="color:white">Create a new Contact</h1>
        </div>
        <div class="col-md-7" style="background:rgba(255,255,255,.7)">
                <h2 style="color:white">Contacts</h2>
                <table id="heroTable" class="table table-hover">
                    <tr>
                        <th width="40%">email</th>
                        <th width="30%">phone</th>
                        <th width="15%"></th>
                    </tr>
                    <c:forEach var="currentCont" items="${contList}">                        
                            <tr>
                                <td>
                                    <c:out value="${currentCont.email}"/>
                                </td>
                                <td>
                                    <c:out value="${currentCont.phone}"/>
                                </td>
                                <td>
                                    <a href="deleteContact?contactId=${currentCont.contactId}">
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
                <h2>Add New Contact</h2>
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createContact">
                    <div class="form-group">
                        <label for="add-cont-phone" class="col-md-4 control-label">Phone:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="phone" placeholder="1234567"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-hero-email" class="col-md-4 control-label">Email:</label>
                        <div class="col-md-8">
                            <input required type="text" class="form-control" name="email" placeholder="Batman@WayneEnterprises.com"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Create Contact"/>
                        </div>
                    </div>
                </form><br><br>
                <a class="col-md-offset-6" href="${pageContext.request.contextPath}/organization">Return to Organizations</a>
            </div>   
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
