/**
 * 
 */
define(['vue', 'text!tpl/article.html', 'css!../script/css/demos.css'], function (Vue, ContentHtml) {
    return Vue.extend({
        template: ContentHtml,
        data: function(){
            return {
            	title: '',
            	content: '',
            	date: '',
            	zhihuList: []
            }
        },
        created: function(){
        	var thisVue = this;
        	var id = this.$route.params.id;
        	$.ajax({
  				url:'index/zhihu/'+id,
  				type:'post',
  				dataType:'json',
  				data:{},
  				success:function(data){
  					//thisVue.zhihuList=data.stories;
  					thisVue.title=data.title;
  					thisVue.content=data.body;
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