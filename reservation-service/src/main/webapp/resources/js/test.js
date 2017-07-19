var Test = {
    count:0,
    some : function(ele){
        $(ele).on("click",this.hi.bind(this));
    },
    hi:function(e){
        this.count++;
        console.log("hi");
    }
}

Test.some($("#id"));
Test.some($("#id2"));
Test.some($("#id3"));
Test.some($("#id4"));
Test.some($("#id5"));

var Test = {
    some : function(){
        var ele = $("#id");
        $(ele).on("click",this.hi);
    },
    hi:function(e){
        console.log("hi");
    }
}

Test.some();




var Test = {
    some : function(base){
        var prev = base.find(".prev");
        var next = base.find(".next");
        $(ele).on("click",this.hi);
    },
    hi:function(e){
        console.log("hi");
    }
}

Test.some($("#root"));

var template = Handlebars.compile(html);
template({
    a:1,
    b:2,
}); // html

function addTodo(data){
    var template = Handlebars.compile(todo);
    $("#li").append(template(data));
}


function Test(){
    this.a = 1;
}

Test.prototype.increment = function(){
    this.a++;
}

Test.prototype.some()new Promise(function(resolve, reject) {
    return this.a;
});

var test = new Test();
test.increment();
test.some(); // 2

var test2 = new Test();
test2.increment();
test2.some(); //2

function	Rectangle(){
}
Rectangle.prototype = new eg.Component();
Rectangle.prototype.constructor =Rectangle;
Rectangle.prototype.som	=function(){
		console.log("a");
}
var	rt = new Rectangle();
rt.some();
rt.on("click",function(){	//	이벤트 등록
	console.log("aa");
});
rt.trigger("click");	//	이벤트 실행







var Board = (function(){
    var count = 1;

    return {
        increment : function(){
            count++;
        },
        getCount:function(){
            return count;
        }
    }
})();

Board.increment();
Board.getCount();

<div id ="test1"></div>
<div id ="test2"></div>

$("#test1").on("click",board1.increment.bind(board1));
$("#test2").on("click",board2.increment.bind(board2));

var like = new Like();

var board1 = new Board();
board1.on("increment",function(){
    //like.thumbUp();
    //aaaa.thumbUp();
    // 여기서 component가 필요한 이유 : like에 종속되지 않게 하기 위해
})
var board2 = new Board();
var board3 = new Board();

function Board(){
    this.count =0;
}
Board.prototype = new eg.Component();
Board.prototype.constructor = Board;
Board.prototype.increment = function(){
    this.count++;
    this.trigger("increment");
}
Board.prototype.getCount =function(){
    return this.count;
}

Class Board extends Component{
    int count=0;
    public void increment(){
        count++;
    }
    public int getCount(){
        return count;
    }
}  // 자바로 따지면 이렇게 만든 것임


var Like = require('like.js');  // commonjs(node.js) (동기)

require(['like.js','board.js'],function(Like,Board){
    var board1 = new Board();
    var like = new Like();
    board1.on("increment",function(){

    })
}); // amd(require.js) (비동기)

import Like from 'like.js'; // esm (동기)
import Board from 'board.js';

import('like.js','board.js').then(function(Like){

}) // esm (비동기)
