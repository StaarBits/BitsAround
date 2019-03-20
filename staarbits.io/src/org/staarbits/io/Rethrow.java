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

@DataAs("Rethrow")
public interface Rethrow extends Serializable
{
  
  /**
   * Rethrows the given <code>{@link Throwable throwable}</code> to <code>{@link Rethrow this}</code> Rethrow.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link Rethrow this}</code> Rethrow has successfully been
   *         rethrown; or <code><strong><b>false</b></strong></code> if it has not.
   */
  <T extends Throwable> boolean rethrow(@NotNull T throwable);
}
