# fyft

面向webapp开发，单页应用。使用require.js管理js模块，没有使用webpack管理vue，组件是普通html文件，前后端未完全分离。
所以本工程主要只是对未学webpack的后端开发者提供一种如何整合Spring Boot和vue.js的思路。

涉及技术:

* Maven 构建工程
* Spring Boot 搭建后台系统 版本 1.5.2.RELEASE
* Spring 全家桶，SpringMVC、Spring JDBC

* fyft-core 基础工程，存放工具封装类
* fyft-wx webapp 单页应用。使用 vue.js 框架，使用 require.js 管理模块化文件
* fyft-admin 后台管理

## 相关链接

* vue.js		https://cn.vuejs.org
* require.js 及相关插件		http://www.requirejs.cn/
* require.css 插件		https://github.com/guybedford/require-css
* jqweui组件		http://jqweui.com/
* layui		http://www.layui.com/
* 基于layui 后台模板 besteasyteam			http://git.oschina.net/besteasyteam/beginner_admin
