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

public final class LogColor
{
  
  /** The character base for the <code>LogColor</code> */
  private static final char CHAR = '&';
  
  /** Formats the given string */
  public static String format(@NotNull String message)
  {
    return message.replace(CHAR + "a", GREEN_BRIGHT).replace(CHAR + "b", CYAN_BRIGHT)
            .replace(CHAR + "c", RED_BRIGHT).replace(CHAR + "d", PURPLE_BRIGHT)
            .replace(CHAR + "e", YELLOW_BRIGHT).replace(CHAR  + "f", WHITE_BRIGHT)
            .replace(CHAR + "0", BLACK).replace(CHAR + "1", BLUE)
            .replace(CHAR + "2", GREEN).replace(CHAR + "3", CYAN)
            .replace(CHAR + "4", RED).replace(CHAR  + "5", PURPLE)
            .replace(CHAR + "6", YELLOW).replace(CHAR + "7", WHITE)
            .replace(CHAR + "8", BLACK_BRIGHT).replace(CHAR + "9", BLUE_BRIGHT);
  }
  
  // Reset
  public static final String RESET = "\033[0m";  // Text Reset
  
  // Regular Colors
  public static final String BLACK = "\033[0;30m";   // BLACK
  public static final String RED = "\033[0;31m";     // RED
  public static final String GREEN = "\033[0;32m";   // GREEN
  public static final String YELLOW = "\033[0;33m";  // YELLOW
  public static final String BLUE = "\033[0;34m";    // BLUE
  public static final String PURPLE = "\033[0;35m";  // PURPLE
  public static final String CYAN = "\033[0;36m";    // CYAN
  public static final String WHITE = "\033[0;37m";   // WHITE
  
  // Bold
  public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
  public static final String RED_BOLD = "\033[1;31m";    // RED
  public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
  public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
  public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
  public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
  public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
  public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
  
  // Underline
  public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
  public static final String RED_UNDERLINED = "\033[4;31m";    // RED
  public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
  public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
  public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
  public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
  public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
  public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE
  
  // Background
  public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
  public static final String RED_BACKGROUND = "\033[41m";    // RED
  public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
  public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
  public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
  public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
  public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
  public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
  
  // High Intensity
  public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
  public static final String RED_BRIGHT = "\033[0;91m";    // RED
  public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
  public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
  public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
  public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
  public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
  public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
  
  // Bold High Intensity
  public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
  public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
  public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
  public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
  public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
  public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
  public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
  public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
  
  // High Intensity backgrounds
  public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
  public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
  public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
  public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
  public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
  public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
  public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
  public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
  
  /** Constructs a new <code>logColor</code> */
  private LogColor()
  {       }
}
