/**
 * Created by ZYJ on 2016/3/29.
 */
//加载依赖库
    "use strict"
var express = require('express');
var path = require('path');
var bodyParser = require('body-parser');
var crypto = require('crypto');
var session = require('express-session');
var moment = require('moment');
var querystring=require('querystring');
var url=require('url');
//引入mongoose
var mongoose= require('mongoose');
//引入模型
var models=require('./models/models');
var User = models.User;
var Note = models.Note;
//使用mongoose连接服务
mongoose.connect('mongodb://localhost:27017/notes');
mongoose.connection.on('error',console.error.bind(console,'连接数据库失败'));
//创建express实例
var app = express();

//定义EJS模块引擎和模块文件位置
app.set('views',path.join(__dirname,'views'));
app.set('view engine','ejs');

//定义静态文件目录
app.use(express.static(path.join(__dirname,'public')));

//定义数据解析器
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));

//建立session模型
app.use(session({
        secret:'1234',
        name:'mynote',
        cookie:{maxAge:1000*60*60*24*7},
        resave:false,
        saveUninitialized:true
}));

app.get('/list',function(req,res){
    console.log('笔记列表');
    var pageNo = querystring.parse(url.parse(req.url).query).pageNo; //获取笔记列表序号
    console.log(pageNo);
    if(!pageNo)
        pageNo = 0;
    if(req.session.user){
        var username = req.session.user.username;
        Note.where({author:username}).count(function(err,count){  //查询笔记总数
            if(err){
                console.log('查找笔记总数目失败：'+err);
                return res.redirect('/');
            }else{
                var total = count;
                //console.log(total);
                Note.find({author:username},function(err,notes){ //获取相应页的笔记列表
                    if(err){
                        console.log('查找笔记列表失败：'+err);
                        return res.redirect('/');
                    }
                    res.render('articlelist',{
                        title:'我的笔记列表',
                        user:req.session.user,
                        notes:notes,
                        pageNo:pageNo,
                        total:total
                    });
                }).skip(pageNo*5).limit(5);   //跳过前pageNo-1页的数据
            }
        });
    }else{
        res.render('login',{
            title:'登录',
            user:req.session.user
        });
    }
});

//响应首页get请求
app.get('/', function (req,res) {


    if(!req.session.user)
    return res.redirect('/login');
   return res.redirect('/list');
});

app.get('/register', function (req,res) {
    if(req.session.user)
        return res.redirect('/');
    req.session.msg2="";
    console.log("注册！");
    res.render('register',{
        title:'注册',
        err :req.session.msg,
        user:req.session.user
    });
});

app.post('/register',function(req,res){
    //req.body可以获取到表单的每项数据
    var msg="";
    var username= req.body.username,
        password=req.body.password,
        passwordRepeat=req.body.passwordRepeat;
    console.log(username);
    var id_check= /^[A-Za-z0-9_]+$/;
    //检查输入的用户名长度，使用trim去掉两端空格
    if(username.trim().length<3||username.trim().length>20){
        msg ="用户名长度应在3到20之间！";
        console.log('用户名长度应在3到20之间！');
        req.session.msg=msg;
        return res.redirect('/register');
    }

    if (!id_check.test(username)) { //正则表达式匹配
        msg ="用户名只能由字母数字下划线组成";
        console.log(msg);
        req.session.msg=msg;
        return res.redirect('/register');
    }

    //检查输入的密码长度，使用trim去掉两端空格
    if(password.trim().length<6||passwordRepeat.trim().length<6){
        msg ="密码长度不能小于6！";
        console.log('密码长度不能小于6！');
        req.session.msg=msg;
        return res.redirect('/register');
    }
    //检查两次输入密码是否一致
    if(password!=passwordRepeat){
        msg ="两次输入的密码不一致！";
        console.log('两次输入的密码不一致！');
        req.session.msg=msg;
        return res.redirect('/register');
    }

    if (!(/[A-Z]/.test(password)&&/[a-z]/.test(password)&&/[0-9]/.test(password))) {  //正则表达式匹配
        msg ="密码必须同时包含大小写字母和数字";
        console.log(msg);
        req.session.msg=msg;
        return res.redirect('/register');
    }

    //检查用户名是否存在，如果不存在，则保存该条记录
    User.findOne({username:username},function(err,user){
        if(err){
            console.log(err);
            return res.redirect('/register');
        }
        if(user){
            msg ="用户名已经存在！";
            console.log('用户名已经存在');
            req.session.msg=msg;
            return res.redirect('/register');
        }
        //对密码进行md5加密
         var md5 = crypto.createHash('md5'),md5password = md5.update(password).digest('hex');
        //新建user对象用于保存数据
         var newUser=new User({
             username:username,
             password:md5password
         });
        newUser.save(function(err,doc){
            if (err){
                console.log(err);
                return res.redirect('/register');
            }
            console.log('注册成功！');
            return res.redirect('/');
        });
    });
});
app.get('/login', function (req,res) {
    if(req.session.user)
        return res.redirect('/');
    req.session.msg="";
    console.log("登录！");
    res.render('login',{
        title:'登录',
        user:req.session.user,
        err2:req.session.msg2
    });
});

app.post('/login',function(req,res){
    var username= req.body.username,
        password=req.body.password;
   var msg2="";
    User.findOne({username:username},function(err,user){
        if(err){
            console.log(err);
            return res.redirect('/login');
        }
        if(!user){
            msg2 ="用户不存在！";
            req.session.msg2=msg2;
            console.log('用户不存在');
            return res.redirect('/login');
        }
        //对密码进行md5加密
        var md5 = crypto.createHash('md5'),md5password = md5.update(password).digest('hex');

       if(user.password!==md5password){
           msg2 ="密码错误！";
           req.session.msg2=msg2;
           console.log('密码错误！');
           return res.redirect('/login');
       }
        console.log('登录成功！');
        req.session.msg2="";
        user.password=null;
        delete user.password;
        req.session.user=user;
        return res.redirect('/');
    });
});

app.post('/post',function(req,res){
        var note=new Note({
            title:req.body.title,
            author:req.session.user.username,
            tag:req.body.tag,
            content:req.body.content
        });
        note.save(function(err,doc){
            if (err){
                console.log(err);
                return res.redirect('/post');
            }
            console.log('文章发表成功！');
            return res.redirect('/');
        });
    });


app.get('/quit', function (req,res) {
    req.session.user=null;
    console.log("退出！");
    return res.redirect('/login');
});
app.get('/post', function (req,res) {
    console.log("发布！");
    res.render('post',{
        title:'发布',
        user:req.session.user
    });
});
app.get('/detail/:_id', function (req,res) {
    console.log("查看笔记！");
    Note.findOne({_id:req.params._id})
        .exec(function(err,art){
            if(err){
                console.log(err);
                return res.redirect('/');
            }
            if(art){
                res.render('detail',{
                title:'笔记详情',
                user:req.session.user,
                    art:art,
                moment:moment
            });}
        });
});
//监听3000端口
app.listen(3000,function(req,res){
    console.log('app is running at port 3000');
});