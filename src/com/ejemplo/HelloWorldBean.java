package com.ejemplo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean( name = "helloWorldBean" )
public class HelloWorldBean implements Serializable {
  private static final long serialVersionUID = 1490630053711309405L;

  private String name;

  public String hello() {
    System.out.println( "Calling hellworld.xhtml" );
    return "success";
  }

  public String getCurrentTime() {
    return LocalDateTime.now().format( DateTimeFormatter.ofPattern( "dd MMM-yyyy HH:mm:ss" ) );
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

}
