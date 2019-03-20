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

import java.nio.charset.Charset;

public final class Charsets
{
  
  /**
   * Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the
   * Unicode character set
   */
  public static final Charset US_ASCII = Charset.forName("US-ASCII");
  /**
   * ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
   */
  public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
  /**
   * Eight-bit UCS Transformation Format
   */
  public static final Charset UTF_8 = Charset.forName("UTF-8");
  /**
   * Sixteen-bit UCS Transformation Format, big-endian byte order
   */
  public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
  /**
   * Sixteen-bit UCS Transformation Format, little-endian byte order
   */
  public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
  /**
   * Sixteen-bit UCS Transformation Format, byte order identified by an
   * optional byte-order mark
   */
  public static final Charset UTF_16 = Charset.forName("UTF-16");
  
  /** Constructs a new <code>Charsets</code> */
  private Charsets()
  {         }
}
