/**
 * Created by ZYJ on 2016/5/16.
 */
var later = require('later');
var https = require('https');
var express = require('express');
var app = express();
var socketio=require('socket.io');
var path=require('path');
var http=require('http');
var request = require('request');
app.use(express.static(__dirname+'/client'));

app.set('view engine', 'ejs');
app.set('views', path.resolve(__dirname, 'views'));


app.get('/', function (req, res) {
    res.render('index');
})

var appid = 'wx07713a80ee6d4c2f';
var appsecret =  '79889fd403f82d5f9fa5f9489e442305';
var access_token;
var username;
var user;

later.date.localTime();
//console.log("Now:" + new Date());

var sched = later.parse.recur().every(2).hour();//每隔二小时重新获取access_token
next = later.schedule(sched).next(10);
//console.log(next);

var timer = later.setInterval(test, sched);
setTimeout(test, 2000);

function test() { //获取access_token
    var options = {
        hostname: 'api.weixin.qq.com',
        path: '/cgi-bin/token?grant_type=client_credential&appid=' + appid + '&secret=' + appsecret
    };
    var req = https.get(options, function (res) {
        var bodyChunks = '';
        res.on('data', function (chunk) {
            bodyChunks += chunk;
        });
        res.on('end', function () {
            var body = JSON.parse(bodyChunks);
            //console.dir(body);
            if (body.access_token) {
                access_token = body.access_token;
                //saveAccessToken(access_token);
                console.log(access_token);
                console.log('getting access_token successfully!');
            } else {
                console.dir(body);
            }
        });
    });
    req.on('error', function (e) {
        console.log('ERROR: ' + e.message);
    });
}

app.post('/', function(req,res){
    var postdata="";

    req.on("data",function(postchunk){
        postdata+=postchunk;
    });

    req.on("end",function(){
        var parseString=require('xml2js').parseString;
        parseString(postdata,function(err,result){
            if(!err){
                //xml数据解析成json数据
                //console.log(postdata);
                var arrMongodb = JSON.stringify(result);//解析json数据
                var arr= JSON.parse(arrMongodb);
                if(arr) {
                    var FromUserName = arr.xml.ToUserName[0];
                    var ToUserName = arr.xml.FromUserName[0];
                    var CreateTime = Date.now();
                    var MsgType = arr.xml.MsgType[0];
                }
                var myresponse="";
                if(MsgType==="text"){
                    if(access_token) {
                        request('https://api.weixin.qq.com/cgi-bin/user/info?access_token=' + access_token + '&openid=' + ToUserName + '&lang=zh_CN', function (err, res, data) {
                            var user = JSON.parse(data);//微信服务器将用户信息以json格式返回
                            var username=user['nickname'];
                            //console.log(username);
                            var content=result['xml']['Content'][0];
                            var news=username+":"+content;
                            messages.unshift(news);
                            io.sockets.emit('newMessage',news);//将消息通过websocket广播
                        });
                    }
                    var content="你发了文字";
                    myresponse="<xml>";
                    myresponse = myresponse + "<ToUserName><![CDATA[" + ToUserName + "]]></ToUserName>";
                    myresponse = myresponse + "<FromUserName><![CDATA[" + FromUserName + "]]></FromUserName>";
                    myresponse = myresponse + "<CreateTime>" + CreateTime + "</CreateTime>";
                    myresponse = myresponse + "<MsgType><![CDATA[text]]></MsgType>";
                    myresponse = myresponse + "<Content><![CDATA["+content+"]]></Content>";
                    myresponse = myresponse + "</xml>";
                    res.end(myresponse);
                }
            }
        });
    });
});

var server = app.listen(9001,function(request,response){
    console.log('weixinwall is running at port 9001');
});

var io = socketio.listen(server);

var messages = [];
//messages.push('欢迎你来到微信墙!');

io.sockets.on('connection', function(socket){
    console.log('connected');
    socket.emit('connected');
    socket.broadcast.emit('newClient',new Date());

    socket.on('getAllMessages',function(){
        socket.emit('allMessages',messages);
    });

    socket.on('disconnect',function(){
        console.log('disconnect');
    });

/*    socket.on('addMessage',function(message){
        messages.unshift(message);
        io.sockets.emit('newMessage',message);
    });*/
});
