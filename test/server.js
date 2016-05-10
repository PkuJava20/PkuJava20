/**
 * Created by ZYJ on 2016/3/4.
 */
    "use strict"
var http = require("http");
var url = require("url");

    http.createServer(function (request, response) {
        console.log("sbsbsbsbsb");
    }).listen(8888);
    console.log("server started");

//function start(route,handle) {
   // http.createServer(function (request, response) {
     //   var postData="";
      //  var pathname = url.parse(request.url).pathname;
      //  console.log("request for "+pathname+" received");
       // route(handle,pathname,response,request);
/*
        request.setEncoding("utf8");
        request.addListener("data",function(postDataChunk){
           postData+=postDataChunk;
            console.log("Received POST data chunk ' "+postDataChunk+" '. ");
        });
        request.addListener("end",function(){
            console.log("sbbsbsbsbsbsbsbsbsbs ");
            route(handle,pathname,response,postData);
        });*/

        //var content = route(handle,pathname);
       // response.writeHead(200, {"Content-Type": "text/plain"});
       // response.write(content);
        //response.end();
   // }).listen(8888);
   // console.log("server started");
//}
//exports.start=start;