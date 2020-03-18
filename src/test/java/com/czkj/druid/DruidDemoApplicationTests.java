package com.czkj.druid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class DruidDemoApplicationTests {


	@Autowired
	DataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement prepareStatement = connection
				.prepareStatement("select * from t_user");
		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			String cityName = resultSet.getString("mobile");
			System.out.println(cityName);
		}
	}
}
