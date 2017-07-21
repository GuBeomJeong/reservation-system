var productId = $('body').attr("id");

$(document).ready(function() {
    ProductDetail.productDetailLoad(productId);
    ProductDetail.commentShortLoad(productId);



});

$(".section_info_tab > .info_tab_lst").on("click","li > a",ProductDetail.clickTabInfo);

$(".section_store_details > .bk_more").on("click",ProductDetail.clickMoreDetail);
