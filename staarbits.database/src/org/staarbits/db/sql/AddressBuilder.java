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

import java.util.Properties;

public final class AddressBuilder
{
  
  private String hostname;
  
  private String driver;
  
  private String schema;
  
  private int port;
  
  private Properties properties;
  
  /** Constructs a new <code>AddressBuilder</code> */
  public AddressBuilder(String hostname, String driver)
  {
    this.hostname = hostname;
    this.driver = driver;
  }
  
  public Address build()
  {
    return new AddressImpl(this.hostname, this.port, this.driver, this.schema, this.properties);
  }
  
  /** Gives the builder a <code>schema</code> */
  public AddressBuilder schema(String schema)
  {
    this.schema = schema;
    return this;
  }
  
  /** Gives the builder a <code>port</code> */
  public AddressBuilder port(final int port)
  {
    this.port = port;
    return this;
  }
  
  /** Gives the builder a <code>properties</code> instance */
  public AddressBuilder properties(Properties properties)
  {
    this.properties = properties;
    return this;
  }
}
