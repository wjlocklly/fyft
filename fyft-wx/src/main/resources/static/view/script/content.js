/**
 * 应用内容
 */
//http://www.javazgs.com/content.json?t=1494604782511
define(['vue', 'text!tpl/content.html', 'css!../script/css/demos.css'], function (Vue, ContentHtml) {
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
  				url:'/blogList',
  				type:'post',
  				dataType:'json',
  				data:{},
  				success:function(data){
  					console.log(data)
  					console.log(data[0].title)
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