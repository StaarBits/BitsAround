package org.staarbits.db.file.yaml;

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

import com.staarbits.core.Nullable;

public interface Protocol<V extends YamlValue>
{
  
  /**
   * Gets the number of items (YAML file configurations) into the database path identified by <code>{@link Protocol this}</code>
   * Protocol.
   * @return The <code>Protocol</code> size.
   */
  int size();
  
  /**
   * Loads the YAML file configuration which is identified by the given <code>key</code>.
   * @param key The key that loads the <code>YamlValue</code> representation.
   * @param <K> The K type for the key.
   * @return The <code>YamlValue</code> which has successfully been loaded by the given <code>key</code>; or <code><strong>null</strong></code>
   *         if there is no YAML file simply identified by the given <code>key</code> in this database path.
   */
  @Nullable
  <K> YamlValue loadYaml(K key);
  
  /**
   * Inserts an array of <code>{@link Object Objects}</code> into the database path identified by <code>{@link Protocol this}</code>
   * Protocol (In this case, the first value, <code>objects[0]</code>, will always represent the key, identified on <code>{@link
   * #loadYaml loadYaml}</code> and <code>{@link #checksYaml checksYaml}</code> methods as <code>K</code> type.)
   * @param objects An array of Objects that are inserted into the database path indicated through this Protocol.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link Protocol this}</code> Protocol has successfully inserted
   *         all those <code>objects</code> in the correct database path; or <code><strong><b>false</b></strong></code> if it
   *         has not.
   */
  boolean insertYaml(Object[] objects);
  
  /**
   * Checks whether there is a <code>{@link YamlValue Yaml FileConfiguration}</code> which is loaded based on the <code>key</code>
   * that is given.
   * @param key The key to be checked.
   * @param <K> The K type for identifying the key.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link Protocol this}</code> Protocol really indicates the
   *         path where there is a <code>key</code> associated to a YAML file; or <code><strong><b>false</b></strong></code>
   *         if there is no YAML file in the path indicated by this Protocol associated to the given <code>key</code>.
   */
  <K> boolean checksYaml(K key);
}
