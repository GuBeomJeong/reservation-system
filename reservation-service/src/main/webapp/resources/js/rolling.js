var Rolling = (function(){
    var rollingCount=0;
    var intervalId ;
    var timeoutId;
    var rollingSize=$(".visual_img > li").size()-3;
    var rollingFlag = 0;

    return {
        timeoutRolling : function (width,base,cycleFlag){
           clearInterval(intervalId);
           clearTimeout(timeoutId);

           timeoutId = setTimeout(function(){
               intervalId = setInterval(Rolling.nxtRolling.bind(this,width,base,cycleFlag), 2000);
           }, 4000);
        },
        setRollingCount : function(count){
            rollingCount = count;
        },
        getRollingCount : function(){
            return rollingCount;
        },
        getRollingSize : function(){
            return rollingSize;
        },
        setRollingSize : function(size){
            rollingSize = size;
        },
        intervalRolling : function(width,base,cycleFlag){

            intervalId = setInterval(Rolling.nxtRolling.bind(this,width,base,cycleFlag), 2000);

        },
        getRollingFlag : function(){
            return rollingFlag;
        },
        // rolling에 flag를 줘서 이벤트 발생 도중이면 이벤트를 스킵
        // animate에 callback함수 등록
         nxtRolling:function(width,base,cycleFlag){
            var $img = base.find(".visual_img");

        	if(rollingCount< rollingSize){
                $img.animate({ "left": "-="+width}, {
                    duration:500,
                    start:function(){
                        rollingFlag=1;
                    },
                    complete:function(){
                        rollingFlag = 0;
                        rollingCount++;
                    }
                });
        	}else if(cycleFlag){
                $img.css("left","0px");
                $img.animate({ "left": "-="+width}, {
                    duration:500,
                    start:function(){
                        rollingFlag=1;
                    },
                    complete:function(){
                        rollingFlag = 0;
                        rollingCount = 0;
                    }
                });
            }
        },
         preRolling:function(width,base,cycleFlag){
            var $img = base.find(".visual_img");

            if(rollingCount>0){
        		$img.animate({ "left": "+="+width }, {
                    duration:500,
                    start:function(){
                        rollingFlag=1;
                    },
                    complete:function(){
                        rollingFlag = 0;
                        rollingCount--;
                    }
                });
        	}else if(cycleFlag){
                $img.animate({ "left": "+="+width },{
                    duration:500,
                    start:function(){
                        rollingFlag=1;
                    },
                    complete:function(){
                        rollingFlag = 0;
                        rollingCount =rollingSize;
                    }
                });
            }
        }

    }
})();
