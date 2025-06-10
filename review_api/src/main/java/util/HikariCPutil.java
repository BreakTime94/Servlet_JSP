package util;

import javax.sql.DataSource;

import org.mariadb.jdbc.Driver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class HikariCPutil {
	private static HikariDataSource dataSource;
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mariadb://np.climbjava.com:3306/sample");
		config.setUsername("sample");
		config.setPassword("1234");
		config.setDriverClassName("org.mariadb.jdbc.Driver");
		
		config.setMaximumPoolSize(10); // 최대 5개
		config.setMinimumIdle(5); //초기대기상태 5개
		config.setIdleTimeout(30000);
		config.setConnectionTimeout(30000);
		config.setPoolName("MyHikariCP");
		
		dataSource = new HikariDataSource(config);
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
	public static void main(String[] args) {
		System.out.println(dataSource);
		
	}
}
