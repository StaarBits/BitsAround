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
import org.staarbits.db.sql.Address;

/* private-package */ final class AddressImpl implements Address
{
  
  /** The hostname */
  private String hostname;
  
  /** The port */
  private int port;
  
  /** The driver path */
  private String driver;
  
  /** The database name */
  private String schema;
  
  /** THe <code>Properties</code> container */
  private Properties properties;
  
  /** Constructs a new <code>AddressImpl</code>*/
  /* private-package */ AddressImpl(String hostname, int port, String driver, String schema, Properties properties)
  {
    this.hostname = hostname;
    this.port = port;
    this.driver = driver;
    this.schema = schema;
    this.properties = properties;
  }
  
  /** {@inheritDoc} */
  @Override
  public String getHostname()
  {
    return hostname;
  }
  
  /** {@inheritDoc} */
  @Override
  public int getPort()
  {
    return port;
  }
  
  /** {@inheritDoc} */
  @Override
  public String getSchema()
  {
    return schema;
  }
  
  /** {@inheritDoc} */
  @Override
  public String getDriver()
  {
    return driver;
  }
  
  /** {@inheritDoc} */
  @Override
  public Properties getProperties()
  {
    return properties;
  }
}
