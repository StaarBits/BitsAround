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

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URL;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/* private-package */ final class ConnectorImpl implements Connector
{
  
  /** The <code>User</code> instance */
  private User who;
  
  /** The <code>Address</code> instance */
  private Address where;
  
  /** Auto-loading determination */
  private boolean automaticLoading;
  
  /** Constructs a new <code>ConnectorImpl</code>*/
  public ConnectorImpl(User who, Address where, boolean automaticLoading)
  {
    this.who = who;
    this.where = where;
    this.automaticLoading = automaticLoading;
  }
  
  /** A map containing all the repositories */
  private Map<String, Repository<?, ?, ?>> repositoryMap = null;
  
  /** {@inheritDoc} */
  @Override
  public Collection<? extends Repository<?, ?, ?>> getRepositories()
  {
    return (this.repositoryMap != null && this.repositoryMap.size() > 0) ?
            (new LinkedList<Repository<?, ?, ?>>(this.repositoryMap.values())) :
            (new LinkedList<Repository<?, ?, ?>>(Collections.emptyList()));
  }
  
  /** {@inheritDoc} */
  @Override
  public boolean registerRepositories(String packagePath)
  {
    if (this.repositoryMap == null)
    {
      this.repositoryMap = new HashMap<String, Repository<?, ?, ?>>();
      DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") Creating the mapping base instance...");
    }
    
    if (packagePath.length() > 0)
    {
      try
      {
        for (Class<?> eachFoundClass : ConnectorImpl.forPackage(ConnectorImpl.class, packagePath, true, DriverHandler.LOGGER))
        {
          if (eachFoundClass != null && Repository.class.isAssignableFrom(eachFoundClass))
          {
            Repository<?, ?, ?> repository = (Repository<?, ?, ?>) eachFoundClass.newInstance();
            this.registerRepository(repository);
          }
        }
      } catch (IOException | IllegalAccessException | InstantiationException error)
      {
        error.printStackTrace();
        return false;
      }
    }
    return false;
  }
  
  /** {@inheritDoc} */
  @Override
  public <K, V extends Serializable, I extends Collection<?>> boolean registerRepository(Repository<K, V, I> repository)
  {
    if (repository != null)
    {
      if (repository instanceof RepositoryBase)
      {
        RepositoryBase<K, V, I> base = (RepositoryBase<K, V, I>) repository;
        
        try
        {
          Field field = RepositoryBase.class.getDeclaredField("indicatedTable");
          field.setAccessible(true);
          String table = (String) field.get(base);
          this.repositoryMap.put(table, repository);
          DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") the repository that indicates '" + table +
                  "' has successfully been registered");
          return true;
        } catch (NoSuchFieldException | IllegalAccessException error)
        {
          error.printStackTrace();
          return false;
        }
      }
    }
    return false;
  }
  
  /** {@inheritDoc} */
  @Override
  public void startRepositories()
  {
    if (this.repositoryMap != null && this.repositoryMap.size() >= 1)
    {
      for (String eachTableName : new LinkedHashSet<String>(this.repositoryMap.keySet()))
      {
        DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") verifying the '" + eachTableName + "' table...");
        
        if (this.repositoryMap.get(eachTableName) != null && this.repositoryMap.get(eachTableName) instanceof RepositoryBase)
        {
          DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") searching for the repository which indicates '"
                  + eachTableName + "' table in SQL database.");
          RepositoryBase<?, ?, ?> repository = (RepositoryBase<?, ?, ?>) this.repositoryMap.get(eachTableName);
          DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") trying to start the repository of '"
                  + eachTableName + "' table.");
          
          if (repository.start(this))
            DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") the repository which indicates the '"
                    + eachTableName + "' has just successfully been started.");
          else
            DriverHandler.LOGGER.log(Level.WARNING, "(Connector@" + this.where.getSchema() + ") something has interrupted the " +
                    "initialization of the repository that indicates '" + eachTableName + "' table.");
        }
      }
    }
  }
  
  /** {@inheritDoc} */
  @Override
  public boolean automaticLoading()
  {
    return this.automaticLoading;
  }
  
  /** The connection stabled when the connector opens a connection to the SQL database. */
  private Connection how = null;
  
  /** {@inheritDoc} */
  @Override
  public Connection connect()
  {
    if (!this.hasConnection())
    {
      DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") is starting to create a connection with " +
              "the '" + this.where.getSchema() + "' database...");
      
      try
      {
        StringBuilder builder = new StringBuilder("jdbc:");
        
        if (this.where.getDriver().equalsIgnoreCase("MySQL"))
          builder.append("mysql");
        else
          throw new RuntimeException("This Connector does not support any driver else besides MySQL");
        
        builder.append(this.where.getHostname()).append(':').append(this.where.getPort()).append("/");
        builder.append(this.where.getSchema());
        
        if (this.where.getProperties() != null)
        {
          builder.append('?');
          for (Object eachKey : this.where.getProperties().keySet())
          {
            String realKey = eachKey.toString();
            if (realKey != null && !realKey.equalsIgnoreCase("null") && realKey.length() > 0)
            {
              builder.append(realKey).append('=').append(this.where.getProperties().getProperty(realKey));
            }
          }
        }
        
        DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") stabling the connection...");
        Connection connection = DriverManager.getConnection(builder.toString(), this.who.getUsername(), this.who.getPassword());
        
        for (int index = 0; index < 100; index++)
        {
          if (connection == null)
            throw new RuntimeException("The connection has not been initialized");
          
          DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") verifying connection: " + index + "%");
        }
        
        DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") connection stabled");
        this.how = connection;
        
        if (this.automaticLoading())
          this.startRepositories();
        
        return this.how;
      } catch (SQLException error)
      {
        DriverHandler.LOGGER.log(Level.WARNING, "(Connector@" + this.where.getSchema() + ") Something interrupted the process:");
        throw new RuntimeException(error);
      }
    }
    return null;
  }
  
  /** {@inheritDoc} */
  @Override
  public void disconnect()
  {
    if (this.hasConnection())
    {
      DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") is starting to turn" +
              " all the connections with database off...");
      
      try
      {
        DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") closing the current connection...");
        this.how.close();
        DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") setting null to the old connection...");
        this.how = null;
        
        for (int index = 0; index < 100; index++)
        {
          DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") verifying " + index + "%");
          
          if (this.hasConnection())
            DriverHandler.LOGGER.log(Level.WARNING, "(Connector@" + this.where.getSchema() + ") Something went wrong because there is a stable connection yet");
        }
      } catch (SQLException error)
      {
        DriverHandler.LOGGER.log(Level.WARNING, "(Connector@" + this.where.getSchema() + ") Something interrupted the process:");
        throw new RuntimeException(error);
      }
      DriverHandler.LOGGER.log(Level.INFO, "(Connector@" + this.where.getSchema() + ") Successfully disconnected");
    }
  }
  
  /** {@inheritDoc} */
  @Override
  public Connection getCurrentConnection()
  {
    return this.how;
  }
  
  /** {@inheritDoc} */
  @Override
  public boolean hasConnection()
  {
    if (this.how != null)
    {
      try
      {
        return !this.how.isClosed();
      } catch (SQLException error)
      {
        return false;
      }
    }
    return false;
  }
  
  /** {@inheritDoc} */
  @Override
  public User getUser()
  {
    return this.who;
  }
  
  /** {@inheritDoc} */
  @Override
  public Address where()
  {
    return this.where;
  }
  
  /**
   * Loads all the <code>{@link Class Classes}</code> found in the <code>packagePath</code>.
   * <p>After loading the class types, a <code>{@link Collection collection}</code> is constructed and all the loaded classes
   * are added to it.
   * @param loader The loader class, also called "host" or "main".
   * @param packagePath The path of the package where the classes which will be loaded from.
   * @return A <code>Collection</code> (which is <i>unmodifiable</i>) containing all the <code>{@link Class classes}</code>
   *         which have been loaded by this process; or an empty (and <i>unmodifiable</i>) collection.
   * @throws IOException If an I/O error occurs whilst the method is trying to load the classes.
   */
  private static Collection<? extends Class<?>> forPackage(Class<?> loader, String packagePath) throws IOException
  {
    return forPackage(loader, packagePath, false, null);
  }
  
  /**
   * Loads all the <code>{@link Class Classes}</code> found in the <code>packagePath</code>.
   * <p>After loading the class types, a <code>{@link Collection collection}</code> is constructed and all the loaded classes
   * are added to it.
   * @param loader The loader class, also called "host" or "main".
   * @param packagePath The path of the package where the classes which will be loaded from.
   * @param logEverything A boolean which determines if the directory of the class needs to be reported through a log.
   * @param logger The logger that, if the <code>logEverything</code> is <code><strong><b>true</b></strong></code>,
   *               sending the logs.
   * @return A <code>Collection</code> (which is <i>unmodifiable</i>) containing all the <code>{@link Class classes}</code>
   *         which have been loaded by this process; or an empty (and <i>unmodifiable</i>) collection.
   * @throws IOException If an I/O error occurs whilst the method is trying to load the classes.
   */
  private static Collection<? extends Class<?>> forPackage(Class<?> loader, String packagePath,
                                                          boolean logEverything, Logger logger) throws IOException
  {
    ArrayList<Class<?>> list = new ArrayList<Class<?>>();
    CodeSource codeSource = loader.getProtectionDomain().getCodeSource();
    
    if (codeSource != null)
    {
      URL resource = codeSource.getLocation();
      resource.getPath();
      list.addAll(processFiles(resource, packagePath));
    }
    
    ArrayList<String> classNames = new ArrayList<String>();
    ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
    
    for (Class<?> eachLoadedClass : list)
    {
      classNames.add(eachLoadedClass.getSimpleName());
      classes.add(eachLoadedClass);
    }
    list.clear();
    Collections.sort(classNames, String.CASE_INSENSITIVE_ORDER);
    
    for (String eachClassName : classNames)
    {
      for (Class<?> eachFoundClass : classes)
      {
        if (eachFoundClass.getSimpleName().equals(eachClassName))
        {
          list.add(eachFoundClass);
          
          if (logEverything)
          {
            if (logger != null)
              logger.log(Level.INFO, eachFoundClass.getSimpleName() + " has successfully been loaded in " + packagePath);
          } else
          {
            Logger.getGlobal().log(Level.INFO, eachFoundClass.getSimpleName() + " has successfully been loaded in "
                    + packagePath);
          }
        }
      }
    }
    return Collections.unmodifiableList(list);
  }
  
  /**
   * Loads the <code><strong><b>class</b></strong></code> by the given <code>className</code>.
   * @param className The class name which identifies the class to be loaded.
   * @return The <code><strong><b>class</b></strong></code> which has been loaded.
   * @throws IOException If an I/O error occurs whilst the method is trying to load the class.
   */
  private static Class<?> loadClass(String className) throws IOException
  {
    try
    {
      return Class.forName(className);
    } catch (ClassNotFoundException error)
    {
      throw new IOException("The " + className + " cannot be found");
    }
  }
  
  /**
   * Processes the <code>{@link Class classes}</code> which are identified by the <code>{@link URL resource}</code> URL
   * and by the <code>path</code> (which indicates the <code><strong><b>package</b></strong></code>.)
   * @param resource The URL resource to process the files.
   * @param path The path of the package where the classes which will be loaded in.
   * @return A <code>List</code> containing the <code>{@link Class Classes}</code>.
   * @throws IOException If an I/O error occurs whilst the process happens.
   */
  private static List<Class<?>> processFiles(URL resource, String path) throws IOException
  {
    if (path == null)
      throw new IOException("The path (" + path + ") needs to be considered valid");
    
    ArrayList<Class<?>> list = new ArrayList<Class<?>>();
    String releasePath = path.replace('.', '/');
    String resourcePath = resource.getPath().replace("%20", " ");
    String jarPath = resourcePath.replaceFirst("[.]jar[!].*", ".jar")
            .replaceFirst("file:", "");
    JarFile jar;
    
    try
    {
      jar = new JarFile(jarPath);
    } catch (IOException error)
    {
      throw new IOException(error);
    }
    
    Enumeration<JarEntry> enumeration = jar.entries();
    
    while (enumeration.hasMoreElements())
    {
      JarEntry entry = enumeration.nextElement();
      String entryName = entry.getName();
      String className = null;
      
      if (entryName.endsWith(".class") && entryName.startsWith(releasePath)
              && entryName.length() > (releasePath.length() + "/".length()))
        className = entryName.replace("/", ".").replace('\\', '.')
                .replace(".class", "");
      
      if (className != null)
        list.add(loadClass(className));
    }
    return list;
  }
}
