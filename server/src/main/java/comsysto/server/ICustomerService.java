package comsysto.server;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface ICustomerService {

    static final String CUSTOMERS_REGION = "customers";

    ICustomer createCustomer(String firstName, String lastName, Date signupDate);

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    List<ICustomer> getAllCustomers();

    @Cacheable(ICustomerService.CUSTOMERS_REGION)
    @Transactional(readOnly = true)
    ICustomer getCustomerById(Integer id);

    @CacheEvict(ICustomerService.CUSTOMERS_REGION)
    void deleteCustomer(Integer id);

    @CacheEvict(value = ICustomerService.CUSTOMERS_REGION, key = "#id")
    void updateCustomer(Integer id, String fn, String ln, Date birthday);
}
