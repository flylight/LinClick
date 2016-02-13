package org.ar.linclick.dto;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by arymar on 10.12.15.
 */
public class HttpResponse<T>  implements Serializable {

  public static final ObjectMapper kk = new ObjectMapper();

  private int status;
  private T data;

  public HttpResponse(int status){
    this.status = status;
  }

  public HttpResponse(int status, T data){
    this(status);
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public T getData() {
    return data;
  }

  public String toJSON(){
    try {
      return kk.writeValueAsString(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "{\"error\":\"true\"}";
  }
}
