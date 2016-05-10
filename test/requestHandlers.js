/**
 * Created by ZYJ on 2016/3/4.
 */
"use strict"
var exec=require("child_process").exec;
var querystring=require("querystring");
var fs=require("fs");
var formidable=require("formidable");
function start(response){
    console.log("requesthandlers 'start' was called");
    /*function sleep(t){
        var startTime=new Date().getTime();
        while(new Date().getTime()<startTime+t);
    }
    sleep(8000);*/
    var body = '<html>'+
                      '<head>'+
                      '<meta http-equiv="Content-Type" content ="text/html; '+
                      'charset=UTF-8" />'+
                      '</head>'+
                      '<body>'+
                          '<form action="/upload" enctype="multipart/form-data" method="post">'+
                          '<input type="file" name="upload">'+
                          '<input type="submit" value="Upload file " />'+
                          '</form>'+
                      '</body>' +
                          '</html>';
    /*
    var body = '<html>'+
        '<head>'+
        '<meta http-equiv="Content-Type" content ="text/html; '+
        'charset=UTF-8" />'+
        '</head>'+
        '<body>'+
        '<form action="/upload"  method="post">'+
            '<textarea name="text" rows="20"cols="60"></textarea>'+
        '<input type="submit" value="submit text" />'+
        '</form>'+
        '</body>' +
        '</html>';*/
    response.writeHead(200, {"Content-Type": "text/html"});
     response.write(body);
      response.end();
   // var content="empty";
    //exec("ls -lah",function(error,stdout,stderr){
      //  response.writeHead(200, {"Content-Type": "text/plain"});
      //  response.write(stdout);
      //  response.end();

        //exec("find /",{timeout:10000,maxBuffer:20000*1024},
           // function(error,stdout,stderr){
           // response.writeHead(200, {"Content-Type": "text/plain"});
           // response.write("hello start");
          //  response.end();
  //  });
    //return content;
}
function upload(response,request){
    console.log("requesthandlers 'upload' was called");
    var form = new formidable.IncomingForm();
    form.uploadDir = "tmp";//定义文件上传的临时文件存放路径
    console.log("about to parse");
    form.parse(request,function(error,fields,files){
        console.log("parsing done");
        console.log(files.upload.path);
       // console.log("./"+form.uploadDir+"/test.png");
        fs.renameSync(files.upload.path,"./fuck/test.png");//files.upload.path就是上传文件的缓存路径
        response.writeHead(200, {"Content-Type": "text/html"});
        response.write("received image :<br/>");
        response.write("<img src='/show' />");
        response.end();
    })
    //return "hello upload";
}

function show(response,postData){
    console.log("requesthandlers 'show' was called");
    fs.readFile("./fuck/test.png","binary", function (error,file) {
        if(error){
            response.writeHead(500,{"Content-Type":"text/plain"});
            response.write(error+"\n");
            response.end();
        }else {
            response.writeHead(200,{"Content-Type":"img/png"});
            response.write(file,"binary");
            response.end();
        }
    });
}
exports.start=start;
exports.upload=upload;
exports.show=show;