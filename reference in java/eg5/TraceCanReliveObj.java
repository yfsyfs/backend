package chpt4.eg5;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class TraceCanReliveObj {

	private static TraceCanReliveObj obj;

	/** 引用队列 */
	private static ReferenceQueue<TraceCanReliveObj> phantomQueue = null;

	private static class CheckRefQueueThread extends Thread {

		@Override
		public void run() {

			while (true) {

				if (phantomQueue != null) {

					PhantomReference<TraceCanReliveObj> phantomReference = null;

					try {
						phantomReference = (PhantomReference<TraceCanReliveObj>) phantomQueue.remove();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (phantomReference != null) {

						System.out.println("TraceCanReliveObj is deleted By GC...");

					}

				}

			}

		}

	}

	/**
	 * 析构方法（任何对象的析构方法只能调用一次）
	 */
	@Override
	protected void finalize() throws Throwable {

		super.finalize();

		System.out.println("TraceCanReliveObj finalize called...");

		// 复活
		obj = this;

	}

	public static void main(String[] args) throws InterruptedException {

		Thread thread = new CheckRefQueueThread();

		thread.setDaemon(true);

		thread.start();

		phantomQueue = new ReferenceQueue<>();

		obj = new TraceCanReliveObj();

		// obj对应的堆实例被虚引用
		PhantomReference<TraceCanReliveObj> phantomReference = new PhantomReference<TraceCanReliveObj>(obj,
				phantomQueue);

		// 强引用失效, 唯余虚引用
		obj = null;

		System.out.println("第一次gc...");

		// 手动触发gc, 调用该实例的finalize方法, 将复活, 不能被gc， 所以phantomQueue中不会有虚引用的, 即phantomReference是null
		System.gc();

		Thread.sleep(1000);

		if (obj == null) {

			System.out.println("obj 是 null");

		} else {

			// 打印这个, 因为finalize中对象复活, 不会被gc
			System.out.println("obj 可用");

		}

		System.out.println("第二次gc...");

		// 再次让强引用失效, 唯余虚引用
		obj = null;

		// 手动触发gc, 不再调用该实例的finalize方法, 对象被回收(因为一个对象的finalize方法只会被调用一次)
		System.gc();

		Thread.sleep(1000);

		if (obj == null) {

			// 打印这个, 因为finalize方法不再被调用
			System.out.println("obj is null");

		} else {

			System.out.println("obj 可用");

		}

	}

}
/*
不需要加任何jvm参数，打印
第一次gc...
TraceCanReliveObj finalize called...
obj 可用
第二次gc...
TraceCanReliveObj is deleted By GC...
obj is null
*/