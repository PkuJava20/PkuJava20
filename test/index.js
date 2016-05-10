/**
 * Created by ZYJ on 2016/3/4.
 */
"use strict"
var server = require("./server");
var router=require("./router");
var requestHandlers=require("./requestHandlers");

var handle={}
handle["/"]=requestHandlers.start;
handle["/start"]=requestHandlers.start;
handle["/upload"]=requestHandlers.upload;
handle["/show"]=requestHandlers.show;
server.start(router.route,handle);