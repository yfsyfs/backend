Spring提供的扩展点——BeanFactoryPostProcessor

前面说过BeanPostProcessor, 即bean的后置处理器，在bean的创建对象的初始化的前后进行拦截工作
而
BeanFactoryPostProcessor是beanFactory的后置处理器，即就像它的注释里面说的一样

Modify the application context's internal bean factory after its standard
initialization. All bean definitions will have been loaded, but no beans
will have been instantiated yet. This allows for overriding or adding
properties even to eager-initializing beans.

即所有的bean定义已经保存并加载，但是没有bean被初始化，即没有任何bean实例被创建（哪怕非懒加载的bean）