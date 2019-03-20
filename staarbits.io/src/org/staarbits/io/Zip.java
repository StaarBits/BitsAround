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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public final class Zip
{
  
  /**
   * Zips off all the files in the <code>path</code> (all the zipped files will be copied (unzipped) in the folder which
   * is indicated as <code>outputDirectory</code>.)
   * @param path The path where the zip file is found in.
   * @param outputDirectory THe output directory is where all the --already -- unzipped files will be taken to.
   * @throws IOException If an I/O error occurs whilst the this method is trying to zipping it all off.
   */
  public static void zipOff(@NotNull final String path, @NotNull final String outputDirectory) throws IOException
  {
    FileInputStream fileInputStream = null;
    ZipInputStream zipInputStream = null;
    FileOutputStream fileOutputStream = null;
    BufferedInputStream in = null;
    BufferedOutputStream out = null;
    
    fileInputStream = new FileInputStream(path);
    in = new BufferedInputStream(fileInputStream);
    zipInputStream = new ZipInputStream(in);
    
    ZipEntry entry = null;
    while ((entry = zipInputStream.getNextEntry()) != null)
    {
      int size;
      byte[] buffer = new byte[2048];
      fileOutputStream = new FileOutputStream(outputDirectory + File.separator + entry.getName());
      out = new BufferedOutputStream(fileOutputStream, buffer.length);
      
      while ((size = zipInputStream.read(buffer, 0, buffer.length)) != -1)
      {
        out.write(buffer, 0, size);
      }
      out.flush();
      out.close();
      fileOutputStream.flush();
      fileInputStream.close();
    }
    new Closer(new Closeable[] {zipInputStream, in, fileInputStream}).close();
  }
  
  /**
   * Converts all the files and folders into a <code>zip</code> file.
   * <p>The zip name is constructed based on an -- already -- created <code>source</code>, you need to declare the name of
   * the file which is built in.
   * @param source The source where the files will be zipped from.
   * @param zipName The name of the zip file which will be built.
   * @param folders A list containing all the folders.
   * @param files A list containing all the files.
   * @throws IOException If an I/O error occurs whilst this method is zipping the files.
   */
  public static void zip(@NotNull final String source, @NotNull final String zipName, @NotNull final List<String> folders,
                         @NotNull final List<String> files) throws IOException
  {
    ZipOutputStream zipOutputStream = null;
    FileOutputStream fileOutputStream = null;
    FileInputStream fileInputStream = null;
    BufferedOutputStream out = null;
    List<String> listedFilenames = new ArrayList<String>();
    byte[] buffer = new byte[2048];
    
    fileOutputStream = new FileOutputStream(zipName);
    out = new BufferedOutputStream(fileOutputStream);
    zipOutputStream = new ZipOutputStream(out);
    ZipEntry entry;
    
    for (String eachFilename : files)
    {
      listedFilenames.add(eachFilename.substring(source.length() + 1));
    }
    
    for (String eachFolder : folders)
    {
      listedFilenames.addAll(getFilenames(source, new File(eachFolder)));
    }
    
    for (String eachFilename : listedFilenames)
    {
      entry = new ZipEntry(eachFilename);
      zipOutputStream.putNextEntry(entry);
      fileInputStream = new FileInputStream(source + File.separator + eachFilename);
      
      int length;
      while ((length = fileInputStream.read(buffer)) > 0)
      {
        zipOutputStream.write(buffer, 0, length);
      }
      fileInputStream.close();
    }
    zipOutputStream.flush();
    zipOutputStream.close();
    out.flush();
    out.close();
  }
  
  /**
   * Gets a <code>LinkedList</code> containing all the filenames which are currently found inside of the given <code>folder</code>.
   * @param folder The folder name -- it must contain the path to.
   * @param node The node file.
   * @return The <code>LinkedList</code> containing all the results of the query.
   */
  @NotNull
  public static LinkedList<String> getFilenames(@NotNull final String folder, @NotNull File node)
  {
    LinkedList<String> linkedList = new LinkedList<String>();
    
    if (node.isFile())
    {
      linkedList.add(node.getAbsoluteFile().toString().substring(folder.length() + 1));
    }
    
    if (node.isDirectory())
    {
      String[] subNode = node.list();
      for (String eachFilename : subNode)
      {
        linkedList.addAll(Zip.getFilenames(folder, new File(node, eachFilename)));
      }
    }
    return linkedList;
  }
  
  /** Constructs a new <code>Zip</code> */
  private Zip()
  {       }
}
