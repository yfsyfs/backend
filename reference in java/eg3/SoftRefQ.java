package com.yfs.finalize;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 以 -Xmx8m 为参数运行程序
 * 
 * @author yfs
 *
 */
public class SoftRefQ {

	/** 软引用队列 */
	private static ReferenceQueue<User> softQueue;

	private static class User {

		private int id;

		private String name;

		public User(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}

	}

	/**
	 * 软引用
	 * 
	 * @author yfs
	 *
	 */
	private static class UserSoftReference extends SoftReference<User> {

		/** 记录强引用的id */
		public int uid;

		/**
		 * 每个软引用可以附带一个引用队列
		 * 
		 * @param user
		 *            该软引用引用的堆上实例, 必须要是User或者其父类(即指定了泛型的下界)
		 * @param queue
		 *            附带的引用队列
		 */
		public UserSoftReference(User referent, ReferenceQueue<? super User> queue) {

			super(referent, queue);

			uid = referent.id;

		}

	}

	/**
	 * 不断的从引用队列中的移除对象(这些对象都是被清除的软引用对象)
	 * 
	 * @author yfs
	 *
	 */
	private static class CheckRefQueueThread extends Thread {

		@Override
		public void run() {

			while (true) {

				if (softQueue != null) {

					UserSoftReference userSoftReference = null;

					// 抛出 InterruptedException 表明此操作耗时, 但是可以中断, 这是一个阻塞方法
					try {
						userSoftReference = (UserSoftReference) softQueue.remove();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// 如果移除成功
					if (userSoftReference != null) {

						System.out.println("user id " + userSoftReference.uid + " is deleted..."); // 不要觉得奇怪，这里打印的uid并不是被gc掉的对象的,而是软引用对象自身中维护的

					}

				}

			}

		}

	}

	public static void main(String[] args) throws InterruptedException {

		Thread thread = new CheckRefQueueThread();

		thread.setDaemon(true);

		// 开一根线程检查softQueue
		thread.start();

		User user = new User(1, "yfs");

		softQueue = new ReferenceQueue<>();

		// 让软引用绑定一个引用队列
		UserSoftReference userSoftReference = new UserSoftReference(user, softQueue);

		// 让强引用失效
		user = null;

		// User [id=1, name=yfs]  通过软引用访问堆上的对象
		System.out.println(userSoftReference.get());

		// 手动触发Full GC
		System.gc();

		System.out.println("After GC");

		// User [id=1, name=yfs] (因为内存足够, 所以尽管User类在堆上的实例只被userSoftReference弱引用,
		// 但也不会被回收,尽管此对象的强引用已经消失了)
		System.out.println(userSoftReference.get());

		// 申请6.32MB 堆内存， 就算不手动触发gc, 也会引发新生代gc的
		byte[] bs = new byte[7 * 925 * 1024];

		// System.gc();

		// null
		System.out.println(userSoftReference.get());

	}

}
/* 执行结果如下
User [id=1, name=yfs]
After GC
User [id=1, name=yfs]
Exception in thread "main" user id 1 is deleted...
java.lang.OutOfMemoryError: Java heap space
	at com.yfs.finalize.SoftRefQ.main(SoftRefQ.java:136)
*/