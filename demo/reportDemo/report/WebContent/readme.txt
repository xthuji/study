项目名：report（必须是这个，代码中的很多链接用了它）
访问路径为：
http://localhost:8080/report/index.html

可根据自己需要配置host和改端口

本demo中使用了3种方法生成图形报表
1、jaspereport
2、jqPlot（jquery插件）
3、fusionChart（flash图表插件）
fusionChart例子中的某些swf模板版本比较老，请自己用最新的模板替换（xml数据不用变，更换模板即可）

后两种方法的样例 都是使用ajax请求获取json数据，
在前端用js组装插件所需要的数据，然后调用插件的方法进行图表渲染
