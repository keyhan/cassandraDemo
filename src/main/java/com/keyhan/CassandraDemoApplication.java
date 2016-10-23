package com.keyhan;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CassandraDemoApplication {

//	@Bean
//    public Cluster cluster() {
//        return Cluster.builder()
//                .addContactPoint("127.0.0.1")
//                .build();
//    }
//
//    @Bean
//    public Session session() {
//        return cluster().connect();
//    }
//
//    @Bean
//    MappingManager mappingManager() {
//        return new MappingManager(session());
//    }

	public static void main(String[] args) {
		SpringApplication.run(CassandraDemoApplication.class, args);
	}
}
