package org.staarbits.io.http;

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
import com.staarbits.core.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.staarbits.io.Serializable;
import org.staarbits.io.thread.ObjectRunnable;

public abstract class HttpMethodExecutor<E> implements Serializable, HttpLink, ObjectRunnable<E>
{
  
  /** The method */
  @NotNull
  private final String method;
  
  /** The timeout */
  private int timeout;
  
  /** The <code>URL</code> link */
  @NotNull
  protected final URL link;
  
  /** Constructs a new <code>HttpMethodExecutor</code> */
  protected HttpMethodExecutor(@NotNull String method, int timeout, @NotNull URL link)
  {
    this.method = method;
    this.timeout = timeout;
    this.link = link;
  }
  
  /**
   * Gets the linked <code>URL</code> which indicates what address <code>{@link HttpLink this}</code> HttpLink directly
   * connects to (it cannot be <code><strong>null</strong></code> -- according the <code>{@link NotNull @NotNull}</code>
   * -- because when a linked URL is declared, the implemented type will use it as necessary.)
   * @return The linked URL.
   */
  @Override
  public final URL linkedURL()
  {
    return this.link;
  }
  
  /**
   * Runs whether there is an error or not.
   * @param responseCode The response code.
   * @param connection The connection (for http.)
   * @return <code><strong><b>true</b></strong></code> if <code>{@link HttpMethodExecutor this}</code> HttpMethodExecutor
   *         is considering no error; or <code><strong><b>false</b></strong></code> if there is an error occurring whilst
   *         this method is trying to be executed.
   * @throws HttpRequestFailException If there is a response code.
   * @throws IOException If an I/O error occurs.
   */
  protected final boolean considerError(final int responseCode, HttpURLConnection connection) throws HttpRequestFailException,
          IOException
  {
    if (responseCode < 200 || responseCode >= 300)
    {
      InputStream errorStream = ((HttpURLConnection) connection).getErrorStream();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
      StringBuilder builder = new StringBuilder();
      String inputLine;
    
      while ((inputLine = bufferedReader.readLine()) != null)
        builder.append(inputLine);
    
      bufferedReader.close();
      throw new HttpRequestFailException(responseCode, builder.toString());
    }
    return true;
  }
  
  /**
   * Executes <code>{@link HttpMethodExecutor this}</code> HttpMethodExecutor.
   * <p>The method is returned the result with the execution made by this own method. Or <code><strong>null</strong></code>
   * if the <code>{@link #timeout() timeout}</code> reaches the expire time.
   * @return The <code>{@link E result}</code> object as the resulting value.
   * @throws HttpRequestFailException If an error occurs whilst <code>{@link HttpMethodExecutor this}</code> HttpMethodExecutor
   *                                  is trying to execute the method.
   */
  @Nullable
  public abstract E execute() throws HttpRequestFailException;
  
  /**
   * Executes <code>{@link HttpMethodExecutor this}</code> HttpMethodExecutor.
   * <p>This method -- basically -- invokes the <code>{@link #execute() execute()}</code> method, the unique difference
   * between these two methods is... This method does not -- directly -- throw <code>{@link HttpRequestFailException}</code>.
   * @return The <code>{@link E result}</code> object as the resulting value.
   * @deprecated It is just not recommended because you -- with the <code>{@link #execute() execute()}</code> is more useful
   *             about the <code>{@link HttpRequestFailException exception}</code> handling.
   */
  @Override
  @Nullable
  @Deprecated
  public E run()
  {
    try
    {
      return this.execute();
    } catch (HttpRequestFailException error)
    {
      error.printStackTrace();
    }
    return null;
  }
  
  /**
   * Gets the <code>method</code> which is executed to <code>{@link HttpMethodExecutor this}</code> HttpMethodExecutor.
   * @return The method.
   */
  @NotNull
  public final String method()
  {
    return this.method;
  }
  
  /**
   * Gets the <code>timeout</code> for <code>{@link HttpMethodExecutor this}</code> HttpMethodExecutor.
   * @return The timeout.
   */
  public final int timeout()
  {
    return this.timeout;
  }
  
  /** The boolean that indicates whether it uses cache or not */
  private boolean useCache = false;
  
  /** Starts using the cache */
  public void startUsingCache()
  {
    this.useCache = true;
  }
  
  /** Stops using the cache */
  public void stopUsingCache()
  {
    this.useCache = false;
  }
  
  /**
   * Checks whether <code>{@link HttpMethodExecutor this}</code> HttpMethodExecutor is using the cache system or not.
   * @return <code><strong><b>true</b></strong></code> if <code>{@link HttpMethodExecutor this}</code> HttpMethodExecutor
   *         uses the cache option; or <code><strong><b>false</b></strong></code> otherwise.
   */
  public boolean usesCache()
  {
    return this.useCache;
  }
}
