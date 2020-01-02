	cd C:\Program Files\PostgreSQL\9.6\bin>
	psql -U postgres
	create database bd_coder_gem_api with owner=postgres encoding='utf8';
	template1=# create user usr_coder_gem_api with password 'pwd_coder_gem_api';
	CREATE ROLE
	template1=# create database bd_coder_gem_api;
	CREATE DATABASE
	template1=# grant ALL on DATABASE bd_coder_gem_api to usr_coder_gem_api;
	GRANT
	template1=# \q	
	
	
	
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_coder_gem_api
spring.datasource.username=usr_coder_gem_api
spring.datasource.password=pwd_coder_gem_api
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false