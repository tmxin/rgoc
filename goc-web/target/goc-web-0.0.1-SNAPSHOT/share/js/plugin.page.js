;(function($,window,document,undefined) {
    var template =
        '<div class="pagination">'+
            '<span class="previous unavailable"><span class="arrow-left"></span></span>'+
            '<div class="page-num-area"></div>'+
            '<a href="javascript:void(0)" class="next"><span class="arrow-right"></span></a>'+
            '<span class="page-skip">' +
                '共<em class="page-total">17630</em>页'+
                '<label>到第'+
                '   <input type="text" name="pageJump" class="page-jump J_PageJump" size="3">页'+
                '</label>'+
                '<button>确定</button>'+
            '</span>'+
    '</div>';

    var template2=
      '  <ul class="pagination">'+
    '<li><a href="#">left</a></li>'+
    '<li><a href="#">2</a></li>'+
    '<li class="active"><a href="#">3</a></li>'+
    '<li><a href="#">8</a></li>'+
   ' <li><a href="#">9</a></li>'+
  '  <li><a href="#">right</a></li>'+
 ' </ul>';
    /**
     * 公告组件主方法
     * @param options
     */
    $.fn.page = function(options){
        $(this).html($(template2));
        /*
        var pages;
        var self = $(this).find('.pagination');
        var page = options.page;

        var showNum = options.showNum;
        showNum = (showNum<3?3:showNum);
        if(pages){
            page = parseInt(pages);
        }
        var tatalPages = options.totalPages;

        preNext(self,page,tatalPages);
        var aStr = pageNum(page,tatalPages,showNum);
        self.find('.page-num-area').replaceWith(aStr);

        self.find('.page-total').text(options.totalPages);


        //以下为页码的监听
        self.find("a").each(function (i){
            if($(this).attr("rel") == "nofollow"){
                $(this).click(function(){
                    var nu = $(this).html();
                    if (jQuery.isFunction(options.change)) {
                        options.change(nu);
                    }
                });
            }
        });

        //以下为上一页，下一页的监听
        if(self.find('.previous').attr("href")){
            self.find('.previous').click(function(){
                if (jQuery.isFunction(options.change)) {
                    options.change(page-1);
                }
            });
        }
        if(self.find('.next').attr("href")){
            self.find('.next').click(function(){
                if (jQuery.isFunction(options.change)) {
                    options.change(page+1);
                }
            });
        }
        //以下为输入框和确认按钮的监听

        self.find("input").bind('input propertychange', function() {
            var data = self.find("input").val();
            if(/^(\+|-)?\d+$/.test( data ) && self.find("input").val() <= tatalPages && self.find("input").val() >0){
                self.find("button").removeAttr("disabled");
            }else{
                self.find("input").val("");
                self.find("button").attr({"disabled":"disabled"});
            }
        });
        self.find("button").click(function(){
            if (jQuery.isFunction(options.change)) {
                options.change(self.find("input").val());
            }
        });*/
    }

    /**
     * 上一页下一页
     * @param self
     * @param page
     * @param tatalPages
     */
    function preNext(self,page,tatalPages){
        var newPreUn = $('<span>').attr("class","previous unavailable").html(self.find('.previous').html());
        var newPre = $('<a>').attr("class","previous").attr("href","javascript:void(0)").html(self.find('.previous').html());
        page == 1 ? self.find('.previous').replaceWith(newPreUn)
                  : self.find('.previous').replaceWith(newPre);

        var newNextUn = $('<span>').attr("class","next unavailable").html(self.find('.next').html());
        var newNext = $('<a>').attr("class","next").attr("href","javascript:void(0)").html(self.find('.next').html());
        page == tatalPages ? self.find('.next').replaceWith(newNextUn)
                           : self.find('.next').replaceWith(newNext);
    }
    /**
     * 显示页码
     * @param page
     * @param tatalPages
     * @param showNum
     * @returns {string}
     */
    function pageNum(page,tatalPages,showNum){
        if(tatalPages==0){
            $('.pagination').hide();
        }
        var aStr = "";
        if(tatalPages<=showNum){
            var i = 1;
            while(i<=tatalPages){
                if(i == page){
                    aStr += '<span class="current">'+i+'</span>';
                }else{
                    aStr += '<a href="javascript:void(0)" rel="nofollow">'+i+'</a>';
                }
                i++;
            }    
        }else if(page<showNum){
            var i = 1;
            while(i<showNum+1){
                if(i == page){
                    aStr += '<span class="current">'+i+'</span>';
                }else{
                    aStr += '<a href="javascript:void(0)" rel="nofollow">'+i+'</a>';
                }
                i++;
            }
            aStr += '<span class="break">...</span>';
        }else if(page>tatalPages - Math.floor(showNum/2+0.5)){
            aStr += '<span class="break">...</span>';
            var i = tatalPages - showNum +1 ;
            while(i<=tatalPages){
                if(i == page){
                    aStr += '<span class="current">'+i+'</span>';
                }else{
                    aStr += '<a href="javascript:void(0)" rel="nofollow">'+i+'</a>';
                }
                i++;
            }
        }else{
            aStr += '<span class="break">...</span>';
            var i = page - Math.floor(showNum/2);
            var nnn = 0;
            while(i<(page + Math.floor(showNum/2+0.5))){
                nnn ++ ;
                if(i == page){
                    aStr += '<span class="current">'+i+'</span>';
                }else{
                    aStr += '<a href="javascript:void(0)" rel="nofollow">'+i+'</a>';
                }
                i++;
            }
            aStr += '<span class="break">...</span>';
        }
        return aStr;
    }
    /**
     * 构建公告方格列表
     * @param data
     */
    function build(self,data){
        $.each(data, function(i, value){
            buildEach(self,value);
        });
    }

    /**
     * 构建每一个公告方格
     * @param self
     * @param data
     */
    function buildEach(self,data){
        var temp = $(template);
        self.append(temp);
        create(temp,data);
    }

    /**
     * 替换每一个方格的内容
     * @param self
     * @param data
     */
    function create(self,data){
        self.find('.notice-left-img').attr("href","/pages/notice_detail.html?noticeId="+data.id);

        var title = data.noticeTitle;
        var contentDiv = $("<div>").append(data.noticeContent);
        self.find('.notice-left-img').find('.itm-pic').lazyload({placeholder : "/images/loading.gif",effect: "fadeIn"})
                                                .attr("data-original",data.pic).attr("alt",title);
        self.find('.notice-content').find('h2').find('a').attr("href","/pages/notice_detail.html?noticeId="+data.id)
                                                .attr("title",title).text(data.id+'.'+(title.length<35?title:(title.substring(0,35)+'\.\.\.')));
        self.find('.notice-content').find('.item-descript').find('span').eq(0).text(contentDiv.text().length<60?contentDiv.text():(contentDiv.text().substring(0,60)+'\.\.\.'));
        self.find('.notice-content').find('.item-descript a').attr("href","/pages/notice_detail.html?noticeId="+data.id);
        self.find('.notice-content').find('.item-author a').attr("href","/pages/court_detail.html?courtId="+data.courtId)
                                                .attr("title",data.court).text(data.court);
        self.find('.notice-content').find('.item-author .date').text(formatDate(data.publishTime));
    }
    /**
     * 时间戳转字符串时间
     * @param timeStamp
     * @returns {string}
     */
    function formatDate(timeStamp){
        var time = new Date(parseInt(timeStamp));
        var timeFormat = ("0000"+(time.getMonth()+1)).substr( -2 )+"月"+
            ("0000"+time.getDate()).substr( -2 )+"日"+" "+
            ("0000"+time.getHours()).substr( -2 )+":"+
            ("0000"+time.getMinutes()).substr( -2 )+":"+
            ("0000"+time.getSeconds()).substr( -2 );
        return timeFormat;
    }

})(jQuery,window,document);	