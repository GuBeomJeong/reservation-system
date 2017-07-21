
$(document).ready(function() {
    $(".visual_img").css("left","-338px");

	Product.productsByCategory();

	Product.loadCategory();

    $(document).scroll(Product.loadProductScroll);

    var rolling = new Rolling($(".event"),1,1);
    rolling.init(".btn_pre_e",".btn_nxt_e,.nxt_fix");

});


$(".event_tab_lst.tab_lst_min").on("click","li",Product.categoryShow);

