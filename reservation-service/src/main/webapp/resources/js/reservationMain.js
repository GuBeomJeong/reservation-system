var ReservationMain = (function () {
    var lastProductId = 0;
    var categoryId = 0;
    var scrollLock = 1;

    var $left_box = $("#left_event_box");
    var $right_box = $("#right_event_box");

    var $categoryList = $(".event_tab_lst.tab_lst_min");

    var productSource = $("#product_template").html();
    var productTemplate = Handlebars.compile(productSource); // compile은 한번만

    var categorySource = $("#category_template").html();
    var categoryTemplate = Handlebars.compile(categorySource);

    function drawProductsByCategory(data){

        var productLeftData ={
            item:[

            ]
        };

        var productRightData ={
            item:[

            ]
        };

        console.log(data);
        for(var i=0;i<data.length;i++){
            if(i%2) {
                productRightData.item.push(data[i]);
            }
            else{
                productLeftData.item.push(data[i]);
            }
        }
        var leftHtml = productTemplate(productLeftData);
        var rightHtml = productTemplate(productRightData);
        $left_box.append(leftHtml);
        $right_box.append(rightHtml);   // append를 두번만 (dom 조작을 최소화)

    }

    function loadProductsByCategory(){
        scrollLock =1;
        if(categoryId){
            var URL = "http://localhost:8080/api/categories/"+categoryId+"/products?lastProductId="+lastProductId;
        }
        else{
            var URL = "http://localhost:8080/api/products?lastProductId="+lastProductId;
        }
        $.ajax({
            url : URL,
            contentType:"application/json",
            type: "get",
            success : function(data){
                if(data.length) {
                    lastProductId = data[data.length - 1].id;
                    drawProductsByCategory(data);
                    scrollLock = 0;
                }
            }
        });
    }

    function clickCategory(e){
        lastProductId = 0;
        categoryId = $(e.currentTarget).data("category");
        $categoryList.find(".active").toggleClass("active");
        $(e.currentTarget).find(".anchor").toggleClass("active");
        $left_box.empty();
        $right_box.empty();
        loadProductsByCategory();
    }

    function loadProductByScroll(){
        var maxHeight = $(document).height();
        var currentScroll = $(window).scrollTop() + $(window).height();

        if (maxHeight <= currentScroll + 100 && scrollLock === 0 ) {
            loadProductsByCategory();
        }
    }

    function drawCategory(data){

        var categoryData={
            item:[

            ]
        };
        for(var i=0;i<data.length;i++){
            categoryData.item.push(data[i]);
        }
        var html = categoryTemplate(categoryData);
        $categoryList.append(html);
    }

    function loadCategory(){

        var URL = "http://localhost:8080/api/categories";

        $.ajax({
            url : URL,
            contentType:"application/json",
            type: "get",
            success : function(data){
                drawCategory(data);
            }
        });
    }

  return {
        init : function(){
            loadProductsByCategory();

            loadCategory();

            $(document).scroll(loadProductByScroll);


            var rolling = new Rolling($(".event"),1,1);
            rolling.applyBtn(".btn_pre_e",".btn_nxt_e,.nxt_fix");
            rolling.intervalRolling();

            $categoryList.on("click","li",clickCategory);
        }
  };

})();

