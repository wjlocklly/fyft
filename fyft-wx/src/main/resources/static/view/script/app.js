/**
 * 主页模块,登录功能
 */

define(['vue', 'text!tpl/app.html', 'css!../script/css/demos.css'], function (Vue, AppHtml) {
    return Vue.extend({
        template: AppHtml,
        data: function(){
            return {
                user: '',
                userCode: '',
                passWord: ''
            }
        },
        ready: function(){
        },
        methods: {
        }
    })
})
