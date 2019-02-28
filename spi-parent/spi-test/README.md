spi测试类
1. 运行TestPI 的main方法, 则控制台抛出异常
Exception in thread "main" java.lang.RuntimeException: 服务接口未发现实现类.
	at com.yfs.spi.PeopleFactory.invoke(PeopleFactory.java:23)
	at com.yfs.test.TestSPI.main(TestSPI.java:8)

2. 打开pom.xml中`spi-interface的第一个实现类`的注释, 则再次运行, 控制台打印
I am a boy.

2. 继续打开pom.xml 中`spi-interface的第二个实现类`的注释, 则再次运行, 控制台打印
I am a boy.
I am a girl.


表明服务被发现