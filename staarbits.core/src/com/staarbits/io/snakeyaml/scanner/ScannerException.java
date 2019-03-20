package com.staarbits.io.snakeyaml.scanner;

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

import com.staarbits.io.snakeyaml.error.Mark;
import com.staarbits.io.snakeyaml.error.MarkedYAMLException;

/**
 * Exception thrown by the {@link Scanner} implementations in case of malformed
 * input.
 */
public class ScannerException extends MarkedYAMLException {

    private static final long serialVersionUID = 4782293188600445954L;

    /**
     * Constructs an instance.
     * 
     * @param context
     *            Part of the input document in which vicinity the problem
     *            occurred.
     * @param contextMark
     *            Position of the <code>context</code> within the document.
     * @param problem
     *            Part of the input document that caused the problem.
     * @param problemMark
     *            Position of the <code>problem</code> within the document.
     * @param note
     *            Message for the user with further information about the
     *            problem.
     */
    public ScannerException(String context, Mark contextMark, String problem, Mark problemMark,
                            String note) {
        super(context, contextMark, problem, problemMark, note);
    }

    /**
     * Constructs an instance.
     * 
     * @param context
     *            Part of the input document in which vicinity the problem
     *            occurred.
     * @param contextMark
     *            Position of the <code>context</code> within the document.
     * @param problem
     *            Part of the input document that caused the problem.
     * @param problemMark
     *            Position of the <code>problem</code> within the document.
     */
    public ScannerException(String context, Mark contextMark, String problem, Mark problemMark) {
        this(context, contextMark, problem, problemMark, null);
    }
}
