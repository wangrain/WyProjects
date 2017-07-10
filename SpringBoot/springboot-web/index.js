/**
 * Created by rain on 2017/5/15.
 */
$(document).ready(function () {
    var calback = function (list) {
        for (var i = 0; i < list.length; i++) {
            var $tr = $('<tr></tr>');
            var $td = $('<td></td>');
            $tr.append($td.clone().text(list[i].userGuid));
            $tr.append($td.clone().text(list[i].userName));
            $tr.append($td.clone().text(list[i].userEmail));
            $tr.append($td.clone().text(list[i].userMobile));
            $tr.append($td.clone().text(list[i].registerTime));
            $('#pageTable').append($tr);
        }
    };
    var url = 'http://localhost/springboot/userInfo/getList.do';
    var params = {};
    $page.getPage(url, params, $('#page_footer'), calback);
});