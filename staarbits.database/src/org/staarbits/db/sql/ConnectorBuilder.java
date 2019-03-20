package org.staarbits.db.sql;

/*
 * Copyright (c) 2019. StaarBits Network & Development says that this file is under the StaarBits Global Copyright (SGC).
 * Every file which contains this annotation as one of the first things written is under the SGC protocol.
 * The SGC (StaarBits Global Copyright) demonstrates that the file which has it cannot be copied and pasted as
 * an annotation file by anyone else who has not gotten the Owner rank at StaarBits. So... The most powerful rank
 * at the executive can spread this file. If someone uses this file without the permission given by the executive
 * administration, this same person will be able to be sued by the SEA (StaarBits Executive Administration); if
 * someone who works at StaarBits spreads this file, this person will as sooner as possible be removed from our
 * team and (s)he will also be able to response a lawsuit as well.
 */

public final class ConnectorBuilder
{
  
  /** Who */
  private User user;
  
  /** The address */
  private Address address;
  
  /** A boolean to determine the auto loading */
  private boolean autoLoading;
  
  /** Constructs a new <code>ConnectorBuilder</code> */
  public ConnectorBuilder(User user, Address address, boolean autoLoading)
  {
    this.user = user;
    this.address = address;
    this.autoLoading = autoLoading;
  }
  
  /** Builds a new <code>Connector</code> instance */
  public final Connector build()
  {
    return new ConnectorImpl(this.user, this.address, this.autoLoading);
  }
}
