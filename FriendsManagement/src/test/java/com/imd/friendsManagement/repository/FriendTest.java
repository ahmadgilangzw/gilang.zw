package com.imd.friendsManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.imd.friendsManagement.entity.Friend;
import com.imd.friendsManagement.repository.FriendRepository;

/**
 * 
 * @author Gilang ZW
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
public class FriendTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
	@Autowired
    private FriendRepository friendRepository;
    
    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(new Friend("andy@example.com","john@example.com"));
        this.entityManager.flush();
        List<Friend> listFriendOne = this.friendRepository.findByEmailPeople("andy@example.com");
        Friend friend = this.friendRepository.findByEmailPeopleAndEmailFriend("andy@example.com","john@example.com");
        
        assertThat(1).isEqualTo(listFriendOne.size());
        assertThat(friend).isNotNull();
        
    }
}
