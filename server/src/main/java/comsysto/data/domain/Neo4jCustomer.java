package comsysto.data.domain;

import comsysto.server.ICustomer;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** @author Daniel Bartl */
@NodeEntity
public class Neo4jCustomer implements ICustomer {

    static private Integer nextIdValue = 1;

    @GraphId
    private Long graphId;

    @Indexed
    private String name;

    @Indexed(indexName = "intId-index")
    private Integer intId;

    private String firstname;

    private String lastname;

    private Date signupDate;

    @Fetch
    @RelatedTo(type = "KNOWS", direction = Direction.BOTH)
    private Set<Neo4jCustomer> friends = new HashSet<Neo4jCustomer>();

    public Neo4jCustomer() {
        this.intId = nextIdValue++;
    }

    public Neo4jCustomer(String firstname, String lastname) {
        this();
        this.name = firstname+" "+lastname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.signupDate = new Date();
    }


    public Neo4jCustomer(String firstname, String lastname, Date signupDate) {
        this();
        this.name = firstname+" "+lastname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.signupDate = signupDate;
    }

    @Override
    public Integer getId() {
        return intId;
    }

    @Override
    public void setId(Integer id) {
       this.intId = id;
    }

    @Override
    public String getFirstName() {
        return firstname;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    @Override
    public String getLastName() {
        return lastname;
    }

    @Override
    public void setLastName(String lastName) {
         this.lastname = lastName;
    }

    @Override
    public Date getSignupDate() {
        return signupDate;
    }

    @Override
    public void setSignupDate(Date signupDate) {
         this.signupDate = signupDate;
    }

    public void addFriend(Neo4jCustomer newFriend) {
        friends.add(newFriend);
        if (!newFriend.areFriends(this)) newFriend.addFriend(this);
    }

    public boolean areFriends(Neo4jCustomer otherNeo4jCustomer) {
        return friends.contains(otherNeo4jCustomer);
    }

    public int amountFriends() {
        return friends.size();
    }

    @Override
    public String toString() {
        return "Neo4jCustomer{" +
                "graphId=" + graphId +
                ", intId=" + intId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", signupDate='" + signupDate + '\'' +
                ", #friends=" + friends.size() +
                '}';
    }
}
