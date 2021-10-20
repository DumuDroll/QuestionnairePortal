package com.dddd.questionnaireportal.database.dao;

public interface DAO<T> {
    void save(T entity);
    void update(T entity);
    void delete(int id);

}
