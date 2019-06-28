package chpt4.eg2;

import java.lang.ref.SoftReference;

/**
 * 演示了软引用在堆内存不足的时候被回收， 以 -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+PrintFlagsFinal 为参数运行
 * 
 * @author yfs
 *
 */
public class SoftRef {

	private static class User {

		public int id;

		public String name;

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

	public static void main(String[] args) throws InterruptedException {

		// 栈上的user 持有User实例A(在java堆上分配)的强引用
		User user = new User(1, "yfs");

		// 栈上的userSoftReference 持有 A的软引用
		SoftReference<User> userSoftReference = new SoftReference<SoftRef.User>(user);

		user = null;

		// User [id=1, name=yfs] 软引用依旧可以访问堆内存
		System.out.println(userSoftReference.get());

		// 触发Full GC
		System.gc();

		System.out.println("After GC...");

		// User [id=1, name=yfs] 因为内存足够, 所以不会导致GC
		System.out.println(userSoftReference.get());

		// 申请6.32MB的堆内存
		byte[] bs = new byte[7 * 925 * 1024];

		// 内存不够了, 导致一次GC，将软引用回收, 导致下面打印null, 其实, 这里的 System.gc() 是多余的, 因为分配一个大对象（Xmx为10MB, 这个对象6.32MB）,因为 通过 -XX:+PrintFlagsFinal参数打印出来的NewRatio 比例是2, 也就是新生代才3MB左右, 一旦分配此对象就会导致新生代gc，软引用被回收
//		System.gc();

		// 主线程停止100ms, gc线程运行
		Thread.sleep(100);

		// null
		System.out.println(userSoftReference.get());

	}

}
