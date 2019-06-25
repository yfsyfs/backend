总结下，给ioc容器中注册组件的方式
1. 包扫描+@controller、@Service、@Repository、@Component ，这种方式比较局限——我们只能把自己写的类注入到IOC容器中，但是不是我们自己写的类
例如第三方的包，就不能使用这种方式了，所以我们要使用下面第二种方式
2. @Bean 一般用于导入第三方的包的组件(导入自己的完全可以)
3. spring还为我们提供了第三种方式---@Import，他可以快速给容器导入一个组件（@Bean还是太麻烦）. 其中@Import 有
@Import @ImportSelector @ImportBeanDefinitionRegistrar 三种用法，在 springboot中 @ImportSelector 用的尤其多
4. 使用spring提供的factorybean（工厂bean）
