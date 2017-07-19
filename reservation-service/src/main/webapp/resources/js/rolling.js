var Rolling = (function(){
    var rollingCount=0;
    var intervalId ;
    var timeoutId;
    var rollingSize=$(".visual_img > li").size()-3;
    var rollingFlag = 0;

    return {
        timeoutRolling : function (){
           clearInterval(intervalId);
           clearTimeout(timeoutId);

           timeoutId = setTimeout(function(){
               intervalId = setInterval(Rolling.nxtRolling, 2000);
           }, 4000);
        },
        getRollingCount : function(){
            return rollingCount;
        },
        setRollingSize : function(size){
            rollingSize = size;
        },
        intervalRolling : function(){

            intervalId = setInterval(Rolling.nxtRolling, 2000);

        },
        getRollingFlag : function(){
            return rollingFlag;
        },
        // rolling에 flag를 줘서 이벤트 발생 도중이면 이벤트를 스킵
        // animate에 callback함수 등록
         nxtRolling:function(width){
            rollingFlag=1;
        	if(rollingCount< rollingSize){
                $(".visual_img").animate({ "left": "-="+width}, "slow",function(){
                  rollingFlag = 0;
                  rollingCount++;
                });

        	}else{
                $(".visual_img").css("left","0px");
                $(".visual_img").animate({ "left": "-="+width}, "slow",function(){
                    rollingFlag = 0;
                    rollingCount = 0;
                });
            }
        },
         preRolling:function(width){
            rollingFlag=1;
            if(rollingCount>0){
        		$(".visual_img").animate({ "left": "+="+width }, "slow",function(){
                    rollingFlag=0;
                    rollingCount--;
                } );
        	}else{
                $(".visual_img").animate({ "left": "+="+width }, "slow",function(){
                    $(".visual_img").css('left',-(width*(rollingSize+1)));
                    rollingFlag=0;
                    rollingCount = rollingSize;
                });
            }


        }

    }
})();
