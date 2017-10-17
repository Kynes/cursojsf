package com.ejemplo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( indexes = { @Index( name = "IDX_FINISH_DATE", columnList = "finishDate" ) } )
public class VoteDelegation extends GenericEntity implements Serializable {
  private static final long serialVersionUID = 6471352680868969197L;

  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, optional = false )
  private Event event;

  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, optional = false )
  private User voter;

  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, optional = false )
  private User proxy;

  // TODO: Añadir @PastOrPresent al pasar a Java EE 8
  @Column
  @Temporal( TemporalType.TIMESTAMP )
  private Date finishDate;

  @Column
  @Temporal( TemporalType.TIMESTAMP )
  private Date startingDate;

  @PrePersist
  private void updateStartingDate() {
    if( startingDate == null ) {
      startingDate = new Date();
    }
  }

  public VoteDelegation(Event event, User voter, User proxy) {
    this.event = event;
    this.voter = voter;
    this.proxy = proxy;
  }

  public VoteDelegation() {
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent( Event event ) {
    this.event = event;
  }

  public User getVoter() {
    return voter;
  }

  public void setVoter( User voter ) {
    this.voter = voter;
  }

  public User getProxy() {
    return proxy;
  }

  public void setProxy( User proxy ) {
    this.proxy = proxy;
  }

  public Date getFinishDate() {
    return finishDate;
  }

  public void setFinishDate( Date finishDate ) {
    this.finishDate = finishDate;
  }

  public Date getStartingDate() {
    return startingDate;
  }

  public void setStartingDate( Date startingDate ) {
    this.startingDate = startingDate;
  }
}
