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
import com.staarbits.core.Nullable;

@DataAs("Null")
public final class Null
{
  
  /**
   * Gets the hash code for <code>{@link Null this}</code> Null.
   * <p>As the <code>Null</code> class always represents an invalid type or number. The developers usually use the <code>-1</code>
   * as a <code>{@link Number number}</code> which they consider invalid. We, this method will also return <code>-1</code>,
   * value (an <code><strong><b>int</b></strong></code> which is considered invalid.)
   * @return The hash code for <code>{@link Null this}</code> Null, <code>-1</code> (an <code><strong>int</strong></code> value
   *         which is considered invalid.)
   */
  @Override
  public final int hashCode()
  {
    return -1;
  }
  
  /**
   * Gets the <code>String</code> representation of <code>{@link Null this}</code> Null.
   * <p>As the <code>Null</code> class always represents an invalid type or number, this method will return an invalid string.
   * So, it will return a <code><strong><b>"null"</b></strong></code> string.
   * @return The <code><strong><b>"null"</b></strong></code> value.
   */
  @Override
  public final String toString()
  {
    return "null";
  }
  
  /**
   * Checks whether the given <code>{@link Object object}</code> is equivalent to <code><strong><b>null</b></strong></code>.
   * @param object An object to be compared to a <code><strong><b>null</b></strong></code> value.
   * @return <code><strong><b>true</b></strong></code> if the given <code>{@link Object object}</code> is equivalent to an
   *         invalid type (<code><strong><b>null</b></strong></code>;) or <code><strong><b>false</b></strong></code> if this
   *         same <code>{@link Object object}</code> is considered valid. So, different from <code><strong>null</strong></code>.
   */
  @Override
  public final boolean equals(@Nullable Object object)
  {
    if (object instanceof String)
    {
      if (((String) object).equalsIgnoreCase(this.toString()))
        return true;
    }
    return object == null;
  }
  
  /** The <code>Null</code> instance */
  public static final Null INSTANCE = new Null();
  
  /** Constructs a new <code>Null</code> */
  private Null()
  {       }
}
