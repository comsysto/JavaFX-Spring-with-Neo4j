package comsysto.setup;

import comsysto.data.domain.Neo4jCustomer;
import comsysto.data.repos.Neo4jCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

/** @author Daniel Bartl */
public class DatabaseSetup {

    @Autowired
    Neo4jCustomerRepository neo4jCustomerRepository;

    final String NAME_KEY = "name";


    @PostConstruct
    public void init() {

        // empty database
        neo4jCustomerRepository.deleteAll();

        // Neo4jCustomers
        Collection<Neo4jCustomer> someNeo4jCustomers = generateSomeCustomers();
        neo4jCustomerRepository.save(someNeo4jCustomers);

        System.out.println();

        System.out.println("Number of customers saved : " + getNumberOfCustomers());

        System.out.println("All customers:");

        for (Neo4jCustomer customer : neo4jCustomerRepository.findAll()) {
            System.out.println(customer);
        }

        System.out.println("Find Stephen's friends:");

        for (Neo4jCustomer friends : neo4jCustomerRepository.findAllFriends("Stephen Chin")) {
            System.out.println(friends);
        }

        System.out.println("Find some new friends for Stephen:");

        for (Neo4jCustomer friends : neo4jCustomerRepository.findNewFriends("Stephen Chin")) {
            System.out.println(friends);
        }

        System.out.println("Find shared friends of Stephen and Ben:");

        for (Neo4jCustomer friends : neo4jCustomerRepository.findOurFriends("Stephen Chin", "Ben Evans")) {
            System.out.println(friends);
        }

        System.out.println();

    }

    public long getNumberOfCustomers() {
        return neo4jCustomerRepository.count();
    }

    public Neo4jCustomer createCustomer(String firstname, String lastname) {
        return neo4jCustomerRepository.save(new Neo4jCustomer(firstname, lastname));
    }


    public ArrayList<Neo4jCustomer> generateSomeCustomers() {
        ArrayList<Neo4jCustomer> someNeo4jCustomers = new ArrayList<Neo4jCustomer>();

        // Customers
        someNeo4jCustomers.add(createCustomer("Adam", "Bien"));
        someNeo4jCustomers.add(createCustomer("Andres", "Almiray"));
        someNeo4jCustomers.add(createCustomer("Ben", "Evans"));
        someNeo4jCustomers.add(createCustomer("Bob", "Lee"));
        someNeo4jCustomers.add(createCustomer("Chris", "Richardson"));
        someNeo4jCustomers.add(createCustomer("Jasper", "Potts"));
        someNeo4jCustomers.add(createCustomer("Jim", "Weaver"));
        someNeo4jCustomers.add(createCustomer("Josh", "Long"));
        someNeo4jCustomers.add(createCustomer("Kirk", "Pepperdine"));
        someNeo4jCustomers.add(createCustomer("Martijn", "Verburg"));
        someNeo4jCustomers.add(createCustomer("Richard", "Bair"));
        someNeo4jCustomers.add(createCustomer("Stephen", "Chin"));
        someNeo4jCustomers.add(createCustomer("Daniel", "Bartl"));

        // Relationships
        someNeo4jCustomers.get(0).addFriend(someNeo4jCustomers.get(1));
        someNeo4jCustomers.get(0).addFriend(someNeo4jCustomers.get(3));
        someNeo4jCustomers.get(0).addFriend(someNeo4jCustomers.get(5));
        someNeo4jCustomers.get(0).addFriend(someNeo4jCustomers.get(7));
        someNeo4jCustomers.get(0).addFriend(someNeo4jCustomers.get(9));
        someNeo4jCustomers.get(0).addFriend(someNeo4jCustomers.get(11));
        someNeo4jCustomers.get(1).addFriend(someNeo4jCustomers.get(2));
        someNeo4jCustomers.get(1).addFriend(someNeo4jCustomers.get(4));
        someNeo4jCustomers.get(1).addFriend(someNeo4jCustomers.get(6));
        someNeo4jCustomers.get(1).addFriend(someNeo4jCustomers.get(8));
        someNeo4jCustomers.get(1).addFriend(someNeo4jCustomers.get(10));
        someNeo4jCustomers.get(2).addFriend(someNeo4jCustomers.get(4));
        someNeo4jCustomers.get(2).addFriend(someNeo4jCustomers.get(7));
        someNeo4jCustomers.get(2).addFriend(someNeo4jCustomers.get(10));
        someNeo4jCustomers.get(3).addFriend(someNeo4jCustomers.get(4));
        someNeo4jCustomers.get(3).addFriend(someNeo4jCustomers.get(11));
        someNeo4jCustomers.get(5).addFriend(someNeo4jCustomers.get(6));
        someNeo4jCustomers.get(6).addFriend(someNeo4jCustomers.get(8));
        someNeo4jCustomers.get(7).addFriend(someNeo4jCustomers.get(9));
        someNeo4jCustomers.get(8).addFriend(someNeo4jCustomers.get(10));
        someNeo4jCustomers.get(9).addFriend(someNeo4jCustomers.get(11));
        someNeo4jCustomers.get(10).addFriend(someNeo4jCustomers.get(11));
        someNeo4jCustomers.get(11).addFriend(someNeo4jCustomers.get(12));

        return someNeo4jCustomers;
    }
}
