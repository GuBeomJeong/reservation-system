var Flicking = function(base){

    this.touchStart_x = 0;
    this.touchStart_y = 0;
    this.move_dx = 0;
    this.base = base;

};

Flicking.prototype.constructor = Flicking;
Flicking.prototype = {
    start : function(e){
        if ( e.type === 'touchstart' && e.touches.length === 1 ) {
            this.touchStart_x = e.touches[ 0 ].pageX;
            this.touchStart_y = e.touches[ 0 ].pageY;
        }
    },
    move : function(e){
        var drag_dist = 0;
        var scroll_dist = 0;

        if ( e.type === 'touchmove' && e.touches.length === 1 ) {
            drag_dist = e.touches[ 0 ].pageX - this.touch_start_x;
            scroll_dist = e.touches[ 0 ].pageY - this.touch_start_y;
            this.move_dx = ( drag_dist / this.base.width ) * 100;

            if ( Math.abs( drag_dist ) > Math.abs( scroll_dist ) ) {

                // ... move slide element

                e.preventDefault( );
            }
        }
    },
    end : function(e){
        if ( e.type === 'touchend' && e.touches.length === 0 ) {

            if ( Math.abs( this.move_dx ) > 40 ) {
                // ... move slide element to Next or Prev slide

            } else {
                // ... move slide element to save_x or save_y position
            }

            this.touchStart_y = 0;
            this.touchStart_x = 0;
            this.move_dx = 0;

            e.preventDefault( );
        }
    }
};