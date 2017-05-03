/**
 * 应用内容
 */
define(['vue', 'text!tpl/content.html', 'css!../script/css/demos.css'], function (Vue, ContentHtml) {
    return Vue.extend({
        template: ContentHtml,
        data: function(){
            return {
                user: ''
            }
        },
        ready: function(){
        },
        methods: {
        	
        }
    })
})