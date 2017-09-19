package com.imd.friendsManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.imd.friendsManagement.entity.People;

/**
 * 
 * @author Gilang ZW
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
public class PeopleTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
	@Autowired
    private PeopleRepository peopleRepository;
    
    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(new People("andy@example.com"));
        this.entityManager.flush();
        People people = this.peopleRepository.findByEmailAddress("andy@example.com");
        
        assertThat(people.getEmailAddress()).isEqualTo("andy@example.com");
    }
}
