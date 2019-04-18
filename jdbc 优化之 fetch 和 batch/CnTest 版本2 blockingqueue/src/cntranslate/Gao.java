package cntranslate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class Gao {

	static Connection conn = null;
	static ResultSet rs = null;
	static PreparedStatement psta = null;
	static PreparedStatement pstb = null;
	static String drivername = "com.ibm.db2.jcc.DB2Driver";
	static String url = "jdbc:db2://10.0.110.166:50000/ecifdb2";
	static String username = "ecif";
	static String pwd = "ecif#166";
	static String sqla = "SELECT PARTY_ID,CUST_NAME FROM ecifper.T01_PER_CUST_INFO";
	static String sqlb = "UPDATE ecifper.T01_PER_CUST_INFO SET PINYING_NAME = ? WHERE PARTY_ID = ?";
	private static final BlockingQueue<Foo> queue = new ArrayBlockingQueue<>(10000);
	private static volatile boolean go = true;

	static {
		try {
			Class.forName(drivername);
			conn = DriverManager.getConnection(url, username, pwd);
			conn.setAutoCommit(false);
			System.out.println("数据库连接成功！");
			psta = conn.prepareStatement(sqla);
			pstb = conn.prepareStatement(sqlb);
			psta.setFetchSize(10000);
			rs = psta.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class Foo {
		private Integer id;
		private String custname;

		public Foo(Integer id, String custname) {
			this.id = id;
			this.custname = custname;
		}

	}

	private static class Producer implements Runnable {
		@Override
		public void run() {
			try {
				while (rs.next()) {
					queue.put(new Foo(rs.getInt(1), rs.getString(2)));
				}
				go = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static class Consumer implements Runnable {
		private int count;

		@Override
		public void run() {
			try {
				while (go) {
					Foo foo = queue.take();
					pstb.setString(1, PinyinHelper.convertToPinyinString(foo.custname, " ", PinyinFormat.WITHOUT_TONE));
					pstb.setInt(2, foo.id);
					pstb.addBatch();
					count++;
					if (count % 10000 == 0) {
						System.out.println(count);
						pstb.executeBatch();
						pstb.clearBatch();
					}
				}
				pstb.executeBatch();
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new Thread(new Producer()).start();
		new Thread(new Consumer()).start();
	}

}
