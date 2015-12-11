package org.ar.linclick.dto;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Created by arymar on 10.12.15.
 */
public class HttpResponse<T>  implements Serializable{

  private HttpStatus status;
  private T data;

  public HttpResponse(HttpStatus status){
    this.status = status;
  }

  public HttpResponse(HttpStatus status, T data){
    this(status);
    this.data = data;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public T getData() {
    return data;
  }

}
