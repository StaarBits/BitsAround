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

import com.staarbits.core.NotNull;
import com.staarbits.core.Nullable;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Getter
{
  
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
  @NotNull
  public static Collection<? extends Class<?>> forPackage(@NotNull Class<?> loader, @NotNull String packagePath) throws IOException
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
  @NotNull
  public static Collection<? extends Class<?>> forPackage(@NotNull Class<?> loader, @NotNull String packagePath,
                                                          boolean logEverything, @Nullable Logger logger) throws IOException
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
  @Nullable
  public static Class<?> loadClass(@NotNull String className) throws IOException
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
  @NotNull
  public static List<Class<?>> processFiles(@Nullable URL resource, @Nullable String path) throws IOException
  {
    if (path == null || Null.INSTANCE.equals(path))
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
  
  /** Constructs a new <code>Getter</code> */
  private Getter()
  {       }
}
