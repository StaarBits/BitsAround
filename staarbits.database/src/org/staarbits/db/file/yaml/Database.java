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

import com.staarbits.core.NotNull;
import java.io.File;
import java.util.LinkedList;

public abstract class Database
{
  
  @NotNull
  private File path;
  
  @NotNull
  private LinkedList<Protocol<?>> currentProtocols;
  
  protected Database(@NotNull File path)
  {
    this.path = path;
    this.currentProtocols = new LinkedList<Protocol<?>>();
  }
  
  /** The number of bytes which is needed by <code>{@link Database this}</code> Database */
  private int necessarySize = 0;
  
  /** The max necessary size available */
  private int maxNecessarySize = 1024;
  
  /** Imports all the <code>{@link Protocol protocols}</code> from the given <code>{@link Database database}</code> */
  public void importProtocols(@NotNull Database database)
  {
    this.currentProtocols.addAll(database.currentProtocols);
  }
  
  /**
   * Registers those all <code>{@link Protocol Protocols}</code> into <code>{@link Database this}</code> Database.
   * @param protocol The protocol to be registered.
   * @return <code><strong><b>true</b></strong></code> if the given <code>{@link Protocol protocol}</code> has successfully
   *         been registered in <code>{@link Database this}</code>; or <code><strong><b>false</b></strong></code> if something
   *         interrupted this process whilst this method was trying to insert the protocol, oversize alike.
   * @throws OutOfMemoryError If the additional size (the number protocol's size multiplied by 4096) reaches the maximum
   *                          of memory allowed, <code>1024 * 32</code> in bytes.
   */
  public boolean registerProtocol(@NotNull Protocol<?> protocol)
  {
    if (!this.currentProtocols.contains(protocol))
    {
      this.maxNecessarySize = 1024 * 32;
      if (protocol.size() >= 1)
      {
        this.currentProtocols.add(protocol);
        int additionalSize = (1024 * 4) * protocol.size();
        
        if (this.maxNecessarySize < additionalSize)
          throw new OutOfMemoryError("The additional size (equivalent to '" + additionalSize + "') has reached the" +
                  " maximum necessary-size available (which is equivalent to '" + this.maxNecessarySize + "')");
        
        this.necessarySize += additionalSize;
        return true;
      }
    }
    return false;
  }
}
