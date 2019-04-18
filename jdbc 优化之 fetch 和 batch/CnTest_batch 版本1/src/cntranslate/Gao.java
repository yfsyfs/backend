package cntranslate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class Gao {
	

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement psta = null;
		PreparedStatement pstb = null;
		String drivername = "com.ibm.db2.jcc.DB2Driver";
		Class.forName(drivername);
		String url = "jdbc:db2://10.0.110.166:50000/ecifdb2";
		String username = "ecif";
		String pwd = "ecif#166";
		try {
			System.out.println("正在连接数据库。。。");
			conn = DriverManager.getConnection(url, username, pwd);
			conn.setAutoCommit(false);
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sqla = "SELECT PARTY_ID,CUST_NAME FROM ecifper.T01_PER_CUST_INFO";
		String sqlb = "UPDATE ecifper.T01_PER_CUST_INFO SET PINYING_NAME = ? WHERE PARTY_ID = ?";
		try {
			psta = conn.prepareStatement(sqla);
			pstb = conn.prepareStatement(sqlb);
			psta.setFetchSize(10000);
			rs = psta.executeQuery();
			System.out.println("正在获取数据。。。");
			int i = 0;
			while (rs.next()) {
				pstb.setString(1, PinyinHelper.convertToPinyinString(rs.getString(2), " ", PinyinFormat.WITHOUT_TONE));
				pstb.setString(2, rs.getString(1));
				pstb.addBatch();
				i++;
				if (i % 10000 == 0) {
					System.out.println(i);
					pstb.executeBatch();
					pstb.clearBatch();
				}
			}
			pstb.executeBatch();  // 扫尾，防止数据条数不被10000整除
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (psta != null) {
					psta.close();
				}
				if (pstb != null) {
					pstb.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
