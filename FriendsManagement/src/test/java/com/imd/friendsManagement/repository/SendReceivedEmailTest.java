package com.imd.friendsManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.imd.friendsManagement.entity.SendReceivedEmail;
import com.imd.friendsManagement.repository.SendReceivedEmailRepository;

/**
 * 
 * @author Gilang ZW
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
public class SendReceivedEmailTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
	@Autowired
    private SendReceivedEmailRepository sendReceivedEmailRepository;
    
    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(new SendReceivedEmail("andy@example.com"));
        this.entityManager.flush();
        List<SendReceivedEmail> listSendReceivedEmail = this.sendReceivedEmailRepository.findAll();
        
        assertThat(1).isEqualTo(listSendReceivedEmail.size());
        
    }
}
