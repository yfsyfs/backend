package chpt4.eg4;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 弱引用——发现就被干掉
 * 
 * @author yfs
 *
 */
public class WeakRef {

	private static ReferenceQueue<User> weakQueue;

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

	private static class UserWeakReference extends WeakReference<User> {

		private int uid;

		public UserWeakReference(User referent, ReferenceQueue<User> queue) {
			super(referent, queue);
			this.uid = referent.id;
		}

	}

	private static class CheckRefQueueThread extends Thread {

		@Override
		public void run() {

			while (true) {

				if (weakQueue != null) {

					UserWeakReference userWeakReference = null;

					try {
						userWeakReference = (UserWeakReference) weakQueue.remove();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (userWeakReference != null) {

						System.out.println("user id " + userWeakReference.uid + " is deleted...");

					}

				}

			}

		}

	}

	public static void main(String[] args) {

		Thread thread = new CheckRefQueueThread();

		thread.setDaemon(true);

		thread.start();

		User user = new User(1, "yfs");

		weakQueue = new ReferenceQueue<>();

		UserWeakReference userWeakReference = new UserWeakReference(user, weakQueue);

		// 强引用失效
		user = null;

		// 内存充足, 不会gc
		System.out.println(userWeakReference.get());

		// 手动触发 Full GC
		System.gc();

		System.out.println("After GC");

		// 不论内存是否充裕, 只要gc, 就会把弱引用回收
		System.out.println(userWeakReference.get());

	}

}
/*
不需要加任何jvm参数, 执行结果如下
User [id=1, name=yfs]
After GC
null
user id 1 is deleted...
*/