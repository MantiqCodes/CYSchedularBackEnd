package io.giskard.scheduler.mongo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "io.giskard.scheduler.mongo")
public class MongoDBConfig {
	public String getDatabaseName() {
		return "myMongoDB";
	}
	@Bean
	public MongoClient mongoClient() {
		ServerAddress address = new ServerAddress("localhost", 27017);
		MongoCredential credential = MongoCredential.createCredential("user", getDatabaseName(), "user".toCharArray());
		MongoClientOptions options = new MongoClientOptions.Builder().build();
        
		MongoClient client = new MongoClient(address, credential, options);
		return client;
	}
	@Bean
	public MongoDbFactory mongoDbFactory() {
		MongoDbFactory factory = new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
		return factory;
	}
	@Bean
	public MongoTemplate mongoTemplate() {
		MongoTemplate template = new MongoTemplate(mongoDbFactory());
		return template;
	}
} 



/*
 * 
 * 
 * db.createUser(
  {
    user: "user",
    pwd: "user",
    roles: [
       { role: "readWrite", db: "myMongoDB" }
    ]
  }
)
 

>>MongoSheell Log in : 

 mongo --host 'localhost' -u user -p user  myMongoDB

>> Drop database 
mongo -u user -p user myMongoDB --eval "db.dropDatabase()"
 * 
 * 
 */



