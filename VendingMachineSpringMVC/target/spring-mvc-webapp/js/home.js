$(document).ready(function () {

    //loadOrders();

//no ajax calls must contact sever to do all inventory actions

    $('#addDollar').on('click', function () {

        var total = parseFloat($("#inputField").val());
        var added = 0;
        added = (total + 1).toFixed(2);
        $("#inputField").val(added);
    });

    $('#addDime').on('click', function () {

        var total = parseFloat($("#inputField").val());
        var added = 0;
        added = (total + 0.1).toFixed(2);
        $("#inputField").val(added);
    });

    $('#addQuarter').on('click', function () {

        var total = parseFloat($("#inputField").val());
        var added = 0;
        added = (total + 0.25).toFixed(2);
        $("#inputField").val(added);
    });

    $('#addNickel').on('click', function () {

        var total = parseFloat($("#inputField").val());
        var added = 0;
        added = (total + 0.05).toFixed(2);
        $("#inputField").val(added);
    });

    $("[id^=item]").on("click", function () {
        $("#numberInput").val(this.getAttribute("id").substring(4));
        //alert(this.getAttribute("id").substring(4));
    });


    $('#returnChange').on('click', function () {

        var totalReturn = parseFloat($("#inputField").val());

        var quarters = Math.floor((totalReturn / .25));
        var quartersVal = quarters * .25;
        var total2 = totalReturn - quartersVal;
        var dimes = Math.floor((total2 / 0.1));
        var dimesVal = dimes * 0.1;
        var total3 = total2 - dimesVal;
        var nickels = Math.floor((total3 / .05));
        var nickelsVal = nickels * .05;
        var total4 = total3 - nickelsVal;
        var pennies = Math.floor((total4 / .01));
        var penniesVal = pennies * .01;
        var total5 = total4 - pennies;

        $("#inputField").val(0);
        $("#numberInput").val("");
        $("#responseLine").val("Come Again");
        $("#changeSlot").val("Q: " + quarters + " | D: " + dimes + " | N: " + nickels + " |P: " + pennies);

    });



//    var maxId = 0;
//    $.ajax({
//        type: 'GET',
//        url: 'https://llama-vending.herokuapp.com/items',
//        success: function (data) {
//            loadOrders();
//            
//            $.each(data, function (index, item) {
//                var i = item.id;
//                var text = "<div id='item" + i + "' class='item col-md-3 col-md-offset-1' style='border: 1px solid black; margin: 10px 20px'>"
//                    + item.id + "<br/>" + item.name + "<br/>" + item.price + "<br/>" + item.quantity + "</div>";
//                $("#productDiv").append(text);
//                maxId = item.id;
//
//                
//            });
//            $("#itemBox").attr('max', parseInt(maxId));
//
//            $("[id^=item]").on("click", function () {
//                $("#numberInput").attr("value", this.getAttribute("id").substring(4));
//                // alert(this.getAttribute("id").substring(4));
//            });
//        },
//        error: function () {
//             $("#responseLine").val("out of Order");
//        }
//    });

});

//function loadOrders(){

//    $("#purchase").on('click', function (change) {
//        var money = $("#inputField").val();
//        var item = $("#numberInput").val();
//        $.ajax({
//
//            type: 'GET',
//            url: "https://llama-vending.herokuapp.com/money/" + money + "/item/" + item,
//            success: function (change) {
//
//                //keep a running total of money in  and only return change when requested
//
//                var total = parseFloat($("#inputField").val());
//                var changeDue = (change.quarters * 0.25) + (change.dimes * 0.1) + (change.nickels * 0.05) + (change.pennies * 0.01);
//                $("#inputField").val((changeDue).toFixed(2));
//
//                //return change after every purchase
//                // var changeDue = ("Q: " + change.quarters + " | D: " + change.dimes +  " | N: " + change.nickels + " | P: " +change.pennies);
//                // $("#changeSlot").val(changeDue)
//                // $("#inputField").val(0);
//
//                $("#responseLine").val("Thank You!!!");
//            },
//            error: function (error) {
//                $("#responseLine").val(error.responseJSON.message);
//
//            }
//
//        });
//    });
//};