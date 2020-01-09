make:
	cd Server
	mvn clean package
	cd ..
run: make
	java -jar Server/target/Server-1.0-SNAPSHOT.jar server Server/config.yml
