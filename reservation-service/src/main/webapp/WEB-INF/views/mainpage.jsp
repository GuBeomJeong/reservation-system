<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="resources/css/style.css" rel="stylesheet">
</head>

<body>
    <div id="container">
        <div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a href="/login" class="btn_my" id = "naver_id_login"> <span title="내 예약">MY</span> </a>
            </header>
        </div>
        <hr>
        <div class="event">
            <div class="section_visual">
                <div class="group_visual">
                    <div class="container_visual">
                        <div class="prev_e">
                            <div class="prev_inn">
                                <a class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i> </a>
                            </div>
                        </div>
                        <div class="nxt_e">
                            <div class="nxt_inn">
                                <a class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i> </a>
                            </div>
                        </div>
                        <div>
                            <div class="container_visual">
                                <!-- [D] 이전,다음 버튼을 클릭할때마다 캐러셀 형태로 순환 됨 --->
                                <ul class="visual_img" style="left:-338px">
                                    <li class="item" style="background-image: url(http://cfile3.uf.tistory.com/image/2624114F54B3BE251F7023); width: 338px;">
                                        <a href="/detail"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit"></h4>
                                                <p class="event_txt_adr"></p>
                                                <p class="event_txt_dsc"></p>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="item" style="background-image: url(http://naverbooking.phinf.naver.net/20170209_66/1486628146913la6nC_JPEG/image.jpg); width: 338px;">
                                        <a href="/detail"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit"></h4>
                                                <p class="event_txt_adr"></p>
                                                <p class="event_txt_dsc"></p>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="item" style="background-image: url(http://naverbooking.phinf.naver.net/20170119_48/1484802596907hmVDm_JPEG/image.jpg); width: 338px;">
                                        <a href="/detail"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit">뮤지컬-김종욱찾기 네이버 예약</h4>
                                                <p class="event_txt_adr">대학로 쁘띠첼씨어터</p>
                                                <p class="event_txt_dsc">네이버 예매시, 손크림/발크림(중 래덤)을 드립니다</p>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="item" style="background-image: url(https://s-media-cache-ak0.pinimg.com/originals/5d/f3/bf/5df3bf829db19ca53eb822c2a75333e3.jpg); width: 338px;">
                                        <a href="/detail"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit"></h4>
                                                <p class="event_txt_adr"></p>
                                                <p class="event_txt_dsc"></p>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="item" style="background-image: url(http://cfile3.uf.tistory.com/image/2624114F54B3BE251F7023); width: 338px;">
                                        <a href="/detail"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit"></h4>
                                                <p class="event_txt_adr"></p>
                                                <p class="event_txt_dsc"></p>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="item" style="background-image: url(http://naverbooking.phinf.naver.net/20170209_66/1486628146913la6nC_JPEG/image.jpg); width: 338px;">
                                        <a href="/detail"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit"></h4>
                                                <p class="event_txt_adr"></p>
                                                <p class="event_txt_dsc"></p>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <span class="nxt_fix"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section_event_tab">
                <ul class="event_tab_lst tab_lst_min">
                    <li class="item" data-category="0">
                        <a class="anchor active"> <span>전체</span> </a>
                    </li>
                    <script id="category_template" type="text/x-handlebars-template">
                    {{#item}}
                    <li class="item" data-category={{id}}>
                        <a class="anchor"> <span>{{name}}</span> </a>
                    </li>
                    {{/item}}
                    </script>

                </ul>
            </div>

            <div class="section_event_lst">
                <p class="event_lst_txt">바로 예매 가능한 전시, 공연, 행사가 <span class="pink">40개</span> 있습니다</p>

                <div class="wrap_event_box">
                    <ul class="lst_event_box" id="left_event_box">

                    </ul>
                    <ul class="lst_event_box" id ="right_event_box">

                    </ul>
                    <!-- 더보기 -->
                    <div class="more">
                        <button class="btn"><span>더보기</span></button>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>
</body>

<script id="product_template" type="text/x-handlebars-template">
<!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
{{#item}}
<li class="item">
    <a href="/products/{{id}}" class="item_book">
        <div class="item_preview"> <img alt="" class="img_thumb" src="http://localhost:8080/files/{{fileId}}"><span class="img_border"></span> </div>
        <div class="event_txt">
            <h4 class="event_txt_tit"> <span>{{name}}</span> <small class="sm">{{placeName}}</small> </h4>
            <p class="event_txt_dsc">{{description}}
            </p>
        </div>
    </a>
</li>
{{/item}}
</script>


<script src="/resources/js/libs/jquery.min.js"></script>
<script src="/resources/js/libs/handlebars-v4.0.10.js"></script>

<script src="/resources/js/rolling.js"></script>
<script src="/resources/js/reservationMain.js"></script>
<script src="/resources/js/main.js"></script>




</html>
