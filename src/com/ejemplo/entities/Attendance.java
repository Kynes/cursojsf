package com.ejemplo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Attendance
 *
 */
@Entity
public class Attendance extends GenericEntity implements Serializable {
  private static final long serialVersionUID = -8129450769901665443L;

  @NotNull
  @ManyToOne( optional = false )
  private User attendee;

  @NotNull
  @ManyToOne( optional = false )
  private Event event;

  @NotNull
  @OneToOne
  private AttendanceRole roleOfAttendance;

  private Boolean accepted;

  @Column
  @Temporal( TemporalType.TIMESTAMP )
  private Date acceptedDate;

  private Boolean present;

  @Column
  @Temporal( TemporalType.TIMESTAMP )
  private Date changeEntranceDate;

  public Boolean getSorter() {
    return acceptedDate == null ? accepted : present;
  }

  public Attendance(Event event, User attendee, AttendanceRole role) {
    this.event = event;
    this.attendee = attendee;
    this.roleOfAttendance = role;
  }

  public Attendance() {
    super();
  }

  public User getAttendee() {
    return attendee;
  }

  public void setAttendee( User attendee ) {
    this.attendee = attendee;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent( Event event ) {
    this.event = event;
  }

  public AttendanceRole getRoleOfAttendance() {
    return roleOfAttendance;
  }

  public void setRoleOfAttendance( AttendanceRole roleOfAttendance ) {
    this.roleOfAttendance = roleOfAttendance;
  }

  public Boolean getAccepted() {
    return accepted;
  }

  public void setAccepted( Boolean accepted ) {
    this.accepted = accepted;
  }

  public Date getAcceptedDate() {
    return acceptedDate;
  }

  public void setAcceptedDate( Date acceptedDate ) {
    this.acceptedDate = acceptedDate;
  }

  public Boolean getPresent() {
    return present;
  }

  public void setPresent( Boolean present ) {
    this.present = present;
  }

  public Date getChangeEntranceDate() {
    return changeEntranceDate;
  }

  public void setChangeEntranceDate( Date changeEntranceDate ) {
    this.changeEntranceDate = changeEntranceDate;
  }
}
