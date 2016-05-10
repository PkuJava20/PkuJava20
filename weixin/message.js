/**
 * Created by ZYJ on 2016/4/16.
 */
var PORT=9001;
var http=require('http');
var qs=require('qs');
var TOKEN='zyj9902';
var path=require('path');
var express = require('express');
var getUserInfo = require('./lib/user').getUserInfo;
var wss = require('./lib/ws.js').wss;
var app = express();
var bodyParser = require('body-parser');
app.set('views',path.join(__dirname,'views'));
app.set('view engine','ejs');

//定义静态文件目录
app.use(express.static(path.join(__dirname,'public')));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));

function checkSignature(params,token){
    var key=[token,params.timestamp,params.nonce].sort().join('');
    var sha1=require('crypto').createHash('sha1');
    sha1.update(key);

    return sha1.digest('hex')==params.signature;
}

var server=http.createServer(function(request,response){
    var query=require('url').parse(request.url).query;
    var params=qs.parse(query);
    request.charset='utf-8';
    if(!checkSignature(params,TOKEN)){
        response.end('signature fail');
        return;
    }
    if(request.method=="GET"){
        //如果请求是GET，返回echostr用于通过服务器有效校验
        response.end(params.echostr);
    }else{
        //否则是微信给开发者服务器的POST请求
        var postdata="";

        request.addListener("data",function(postchunk){
            postdata+=postchunk;
        });

        //获取到了POST数据
        request.addListener("end",function(){
            var parseString=require('xml2js').parseString;
            parseString(postdata,function(err,result){
                if(!err){
                    //xml数据解析成json数据
                    //console.log(postdata);
                    var arrMongodb = JSON.stringify(result);//解析json数据
                    var arr= JSON.parse(arrMongodb);
                    var FromUserName=arr.xml.ToUserName[0];
                    var ToUserName=arr.xml.FromUserName[0];
                    var CreateTime=Date.now();
                    var MsgType=arr.xml.MsgType[0];
                    var myresponse="";
                    if(MsgType==="text"){
                        getUserInfo(ToUserName)
                            .then(function(userInfo){
                                //获得用户信息，合并到消息中
                                result.user = userInfo;
                                //将消息通过websocket广播
                                wss.broadcast(result);
                                var content="你发了文字";
                                myresponse="<xml>";
                                myresponse = myresponse + "<ToUserName><![CDATA[" + ToUserName + "]]></ToUserName>";
                                myresponse = myresponse + "<FromUserName><![CDATA[" + FromUserName + "]]></FromUserName>";
                                myresponse = myresponse + "<CreateTime>" + CreateTime + "</CreateTime>";
                                myresponse = myresponse + "<MsgType><![CDATA[text]]></MsgType>";
                                myresponse = myresponse + "<Content><![CDATA["+content+"]]></Content>";
                                myresponse = myresponse + "</xml>";
                                //response.write(myresponse);
                                response.end(myresponse);
                            })
                    }
                    if(MsgType==="image"){
                        var content="你发了图片";
                        myresponse="<xml>";
                        myresponse = myresponse + "<ToUserName><![CDATA[" + ToUserName + "]]></ToUserName>";
                        myresponse = myresponse + "<FromUserName><![CDATA[" + FromUserName + "]]></FromUserName>";
                        myresponse = myresponse + "<CreateTime>" + CreateTime + "</CreateTime>";
                        myresponse = myresponse + "<MsgType><![CDATA[text]]></MsgType>";
                        myresponse = myresponse + "<Content><![CDATA["+content+"]]></Content>";
                        myresponse = myresponse + "</xml>";
                        response.write(myresponse);
                        response.end('success');
                    }
                    if(MsgType==="voice"){
                        var content="你发了语音";
                        myresponse="<xml>";
                        myresponse = myresponse + "<ToUserName><![CDATA[" + ToUserName + "]]></ToUserName>";
                        myresponse = myresponse + "<FromUserName><![CDATA[" + FromUserName + "]]></FromUserName>";
                        myresponse = myresponse + "<CreateTime>" + CreateTime + "</CreateTime>";
                        myresponse = myresponse + "<MsgType><![CDATA[text]]></MsgType>";
                        myresponse = myresponse + "<Content><![CDATA["+content+"]]></Content>";
                        myresponse = myresponse + "</xml>";
                        response.write(myresponse);
                        response.end('success');
                    }
                    if(MsgType==="video"){
                        var content="你发了视频";
                        myresponse="<xml>";
                        myresponse = myresponse + "<ToUserName><![CDATA[" + ToUserName + "]]></ToUserName>";
                        myresponse = myresponse + "<FromUserName><![CDATA[" + FromUserName + "]]></FromUserName>";
                        myresponse = myresponse + "<CreateTime>" + CreateTime + "</CreateTime>";
                        myresponse = myresponse + "<MsgType><![CDATA[text]]></MsgType>";
                        myresponse = myresponse + "<Content><![CDATA["+content+"]]></Content>";
                        myresponse = myresponse + "</xml>";
                        response.write(myresponse);
                        response.end('success');
                    }
                    if(MsgType==="shortvideo"){
                        var content="你发了小视频";
                        myresponse="<xml>";
                        myresponse = myresponse + "<ToUserName><![CDATA[" + ToUserName + "]]></ToUserName>";
                        myresponse = myresponse + "<FromUserName><![CDATA[" + FromUserName + "]]></FromUserName>";
                        myresponse = myresponse + "<CreateTime>" + CreateTime + "</CreateTime>";
                        myresponse = myresponse + "<MsgType><![CDATA[text]]></MsgType>";
                        myresponse = myresponse + "<Content><![CDATA["+content+"]]></Content>";
                        myresponse = myresponse + "</xml>";
                        response.write(myresponse);
                        response.end('success');
                    }
                    if(MsgType==="location"){
                        var content="你发了地理位置信息";
                        myresponse="<xml>";
                        myresponse = myresponse + "<ToUserName><![CDATA[" + ToUserName + "]]></ToUserName>";
                        myresponse = myresponse + "<FromUserName><![CDATA[" + FromUserName + "]]></FromUserName>";
                        myresponse = myresponse + "<CreateTime>" + CreateTime + "</CreateTime>";
                        myresponse = myresponse + "<MsgType><![CDATA[text]]></MsgType>";
                        myresponse = myresponse + "<Content><![CDATA["+content+"]]></Content>";
                        myresponse = myresponse + "</xml>";
                        response.write(myresponse);
                        response.end('success');
                    }
                    if(MsgType==="link"){
                        var content="你发了链接信息";
                        myresponse="<xml>";
                        myresponse = myresponse + "<ToUserName><![CDATA[" + ToUserName + "]]></ToUserName>";
                        myresponse = myresponse + "<FromUserName><![CDATA[" + FromUserName + "]]></FromUserName>";
                        myresponse = myresponse + "<CreateTime>" + CreateTime + "</CreateTime>";
                        myresponse = myresponse + "<MsgType><![CDATA[text]]></MsgType>";
                        myresponse = myresponse + "<Content><![CDATA["+content+"]]></Content>";
                        myresponse = myresponse + "</xml>";
                        response.write(myresponse);
                        response.end('success');
                    }
                }
            });
        });
    }
});

app.get('/', function (req,res) {
    res.render('show',{
    });
});

app.listen(9003,function(req,res){
    console.log('app is running at port 9003');
});

server.listen(PORT);
console.log("Server ruuning at port: "+PORT+".");