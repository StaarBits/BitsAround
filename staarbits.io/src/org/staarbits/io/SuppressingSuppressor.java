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

import java.io.Closeable;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class SuppressingSuppressor implements Suppressor
{
  
  /** {@inheritDoc} */
  @Override
  public void suppress(Closeable closeable, Throwable thrown, Throwable suppressed)
  {
    if (thrown.equals(suppressed))
      return;
    
    try
    {
      SuppressingSuppressor.METHOD.invoke(thrown, suppressed);
    } catch (Throwable throwable)
    {
      LogSuppressor.getInstance(Logger.getGlobal()).suppress(closeable, thrown, suppressed);
    }
  }
  
  /**
   * Checks whether <code>{@link SuppressingSuppressor this}</code> SuppressingSuppressor is available.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link SuppressingSuppressor this}</code> SuppressingSuppressor
   *        is currently available; or <code><strong><b>false</b></strong></code> if it is not.
   */
  public static boolean available()
  {
    return METHOD != null;
  }
  
  /** The method "addSuppress" */
  /* private-package */ static final Method METHOD = SuppressingSuppressor.invokeMethod();
  
  /** Loads a possible way to invoke the <code>addSuppress(Throwable)</code> method from the Throwable */
  /* private-package */ static Method invokeMethod()
  {
    try
    {
      return Throwable.class.getMethod("addSuppressed", Throwable.class);
    } catch (Throwable throwable)
    {
      return null;
    }
  }
  
  /** The <code>Suppressor</code> instance */
  private static final Suppressor INSTANCE = new SuppressingSuppressor();
  
  /** The <code>Suppressor</code> instance */
  public static Suppressor getInstance()
  {
    return INSTANCE;
  }
  
  /** Constructs a new <code>SuppressingSuppressor</code> */
  private SuppressingSuppressor()
  {           }
}
