
$(document).ready(function() {
    $(".visual_img").css("left","-338px");

	Product.productsByCategory();

	Product.loadCategory();

    $(document).scroll(Product.loadProductScroll);

    var rolling = new Rolling($(".event"),1,1);
    rolling.applyBtn(".btn_pre_e",".btn_nxt_e,.nxt_fix");
    rolling.intervalRolling();


});


$(".event_tab_lst.tab_lst_min").on("click","li",Product.categoryShow);

