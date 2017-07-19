var Product = (function () {
    var left = 1;
    var productPage =1;
    var categoryId = 0;

    var $left_box = $("#left_event_box");
    var $right_box = $("#right_event_box");

  return {
      productsByCategory : function(){
          var productSource = $("#product_template").html();
          var productTemplate = Handlebars.compile(productSource);

          if(categoryId){
              var URL = "http://localhost:8080/api/products/categories/"+categoryId+"/pages/"+productPage;
          }
          else{
              var URL = "http://localhost:8080/api/products/pages/"+productPage;
          }
          $.ajax({
              url : URL,
              contentType:"application/json",
              type: "get",
              success : function(data){
                  var productData ={
                      item:{
                          
                      }
                  }
                  console.log(data);
                  for(var i=0;i<data.length;i++){
                      productData.item = data[i];

                      var html = productTemplate(productData);
                      if(left){
                          $left_box.append(html);
                          left = 0;
                      }
                      else{
                          $right_box.append(html);
                          left=1;
                      }
                  }
              }
          });
      },
      categoryShow : function(){
          left =1;
          productPage = 1;
          categoryId = $(this).data("category");
      	$(".event_tab_lst.tab_lst_min").find(".active").toggleClass("active");
      	$(this).children().toggleClass("active");
        $left_box.empty();
        $right_box.empty();
        Product.productsByCategory();
      },
      loadProductScroll : function (){
          var maxHeight = $(document).height();
          var currentScroll = $(window).scrollTop() + $(window).height();

          if (maxHeight <= currentScroll + 100) {
              productPage++;
              Product.productsByCategory();
          }
      },
      loadCategory:function(){
        var categorySource = $("#category_template").html();
        var categoryTemplate = Handlebars.compile(categorySource);
      	$list = $(".event_tab_lst.tab_lst_min");

      	var URL = "http://localhost:8080/api/categories";

      	$.ajax({
      		url : URL,
      		contentType:"application/json",
      		type: "get",
      		success : function(data){
      			for(var i=0;i<data.length;i++){
                    var html = categoryTemplate({item:data[i]});
                    $list.append(html);
      				//$list.append('<li class="item" data-category="'+data[i].id+'"><a class="anchor"> <span>'+data[i].name+'</span> </a></li>');
      			}
      		}
      	});
      }
  };

})();
