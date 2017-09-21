package com.imd.friendsManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.imd.friendsManagement.entity.Subscribe;
import com.imd.friendsManagement.repository.SubscribeRepository;

/**
 * 
 * @author Gilang ZW
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
public class SubscribeTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
	@Autowired
    private SubscribeRepository subscribeRepository;
    
    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(new Subscribe("andy@example.com","john@example.com"));
        this.entityManager.flush();
        Subscribe subscribe = this.subscribeRepository.findByRequestorAndTarget("andy@example.com","john@example.com");
        List<Subscribe> listSubscribe = this.subscribeRepository.findByTarget("john@example.com");
        
        assertThat(1).isEqualTo(listSubscribe.size());
        assertThat(subscribe).isNotNull();
        
    }
}
