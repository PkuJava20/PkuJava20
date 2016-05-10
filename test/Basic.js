/**
 * Created by ZYJ on 2016/3/8.
 */

function test(){
    var a=1;
 global.a=2;
    this.a=3;
    global.a=4;
    console.log(a);
    console.log(this.a);
    console.log(global.a);
}
test();
console.log("\n");
/*
var name='var-name';
global.name='global-name';
this.name='module-name';
global.name='global-name2';
console.log(global.name);
console.log(this.name);
console.log(name);
*/
var b=1;
global.b=2;
this.b=3;
global.b=4;
console.log(b);
console.log(this.b);
console.log(global.b);