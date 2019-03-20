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

import java.util.Collection;

public interface Repository<K, V extends java.io.Serializable, I extends Collection<?>>
{
  
  /**
   * Loads the <code>{@link V instance}</code> which represents the beans of the sort of values contained by the SQL table
   * indicated by <code>{@link Repository this}</code> Repository (<code><strong><b>WATCH OUT</b></strong></code>: This
   * method may also return <code><strong><b>null</b></strong></code> if there is no values associated to the given
   * <code>key</code> into the indicated SQL table.)
   * @param key The key whose the values are loaded.
   * @return The <code>{@link V value}</code> built by all the items associated to the <code>key</code>; or simply
   *         <code><strong><b>null</b></strong></code> if there is no associated value.
   */
  V load(K key);
  
  /**
   * Checks whether <code>{@link Repository this}</code> Repository has the possibility to find and compare values which
   * are associated to the given <code>key</code> or whether there is no value associated to the <code>key</code> (or --
   * in some cases -- it does not even exist.)
   * @param key The key to check whether there is associated values or not.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link Repository this}</code> Repository may find the
   *         values associated to the <code>key</code> in the SQL database; or <code><strong><b>false</b></strong></code>
   *         if it may not.
   */
  boolean exists(K key);
  
  /**
   * Inserts all the data which are found contained by the <code>insertCollection</code>.
   * @param insertCollection A collection containing all the items to be inserted into the SQL database.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link Repository this}</code> Repository has successfully
   *         inserted all the items in the <code>insertCollection</code>; or <code><strong><b>false</b></strong></code> if
   *         it has not.
   */
  boolean insertInto(I insertCollection);
}
