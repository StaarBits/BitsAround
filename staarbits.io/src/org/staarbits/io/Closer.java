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
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The Closer is used for closing all the <code>{@link Closeable Closeables}</code> which are present in the Closeable.
 */
public final class Closer
{
  
  /** A set containing all the Closeables to be closed */
  @NotNull
  private Set<Closeable> closeableCache;
  
  /** Constructs a new <code>Closer</code> */
  public Closer()
  {
    this.closeableCache = new LinkedHashSet<Closeable>();
  }
  
  /** Constructs a new <code>Closer</code> */
  public Closer(@NotNull final Closeable closeable)
  {
    this.closeableCache = new LinkedHashSet<Closeable>();
    this.closeableCache.addAll(Collections.singletonList(closeable));
  }
  
  /** Constructs a new <code>Closer</code> */
  public Closer(@NotNull final Closeable[] closeables)
  {
    this.closeableCache = new LinkedHashSet<Closeable>();
    this.closeableCache.addAll(Arrays.asList(closeables));
  }
  
  /** Constructs a new <code>Closer</code> */
  public Closer(@NotNull final Collection<? extends Closeable> collection)
  {
    this.closeableCache = new LinkedHashSet<Closeable>();
    this.closeableCache.addAll(collection);
  }
  
  /** A boolean which indicates whether it has closed all the closeables or not */
  private boolean hasClosed = false;
  
  /** Closes all the present <code>{@link Closeable Closeables}</code> */
  public void close()
  {
    if (!this.hasClosed)
    {
      if (this.closeableCache.size() > 0)
      {
        for (Closeable eachCloseable : this.closeableCache)
        {
          if (eachCloseable != null)
          {
            try
            {
              eachCloseable.close();
            } catch (IOException error)
            {
              throw new RuntimeException("An IOException has interrupted the process whilst the Closer was trying to" +
                      " close the data in the Closeable (" + eachCloseable.getClass().getSimpleName() + ") -- ", error);
            }
          }
        }
      }
    }
  }
  
  /**
   * Resets <code>{@link Closer this}</code> Closer (withdrawing all the present <code>{@link Closeable Closeables}</code>
   * and sets the boolean determination to <code><strong>false</strong></code>, resetting the cache.)
   * @return <code><strong><b>this</b></strong></code> instance.
   */
  @NotNull
  public final Closer reset()
  {
    this.closeableCache.clear();
    this.hasClosed = false;
    return this;
  }
  
  /**
   * Increments a <code>Closeable</code> to <code>{@link Closer this}</code> Closer.
   * @param closeable The <code>java.io.Closeable</code> implementation which is incremented to this Closer.
   * @return <code><strong><b>this</b></strong></code> instance.
   */
  @NotNull
  public Closer increment(@NotNull Closeable closeable)
  {
    if (this.hasClosed)
      throw new RuntimeException("The closeable has already closed all the present Closeables");
    
    this.closeableCache.add(closeable);
    return this;
  }
}
