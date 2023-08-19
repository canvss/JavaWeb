window.onload = function(){
    var btn = document.getElementById("btn")
    btn.onclick = function(){
        alert("我是一个点击按钮")
    }
}
//if (null){
//    alert("数据有效")
//}else{
//    alert("数据无效")
//}

//let flag = confirm("是否确定？")
//alert(flag)
////在控制台输出函数
//console.log(flag)

function fun1(){
    console.log("fun1...")
}
//fun1();

function fun2(i,j){
    console.log(i+j);
}
//fun2(10,20);

function fun3(i,j){
    return i*j;
}
let sum = fun3(5,4);
//alert(sum);

let o = new Object()
o.id = 100;
o.name = "canvs";
o.age = 18;
console.log(o)  //Object { id: 100, name: "canvs", age: 18 }
o.hello = function(){
    console.log("hello " + o.name); //hello canvs
}
o.hello();

let o2 = {
    id: 101,
    name: "Tom",
    age: 20,
    hello: function(){
        console.log("hello " + this.name);
    }
}
console.log(o2);
o2.hello();

//console.log(this.alert("AAAA"));

let user = {
    id: 1001,
    name: "jerry",
    age: 20,
    getName:function(){
        return this.name;
    }
}
console.log(user.getName());

let array = new Array();
array[0] = 'a';
array[1] = 'b';
array[2] = 'c';
array[3] = 'd';
console.log(array); //Array(4) [ "a", "b", "c", "d" ]

let arr = [1,2,3,4];
console.log(arr);   //Array(4) [ 1, 2, 3, 4 ]

array.push('e');
console.log(array);
//删除最后一位元素
array.pop();
console.log(array);

arr.reverse();
console.log(arr);   //Array(4) [ 4, 3, 2, 1 ]

let s = array.join();
console.log(typeof s);  //string

arr2 = s.split();
console.log(arr2)
arr2.splice();
console.log(arr2.length);   //1

let arr3 = [4,2,5,6,1,7];
//for(let i = 0; i < arr3.length; i++) {
//    alert(arr3[i]);
//}
//for(index in arr3){
//    console.log(arr3[index])
//}
for(num of arr3){
    console.log(num);
}