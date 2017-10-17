package com.ejemplo.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Event that can have votes.
 */
@Entity
@Table( indexes = { @Index( name = "IDX_VOTING_NAME", columnList = "name" ) } )
public class VotingOption extends GenericEntity implements Serializable, Comparable<VotingOption> {
  private static final long serialVersionUID = 4885309091980210702L;

  @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, optional = false )
  private Voting voting;

  private Long optionOrder;

  public VotingOption(Long order, String name, String description, Voting voting) {
    this.setDescription( description );
    this.setVoting( voting );
    this.setOptionOrder( order );
    this.setName( name );
  }

  @Override
  public int compareTo( VotingOption otherVoting ) {
    return getOptionOrder() == null || otherVoting.getOptionOrder() == null ? 0
        : getOptionOrder().compareTo( otherVoting.getOptionOrder() );
  }

  public VotingOption() {
  }

  public Voting getVoting() {
    return voting;
  }

  public void setVoting( Voting voting ) {
    this.voting = voting;
  }

  public Long getOptionOrder() {
    return optionOrder;
  }

  public void setOptionOrder( Long optionOrder ) {
    this.optionOrder = optionOrder;
  }

}
