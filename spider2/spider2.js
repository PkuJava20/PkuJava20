// 引入模块
"use strict"
var http = require('http');
var fs = require('fs');
var cheerio = require('cheerio');
var request = require('request');
global.imgsrc=[];//存放新闻图片路径
global.imgtitle=[];//存放新闻图片标题
global.i=1;
global.queue1=[];//存放新闻标题和对应的供稿单位
//global.detail=[];//存放所有新闻的具体段落内容
global.urlQueue=[];
function gethttp(num){ //用递归抓取所有页的新闻列表数据
    if(num<14){//递归出口
        http.get("http://www.ss.pku.edu.cn/index.php/newscenter/news" + "?start=" + 20 * num, function (res) {
            //console.log(url);
            var html = ''; // 保存抓取到的HTML源码
            var news = [];  // 保存解析HTML后的数据
            res.setEncoding('utf-8');

            // 抓取页面内容
            res.on('data', function (chunk) {
                html += chunk;
            });

            //网页内容抓取完毕
            res.on('end', function () {
                //console.log(html);
                var $ = cheerio.load(html);

                $('#info-list-ul li').each(function (index, item) {
                    var news_item = {
                        title: $('.info-title', this).text(), // 获取新闻标题
                        time: $('.time', this).text(), // 获取新闻时间
                        link: 'http://www.ss.pku.edu.cn' + $('a', this).attr('href'), // 获取新闻详情页链接
                    };
                    news.push(news_item);
                    urlQueue.push(news_item.link);//将新闻详情页链接放入队列
                });
               // console.log(news);
                saveData('data/data' + (num+1) + '.json', news);//保存每页数据
                //readData('data/data.json');
            });
        }).on('error', function (err) {
            console.log(err);
        });
        gethttp(num+1);//递归到下一个新闻页
    }
}

gethttp(0);//开始递归
setTimeout(last,10000);
setTimeout(last2,25000);
setTimeout(last3,40000);

function last(){
    try {
        console.log(urlQueue.length);
        for (var i = 0; i < urlQueue.length; i++) {
            getDetail(encodeURI(urlQueue[i]));
        }
    }
    catch (err){console.log("error!!!")}
}
function last2(){
    try {
        //console.log(queue1.length);
        saveData('newsafforder/newsafforder.json', queue1);
        //saveData('newsdetail/newsdetail.json', detail);
    }catch (err){console.log("error!!!")}
}
function last3() {
    try {
        for (var i = 0; i < imgtitle.length; i++) {
            //console.log(imgtitle[i]);
            imgtitle[i]=imgtitle[i].replace("*","");
            imgtitle[i]=imgtitle[i].replace(":","");
            imgtitle[i]=imgtitle[i].replace(" ","");
            imgtitle[i]=imgtitle[i].replace("(","");
            imgtitle[i]=imgtitle[i].replace(")","");
            imgtitle[i]=imgtitle[i].replace("：","");
            imgtitle[i]=imgtitle[i].replace("/","");
            imgtitle[i]=imgtitle[i].replace(".","");
            imgtitle[i]=imgtitle[i].replace(" ","");
            imgtitle[i]=imgtitle[i].replace("http","");
            imgtitle[i]=imgtitle[i].replace("\\t","");
                request(imgsrc[i]).pipe(fs.createWriteStream('/spider2/picture/' + imgtitle[i] + imgsrc[i].substring(imgsrc[i].length - 4, imgsrc[i].length).toLowerCase()));//通过URL将图片下载到本地并命名
    }
    }catch (err){console.log("error!!!")}
}
function getDetail(path){
    http.get(path, function (res) {
        //console.log(path);
        var html2 = ''; // 保存抓取到的HTML源码
        var detail=[];
        res.setEncoding('utf-8');

        // 抓取页面内容
        res.on('data', function (chunk) {
            html2 += chunk;
        });

        //网页内容抓取完毕
        res.on('end', function () {
            //console.log(html2);
            var $ = cheerio.load(html2);
            var news_afforder = {
                news_title:$('.article-title').text().trim(),  //获取新闻标题
                news_afforder:$("[href='#']").eq(2).text().trim(),//获取供稿单位
        }
            queue1.push(news_afforder);
            $('.article-content p').each(function (index, item) {
                        var detail_item = {
                            info:$(this).text(),//获取新闻具体内容
                }
                if(detail_item.info.length>30)
                   detail.push(detail_item);
            });
            saveData('newsdetail/newsdetail.'+i+'.json', detail);
            i++;
            $('.article-content p').each(function (index, item) {
                var img_item = {
                    imgsrc: 'http://www.ss.pku.edu.cn' + $('img', this).attr('src'), // 获取图片路径
                    imgtitle:$('span', this).text(),//获取图片标题
                };
                if(img_item.imgsrc!='http://www.ss.pku.edu.cn'+undefined)
                {
                    imgsrc.push(img_item.imgsrc);
                }
                if(img_item.imgtitle!=''&&img_item.imgtitle!=' '&&img_item.imgtitle!=undefined&&img_item.imgtitle.length<20)
                imgtitle.push(img_item.imgtitle);
        });
    }).on('error', function (err) {
        console.log(err);
    });
});
}

//getDetail(encodeURI("http://www.ss.pku.edu.cn/index.php/newscenter/news/2373-引领变革时代-聚焦北京大学2016软微创新创业新年论坛"));

/**
 * 保存数据到本地
 *
 * @param {string} path 保存数据的文件
 * @param {array} news 新闻信息数组
 */
function saveData(path, news) {
    fs.writeFile(path, JSON.stringify(news, null, 4), function(err) {
        if (err) {
            return console.log(err);
        }
        console.log('Data saved');
    });
}


/**
 * 保存数据到本地
 *
 * @param {string} path 保存数据的文件
 */
function readData(path) {
    fs.readFile(path, {encoding: 'utf-8'}, function (err, bytesRead) {
        if (err)
            console.log(err);
        else {
            var data = JSON.parse(bytesRead);
            console.log(data);
            console.log("readData success");
        }
    });
}

