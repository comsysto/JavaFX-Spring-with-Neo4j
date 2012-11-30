/*
 * Copyright (c) 2012, Stephen Chin
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL STEPHEN CHIN OR ORACLE CORPORATION BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package comsysto.server;

import comsysto.data.repos.Neo4jCustomerRepository;
import comsysto.data.domain.Neo4jCustomer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Neo4jCustomerService implements ICustomerService {

    @Autowired
    Neo4jCustomerRepository neo4jCustomerRepository;

    @Override
    public ICustomer createCustomer(String firstName, String lastName, Date signupDate) {

        Neo4jCustomer newCustomer = new Neo4jCustomer(firstName, lastName, signupDate);

        neo4jCustomerRepository.save(newCustomer);

        return newCustomer;
    }

    @Override
    public List<ICustomer> getAllCustomers() {

        List<ICustomer> list = new ArrayList<ICustomer>();

        for (Neo4jCustomer customer : neo4jCustomerRepository.findAll()) {
            list.add(customer);
        }

        return list;
    }

    @Override
    public ICustomer getCustomerById(Integer id) {
        return neo4jCustomerRepository.findOne((long)id);
    }

    @Override
    public void deleteCustomer(Integer id) {
        Neo4jCustomer neo4jCustomer = neo4jCustomerRepository.findByPropertyValue("intId", id);
        neo4jCustomerRepository.delete(neo4jCustomer);
    }

    @Override
    public void updateCustomer(Integer id, String fn, String ln, Date birthday) {
        Neo4jCustomer customer = neo4jCustomerRepository.findOne((long)id);
        customer.setFirstName(fn);
        customer.setLastName(ln);
        customer.setSignupDate(birthday);
    }
}
