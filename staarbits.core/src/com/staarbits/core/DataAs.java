package com.staarbits.core;

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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The @DataAs is an annotation which declares a data-name for the <code>{@link ElementType#TYPE class}</code> or the
 * <code>{@link ElementType#ANNOTATION_TYPE annotation}</code> and a warning -- if needed.
 */
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
public @interface DataAs
{
  
  /**
   * Represents the <code>String</code> which works as the value which is the declared-data's name.
   * @return The value.
   */
  @NotNull
  String value();
  
  /**
   * Represents the warning which must be thrown when the class is being loaded.
   * @return The warning in a <code>String</code>.
   */
  @NotNull
  String warning()
          default "";
}
