
$(document).ready(function() {
    $(".visual_img").css("left","-338px");

	Product.productsByCategory();

	Product.loadCategory();

    $(document).scroll(Product.loadProductScroll);

});

$(".event_tab_lst.tab_lst_min").on("click","li",Product.categoryShow);
Rolling.intervalRolling(338,$(".event"),1);

$(".event .btn_pre_e").on("click",function(){
  if(!Rolling.getRollingFlag()){
      Rolling.preRolling(338,$(".event"),1);
      Rolling.timeoutRolling(338,$(".event"),1);
  }
});
$(".event .btn_nxt_e,.nxt_fix").on("click",function(){
  if(!Rolling.getRollingFlag()){
      Rolling.nxtRolling(338,$(".event"),1);
      Rolling.timeoutRolling(338,$(".event"),1);
  }
});
