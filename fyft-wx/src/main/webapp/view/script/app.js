/**
 * 主页模块
 */

define(['vue', 'text!tpl/login.html'], function (Vue, appHtml) {

    return Vue.extend({
        template: appHtml,
        data: function(){
            return {
                user: ''
            }
        },
        ready: function(){
            //this.user = this.$cookie.get('killPhone')
        }
    })
})
