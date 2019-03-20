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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public final class Copier
{
  
  /** The source <code>File</code> */
  @NotNull
  private File source;
  
  /** The destination <code>File</code> */
  @NotNull
  private File destination;
  
  /** Constructs a new <code>Copier</code> */
  public Copier(@NotNull File source, @NotNull File destination)
  {
    this.source = source;
    this.destination = destination;
  }
  
  /** The <code>operation</code> value */
  private int operation = CONVENTIONAL_STREAM_WAY;
  
  /**
   * Copies the <code>{@link #source source}</code> (the file where all the data will be taken from) the <code>{@link #destination
   * destination}</code> (path which will receive all the data.)
   * @throws IOException If an I/O error occurs whilst the file was being copied.
   * @throws FileNotFoundException If the <code>{@link #source source}</code> is not found.
   */
  public void copy() throws IOException, FileNotFoundException
  {
    if (this.source == null || !this.source.exists())
      throw new FileNotFoundException("The " + this.source.getPath() + " could not be found");
    
    if (this.operation == CONVENTIONAL_STREAM_WAY)
    {
      InputStream in = null;
      OutputStream out = null;
      
      try
      {
        in = new FileInputStream(this.source);
        out = new FileOutputStream(this.destination);
        byte[] buffer = new byte[1024];
        int length;
        
        while ((length = in.read(buffer)) > 0)
        {
          out.write(buffer, 0, length);
        }
      } finally
      {
        in.close();
        out.close();
      }
    } else if (this.operation == CHANNELS_WAY)
    {
      FileChannel sourceChannel = null;
      FileChannel destinationChannel = null;
      
      try
      {
        sourceChannel = new FileInputStream(this.source).getChannel();
        destinationChannel = new FileOutputStream(this.destination).getChannel();
        destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
      } finally
      {
        sourceChannel.close();
        destinationChannel.close();
      }
    } else if (this.operation == NIO_WAY)
    {
      Files.copy(this.source.toPath(), this.destination.toPath());
    } else
    {
      throw new IOException("The " + operation + " could not be found. To load a valid operation, it needs to be" +
              " between 1 and 3 (1 = Conventional Stream Sending Data, 2 = Sending data through the Channels" +
              " and 3 = Using the java.io.nio.Files.copy(sourcePath, destinationPath).");
    }
  }
  
  /**
   * Declares a sort of the <code>operation</code> which is built by <code>{@link Copier this}</code> Copier.
   * @param operation The int value which identifies the operation that will be done.
   * @return <code><strong><b>this</b></strong></code> instance.
   */
  @NotNull
  public Copier by(int operation)
  {
    this.operation = operation;
    return this;
  }
  
  /** The conventional stream way */
  public static final int CONVENTIONAL_STREAM_WAY = 1;
  
  /** The way that uses the FileChannels to transfer the data */
  public static final int CHANNELS_WAY = 2;
  
  /** The way which calls the <code>Files</code> class from the java.io.nio package */
  public static final int NIO_WAY = 3;
}
