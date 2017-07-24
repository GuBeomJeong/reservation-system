
var ProductDetail = (function(){

    var productDetail;
    var productDetailImages;
    var rollingCount=0;
    var commentImages =[

    ];
    var productId = $(location).attr('href').split("/")[4];

    var commentImageSource = $("#comment_image_template").html();
    var commentImageTemplate = Handlebars.compile(commentImageSource);

    var productDetailSource = $("#image_template").html();
    var productDetailTemplate = Handlebars.compile(productDetailSource);

    var commentSource = $("#comment_template").html();
    var commentTemplate = Handlebars.compile(commentSource);

    function makeProductDetailItem(i){
        return {"name":productDetail.name,
                "description" : productDetail.description,
                "fileId" : productDetailImages[i] };
    }

    function drawProductDetail(){



        var productDetailData = {
            item : [

            ]
        };

        productDetailData.item.push(makeProductDetailItem(productDetailImages.length-1));
        for(var i=0;i<productDetailImages.length;i++){
            productDetailData.item.push(makeProductDetailItem(i));
        }
        productDetailData.item.push(makeProductDetailItem(0));  // 양쪽 이미지를 하나씩더 붙이기 위함

        var html = productDetailTemplate(productDetailData);
        $(".main .visual_img").append(html);
        $("#content").text(productDetail.content);
        $("#event_info").text(productDetail.event);
        $(".btn_goto_home").attr("href",productDetail.homepage);
        $("#image_size").text(productDetailImages.length);
        $("#comment_avg").text(productDetail.commentAverage);
        $("#comment_count").text(productDetail.commentCount);
        $("#comment_graph_value").css("width",(productDetail.commentAverage * 20) + "%");
        $("#addr").text(productDetail.placeStreet);
        $("#addr_old").text(productDetail.placeLot);
        $("#store_tel").text(productDetail.tel);
        drawMap();
    }

    function loadProductDetail(){

        var URL = "http://localhost:8080/api/products/"+productId;

        $.ajax({
            url : URL,
            contentType:"application/json",
            type: "get",
            success : function(data){
                console.log(data);

                productDetail = data;
                productDetailImages = data.filesId; // save data

                drawProductDetail();

                var rolling = new Rolling($(".main"),1,0);
                rolling.applyBtn(".btn_prev",".btn_nxt");
                rolling.applyFlicking();

            }
        });
    }

    function drawCommentShort(data){


        var commentData = {
            item : [

            ]
        };

        for(var i=0;i<data.length;i++){
            commentImages[i] = data[i].filesId; // 각각 comment index에 해당하는 이미지 fileid들을 저장
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
    }

    function loadCommentShort(){
        var URL = "http://localhost:8080/api/products/"+productId+"/comments";

        $.ajax({
            url : URL,
            contentType:"application/json",
            type: "get",
            success : function(data){
                console.log(data);

                drawCommentShort(data);

                $(".comment_image_popup").on("click",clickImagePopup);
            }
        });
    }

    function clickImagePopup(e){

        e.preventDefault();

        var $element = $("#comment_image_layer");
        var $list = $element.find(".visual_img");

        $list.css("left",0);
        $list.empty();  // 다시 켜질 때마다 초기화 해주기

        var index = $(this).data("index"); // index를 통해 각 comment에 맞는 이미지를 불러오기 위함
        console.log(index);

        var commentImageData = {
            "commentImageItem": [

            ]
        };

        var fileList = commentImages[index];  // comment에 맞는 이미지 리스트

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
        rolling.applyFlicking();

        $element.find('a.btn-layerClose').on("click",function(){
            //isDim ? $('.dim-layer').fadeOut() : $el.fadeOut();
            $element.fadeOut();
            rolling.offRolling();
            return false;
        });

        // $('.layer .dimBg').click(function(){
        //     $('.dim-layer').fadeOut();
        //     return false;
        // });

    }
    function clickTabInfo(){
        var $base = $(".section_info_tab");
        var $current = $base.find(".active");
        var $detail = $base.find(".detail_area_wrap");
        var $location = $base.find(".detail_location");

        $current.toggleClass("active");
        $(this).toggleClass("active");
        if($(this).parent().hasClass("_detail")){
            $detail.removeClass("hide");
            $location.addClass("hide");
        }else{
            $detail.addClass("hide");
            $location.removeClass("hide");
        }

    }

    function clickMoreDetail(){
        $(this).css("display","none");
        $(this).siblings("a").css("display","");
    }

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
    }

  return {
      init :function(){
          loadProductDetail();
          loadCommentShort();

          $(".section_info_tab > .info_tab_lst").on("click","li > a",clickTabInfo);

          $(".section_store_details > .bk_more").on("click",clickMoreDetail);

          $(".section_btn .bk_btn").on("click",function(){
              var url = "http://localhost:8080/products/"+productId+"/reserve";
              $(location).attr('href',url);
          })
      },
      updateRollingCount : function(flag){
          flag ? rollingCount++:rollingCount--;
          rollingCount = (rollingCount+productDetailImages.length)%(productDetailImages.length);

          $("#image_num").text(rollingCount+1);
      }

  }



})();
