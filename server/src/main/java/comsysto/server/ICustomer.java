package comsysto.server;

import java.io.Serializable;
import java.util.Date;

public interface ICustomer extends Serializable {
    Integer getId();

    void setId(Integer id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    Date getSignupDate();

    void setSignupDate(Date signupDate);
}
