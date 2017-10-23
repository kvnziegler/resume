<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Vending Machine</title>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/home.css">
    </head>

    <body>
        <div class="container-fluid">
            <div id="title">
                <h1><b>Catch a Snaaack!!</b></h1>
            </div>
            <hr/>

            <div class="column col-md-8" id="productDiv" style="text-align: center">
                <c:forEach var="currentStock" items="${stockList}">

                    <div id="item${currentStock.id}" class='item col-md-3 col-md-offset-1' style='border: 1px solid black; margin: 10px 20px'>
                        ${currentStock.id}<br/> ${currentStock.name}<br/>  $${currentStock.price}<br/> ${currentStock.stock}
                    </div>
                </c:forEach>                  
            </div>
            <div class="col-md-4 column" id="uiDiv">
                <form id="messages" class="col-md-12 row" role="form" modelAttribute="stock"
                      action="${pageContext.request.contextPath}/" method="POST">
                    <div>
                        <div id="totalIn">
                            <span>Total $ In</span><br/>
                        </div>
                        <div id="totalInput">   

                            <c:choose>
                                <c:when test="${ not empty outOfStock}">
                                    <input name="moneyHere" readonly="readonly"
                                           id="inputField" type="number" step="0.01"  value="${newMoney}"></input>
                                </c:when>
                                <c:when test="${ not empty notEnoughMoney}">
                                    <input name="moneyHere" readonly="readonly" 
                                           id="inputField" type="number" step="0.01" value="${newMoney}"></input>
                                </c:when>
                                <c:when test="${ not empty thanks}">
                                    <input name="moneyHere" readonly="readonly" 
                                           id="inputField" type="number" step="0.01" value="${newMoney}"></input>
                                </c:when>
                                <c:when test="${ not empty jumpTheGun}">
                                    <input name="moneyHere" readonly="readonly" 
                                           id="inputField" type="number" step="0.01" value="${jumpTheGun}"></input>
                                </c:when>
                                <c:otherwise>
                                    <input name="moneyHere" readonly="readonly" 
                                           id="inputField" type="number" step="0.01" value="0"></input><br/>
                                </c:otherwise>
                            </c:choose>

                            <!--<input name="moneyHere" readonly="readonly" id="inputField" type="number" step="0.01" value="0"></input><br/>-->
                        </div>
                        <div id="moneyButtons">
                            <button type="button" id="addDollar" class="addButtons">Add Dollar</button><button type="button" id="addQuarter" class="addButtons">Add Quarter</button><br/>
                            <button type="button" id="addDime" class="addButtons">Add Dime</button><button type="button" id="addNickel" class="addButtons">Add Nickel</button>
                        </div>
                        <hr/>
                    </div>
                    <div>
                        <div id="messageText">
                            <span id="messages">Messages</span><br/>
                        </div>
                        <div id="messageoutput" class="messageInOutput">
                            <c:choose>
                                <c:when test="${ not empty outOfStock}">
                                    <input readonly="readonly" id="responseLine" value="${outOfStock}"></input>
                                </c:when>
                                <c:when test="${ not empty notEnoughMoney}">
                                    <input readonly="readonly" id="responseLine" value="${notEnoughMoney}"></input>
                                </c:when>
                                <c:when test="${ not empty thanks}">
                                    <input readonly="readonly" id="responseLine" value="${thanks}"></input>
                                </c:when>
                                <c:otherwise>
                                    <input readonly="readonly" id="responseLine"><br/></input>
                                </c:otherwise>
                            </c:choose>
                            <!--<input readonly="readonly" id="responseLine"></input><br/>-->
                        </div>
                        <div id ="messageScreen" class="messageInOutput">
                            Item: <input name="numberInput" type="number" id="numberInput" path="numberInput" readonly="readonly"></input><br/>
                        </div>
                        <div id="purchaseButton">
                            <button type="submit" id="purchase">Make Purchase</button>
                        </div>
                        <hr/>
                    </div>
                    <div>
                        <div id="changeDiv">
                            <span id="change">Change</span><br/>
                        </div>
                        <div id="changeOutput">
                            <input id="changeSlot" readonly="readonly" type "string"></input><br/>
                        </div>
                        <div id="returnButton">
                            <button type="button" id="returnChange">Return Change</button>
                        </div>
                    </div>
                    <hr/>
                </form>
            </div>
        </div>
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/home.js"></script>
    </body>

</html>

