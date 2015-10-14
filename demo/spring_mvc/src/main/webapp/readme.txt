lib/目录中的两个jar包
因使用tomcat启动时会报错（runjetty不报错）
NoClassDefFoundError: javax/servlet/jsp/jstl/core/Config

使用spring3.05 mvc进行开发，使用tomcat容器，通过url映射寻找view的时候，
会报错NoClassDefFoundError: javax/servlet/jsp/jstl/core/Config，
如果随便去找个jstl包过来放入web-inf/lib会报错，
正确的下载地址在这里，http://archive.apache.org/dist/jakarta/taglibs/standard/binaries/
下载jakarta-taglibs-standard-1.1.2.zip这个包，
解压缩后将standard和jstl两个包放入lib下即可 