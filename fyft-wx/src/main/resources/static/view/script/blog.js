/**
 * 应用内容
 */
//http://www.javazgs.com/content.json?t=1494604782511
define(['vue', 'text!tpl/blog.html', 'css!../script/css/demos.css'], function (Vue, ContentHtml) {
    return Vue.extend({
        template: ContentHtml,
        data: function(){
            return {
                blog: '',
                blogList: []
            }
        },
        created: function(){
        	this.blog="git page 博客列表";
        	var thisVue = this;
        	$.ajax({
  				url:'/index/blogList',
  				type:'post',
  				dataType:'json',
  				data:{},
  				success:function(data){
  					thisVue.blogList=data;
  				},
  				error:function(stats){
  					$.toptip('系统异常，请稍后再试', 'error');
  				}
  			});
        },
        ready: function(){
        	
        },
        methods: {
        	
        }
    })
})