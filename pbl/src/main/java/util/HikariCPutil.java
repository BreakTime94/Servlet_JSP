package util;

import javax.sql.DataSource;

import org.mariadb.jdbc.Driver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import net.sf.log4jdbc.sql.jdbcapi.DriverSpy;


public class HikariCPutil {
	private static HikariDataSource dataSource;
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:log4jdbc:mariadb://np.climbjava.com:3306/pbl");
		config.setUsername("sample");
		config.setPassword("1234");
//		config.setDriverClassName("org.mariadb.jdbc.Driver");
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
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
