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
import java.io.Closeable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogSuppressor implements Suppressor
{
  
  /** The <code>LogSuppressor</code> instance */
  @NotNull
  private static Suppressor INSTANCE = null;
  
  /** The <code>LogSuppressor</code> instance */
  public static Suppressor getInstance(@NotNull Logger logger)
  {
    if (INSTANCE == null)
    {
      INSTANCE = new LogSuppressor(logger);
    }
    return INSTANCE;
  }
  
  /** The <code>Logger</code> instance */
  @NotNull
  private Logger logger;
  
  /** Constructs a new <code>LogSuppressor</code> */
  private LogSuppressor(@NotNull final Logger logger)
  {
    this.logger = logger;
  }
  
  /** {@inheritDoc} */
  @Override
  public void suppress(Closeable closeable, Throwable thrown, Throwable suppressed)
  {
    if (this.logger != null)
    {
      this.logger.log(Level.WARNING, "Suppressing exception thrown when closing " + closeable, suppressed);
    }
  }
}
