package org.staarbits.io.http.method;

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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import org.staarbits.io.http.HttpMethodExecutor;
import org.staarbits.io.http.HttpRequestFailException;
import org.staarbits.io.http.RequestFail;
import org.staarbits.io.json.JsonObject;
import org.staarbits.io.json.JsonValue;

public class GetHttpMethodExecutor extends HttpMethodExecutor<JsonObject>
{

  /** Constructs a new <code>GetHttpMethodExecutor</code> */
  public GetHttpMethodExecutor(int timeout, @NotNull URL link)
  {
    super("GET", timeout, link);
  }
  
  /** {@inheritDoc} */
  @Override
  public JsonObject execute() throws HttpRequestFailException
  {
    URLConnection connection = null;
    
    try
    {
      connection = (HttpURLConnection) link.openConnection();
      ((HttpURLConnection) connection).setRequestMethod(toString());
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setUseCaches(usesCache());
      connection.setReadTimeout(timeout());
      connection.setConnectTimeout(timeout());
      int responseCode = ((HttpURLConnection) connection).getResponseCode();
      
      if (considerError(responseCode, (HttpURLConnection) connection))
      {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String inputLine;
  
        while ((inputLine = bufferedReader.readLine()) != null)
          builder.append(inputLine);
  
        bufferedReader.close();
        return (JsonObject) JsonValue.parse(builder.toString());
      }
      return null;
    } catch (MalformedURLException error)
    {
      error.printStackTrace();
    } catch (ProtocolException error)
    {
      error.printStackTrace();
    } catch (IOException error)
    {
      if (error instanceof SocketTimeoutException)
        throw new HttpRequestFailException(RequestFail.TIMEOUT);
    } finally
    {
      if (connection != null)
        ((HttpURLConnection) connection).disconnect();
    }
    
    return null;
  }
}
