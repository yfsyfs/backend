package com.yfs.job;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Connection connection = null;
		Statement statement = null;
		try {
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String user = "postgres";
			String password = "postgres";
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, password);
			String sql = "select * from t_face_capture";
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getLong("id"));
				System.out.println(resultSet.getLong("cid"));
				System.out.println(resultSet.getString("device_name"));
				System.out.println(resultSet.getLong("capture_time"));
				System.out.println(resultSet.getString("capture_address"));
				System.out.println(resultSet.getDouble("longitude"));
				System.out.println(resultSet.getDouble("latitude"));
				InputStream sceneStream = resultSet.getBinaryStream("scene_stream");
				FileUtils.copyInputStreamToFile(sceneStream, new File("d:/sceneStream.jpg"));
				InputStream smallStream = resultSet.getBinaryStream("small_stream");
				FileUtils.copyInputStreamToFile(smallStream, new File("d:/smallStream.jpg"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}

		}
	}

}
