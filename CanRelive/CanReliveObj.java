package chpt4.eg1;

/**
 * 可复活的
 * 
 * @author yfs
 *
 */
public class CanReliveObj {

	public static CanReliveObj obj;

	@Override
	public void finalize() throws Throwable {

		super.finalize();

		System.out.println("CanReliveObj finalize called...");

		obj = this;

	}

	public static void main(String[] args) throws InterruptedException {

		obj = new CanReliveObj();

		obj = null;

		// 手工调用System.gc将导致一次Full GC
		System.gc();

		// 主线程停顿100ms(gc线程仍旧进行, 则将回收上面分配的CanReliveObj的新生代堆内存， 则覆盖的finalize方法执行,
		// 堆内存复活)
		Thread.sleep(100);

		if (obj == null) {

			System.out.println("obj is null...");

		} else {

			// 打印这个, 因为 堆内存复活
			System.out.println("obj可用...");

		}

		System.out.println("第二次gc...");

		obj = null;

		System.gc();

		Thread.sleep(100);

		if (obj == null) {

			// 打印这个, 因为一个对象的finalize只能调用一次
			System.out.println("obj is null...");

		} else {

			System.out.println("obj可用...");

		}

	}

}
