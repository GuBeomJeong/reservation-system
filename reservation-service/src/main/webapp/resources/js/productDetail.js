
var ProductDetail = (function(){

    var productDetail;
    var imageItems;
    var rollingCount=0;

    function makeProductDetail(i){
        return {"name":productDetail.name,
                "description" : productDetail.description,
                "fileId" : imageItems[i] }
    };


    function drawMap(){
        var map = new naver.maps.Map('map');
        var myaddress = '서울시 강남구 역삼동 825-2';// 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)
        naver.maps.Service.geocode({address: myaddress}, function(status, response) {
        if (status !== naver.maps.Service.Status.OK) {
            return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
        }
        var result = response.result;
        // 검색 결과 갯수: result.total
        // 첫번째 결과 결과 주소: result.items[0].address
        // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
        var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
        map.setCenter(myaddr); // 검색된 좌표로 지도 이동
        // 마커 표시
        var marker = new naver.maps.Marker({
          position: myaddr,
          map: map
        });
        // 마커 클릭 이벤트 처리
        naver.maps.Event.addListener(marker, "click", function(e) {
          if (infowindow.getMap()) {
              infowindow.close();
          } else {
              infowindow.open(map, marker);
          }
        });
        // 마크 클릭시 인포윈도우 오픈
        var infowindow = new naver.maps.InfoWindow({
            content: '<h4> [네이버 개발자센터]</h4><a href="https://developers.naver.com" target="_blank"><img src="https://developers.naver.com/inc/devcenter/images/nd_img.png"></a>'
        });
    });
    };

  return {
      updateRollingCount : function(flag){
          flag ? rollingCount++:rollingCount--;
          rollingCount = (rollingCount+imageItems.length)%(imageItems.length);

          $("#num").text(rollingCount+1);
      },
      productDetailLoad : function(productId){
          var productDetailSource = $("#image_template").html();
          var productDetailTemplate = Handlebars.compile(productDetailSource);

          var URL = "http://localhost:8080/api/products/"+productId;

          $.ajax({
              url : URL,
              contentType:"application/json",
              type: "get",
              success : function(data){
                  console.log(data);
                  var productDetailData = {
                      item : [

                      ]
                  }
                  productDetail = data;
                  imageItems = data.filesId;

                  productDetailData.item.push(makeProductDetail(imageItems.length-1));
                  for(var i=0;i<imageItems.length;i++){
                        productDetailData.item.push(makeProductDetail(i));
                  }
                  productDetailData.item.push(makeProductDetail(0));
                  var html = productDetailTemplate(productDetailData);
                  $(".visual_img").append(html);
                  $("#content").text(productDetail.content);
                  $("#event_info").text(productDetail.event);
                  $(".btn_goto_home").attr("href",productDetail.homepage);
                  $("#size").text(imageItems.length);
                  $("#comment_avg").text(productDetail.commentAverage);
                  $("#comment_count").text(productDetail.commentCount);
                  $("#addr").text(productDetail.placeStreet);
                  $("#addr_old").text(productDetail.placeLot);
                  $("#store_tel").text(productDetail.tel);
                  drawMap();

                  Rolling.setRollingSize(imageItems.length-1);
              }
          });
      },
      commentShortLoad : function(productId){
          var commentSource = $("#comment_template").html();
          var commentTemplate = Handlebars.compile(commentSource);

          var URL = "http://localhost:8080/api/comments/products/"+productId;
          $.ajax({
              url : URL,
              contentType:"application/json",
              type: "get",
              success : function(data){
                  console.log(data);
                  var commentData = {
                      item : [

                      ]
                  }
                  var commentItem;

                  for(var i=0;i<data.length;i++){
                      commentData.item.push({
                          "comment" : data[i].comment,
                          "score" : data[i].score,
                          "date" : data[i].createDate,
                          "fileId" : data[i].filesId[0],
                          "imageCount" : data[i].filesId.length,
                          "userName" : data[i].userName
                      })
                  }

                  var html = commentTemplate(commentData);
                  $(".list_short_review").append(html);
              }
          });
      },
      clickTabInfo : function(){
          var $root = $(".section_info_tab");
          var $current = $root.find(".active");
          var $detail = $root.find(".detail_area_wrap");
          var $location = $root.find(".detail_location");

          $current.toggleClass("active");
          $(this).toggleClass("active");
          if($(this).parent().hasClass("_detail")){
              $detail.removeClass("hide");
              $location.addClass("hide");
          }else{
              $detail.addClass("hide");
              $location.removeClass("hide");
          }

      },
      clickMoreDetail : function(){
          $(this).css("display","none");
          $(this).siblings("a").css("display","");
      }
  }



})();
