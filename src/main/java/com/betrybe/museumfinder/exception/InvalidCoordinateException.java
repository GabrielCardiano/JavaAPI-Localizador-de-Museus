package com.betrybe.museumfinder.exception;

public class InvalidCoordinateException extends RuntimeException {

  public InvalidCoordinateException() {
    super("coordenadas inválidas");
  }
}
