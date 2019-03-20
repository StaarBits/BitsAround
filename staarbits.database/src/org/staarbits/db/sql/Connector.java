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

import java.sql.Connection;
import java.util.Collection;

public interface Connector
{
  
  /**
   * Connects <code>{@link Connector this}</code> Connector at the SQL server indicated by the <code>{@link #where() Address}</code>.
   * <p>When a <code>Connection</code> is opened, the method will indicate all the events happening during the process in a Logger
   * named "<code>StaarLight</code>".
   * @return The <code>java.sql.Connection</code> which has been opened by <code>{@link Connector this}</code> Connector (which is
   *         the same returned by <code>{@link #getCurrentConnection() getCurrentConnection()}</code> after invoking this method,
   *         of course.)
   */
  Connection connect();
  
  /** Disconnects <code>{@link Connector this}</code> Connector from the SQL server it is currently connected to. */
  void disconnect();
  
  /**
   * Registers the given <code>{@link Repository repository}</code> in <code>{@link Connector this}</code> Connector (remembering
   * that this method does not initialize this same repository, the method which should start all the post-registered <code>{@link
   * Repository repositories}</code> is the <code>{@link #startRepositories()}</code> or the <code>{@link #connect()}</code> if
   * the <code>{@link #automaticLoading() automaticLoading()}</code>.)
   * @param repository The <code>Repository</code> which will be registered through this method.
   * @param <K> The key object-value used by this Repository.
   * @param <V> The value loaded by this Repository.
   * @param <I> A collection containing all the set of values which will be inserted into the SQL table.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link Connector this}</code> Connector has successfully registered
   *         the given <code>{@link Repository repository}</code>; or <code><strong><b>false</b></strong></code> if it has not.
   */
  <K, V extends java.io.Serializable, I extends Collection<?>> boolean registerRepository(Repository<K, V, I> repository);
  
  /**
   * Registers all the <code>{@link Repository repositories}</code> which are found at the given <code>packagePath</code>.
   * @param packagePath The package path where the Repositories are found in.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link Connector this}</code> Connector has successfully registered
   *         all the <code>{@link Repository Repositories}</code> found in <code>packagePath</code>; or <code><strong>false</strong></code>
   *         if something has gone wrong whilst the method was trying to register a specific Repository.
   */
  boolean registerRepositories(String packagePath);
  
  /**
   * Checks whether <code>{@link Connector this}</code> Connector automatically loads all the <code>{@link Repository repositories}</code>
   * registered in <code>{@link Connector this}</code> Connector when a connection is stabled between the <code>Connector</code> and
   * the SQL database.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link Connector this}</code> Connector automatically loads all the
   *         registered <code>{@link Repository Repositories}</code> when the <code>{@link #connect() connect()}</code> method is
   *         invoked; or <code><strong><b>false</b></strong></code> if the <code>{@link #startRepositories() startRepositories()}</code>
   *         method needs to be invoked.
   */
  boolean automaticLoading();
  
  /** Starts all the <code>{@link Repository repositories}</code> */
  void startRepositories();
  
  /**
   * Gets a <code>Collection</code> containing the <code>{@link Repository Repositories}</code> which work beneath <code>{@link
   * Connector this}</code> Connector's <code>{@link Connection connection}</code>.
   * @return A <code>java.util.Collection</code> containing the registered repositories.
   */
  Collection<? extends Repository<?, ?, ?>> getRepositories();
  
  /**
   * Gets the current <code>java.sql.Connection</code> which is stable for <code>{@link Connector this}</code> Connector
   * <p>
   * This method may always return <code><strong><b>null</b></strong></code>, if it happens, is basically because the
   * <code>{@link #connect() connect()}</code> method has not been invoked since <code>{@link Connector this}</code> Connector
   * stopped the last connection or since it was constructed. The method returns <code><strong><b>null</b></strong></code>
   * after the <code>{@link #disconnect() disconnect()}</code> method is invoked.
   * @return The current <code>{@link Connection connection}</code>; or <code><strong><b>null</b></strong></code> if this
   *         Connector is not currently opened.
   */
  Connection getCurrentConnection();
  
  /**
   * Checks whether <code>{@link Connector this}</code> Connector has initialized and opened a <code>java.uil.Connection</code>
   * which is opened so far (then, it must not have been closed or interrupted.)
   * @return <code><strong><b>true</b></strong></code> if <code>{@link Connector this}</code> Connector has an opened and stable
   *         <code>{@link java.sql.Connection connection}</code>; or <code><strong><b>false</b></strong></code> if it does not.
   */
  boolean hasConnection();
  
  /**
   * Gets the <code>User</code> instance that represents the real user registered in the SQL server. As one <code>Connection</code>
   * needs to be created here, the <code>{@link #connect() connect()}</code> method needs an user to connect as (remembering, this
   * same <code>User</code> needs to have been registered at the SQL server before.)
   * @return The <code>User</code> representation.
   */
  User getUser();
  
  /**
   * Gets the <code>Address</code> which indicates the <code>{@link Address#getHostname() hostname}</code>, <code>{@link Address#getPort() port}</code>,
   * what <code>{@link Address#getDriver() driver}</code> must be used and all the <code>{@link Address#getProperties() properties}</code>
   * this sort of <code>Connection</code> needs to have.
   * @return The <code>Address</code> to indicate the machine for connecting to.
   */
  Address where();
}
