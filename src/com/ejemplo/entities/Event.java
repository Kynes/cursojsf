package com.ejemplo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Event that can have votes.
 */
@Entity
@Table( name = "evento",
    indexes = { @Index( name = "IDX_CLOSING_DATE", columnList = "closingDate" ),
        @Index( name = "IDX_DATES", columnList = "creationDate,closingDate" ) } )
public class Event extends GenericEntity implements Serializable {
  private static final long serialVersionUID = -6385495086319807737L;

  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, optional = false )
  private User creator;

  // TODO: Añadir @PastOrPresent al pasar a Java EE 8
  @Column( updatable = false )
  @Temporal( TemporalType.TIMESTAMP )
  private Date creationDate;

  @Column
  @Temporal( TemporalType.TIMESTAMP )
  private Date startingDate;

  @Column
  @Temporal( TemporalType.TIMESTAMP )
  private Date closingDate;

  @OneToMany( fetch = FetchType.LAZY, mappedBy = "event" )
  private List<Attendance> attendees;

  @OneToMany( fetch = FetchType.EAGER, mappedBy = "event", cascade = { CascadeType.ALL } )
  private List<AttendanceRoleTraits> invitedRoles;

  @ManyToMany( fetch = FetchType.LAZY, cascade = { CascadeType.ALL } )
  @JoinTable( name = "event_admins" )
  private List<User> admins;

  @OneToMany( fetch = FetchType.LAZY, mappedBy = "event", cascade = { CascadeType.ALL } )
  private List<Voting> votings;

  @OneToMany( fetch = FetchType.LAZY, mappedBy = "event", cascade = { CascadeType.ALL } )
  private List<VoteDelegation> delegations;

  @PrePersist
  private void updateCreationDate() {
    if( creationDate == null ) {
      creationDate = new Date();
    }
  }

  public Event() {
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator( User creator ) {
    this.creator = creator;
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

  public List<Attendance> getAttendees() {
    return attendees;
  }

  public void setAttendees( List<Attendance> attendees ) {
    this.attendees = attendees;
  }

  public List<AttendanceRoleTraits> getInvitedRoles() {
    return invitedRoles;
  }

  public void setInvitedRoles( List<AttendanceRoleTraits> invitedRoles ) {
    this.invitedRoles = invitedRoles;
  }

  public List<User> getAdmins() {
    return admins;
  }

  public void setAdmins( List<User> admins ) {
    this.admins = admins;
  }

  public List<Voting> getVotings() {
    return votings;
  }

  public void setVotings( List<Voting> votings ) {
    this.votings = votings;
  }

  public List<VoteDelegation> getDelegations() {
    return delegations;
  }

  public void setDelegations( List<VoteDelegation> delegations ) {
    this.delegations = delegations;
  }
}
