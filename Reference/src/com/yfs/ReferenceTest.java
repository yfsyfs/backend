package com.yfs;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import org.junit.jupiter.api.Test;

/**
* @Description: 各种引用的测试类 ， 下面提及不加jvm参数启动的话, 默认就是内存充裕的情况
* @author: 影法师
* @date: 2019年3月30日 上午9:26:41
*
* @Copyright: 2019 https://yfsyfs.github.io Inc. All rights reserved.
* 注意：本内容仅限于影法师信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class ReferenceTest {

	/**
	* @Description: 内存充足的情况下, 不会发生GC，直接运行，不需要加额外的jvm参数，运行结果就是打印一句话   运行完了...
	* @author: 影法师
	* @date: 2019年3月30日 上午9:27:22
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void nogc() {
		YFSReference reference = new YFSReference();
		reference = null;
		System.out.println("运行完了...");
	}

	/**
	* @Description: 手动触发gc, 不加任何jvm参数的情况下运行, 结果是 运行完了... now time : 1553909361432 is gc 说明我们手动触发了gc(虽然内存很充裕)
	* @author: 影法师
	* @date: 2019年3月30日 上午9:27:22
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void gc1() {
		YFSReference reference = new YFSReference();
		reference = null;
		// 让 reference 引用的堆内存再也无人引用, 该堆内存就处于可以被回收的境地
		System.gc();
		System.out.println("运行完了...");
	}

	/**
	* @Description: 虽然没有手动触发gc，但是内存不足，所以会触发gc线程回收垃圾. 此例需要配置jvm参数 -Xmx10m -Xms10m 控制台打印 now time : 1553909607755 is gc 运行完了...
	* @author: 影法师
	* @date: 2019年3月30日 上午9:27:22
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void gc2() {
		YFSReference reference = new YFSReference();
		// 让 reference 引用的堆内存再也无人引用, 该堆内存就处于可以被回收的境地
		reference = null;
		DrainMemory.drainMemory();
		System.out.println("运行完了...");
	}

	/**
	* @Description: 测试强引用  不加任何jvm参数, 和 gc1 比较就知道强引用不会被回收
	* @author: 影法师
	* @date: 2019年3月30日 上午9:24:52
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void strong() {
		YFSReference reference = new YFSReference();
		System.gc();
		System.out.println("运行完了...");
	}

	/**
	* @Description: 演示了即便内存OOM也不回收强引用  启动jvm参数是 -Xmx5m -Xms5m， 结果就是 java.lang.OutOfMemoryError: Java heap space 也没看到YFSReference被回收
	* 所以强引用就是所谓的死不放手
	* @author: 影法师
	* @date: 2019年3月30日 上午10:06:19
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void strongOOM() {
		YFSReference reference = new YFSReference();
		DrainMemory.drainMemory();
		System.out.println("运行完了...");
	}

	/**
	* @Description: 软引用测试  不加任何jvm参数 运行结果发现没有被回收 说明内存充足的情况是软引用是不会被回收的(即便手动敦促jvm进行full gc也不会回收软引用引用的堆内存)
	* @author: 影法师
	* @date: 2019年3月30日 上午9:42:04
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void softnogc() {
		// new YFSReference() 出来的堆内存目前只有一个软引用——softReference
		SoftReference<YFSReference> softReference = new SoftReference<YFSReference>(new YFSReference());
		System.out.println(softReference.get()); // com.yfs.YFSReference@59494225
		System.gc();
		System.out.println("运行完了...");
	}

	/**
	* @Description: 软引用测试  jvm参数是 -Xmx17m -Xms17m 运行结果是 now time : 1553910690748 is gc 运行完了... 说明内存不足的时候, 软引用就会被回收 所以软引用不会引起OOM
	* @author: 影法师
	* @date: 2019年3月30日 上午9:42:04
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void softgc() {
		// 现在new YFSReference() 出来的堆内存目前只有一个软引用——softReference
		SoftReference<YFSReference> softReference = new SoftReference<YFSReference>(new YFSReference());
		DrainMemory.drainMemory();
		DrainMemory.drainMemory();
		System.out.println("运行完了...");
	}

	/**
	* @Description: 弱引用测试  不加任何jvm参数, 运行结果是运行完了... now time : 1553910869739 is gc 说明弱引用不管内存是否充裕（这是弱引用和软引用的区别），只要被gc线程逮着就是个死(即被回收的下场)
	* 所以内存不足的时候, 触发gc，则弱引用更是会被回收的. 但是在内存充裕情况下, system.gc手动敦促gc只会回收掉弱引用而不会回收掉软引用
	* @author: 影法师
	* @date: 2019年3月30日 上午9:53:17
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void weak() {
		// 现在new YFSReference() 出来的堆内存目前只有一个弱引用——weakReference
		WeakReference<YFSReference> weakReference = new WeakReference<YFSReference>(new YFSReference());
		// 手动敦促gc线程开始逮人
		System.gc();
		System.out.println("运行完了...");
	}

	/**
	* @Description: 虚引用测试  运行结果是 null 我运行完了... now time : 1553911135245 is gc 说明 虚引用在实例化后，就被终止了
	* 而且虚引用
	* @author: 影法师
	* @date: 2019年3月30日 上午9:57:00
	* @param: 
	* @return: void
	* @throws
	 */
	@Test
	public void phantom() {
		ReferenceQueue<YFSReference> referenceQueue = new ReferenceQueue<>();
		// 虚引用必须要和引用队列一起运用
		PhantomReference<YFSReference> phantomReference = new PhantomReference<YFSReference>(new YFSReference(),
				referenceQueue);
		System.out.println(phantomReference.get()); // null(JDK文档 中写明了这个方法返回的就是null)
		System.gc();
		System.out.println("我运行完了...");
	}

}
