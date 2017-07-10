/**
 * Created by rain on 2017/5/15.
 */
var $page = {};
$page.getPage = function (url,param,$page_footer,callback) {
    $.ajax({
        url:url,
        data:param,
        type:"GET",
        success: function (data) {
            $page_footer.empty();
            data = $.parseJSON(data);
            var pageNum = data.totalCount/data.pageSize;
            var $page_pre = $('<li><a aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>');
            $page_footer.append($page_pre);
            for(var pageNo=1;pageNo<=pageNum;pageNo++) {
                var $li = $('<li id="pageLi' + pageNo + '"><a ><span aria-hidden="true">'+pageNo+'</span></a></li>');
                $li.unbind("click").bind("click",data,function(e){
                    param.begin = pageNo * data.pageSize;
                    $page.getPage(url,param,$page_footer,callback)
                });
                if(pageNo == 10){
                    $page_footer.append($('<li><span>……</span></li>'));
                    break;
                }else{
                    $page_footer.append($li);
                }
            }
            var $page_next = $('<li><a  aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>');
            $page_footer.append($page_next);
            callback(data.result);
        },
        error : function (XMLHttpRequest, textStatus){
            alert("查询失败："+textStatus);
        }
    });
};