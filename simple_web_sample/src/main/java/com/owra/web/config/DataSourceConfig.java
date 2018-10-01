package com.owra.web.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mysql.jdbc.Driver;

import oracle.jdbc.OracleDriver;

@Configuration
public class DataSourceConfig {

	@Value("${spring.dbtype}")
	private String DbType;
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String userPasswd;
	
	@Bean
	public DataSource dataSource(Environment environment) {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setUsername(userName);
		dataSource.setPassword(userPasswd);
		try {
			if("mysql".equals(DbType)) {
				Driver driver = new com.mysql.jdbc.Driver();
				dataSource.setDriver(driver);
				dataSource.setUrl("jdbc:mysql://"+dbUrl);
			} else if("oracle".equals(DbType)) {
				OracleDriver driver = new oracle.jdbc.OracleDriver();
				dataSource.setDriver(driver);
				dataSource.setUrl("jdbc:oracle:thin:@"+dbUrl);
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return dataSource;
		
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFatory(DataSource datasource) throws Exception {
		
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(datasource);
		sqlSessionFactory.setTypeAliasesPackage("com.owra.web.example.domain");
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("/mapper/*/*.xml"));
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
