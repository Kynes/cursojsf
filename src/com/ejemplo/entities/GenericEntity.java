package com.ejemplo.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: GenericEntity
 *
 */
@MappedSuperclass
public abstract class GenericEntity {
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  @Column( updatable = false, nullable = false )
  private Long id;

  @Size( min = 2, max = 50 )
  @Column( length = 50 )
  private String name;

  @Column
  private String description;

  @Column
  @Version
  private int version;

  public Long getId() {
    return id;
  }

  public void setId( Long id ) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription( String description ) {
    this.description = description;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion( int version ) {
    this.version = version;
  }
}
