package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type General controller advice.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Handle invalid coordinate exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(InvalidCoordinateException.class)
  public ResponseEntity<String> handleInvalidCoordinateException(
      InvalidCoordinateException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());

    // getMessage() busca menssagem de erro definidia na Exceção (InvalidCoordinateException)
  }

  /**
   * Handle museum not found exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(MuseumNotFoundException.class)
  public ResponseEntity<String> handleMuseumNotFoundException(
      MuseumNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }

  /**
   * Handle runtime exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }

  /**
   * Handle exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(
      Exception exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }

  /**
   * Handle throwable response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<String> handleThrowable(Throwable exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_GATEWAY)
        .body(exception.getMessage());
  }
}
