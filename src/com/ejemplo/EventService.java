package com.ejemplo;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.ejemplo.entities.Event;
import com.ejemplo.entities.User;

@Stateless
@LocalBean
public class EventService extends AbstractService<Event> {
  public EventService() {
    super( Event.class );
  }

  public List<Event> activeEvents( User user ) {
    final TypedQuery<Event> query = entityManager.createQuery(
        "select e from Event e, e.admins a where e.closingDate < current_timestamp and ( e.creator.id = :id or a.id = :id )",
        Event.class );
    query.setParameter( "id", user.getId() );

    return query.getResultList();
  }

  public void guardarEvento( Event evento, User creador ) {
    evento.setCreator( creador );
    evento.setStartingDate( new Date() );

    this.persist( evento );
  }

}
