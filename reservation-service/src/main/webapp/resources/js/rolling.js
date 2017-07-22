var Rolling = function(base,cycleFlag,timeoutFlag){
    this.rollingCount=0;
    this.intervalId;
    this.timeoutId;
    if(cycleFlag) {
        this.rollingSize = $(base).find(".visual_img > li").size() - 3;
    }else{
        this.rollingSize = $(base).find(".visual_img > li").size() - 1;
    }
    this.rollingFlag = 0;
    this.width = $(base).find(".visual_img > li").width();
    this.base = base;
    this.cycleFlag = cycleFlag;
    this.timeoutFlag = timeoutFlag;
};

Rolling.prototype.constructor = Rolling;
Rolling.prototype = {
    applyBtn : function(pre,nxt){

        this.base.on("click",pre,function(){
            if(!this.rollingFlag){
                this.preRolling();
                if(this.timeoutFlag) {
                    this.timeoutRolling();
                }else {
                    ProductDetail.updateRollingCount(0); // 임시로 해놓은 것
                }
            }
        }.bind(this));

        this.base.on("click",nxt,function(){
            if(!this.rollingFlag){
                this.nxtRolling();
                if(this.timeoutFlag) {
                    this.timeoutRolling();
                }else {
                    ProductDetail.updateRollingCount(1); // 임시로 해놓은 것
                }
            }
        }.bind(this));
    },
    offRolling : function(){
        this.base.off("");
    },
    timeoutRolling : function (){
        clearInterval(this.intervalId);
        clearTimeout(this.timeoutId);

        this.timeoutId = setTimeout(function(){
            this.intervalId = setInterval(this.nxtRolling.bind(this), 2000);
        }.bind(this), 4000);
    },
    intervalRolling : function(){

        this.intervalId = setInterval(this.nxtRolling.bind(this), 2000);

    },
    // rolling에 flag를 줘서 이벤트 발생 도중이면 이벤트를 스킵
    // animate에 callback함수 등록
    nxtRolling:function(){
        var $img = this.base.find(".visual_img");

        if(this.rollingCount< this.rollingSize){
            $img.animate({ "left": "-="+this.width}, {
                duration:500,
                start:function(){
                    this.rollingFlag=1;
                }.bind(this),
                complete:function(){
                    this.rollingFlag = 0;
                    this.rollingCount++;
                }.bind(this)
            });
        }else if(this.cycleFlag){
            $img.css("left","0px");
            $img.animate({ "left": "-="+this.width}, {
                duration:500,
                start:function(){
                    this.rollingFlag=1;
                }.bind(this),
                complete:function(){
                    this.rollingFlag = 0;
                    this.rollingCount = 0;
                }.bind(this)
            });
        }
    },
    preRolling:function(){
        var $img = this.base.find(".visual_img");

        if(this.rollingCount>0){
            $img.animate({ "left": "+="+this.width }, {
                duration:500,
                start:function(){
                    this.rollingFlag=1;
                }.bind(this),
                complete:function(){
                    this.rollingFlag = 0;
                    this.rollingCount--;
                }.bind(this)
            });
        }else if(this.cycleFlag){
            $img.animate({ "left": "+="+this.width },{
                duration:500,
                start:function(){
                    this.rollingFlag=1;
                }.bind(this),
                complete:function(){
                    $img.css('left',-(this.width*(this.rollingSize+1)));
                    this.rollingFlag = 0;
                    this.rollingCount =this.rollingSize;
                }.bind(this)
            });
        }
    },
    applyFlicking : function(){
        var flicking = new Flicking(this);
        flicking.init();
    }

};

var Flicking = function(rolling){

    this.touchStart_x = 0;
    this.touchStart_y = 0;
    this.move_dx = 0;
    this.rolling = rolling;

};

Flicking.prototype.constructor = Flicking;
Flicking.prototype = {
    init: function(){

        $(this.rolling.base).on("touchstart",".visual_img",this.start.bind(this));
        $(this.rolling.base).on("touchmove",".visual_img",this.move.bind(this));
        $(this.rolling.base).on("touchend",".visual_img",this.end.bind(this));
    },
    start : function(e){

        if ( e.type === 'touchstart' && e.originalEvent.touches.length === 1 ) {
            this.touchStart_x = e.originalEvent.touches[ 0 ].pageX;
            this.touchStart_y = e.originalEvent.touches[ 0 ].pageY;
        }

    },
    move : function(e){
        var drag_dist = 0;
        var scroll_dist = 0;

        if ( e.type === 'touchmove' && e.originalEvent.touches.length === 1 ) {
            drag_dist = e.originalEvent.touches[ 0 ].pageX - this.touchStart_x;
            scroll_dist = e.originalEvent.touches[ 0 ].pageY - this.touchStart_y;
            this.move_dx = ( drag_dist / this.rolling.width ) * 100;


            if ( Math.abs( drag_dist ) > Math.abs( scroll_dist ) ) {

                // ... move slide element

                e.preventDefault( );
            }
        }
    },
    end : function(e){
        if ( e.type === 'touchend' && e.originalEvent.touches.length === 0 ) {

            if ( this.move_dx  > 40 ) {
                this.rolling.preRolling();

            } else if(this.move_dx < 40){
                this.rolling.nxtRolling();
            }

            this.touchStart_y = 0;
            this.touchStart_x = 0;
            this.move_dx = 0;

            e.preventDefault( );
        }
    }
};
