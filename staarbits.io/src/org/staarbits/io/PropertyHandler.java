package org.staarbits.io;

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

import com.staarbits.core.DataAs;
import com.staarbits.core.NotNull;
import com.staarbits.core.Nullable;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@DataAs("PropertyHandler")
public class PropertyHandler implements Closeable
{
  
  /** A boolean which determines whether this handler is currently opened or closed */
  private boolean open;
  
  /** Constructs a new <code>PropertyHandler</code> */
  public PropertyHandler()
  {
    this.open = false;
  }
  
  /** The <code>InputStream</code> which represents the resource */
  @Nullable
  private InputStream resourceStream = null;
  
  /**
   * Loads the resource which is loaded through the <code>filename</code>.
   * <p>This resource will be loaded by a couple of ways, one of the mis as the <code>Properties</code> constructor, it
   * will receive all the data considered a "property" by the system which is contained by the resource and the other way
   * is as an <code>InputStream</code>.
   * @param filename The filename to identify the file whose the properties will be interpreted.
   * @throws IOException If <coed>{@link PropertyHandler this}</coed> PropertyHandler is closed.
   */
  public void load(@NotNull final String filename) throws IOException
  {
    if (this.open)
      throw new IOException("The PropertyHandler has already been opened");
    
    this.loadedProperties = new Properties();
    this.reportedFilename = filename;
    this.resourceStream = PropertyHandler.class.getResourceAsStream(filename);
    this.loadedProperties.load(this.resourceStream);
    this.open = true;
  }
  
  /**
   * Checks whether <code>{@link PropertyHandler this}</code> PropertyHandler is currently opened or not.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link PropertyHandler this}</code> PropertyHandler is
   *         currently opened; or <code><strong><b>false</b></strong></code> if it is not opened or if has not been
   *         opened since it was constructed, closed or aborted.
   */
  public boolean opened()
  {
    return this.open;
  }
  
  /**
   * Aborts all the operations which have been processed for <code>{@link PropertyHandler this}</code> PropertyHandler.
   * @throws IOException If <code>{@link PropertyHandler this}</code> PropertyHandler is not currently opened.
   */
  public void abort() throws IOException
  {
    if (!this.open)
      throw new IOException("The PropertyHandler cannot be aborted because it is not currently opened");
    
    this.open = false;
    this.loadedProperties = null;
    this.reportedFilename = null;
    this.resourceStream = null;
  }
  
  /**
   * Gets the <code>{@link Object object}</code> value which is considered by the <code>{@link Properties properties}</code>
   * of the loaded resource.
   * @param key The key which has been associated to the object-value that will be returned.
   * @return The <code>{@link Object object}</code> value that is associated to the given <code>key</code>; or simply
   *         <code><strong><b>null</b></strong></code> if no object-value is found with <code>key</code>.
   * @throws IOException If <code>{@link PropertyHandler this}</code> PropertyHandler is closed or if the <code>key</code>
   *                     is invalid (equivalent to <code><strong>{@link Null null}</strong></code> or empty.)
   */
  @Nullable
  public Object getProperty(@NotNull final String key) throws IOException
  {
    if (!this.open)
      throw new IOException("The PropertyHandler is not opened");
    
    if (Null.INSTANCE.equals(key))
      throw new IOException("The key must be different from null");
    else if (key.isEmpty())
      throw new IOException("The key must not be an empty character sequence");
    
    return this.loadedProperties.getProperty(key);
  }
  
  /**
   * Closes <code>{@link PropertyHandler this}</code> PropertyHandler, setting all the variables to <code><b>null</b></code>
   * and setting the <code>{@link #open open}</code> variable to <code><strong>false</strong></code>.
   * @throws IOException If <code>{@link PropertyHandler this}</code> PropertyHandler is not opened.
   */
  @Override
  public void close() throws IOException
  {
    if (!this.open)
      throw new IOException("The PropertyHandler is not opened");
    
    this.loadedProperties = null;
    this.reportedFilename = null;
    this.resourceStream = null;
    this.open = true;
  }
  
  /**
   * Loads the resource which is loaded through the <code>filename</code> as a XML file.
   * <p>Differently from <code>{@link #load(String) load(filename)}</code> method, this one will not just load the resource
   * as a <code>{@link Properties}</code> instance, however as a <code>InputStream</code>.
   * @param filename The filename to identify the file whose the properties will be interpreted.
   * @throws IOException If <code>{@link PropertyHandler this}</code> PropertyHandler is closed.
   */
  public void loadXML(@NotNull final String filename) throws IOException
  {
    if (this.open)
      throw new IOException("The PropertyHandler has already been opened");
    
    if (!filename.endsWith(".xml"))
      throw new IOException("THe XML format has not been found");
    
    this.loadedProperties = new Properties();
    this.reportedFilename = filename;
    
    InputStream in = new FileInputStream(filename);
    this.loadedProperties.loadFromXML(in);
    this.reportedKeys = this.loadedProperties.keySet();
    this.open = true;
  }
  
  /** A <code>Set</code> containing the reported keys */
  @Nullable
  private Set<Object> reportedKeys = null;
  
