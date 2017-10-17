package com.ejemplo;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.ejemplo.entities.Event;
import com.ejemplo.entities.User;

@SessionScoped
@ManagedBean( name = "helloWorldBean" )
public class HelloWorldBean implements Serializable {
  private static final long serialVersionUID = 1490630053711309405L;

  @Inject
  private EventService eventService;

  private List<Event> data;
  private Event temporal;
  private User loggedUser;

  @PostConstruct
  private void init() {
    loggedUser = new User();
    loggedUser.setId( 1L );
  }

  public void guardarEvento() {
    eventService.guardarEvento( temporal, loggedUser );

    data = eventService.activeEvents( loggedUser );

    temporal.setName( "" );
  }

  public List<Event> getData() {
    return data;
  }

  public void setData( List<Event> data ) {
    this.data = data;
  }

  public EventService getEventService() {
    return eventService;
  }

  public void setEventService( EventService eventService ) {
    this.eventService = eventService;
  }

  public Event getTemporal() {
    return temporal;
  }

  public void setTemporal( Event temporal ) {
    this.temporal = temporal;
  }

}
