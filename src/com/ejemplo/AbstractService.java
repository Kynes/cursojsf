package com.ejemplo;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import com.ejemplo.entities.GenericEntity;

public abstract class AbstractService<T extends GenericEntity> {
  @Inject
  protected EntityManager entityManager;

  private Class<T> entityClass;

  public AbstractService(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public T persist( final @NotNull T entity ) {
    entityManager.persist( entity );
    return entity;
  }

  public T findById( final @NotNull Long id ) {
    return entityManager.find( entityClass, id );
  }

  public void remove( final @NotNull T entity ) {
    entityManager.remove( entityManager.merge( entity ) );
    entityManager.flush();
  }

  public T merge( final @NotNull T entity ) {
    return entityManager.merge( entity );
  }

  public void refresh( final @NotNull T entity ) {
    entityManager.refresh( entity );
  }

  public boolean delete( final List<Long> ids ) {
    final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    final CriteriaDelete<T> q = cb.createCriteriaDelete( entityClass );
    final Root<T> root = q.from( entityClass );
    q.where( root.get( "id" ).in( ids ) );

    final int deletedEntities = entityManager.createQuery( q ).executeUpdate();

    return deletedEntities == ids.size();
  }

  public List<T> listAll() {
    return getListAllQuery().getResultList();
  }

  public TypedQuery<T> getListAllQuery() {
    final CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery( entityClass );
    final CriteriaQuery<T> select = criteria.select( criteria.from( entityClass ) );

    return entityManager.createQuery( select );
  }

}
