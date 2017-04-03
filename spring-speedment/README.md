Spring Boot and Speedment project
===========================================

See

* http://projects.spring.io/spring-boot
* https://github.com/speedment/speedment


To run the demo, perform the following steps.

* Create a MySQL database with name *hares* with the following table and some test data:
```
		CREATE DATABASE `spring_speedment`;
		
		CREATE TABLE `customer` (
		  `id` bigint(20) NOT NULL AUTO_INCREMENT,
		  `name` varchar(32) NOT NULL,
		  PRIMARY KEY (`id`),
		  UNIQUE KEY `name` (`name`)
		) ;
	    
		INSERT INTO `spring_speedment`.`customer` (`name`) VALUES ("cust1");
```

* With MySQL running, start the Speedment code generator tool

```
    mvn speedment:tool
```

* Enter database credentials in the UI, fill in the schema *hares*

* Generate code by clicking the "Generate" button without changing any defaults

* Exit the Speedment Tool

* Clean and test the application
```
    mvn clean test
```
