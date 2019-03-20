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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The @NotNull annotation is an annotation which indicates the annotated items (which -- in this case -- might be a
 * <code>{@link ElementType#FIELD field}</code>, <code>{@link ElementType#LOCAL_VARIABLE local variable}</code>,
 * or <code>{@link ElementType#PARAMETER parmater}</code>) cannot -- in any clauses -- be equivalent to
 * <code><strong><b>null</b></strong></code>, or when declared on a <code>{@link ElementType#METHOD method}</code>,
 * the returning-type of the method cannot be equivalent to <code><strong><b>null</b></strong></code>.
 */
@Retention(RUNTIME)
@DataAs("@NotNull")
@Target({FIELD, LOCAL_VARIABLE, PARAMETER, METHOD})
public @interface NotNull
{
  
  /*
   * The @NotNull annotation is described as a non-usage value. Then, it works without any value about declaration.
   */
}
