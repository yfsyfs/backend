使用gradle构建项目的helloworld, 因为本机安装了gradle3.5.1 版本, 所以有两种方式进行构建
1. cmd进到项目根路径下面, 执行
gradle build
则在项目根路径的build目录下的libs目录中会产生initializr-start-0.0.1-SNAPSHOT.jar(这个名字是build.gradle中的jar属性决定的)
然后使用java -jar initializr-start-0.0.1-SNAPSHOT.jar , 然后访问http://localhost/hello
浏览器打印
Hello World!

2. cmd到项目根路径下面, 执行
gradlew.bat build
(我的机器是Windows)
然后就会先到网络上下载gradle2.13, 然后使用gradle2.13版本对项目进行构建, 后面的执行是一样的. 由此可见, gradlew的作用是
不需要你本机安装了gradle,但是需要你配置GRADLE_USER_HOME环境变量（gradle-wrapper.properties需要）
我本机配置的GRADLE_USER_HOME=D:\gradle-3.5.1\repository
所以下载好的gradle2.13位于D:\gradle-3.5.1\repository\wrapper\dists