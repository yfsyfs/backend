package com.yfs.dao;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(String id) {
		String sql = "insert into `tbl_user`(username, age) values(?, ?)";
		String username = id + "_" + UUID.randomUUID().toString().substring(0, 5); // 随机生成用户名

		jdbcTemplate.update(sql, username, 18); // jdbcTemplate中增删改调用的都是这个update方法
	}

}
