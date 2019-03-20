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
import java.io.File;
import java.util.logging.Logger;

public class Deleter
{
  
  /** Constructs a new <code>Deleter</code> */
  public static Deleter getInstance(@NotNull final String pathname)
  {
    return new Deleter(pathname);
  }
  
  /** Constructs a new <code>Deleter</code> */
  public static Deleter getInstance(@NotNull final String pathname, @NotNull final Logger logger)
  {
    return new LogDeleter(pathname, logger);
  }
  
  /** The pathname */
  @NotNull
  /* private-package */ String pathname;
  
  /** Constructs a new <code>Deleter</code> */
  /* private-package */ Deleter(@NotNull final String pathname)
  {
    this.pathname = pathname;
  }
  
  /**
   * Deletes the <code>{@link File file}</code> (or the files if the <code>{@link #pathname pathname}</code> indicates a
   * directory.)
   * @return <code><strong><b>true</b></strong></code> if the associated <code>{@link File file}</code>(s) has successfully
   *         been deleted; or <code><strong><b>false</b></strong></code> if it has not.
   */
  public boolean delete()
  {
    if (!new File(this.pathname).exists())
      return false;
    
    File path = new File(this.pathname);
    
    if (path.isDirectory())
    {
      for (File eachFile : path.listFiles())
      {
        new Deleter(eachFile.getPath()).delete();
      }
    }
    path.delete();
    return true;
  }
}
