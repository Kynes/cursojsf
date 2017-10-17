package com.ejemplo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Event that can have votes.
 */
@Entity
public class Vote extends GenericEntity implements Serializable {
  private static final long serialVersionUID = 5365810865843883483L;

  @NotNull
  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, optional = false )
  private User voter;

  @NotNull
  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, optional = false )
  private Voting voting;

  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST } )
  private User proxy;

  @NotNull
  @Column( updatable = false )
  @Temporal( TemporalType.TIMESTAMP )
  private Date votingDate;

  @NotNull
  @ManyToOne( fetch = FetchType.EAGER, optional = false )
  private VotingOption option;

  @PrePersist
  private void updateCreationDate() {
    if( votingDate == null ) {
      votingDate = new Date();
    }
  }

  public Vote() {
  }

  public User getVoter() {
    return voter;
  }

  public void setVoter( User voter ) {
    this.voter = voter;
  }

  public Voting getVoting() {
    return voting;
  }

  public void setVoting( Voting voting ) {
    this.voting = voting;
  }

  public User getProxy() {
    return proxy;
  }

  public void setProxy( User proxy ) {
    this.proxy = proxy;
  }

  public Date getVotingDate() {
    return votingDate;
  }

  public void setVotingDate( Date votingDate ) {
    this.votingDate = votingDate;
  }

  public VotingOption getOption() {
    return option;
  }

  public void setOption( VotingOption option ) {
    this.option = option;
  }
}
