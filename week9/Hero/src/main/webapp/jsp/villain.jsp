<%-- 
    Document   : villains
    Created on : Oct 23, 2017, 1:58:38 PM
    Author     : kvnzi
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Villains</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container-fluid">
            <center><h1>Vile Individuals Lawlessly Loathing Anyone in Nearby(proximity)</h1></center>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hero">Hero</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/villain">Villain</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/location">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                </ul>    
            </div>
            <div class="col-md-7">
                <h2>Villains</h2>
                <span class="col-md-4">Name</span><span class="col-md-4">Power(s)</span><span class="col-md-4">Description</span>
                <hr><hr>
                <!-- dynamicly load villains here-->
            </div>
            <div class="col-md-5">
                <!--add new villain here-->
                <form>
                    
                </form>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
