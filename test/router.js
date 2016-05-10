/**
 * Created by ZYJ on 2016/3/4.
 */
function route(handle,pathname,response,request){
    console.log("About to route a request for "+ pathname);
    if(typeof handle[pathname]==='function'){
        //console.log(postData);
         handle[pathname](response,request);
    }
    else {
        console.log("not find request handler for "+ pathname);
         response.writeHead(404, {"Content-Type": "text/plain"});
         response.write("404 not found");
        response.end();
        //return "404 not found";
    }
}

exports.route = route;