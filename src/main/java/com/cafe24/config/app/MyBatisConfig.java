package com.cafe24.config.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
	
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:config/app/mybatis/configuration.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	
	/*
	 * getMapper()에 의해 리턴된 매퍼가 가진 메서드를 포함해서 SQL을 처리하는 
	 * 마이바티스 메서드를 호출할때 SqlSessionTemplate은 SqlSession이 
	 * 현재의 스프링 트랜잭션에서 사용될수 있도록 보장한다. 
	 * 추가적으로 SqlSessionTemplate은 필요한 시점에 세션을 닫고, 
	 * 커밋하거나 롤백하는 것을 포함한 세션의 생명주기를 관리한다. 
	 * 또한 마이바티스 예외를 스프링의 DataAccessException로 변환하는 작업또한 처리한다.
	 * [참고]http://www.mybatis.org/spring/ko/sqlsession.html
	*/
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	
}
