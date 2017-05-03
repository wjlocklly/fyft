
#### 官网	[http://www.layui.com](http://www.layui.com)

版本：1.0.9
目录结构
```code
    ├─css //css目录
    │  └─modules //模块css目录（一般如果模块相对较大，我们会单独提取）
    │      ├─laydate
    │      ├─layer
    │      │  └─default
    │      └─layim
    │          └─skin
    ├─font  //字体图标目录
    ├─images //图片资源目录（一些表情等）
    │  └─face
    └─lay //JS目录
        ├─dest //经过合并的完整模块
        └─modules //各模块/组件
```

规定使用规范化方法使用，不使用直接引入layui.all.js方式

规范化使用
```JavaScript
<script>
layui.config({
  base: '/res/js/modules/' //你的模块目录
}).use('index'); //加载入口
</script>
```

上述的 index 即为你 /res/js/modules/ 目录下的 index.js，它的内容应该如下：
```JavaScript
/**
  项目JS主入口
  以依赖Layui的layer和form模块为例
**/
layui.define(['layer', 'form'], function(exports){
  var layer = layui.layer
  ,form = layui.form();

  layer.msg('Hello World');

  exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});

```
详细看官方文档
