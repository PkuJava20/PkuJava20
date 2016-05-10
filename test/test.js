/**
 * Created by ZYJ on 2016/3/22.
 */
    /*
var start = Date.now();
setTimeout(function(){
    console.log(Date.now()-start);
    for(var i =0; i< 10000000000;i++){}
},1000);
setTimeout(function(){
    console.log(Date.now()-start);
},2000);
*/
"use strict"
function a(i) {
    if (i < 10) {
        (function (i) {
            setTimeout(function () {
                //console.log(i);
            }, 2000);
        })(i);
        console.log(i);
        i++;
        console.log(a(i));
        return i;
    }
}
a(0);