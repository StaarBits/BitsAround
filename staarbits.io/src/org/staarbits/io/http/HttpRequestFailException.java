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

import com.staarbits.core.DataAs;
import com.staarbits.core.NotNull;
import com.staarbits.core.Nullable;
import org.staarbits.io.Serializable;

@DataAs("HttpRequestFailException")
public class HttpRequestFailException extends Exception implements Serializable
{
  
  /** The kind of <code>RequestFail</code> */
  @NotNull
  private final RequestFail fail;
  
  /** The response code */
  private int responseCode;
  
  /** The response <code>String</code> value */
  @Nullable
  private String response;
  
  /** Constructs a new <code>HttpRequestFailException</code> */
  public HttpRequestFailException(int responseCode, @Nullable String response)
  {
    super();
    this.fail = RequestFail.RESPONSE;
    this.responseCode = responseCode;
    this.response = response;
  }
  
  /** Constructs a new <code>HttpRequestFailException</code> */
  public HttpRequestFailException(@NotNull RequestFail fail)
  {
    this.fail = fail;
  }
  
  /**
   * Gets the <code><strong><b>{@link Integer int}</b></strong></code> value which is the response error-code equivalent
   * to this reason that makes <code>{@link HttpRequestFailException this}</code> HttpRequestFailException be thrown after
   * declaring the given <code>responseCode</code> to this value.
   * @param responseCode The response code which will be replacing the response code.
   * @return The response error-code.
   */
  public final int responseCode(final int responseCode)
  {
    this.updateResponseCode(responseCode);
    return this.responseCode();
  }
  
  /**
   * Gets the <code><strong><b>{@link Integer int}</b></strong></code> value which is the response error-code equivalent
   * to this reason that makes <code>{@link HttpRequestFailException this}</code> HttpRequestFailException be thrown.
   * @return The response error-code.
   */
  public final int responseCode()
  {
    return this.responseCode;
  }
  
  /**
   * Gets the <code>String</code> representation which is the response for <code>{@link HttpRequestFailException this}</code>
   * HttpRequestFailException, declaring the motive which has been set as <code>response</code>.
   * @param response The response which will be replacing the response.
   * @return The <code>String</code> response.
   */
  @NotNull
  public final String response(@NotNull final String response)
  {
    this.updateResponse(response);
    return this.response();
  }
  
  /**
   * Gets the <code>String</code> representation which is the response for <code>{@link HttpRequestFailException this}</code>
   * HttpRequestFailException, declaring the motive.
   * @return The <code>String</code> response.
   */
  @NotNull
  public final String response()
  {
    return this.response;
  }
  
  /**
   * Updates the <code>responseCode</code> that has been set in <code>{@link HttpRequestFailException this}</code>
   * HttpRequestFailException.
   * @param responseCode The response code which will be replacing the response code.
   */
  public final void updateResponseCode(final int responseCode)
  {
    this.responseCode = responseCode;
  }
  
  /**
   * Updates the <code>response</code> which has been set in <code>{@link HttpRequestFailException this}</code>
   * HttpRequestFailException.
   * @param response The response which will be replacing the response.
   */
  public final void updateResponse(final String response)
  {
    this.response = response;
  }
}
