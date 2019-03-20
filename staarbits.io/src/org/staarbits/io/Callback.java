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

/**
 * The Callback <code>interface</code> is a sort of the callback which usually works as a lambda or simply invoking the
 * non-constructor instance.
 * @param <E> The E type for the data which will call it back.
 */
public interface Callback<E>
{
  
  /**
   * Calls <code>{@link Callback this}</code> Callback.
   * <p>This <code>Callback</code> is invoked through this method, which is executed by a <code>{@link E data}</code>
   * (which is described as an <code>{@link E}</code> type.)
   * @param data The data which will execute this
   */
  void call(E data);
}
