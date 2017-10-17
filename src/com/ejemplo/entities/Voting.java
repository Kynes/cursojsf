package com.ejemplo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Event that can have votes.
 */
@Entity
public class Voting extends GenericEntity implements Serializable {
  private static final long serialVersionUID = 5365810865843883483L;

  @NotNull
  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, optional = false )
  private User creator;

  @NotNull
  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, optional = false )
  private Event event;

  @NotNull
  @Column( updatable = false )
  @Temporal( TemporalType.TIMESTAMP )
  private Date creationDate;

  @Column
  @Temporal( TemporalType.TIMESTAMP )
  private Date startingDate;

  @Column
  @Temporal( TemporalType.TIMESTAMP )
  private Date closingDate;

  @OrderBy( "optionOrder" )
  @OneToMany( fetch = FetchType.LAZY, mappedBy = "voting", cascade = { CascadeType.ALL } )
  private SortedSet<VotingOption> options;

  @OneToMany( fetch = FetchType.LAZY, mappedBy = "voting", cascade = { CascadeType.ALL } )
  private List<Vote> votes;

  private boolean simple;

  private boolean multiple;

  @Min( 1 )
  private Integer numberOptions;

  @PrePersist
  private void updateCreationDate() {
    if( creationDate == null ) {
      creationDate = new Date();
    }
  }

  // Conditional State Validation

  @AssertTrue( message = "{es.sda.voting.agora.constraints.Voting.simpleMultipleIncompatible}" )
  public boolean isSimpleMultipleIncompatible() {
    return simple != multiple;
  }

  @AssertTrue( message = "{es.sda.voting.agora.constraints.Voting.simpleOptionsCorrect}" )
  public boolean isSimpleOptionsCorrect() {
    return simple ? options != null && options.size() == 3 : true;
  }

  @AssertTrue( message = "{es.sda.voting.agora.constraints.Voting.numberOptionsPresent}" )
  public boolean isNumberOptionsPresent() {
    return multiple ? numberOptions != null : true;
  }

  @AssertTrue( message = "{es.sda.voting.agora.constraints.Voting.multipleOptionsPresent}" )
  public boolean isMultipleOptionsPresent() {
    return !simple ? options != null && options.size() > 1 : true;
  }

  @AssertTrue( message = "{es.sda.voting.agora.constraints.Voting.moreChoicesThanOptions}" )
  public boolean isMoreChoicesThanOptions() {
    return multiple ? numberOptions != null && options != null && options.size() > numberOptions
        : true;
  }

  public boolean isRunning() {
    return startingDate != null && closingDate == null;
  }

  public Voting() {
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator( User creator ) {
    this.creator = creator;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent( Event event ) {
    this.event = event;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate( Date creationDate ) {
    this.creationDate = creationDate;
  }

  public Date getStartingDate() {
    return startingDate;
  }

  public void setStartingDate( Date startingDate ) {
    this.startingDate = startingDate;
  }

  public Date getClosingDate() {
    return closingDate;
  }

  public void setClosingDate( Date closingDate ) {
    this.closingDate = closingDate;
  }

  public SortedSet<VotingOption> getOptions() {
    return options;
  }

  public void setOptions( SortedSet<VotingOption> options ) {
    this.options = options;
  }

  public List<Vote> getVotes() {
    return votes;
  }

  public void setVotes( List<Vote> votes ) {
    this.votes = votes;
  }

  public boolean isSimple() {
    return simple;
  }

  public void setSimple( boolean simple ) {
    this.simple = simple;
  }

  public boolean isMultiple() {
    return multiple;
  }

  public void setMultiple( boolean multiple ) {
    this.multiple = multiple;
  }

  public Integer getNumberOptions() {
    return numberOptions;
  }

  public void setNumberOptions( Integer numberOptions ) {
    this.numberOptions = numberOptions;
  }
}
