package com.kuber.moneymachine.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse<T> {
  private boolean success;
  private String error;
  private T data;

  public static <T> GenericResponse<T> getFailureResponse(String error){
    return new GenericResponse<>(false, error,  (T) null);
  }

  public static <T> GenericResponse<T> getSuccessResponse(T data){
    return new GenericResponse<>(true, null,  data);
  }

}
