package util;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPUtil {
	private static HikariDataSource dataSource;
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:log4jdbc:mariadb://np.climbjava.com:3306/practice");
		config.setUsername("sample");
		config.setPassword("1234");
//		config.setDriverClassName("org.mariadb.jdbc.Driver");
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setMaximumPoolSize(10); // 최대 10개
		config.setMinimumIdle(5); //초기대기상태 5개
		config.setIdleTimeout(30000);
		config.setConnectionTimeout(30000);
		config.setPoolName("MyHikariCP");
		
		dataSource = new HikariDataSource(config);
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
	public static void main(String[] args) throws SQLException {
		System.out.println(getDataSource());
	}
}
