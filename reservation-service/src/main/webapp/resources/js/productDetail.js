
var ProductDetail = (function(){

    var productDetail;
    var imageItems;
    var rollingCount=0;
    var commentImages =[

    ];

    var commentImageSource = $("#comment_image_template").html();
    var commentImageTemplate = Handlebars.compile(commentImageSource);

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
                  productDetailData.item.push(makeProductDetail(0));  // 양쪽 이미지를 하나씩더 붙이기 위함

                  var html = productDetailTemplate(productDetailData);
                  $(".main .visual_img").append(html);
                  $("#content").text(productDetail.content);
                  $("#event_info").text(productDetail.event);
                  $(".btn_goto_home").attr("href",productDetail.homepage);
                  $("#size").text(imageItems.length);
                  $("#comment_avg").text(productDetail.commentAverage);
                  $("#comment_count").text(productDetail.commentCount);
                  $("#comment_graph_value").css("width",(productDetail.commentAverage * 20) + "%")
                  $("#addr").text(productDetail.placeStreet);
                  $("#addr_old").text(productDetail.placeLot);
                  $("#store_tel").text(productDetail.tel);
                  drawMap();

                  var rolling = new Rolling($(".main"),1,0);
                  rolling.applyBtn(".btn_prev",".btn_nxt");
                  rolling.applyFlicking();

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
                  };
                  var commentItem;

                  for(var i=0;i<data.length;i++){
                      commentImages[i] = data[i].filesId;
                      commentData.item.push({
                          "index" : i,
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
                  $(".comment_image_popup").on("click",ProductDetail.clickImagePopup);
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
      },
      clickImagePopup : function(e){

        e.preventDefault();

        var $element = $("#comment_image_layer");
        var $list = $element.find(".visual_img");

        $list.css("left",0);
        $list.empty();


        var index = $(this).data("index");
        console.log(index);


        var commentImageData = {
            "commentImageItem": [

            ]
        };

        var fileList = commentImages[index];

        for(var i=0;i<fileList.length;i++){
            commentImageData.commentImageItem.push({"fileId":fileList[i]});
        }
        console.log(commentImageData);

        //isDim ? $('.dim-layer').fadeIn() : $element.fadeIn();
        $element.fadeIn();

        $element.css("top", Math.max(0, (($(window).height() - $element.outerHeight()) / 2) + $(window).scrollTop()) + "px");
        $element.css("left", Math.max(0, (($(window).width() - $element.outerWidth()) / 2) + $(window).scrollLeft()) + "px");

        var html = commentImageTemplate(commentImageData);
        $list.append(html);

        var rolling = new Rolling($element,0,0);
        rolling.applyBtn(".btn_prev",".btn_nxt");

        $element.find('a.btn-layerClose').click(function(){
            //isDim ? $('.dim-layer').fadeOut() : $el.fadeOut();
            $element.fadeOut();
            rolling.offBtn();
            return false;
        });

        // $('.layer .dimBg').click(function(){
        //     $('.dim-layer').fadeOut();
        //     return false;
        // });

    }
  }



})();
