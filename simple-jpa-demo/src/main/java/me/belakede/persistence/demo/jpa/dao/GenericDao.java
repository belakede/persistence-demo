package me.belakede.persistence.demo.jpa.dao;

import me.belakede.persistence.demo.jpa.domain.PersistentEntity;

import java.util.List;

public interface GenericDao<T extends PersistentEntity> {

    Long create(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

    T findById(Integer id);

    void clearCache();

}
