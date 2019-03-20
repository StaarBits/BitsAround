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

public interface Address
{
  
  /**
   * Gets the hostname of <code>{@link Address this}</code> Address.
   * @return The hostname.
   */
  String getHostname();
  
  /**
   * Gets the port which is opened for <code>{@link Address this}</code> Address.
   * @return The address port.
   */
  int getPort();
  
  /** Gets the database name. */
  String getSchema();
  
  /**
   * Gets the <code>Properties</code> which contain all the properties of <code>{@link Address this}</code> Address.
   * @return The <code>Properties</code> instance.
   */
  Properties getProperties();
  
  /**
   * Gets the <code>Driver</code> used for creating connections between the J- connector and the SQL server & database.
   * @return The driver name.
   */
  String getDriver();
}
