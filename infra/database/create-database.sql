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
	scp /c/home/teste1.sql usuario1@meudominio.com.br:~
	
	--logando na vm
	ssh usuario1@meudominio.com.br (pwd /home/usuario1)
	
	
	--executando o dump
	psql -U usr_coder_gem_api -d bd_coder_gem_api -f /home/usuario1/teste1.sql bd_coder_gem_api
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
	
		IMPORTANTE: Colocar no pom o executable e finalname. se alterar o nome do jar vai dar problema...
		
		cd /c/home/gustavo/desenv/workspace-github/coder-gem-api
		cd /home/gustavo/dev/workspace-coder/coder-gem-api/
		
		mvn package -DskipTests
		ls target/ -lah
		cd target
		scp coder-gem-api.jar usuario1@meudominio.com.br:~
		ssh usuario1@meudominio.com.br
		sudo rm /etc/init.d/coder-gem-api
		sudo rm /var/apps/coder-gem-api/coder-gem-api.jar
		sudo mv coder-gem-api.jar /var/apps/coder-gem-api/
		ls -lah /var/apps/coder-gem-api/
		java -jar /var/apps/coder-gem-api/coder-gem-api.jar
			http://meudominio.com.br:8084/coder-gem/pessoas 
			http://localhost:8084/coder-gem/pessoas
		
		
		via init.d (System V - antigo)

			sudo ln -s /var/apps/coder-gem-api/coder-gem-api.jar /etc/init.d/coder-gem-api
	
			sudo update-rc.d coder-gem-api defaults
			service coder-gem-api start
			service coder-gem-api status
	
			/etc/init.d/coder-gem-api stop
			/etc/init.d/coder-gem-api start
			http://192.168.1.119:8085/coder-gem-api/pessoas
			
		via systemd

			sudo cp /home/gustavo/dev/workspace-coder/coder-gem-api/infra/coder-gem-api.service /etc/systemd/system 
			#executar para atualizar
			systemctl daemon-reload
			
			service coder-gem-api stop
			service coder-gem-api start
			service coder-gem-api status
		
		
		service coder-gem-api stop && service coder-gem-api start && service coder-gem-api status
		
		
		
		mvn package -DskipTests -Dspring.profiles.active=prod 
		mvn clean package -Pprod -DskipTests
		
		java -jar target/coder-gem-api-0.0.1-SNAPSHOT.jar

		
 deploy frontend
 
 	#preparando o ambiente:
	  	ssh usuario1@meudominio.com.br
	 	cd /var/www
	 	ls -lah
	 	sudo mkdir coder-gem-api
		exit
	 
 	replace enviroment -> url ->  pela de produção
 	
 	cd /c/home/gustavo/desenv/workspace-github/coder-gem-ui/
 	ng build
 	cd dist/
 	ls -lah
 	tar -cf coder-gem-ui.tar .
 	scp  coder-gem-ui.tar usuario1@meudominio.com.br:~
 	ssh usuario1@meudominio.com.br 
 	ls -lah
 	sudo cp coder-gem-ui.tar /var/www/
 	cd /var/www/
 	ls -lah
 	sudo tar -xf coder-gem-ui.tar
 	ls coder-gem-ui -lah
	sudo rm coder-gem-ui.tar
	ls -lah
 	
	
	ls /etc/nginx/sites-enabled/ -lah
	sudo ln -s /etc/nginx/sites-available/nginx-default.conf /etc/nginx/sites-enabled/
