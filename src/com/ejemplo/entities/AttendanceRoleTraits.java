package com.ejemplo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AttendanceRoleTraits extends GenericEntity implements Serializable {
  private static final long serialVersionUID = -4012151959964083675L;

  public AttendanceRoleTraits() {
  }

  public AttendanceRoleTraits(Event event, AttendanceRole attendanceRole) {
    this.event = event;
    this.role = attendanceRole;
  }

  @ManyToOne( optional = false )
  private Event event;

  @ManyToOne( optional = false )
  private AttendanceRole role;

  private boolean canVote;
  private boolean canSpeak;

  public Event getEvent() {
    return event;
  }

  public void setEvent( Event event ) {
    this.event = event;
  }

  public AttendanceRole getRole() {
    return role;
  }

  public void setRole( AttendanceRole role ) {
    this.role = role;
  }

  public boolean isCanVote() {
    return canVote;
  }

  public void setCanVote( boolean canVote ) {
    this.canVote = canVote;
  }

  public boolean isCanSpeak() {
    return canSpeak;
  }

  public void setCanSpeak( boolean canSpeak ) {
    this.canSpeak = canSpeak;
  }
}
