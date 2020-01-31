	cd C:\Program Files\PostgreSQL\9.6\bin>
	psql -U postgres
	create database bd_coder_gem_api with owner=postgres encoding='utf8';
	template1=# create user usr_coder_gem_api with password 'pwd_coder_gem_api';
	CREATE ROLE
	template1=# grant ALL on DATABASE bd_coder_gem_api to usr_coder_gem_api;
	GRANT
	template1=# \q	
	
	psql -U usr_coder_gem_api -d bd_coder_gem_api
	pwd_coder_gem_api
	
	\connect bd_coder_gem_api
	
	\q
	
	--enviando o dump
	scp /c/home/teste1.sql coder1@codersistemas.com.br:~
	
	--logando na vm
	ssh coder1@codersistemas.com.br (pwd /home/coder1)
	
	
	--executando o dump
	psql -U usr_coder_gem_api -d bd_coder_gem_api -f /home/coder1/teste1.sql bd_coder_gem_api
	pwd_coder_gem_api
	
	
	psql -U usr_coder_gem_api -d bd_coder_gem_api
	pwd_coder_gem_api
	
	--verificando os schemas
	\dn
	
	--verificando as tabelas
	\dt
	
	
	--agora é...
	--Gerar o build no perfil de PRD (sem create-table) 
	
	
	
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_coder_gem_api
spring.datasource.username=usr_coder_gem_api
spring.datasource.password=pwd_coder_gem_api
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=FALSE




grant ALL on schema public to usr_coder_gem_api;






-----------------------------------------------------------
--                  GERAR ARTEFATO
-----------------------------------------------------------





-----------------------------------------------------------
--                  GERAR ARTEFATO
-----------------------------------------------------------

	API:
		
		cd /c/home/gustavo/desenv/workspace-github/coder-gem-api
		mvn package -DskipTests
		cd target
		ls -lah
		cp coder-gem-api-0.0.1-SNAPSHOT.jar coder-gem-api.jar
		ls -lah
		scp coder-gem-api.jar coder1@codersistemas.com.br:~
		ssh coder1@codersistemas.com.br
		sudo rm /etc/init.d/coder-gem-api
		sudo rm /var/apps/coder-gem-api/coder-gem-api.jar
		sudo mv coder-gem-api.jar /var/apps/coder-gem-api/
		ls -lah /var/apps/coder-gem-api/

		sudo ln -s /var/apps/coder-gem-api/coder-gem-api.jar /etc/init.d/coder-gem-api

		sudo update-rc.d coder-gem-api defaults
		service coder-gem-api start
		service coder-gem-api status

		/etc/init.d/coder-gem-api stop
		/etc/init.d/coder-gem-api start
		http://192.168.1.119:8085/coder-gem-api/pessoas

		
		
		
		
		
		mvn package -DskipTests -Dspring.profiles.active=prod 
		mvn clean package -Pprod -DskipTests
		
		java -jar target/coder-gem-api-0.0.1-SNAPSHOT.jar

		
