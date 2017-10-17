package com.ejemplo.entities;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author Eduardo Ramirez
 */
@Entity
@Table( indexes = { @Index( name = "IDX_LOGIN", columnList = "login" ),
    @Index( name = "IDX_LOGIN_PASS", columnList = "login,password" ) } )
public class User extends GenericEntity implements Serializable {
  private static final long serialVersionUID = 5725330676774990891L;

  @NotNull
  @Size( min = 2, max = 50 )
  @Column( length = 50, nullable = false )
  private String name;

  @NotNull
  @Size( min = 2, max = 50 )
  @Column( length = 50, nullable = false )
  private String surnames;

  @Column
  private String telephone;

  @Column
  private String email;

  @NotNull
  @Column( length = 10, nullable = false )
  private String login;

  @NotNull
  @Size( min = 1, max = 256 )
  @Column( length = 256, nullable = false )
  private String password;

  @Column
  @Temporal( TemporalType.DATE )
  // TODO: Añadir @PastOrPresent al pasar a Java EE 8
  private Date dateOfBirth;

  @OneToMany( fetch = FetchType.LAZY, mappedBy = "creator" )
  private List<Event> createdEvents;

  @OneToMany( fetch = FetchType.LAZY, mappedBy = "attendee" )
  private List<Attendance> attendances;

  @ManyToMany( mappedBy = "admins", fetch = FetchType.LAZY )
  private List<Event> eventsAdmin;

  public User() {
  }

  public User(String name, String surnames, String login, String plainTextPassword, String email) {
    this.name = name;
    this.surnames = surnames;
    this.login = login;
    this.password = digestPassword( plainTextPassword );
    this.email = email;
    this.dateOfBirth = new Date();
  }


  @PrePersist
  private void digestPassword() {
    password = digestPassword( password );
  }

  /**
   * Digest password with <code>SHA-256</code> then encode it with Base64.
   *
   * @param plainTextPassword the password to digest and encode
   * @return digested password
   */
  public String digestPassword( String plainTextPassword ) {
    try {
      MessageDigest md = MessageDigest.getInstance( "SHA-256" );
      md.update( plainTextPassword.getBytes( "UTF-8" ) );
      byte[] passwordDigest = md.digest();
      return new String( Base64.getMimeEncoder().encode( passwordDigest ), StandardCharsets.UTF_8 );
    } catch( Exception e ) {
      throw new RuntimeException( "Exception encoding password", e );
    }
  }

  @Override
  public String toString() {
    return getFullName() + " (" + login + ")";
  }

  public String getFullName() {
    return name + ' ' + surnames;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName( String name ) {
    this.name = name;
  }

  public String getSurnames() {
    return surnames;
  }

  public void setSurnames( String surnames ) {
    this.surnames = surnames;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone( String telephone ) {
    this.telephone = telephone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail( String email ) {
    this.email = email;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin( String login ) {
    this.login = login;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth( Date dateOfBirth ) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<Event> getCreatedEvents() {
    return createdEvents;
  }

  public void setCreatedEvents( List<Event> createdEvents ) {
    this.createdEvents = createdEvents;
  }

  public List<Attendance> getAttendances() {
    return attendances;
  }

  public void setAttendances( List<Attendance> attendances ) {
    this.attendances = attendances;
  }

  public List<Event> getEventsAdmin() {
    return eventsAdmin;
  }

  public void setEventsAdmin( List<Event> eventsAdmin ) {
    this.eventsAdmin = eventsAdmin;
  }
}
