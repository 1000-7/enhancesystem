;
//jQuery CDN引入
document.write("<script src='https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js'></script>");
//bootstrap CDN引入
document.write("<link href='https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet'>");
document.write("<script src='https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
//jQuery-UI CDN引入
//document.write("<link href='https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.css' rel='stylesheet'>");
//document.write("<script src='https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.js'></script>");


var json=[
  {'key':'iOS','url':'/九寨沟/'}
  ,{'key':'Spring','url':'/景点/'}
  ,{'key':'JBOSS','url':'这是一条很长的很长的很长的很长的很长的很长的很长的很长的tooltip'}
 ];
var json2=[
  /*
    {"entity":"\"141\"绿色工程","attributes":
  [{"tag":"中文名","attribute":"\"141\"绿色工程"},
    {"tag":"体系数量","attribute":"4"},
    {"tag":"在建工程","attribute":"治沙工程"},
    {"tag":"基地名称","attribute":"南方速生丰产用材林基地"},
    {"tag":"建设期限","attribute":"1978年一2050年"}]
  },
{"entity":"宝钢","attributes":
  [{"tag":"中文名","attribute":"宝钢"},
  {"tag":"体系数量","attribute":"11111"},
  {"tag":"在建工程","attribute":"22222"},
  {"tag":"基地名称","attribute":"333333"},
  {"tag":"建设期限","attribute":"1978年一2050年"}]
},
{"entity":"SVN","attributes":
[{"tag":"中文名","attribute":"SVN"},
{"tag":"体系数量","attribute":"11111"},
{"tag":"在建工程","attribute":"22222"},
{"tag":"基地名称","attribute":"333333"},
{"tag":"建设期限","attribute":"1978年一2050年"}]
},
{"entity":"Spring","attributes":
[{"tag":"中文名","attribute":"Spring"},
{"tag":"体系数量","attribute":"11111"},
{"tag":"在建工程","attribute":"22222"},
{"tag":"基地名称","attribute":"333333"},
{"tag":"建设期限","attribute":"1978年一2050年"}]
}*/
];
function getContentByMethods(method,tag){
    var contentNodes = null;
    if(method == 'ID'){
        contentNodes = $("#"+tag);
    } 
    if(method == 'class'){
      contentNodes = $("."+tag);
    }
    /*if(method == 'Xpath'){
        //document.setProperty("SelectionLanguage","XPath");
        var evaluator = new XPathEvaluator();
        if($.browser.mise){
            contentNodes = evaluator.selectNodes(tag);
        }
        else{
            contentNodes = document.evaluate(tag, document.documentElement, null, XPathResult.ORDERED_NODE_ITERATOR_TYPE, null);
        }
    }*/
    if(method == 'element'){
      contentNodes = $(tag);
    }
    //console.info(contentNodes)

    getContentText(contentNodes);
    //console.info(contentTextAll);
    $.ajax({
      type:'post',
      url:'/entity/findall',
      data:{
        text:contentTextAll
      },
      //contentType: 'application/x-www-form-urlencoded',
      success:function(rtn){
          json2 = rtn;
      }
    });

    hilightText(contentNodes);
    contentTextAll =""    //置空查询文本
}

/*
function hilightText(nodes){
  $.each(nodes,function() {
    if(this.children.length == 0){
      //console.info(this.innerHTML);
      var c= this.innerHTML;
      var reg;
      for(var i=0;i<json.length;i++){
       var j=json[i];
       reg=new RegExp(j.key+"(?!([^<]*>)|([^<]*<\/a>))","ig");
       c = c.replace( reg,"<a data-toggle='tooltip' style='color:#1C86EE' title='"+j.url+"' href='"+j.url+"'>"+j.key+"</a>");
      }
      this.innerHTML=c;
      //console.info(this);
      console.info(json2);
    }
    else{
      hilightText(this.children);
    }
  });
}*/

function hilightText(nodes){
  $.each(nodes,function() {
    if(this.children.length == 0){
      //console.info(this.innerHTML);
      var c= this.innerHTML;
      var reg;
      for(var i=0;i<json2.length;i++){
       var j=json2[i].attributes;
       var showContent= "";
       $.each(j,function(){
        showContent += this.tag+':'+this.attribute+'<br/>';
       });
       reg=new RegExp(json2[i].entity+"(?!([^<]*>)|([^<]*<\/a>))","ig");
       c = c.replace( reg,"<a data-toggle='tooltip'data-placement='bottom' data-html='true' title='"+showContent+"' style='color:#1C86EE' href='"+showContent+"'>"+json2[i].entity+"</a>");
      }
      this.innerHTML=c;
      //console.info(this);
    }
    else{
      hilightText(this.children);
    }
  });
}

var contentTextAll =""
function getContentText(cluster){
  $.each(cluster,function() {
    if(this.children.length == 0){
      //console.info(this.innerHTML);
      contentTextAll += this.innerHTML;
    }
    else{
      getContentText(this.children);
    }
  });
}
