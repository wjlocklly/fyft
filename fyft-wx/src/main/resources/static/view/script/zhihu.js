/**
 * 知乎日报
 */
//https://news-at.zhihu.com/api/4/news/latest
define(['vue', 'text!tpl/zhihu.html', 'css!../script/css/demos.css'], function (Vue, ContentHtml) {
    return Vue.extend({
        template: ContentHtml,
        data: function(){
            return {
            	title: '',
            	date: '',
            	zhihuList: []
            }
        },
        created: function(){
        	this.title="知乎日报";
        	var thisVue = this;
        	$.ajax({
  				url:'/index/zhihuList',
  				type:'post',
  				dataType:'json',
  				data:{},
  				success:function(data){
  					thisVue.zhihuList=data.stories;
  					thisVue.date=data.date;
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