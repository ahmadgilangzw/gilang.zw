package com.imd.friendsManagement.service;

import java.util.List;

/**
 * 
 * @author Gilang ZW
 *
 * @param <V>
 */
public interface CrudService<V> {
    
    V save(V entity);
    
    V getById(Long id);
    
    List<V> getAll();

    void delete(Long id);
}