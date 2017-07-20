var productId = $('body').attr("id");

$(document).ready(function() {
    ProductDetail.productDetailLoad(productId);
    ProductDetail.commentShortLoad(productId);


});

$(".main .btn_prev").on("click",function(){
  if(!Rolling.getRollingFlag()){
      Rolling.preRolling(414,$(".main"),1);
      ProductDetail.updateRollingCount(0);
  }
});
$(".main .btn_nxt").on("click",function(){
  if(!Rolling.getRollingFlag()){
      Rolling.nxtRolling(414,$(".main"),1);
      ProductDetail.updateRollingCount(1);
  }
});



$(".section_info_tab > .info_tab_lst").on("click","li > a",ProductDetail.clickTabInfo);

$(".section_store_details > .bk_more").on("click",ProductDetail.clickMoreDetail);
