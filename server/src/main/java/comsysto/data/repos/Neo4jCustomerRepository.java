package comsysto.data.repos;

import comsysto.data.domain.Neo4jCustomer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/** @author Daniel Bartl */
public interface Neo4jCustomerRepository extends GraphRepository<Neo4jCustomer> {

    List<Neo4jCustomer> findOneByName(String name);

    // Neo4jCustomer with firstname and lastname
    @Query("start customer=node:Neo4jCustomer(firstname={0}) " +
            "where customer.lastname = {1} " +
            "return customer")
    List<Neo4jCustomer> findCustomer(String firstname, String lastname);

    // all friends of one customer
    @Query("start customer=node:Neo4jCustomer(name={0}) " +
            "match customer-[:KNOWS]-(friend) " +
            "return friend")
    List<Neo4jCustomer> findAllFriends(String name);

    // friends of friends, that aren't already friends of this customer
    @Query("start customer=node:Neo4jCustomer(name={0}) " +
            "match customer-[:KNOWS*1..3]-(friend) " +
            "where not(customer-[:KNOWS]-friend) and not(customer = friend) " +
            "return distinct friend")
    List<Neo4jCustomer> findNewFriends(String name);

    // common friends
    @Query("start customer1=node:Neo4jCustomer(name={0}), customer2=node:Neo4jCustomer(name={1}) " +
            "match customer1-[:KNOWS]-(friend)-[:KNOWS]-customer2 " +
            "where not(customer1 = customer2) " +
            "return distinct friend")
    List<Neo4jCustomer> findOurFriends(String name1, String name2);
}
