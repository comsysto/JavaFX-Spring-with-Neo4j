package comsysto.config;

import comsysto.server.ICustomerService;
import comsysto.server.Neo4jCustomerService;
import comsysto.setup.DatabaseSetup;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

/** @author Daniel Bartl */
@Configuration
@EnableNeo4jRepositories("comsysto.data.repos")
public class Neo4jConfig extends Neo4jConfiguration {

    @Bean
    public GraphDatabaseService graphDatabaseService() {

        // removed with every maven clear
        return new EmbeddedGraphDatabase("./target/neo4j-testdb");
        // stays after clear
        //return new EmbeddedGraphDatabase("./data/neo4j-testdb");
    }

    @Bean
    public DatabaseSetup databaseSetup() {

        return new DatabaseSetup();

    }

    @Bean(name = "neo4jCustomerService")
    public ICustomerService customerService() {

        return new Neo4jCustomerService();

    }

}
