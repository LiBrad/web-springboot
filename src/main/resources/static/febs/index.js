layui.extend({
    febs: 'lay/modules/febs',
    validate: 'lay/modules/validate'
}).define(['febs', 'conf', 'jquery'], function (exports) {
    layui.febs.initPage();
    // 拓展jq函数
    var $ = layui.jquery;
    $.fn.serializeJson = function() {
        var serializeObj = {};
        $(this.serializeArray()).each(function(){
            serializeObj[this.name] = this.value;
        });
        return serializeObj;
    };
    exports('index', {});
});