
var ProductReserve = (function(){

    var productId = $(location).attr('href').split("/")[4];

    var priceSource = $("#price_template").html();
    var priceTemplate = Handlebars.compile(priceSource);
    var userId;

    var reservePrice;
    var productDetail;
    var productPrices;

    function loadProductReserve(){
        var URL = "http://localhost:8080/api/products/"+productId+"/reserve";

        $.ajax({
            url : URL,
            contentType:"application/json",
            type: "get",
            success : function(data){
                console.log(data);
                productDetail = data.productDetail;
                productPrices = data.productPrices;

                console.log(productPrices);

                reservePrice = new Array(productPrices.length);

                var priceItemData = {
                    item : [

                    ]
                };

                for(var i=0;i<productPrices.length;i++){
                    reservePrice[i] = new ReservePrice(productPrices[i],i);
                    priceItemData.item.push(reservePrice[i].makeItem());
                }

                var html = priceTemplate(priceItemData);
                $(".ticket_body").append(html);

                for(var i=0;i<productPrices.length;i++){
                    reservePrice[i].init();
                }
            }
        });
    }

    function drawUserInfo(data){
        $("#name").attr("value",data.username);
        $("#tel").attr("value",data.tel);
        $("#email").attr("value",data.email);
    }

    function loadUserInfo(){
        var URL = "http://localhost:8080/api/user";

        $.ajax({
            url : URL,
            contentType:"application/json",
            type: "get",
            success : function(data) {
                userId = data.id;
                drawUserInfo(data);
            }
        });

    }

    function submitReservation(){
        var URL = "http://localhost:8080/api/reservation";

        var priceType = [0,0,0]

        for(var i=0;i<reservePrice.length;i++){
            priceType[reservePrice[i].type-1] = reservePrice[i].count;
        }

        var reservationInfo = {
            "productId":productId,
            "userId":userId,
            "generalTicketCount":priceType[0],
            "youthTicketCount":priceType[1],
            "childTicketCount":priceType[2],
            "reservationName":$("#name").val(),
            "reservationTel":$("#tel").val(),
            "reservationEmail":$("#email").val(),
            "reservationDate":"2017-07-24",
            "reservationType":0
        }

        $.ajax({
            url : URL,
            contentType:"application/json",
            type: "post",
            data: JSON.stringify(reservationInfo),
            success : function() {
                $(location).attr('href', '/');
            }
        });
    }

    function checkValid(){
        var regexEmail = /\w+@\w+\.[a-zA-Z]+/;
        var regexTel = /0\d{1,2}-\d{3,4}-\d{4}/;

        if($("#name").val() != "" && regexTel.test($("#tel").val()) &&
            regexEmail.test($("#email").val()) && $("#chk3").is(":checked") && ReservePrice.countSum>0){
            $(".box_bk_btn .bk_btn_wrap").removeClass("disable");
        }else{
            $(".box_bk_btn .bk_btn_wrap").addClass("disable");
        }


    }

    function showAgreement() {
        $(this).parent(".agreement").toggleClass("open");
    }
    return {
        init : function(){
            loadProductReserve();
            loadUserInfo();

            $("#name,#tel,#email").on("keyup",checkValid);
            $("#chk3").on("change",checkValid);
            $(".box_bk_btn .bk_btn_wrap").on("click",function(){
                if(!$(".box_bk_btn .bk_btn_wrap").hasClass("disable")) {
                    submitReservation();
                }
            });
            $(".section_booking_agreement").on("click",".btn_agreement",showAgreement);

        },
        checkValid : checkValid
    }


})();

function ReservePrice(productPrices,index){
    this.count=0;
    this.type = productPrices.priceType;
    this.price = productPrices.price;
    this.discountPrice = this.price * (1-productPrices.discountRate);
    this.discount = productPrices.discountRate*100;
    this.index = index;
    this.base;
    if(productPrices.priceType === 1){
        this.typeValue = "성인";
    }else if(productPrices.priceType === 2){
        this.typeValue = "청소년";
    }else if(productPrices.priceType === 3){
        this.typeValue= "아동";
    }
}



ReservePrice.countSum = 0;
ReservePrice.constructor = ReservePrice;
ReservePrice.prototype = new eg.Component();
ReservePrice.prototype.increment = function(){
    this.count++;
    ReservePrice.countSum++;
    console.log(ReservePrice.countSum);

    //this.trigger("increment");
    if(this.count === 1){
        $(this.base).find(".ico_minus3").toggleClass("disabled")
        $(this.base).find(".individual_price").addClass("on_color");
    }

    $(this.base).find(".count_control_input").attr("value",this.count);
    $(this.base).find(".total_price").text(this.count*this.discountPrice);
    $("#reservation_final").text("총 "+ReservePrice.countSum+"매");
    ProductReserve.checkValid();


};
ReservePrice.prototype.decrement = function(){
    if(this.count) {
        this.count--;
        ReservePrice.countSum--;
        //this.trigger("decrement");
        if(this.count === 0){
            $(this.base).find(".ico_minus3").toggleClass("disabled");
            $(this.base).find(".individual_price").removeClass("on_color");
        }
        $(this.base).find(".count_control_input").attr("value", this.count);
        $(this.base).find(".total_price").text(this.count * this.discountPrice);
        $("#reservation_final").text("총 "+ReservePrice.countSum+"매");
        ProductReserve.checkValid();
    }
};
ReservePrice.prototype.makeItem = function(){
    return {
        "type_value" : this.typeValue,
        "count" : this.count,
        "price" : this.price,
        "discount" : this.discount,
        "total_price" : this.discountPrice * this.count,
        "discount_price" : this.discountPrice
    }
};
ReservePrice.prototype.init = function(){
    this.base = $(".ticket_body .qty").eq(this.index);
    $(this.base).on("click",".ico_plus3",this.increment.bind(this));
    $(this.base).on("click",".ico_minus3",this.decrement.bind(this));

};


$(document).ready(function() {

    ProductReserve.init();


});