  /**
   * Reads all the <code>{@link Properties properties}</code> individually, without changing any value of <code>{@link PropertyHandler this}</code>
   * PropertyHandler.
   * <p>This method needs that <code>{@link PropertyHandler this}</code> PropertyHandler has already been <code>{@link #load(String) loaded}</code>.
   * Otherwise, it will not be possible to find the reported <code>{@link #getFilename() filename}</code> to reload all the
   * data again.
   * @return The <code>Map</code> containing all the properties copied from the source, in this case, the reported file.
   * @throws IOException If <code>{@link PropertyHandler this}</code> PropertyHandler is not opened.
   */
  @NotNull
  public Map<String, Object> readAll() throws IOException
  {
    if (!this.open)
    {
      throw new IOException("This PropertyHandler cannot be read because it is not opened");
    }
    Properties properties = new Properties();
    FileReader reader = new FileReader(this.reportedFilename);
    properties.load(reader);
    
    Set<Object> reportedKeys = properties.keySet();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    for (Object eachReportedKey : reportedKeys)
    {
      if (!(eachReportedKey instanceof String))
        throw new IOException("The '" + eachReportedKey.toString() + "' must be considered a String");
      
      hashMap.put((String) eachReportedKey, properties.getProperty((String) eachReportedKey));
    }
    this.reportedKeys = reportedKeys;
    return hashMap;
  }
  
  /**
   * Gets a <code>Set</code> containing all the keys which have been reported by the last time when the <code>{@link #readAll() readAll()}</code>
   * method was invoked.
   * <p>If the method is called and the <code>{@link #readAll() readAll()}</code> method has not still been invoked, this
   * value will simply be equivalent to <code><strong>null</strong></code>.
   * @return The <code>Set</code> which contains the keys that have been reported by the last time that the readAll() method
   *         was invoked; or <code><strong>null</strong></code> if the <code>{@link #readAll() readAll()}</code> method has
   *         not been called yet.
   */
  @NotNull
  public final Set<Object> reportedKeys()
  {
    return (this.reportedKeys == null || this.reportedKeys.size() == 0) ?
            Collections.emptySet() : this.reportedKeys;
  }
  
  /**
   * The <code>Properties</code> instance which is loaded, by default, it is equivalent to <code><strong>null</strong></code>.
   * <p>This <code>Properties</code> type is loaded when <code>{@link #load(String) load(filename)}</code> method is invoked,
   * before opening <code>{@link PropertyHandler this}</code> PropertyHandler, that method creates a new instance of the
   * properties, and loads all the resource in the properties instance.
   * <p>If the <code>PropertyHandler</code> is closed or if its operation is aborted, this <code>Properties</code> will be
   * indicating <code><strong>null</strong></code>.
   */
  private Properties loadedProperties = null;
  
  /** The filename which is reported as the name of the resource */
  private String reportedFilename = null;
  
  /**
   * Gets the filename which identifies the name of hte file and its extension.
   * <p>The filename is set when the <code>{@link #load(String) load(filename)}</code> is invoked. So, if the properties
   * has not been loaded yet, the reported filename will demonstrate it being equivalent to <code><strong>null</strong></code>.
   * @return The reported filename.
   */
  @Nullable
  public final String getFilename()
  {
    return this.reportedFilename;
  }
  
  /**
   * Writes the <code>{@link #loadedProperties properties}</code> present in the <code>{@link Map map}</code>.
   * @param filename The filename.
   * @param map The map containing all the properties to be written.
   * @param comment THe comment to be inserted into the file.
   * @throws IOException If an I/O error occurs whilst this method is trying to write it out.
   */
  public void write(@NotNull final String filename, @NotNull Map<String, Object> map, @Nullable String comment) throws IOException
  {
    if (Null.INSTANCE.equals(filename))
    {
      throw new NullPointerException("The '" + filename + "' cannot be equivalent to null");
    } else if (filename.isEmpty())
    {
      throw new IOException("The '" + filename + " must not be empty");
    } else if (map == null || map.isEmpty())
    {
      throw new IOException("The '" + filename + " mut have a Map whose the values are being put in the file");
    }
    Properties properties = new Properties();
    properties.putAll(map);
    properties.store(new FileWriter(filename), (Null.INSTANCE.equals(comment) ? "" : comment));
  }
  
  /**
   * Writes the <code>{@link #loadedProperties properties}</code> present in the <code>{@link Map map}</code> in a XML format.
   * @param filename The filename.
   * @param map The map containing all the properties to be written.
   * @param comment The comment to be inserted into the XML file.
   * @throws IOException If an I/O error occurs whilst the method is trying write it out; this error may be the filename
   *                     equivalent to <code><strong>null</strong></code>, the Map is considered invalid either <code><b>null</b></code>
   *                     or empty alike; or if the <code>filename</code> does not end with ".xml" format.
   * @throws NullPointerException If some @NotNull-declared values are equivalent to <code><strong>null</strong></code>.
   */
  public void writeXML(@NotNull final String filename, @NotNull Map<String, Object> map, @Nullable String comment) throws IOException,
          NullPointerException
  {
    if (Null.INSTANCE.equals(filename))
    {
      throw new NullPointerException("The '" + filename + "' cannot be equivalent to null");
    } else if (map == null || map.isEmpty())
    {
      throw new IOException("The map needs to have received a value for this method to write");
    } else if (!filename.endsWith(".xml"))
    {
      throw new IOException("The '" + filename + "' cannot get a format different from the XML");
    }
    Properties properties = new Properties();
    properties.putAll(map);
    properties.storeToXML(new FileOutputStream(filename), (Null.INSTANCE.equals(comment) ? "" : comment));
  }
}
