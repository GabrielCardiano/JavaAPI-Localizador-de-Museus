package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Museum service.
 */
@Service
public class MuseumService implements MuseumServiceInterface {

  MuseumFakeDatabase database;

  @Autowired
  public MuseumService(MuseumFakeDatabase database) {
    this.database = database;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    if (!CoordinateUtil.isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }

    return database.getClosestMuseum(coordinate, maxDistance)
        .orElseThrow(MuseumNotFoundException::new);
  }

  @Override
  public Museum createMuseum(Museum museum) {
    Coordinate coordinate = museum.getCoordinate();
    if (!CoordinateUtil.isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }

    Museum newMuseum = database.saveMuseum(museum);
    return newMuseum;
  }

  @Override
  public Museum getMuseum(Long id) {
    return database.getMuseum(id)
        .orElseThrow(MuseumNotFoundException::new);
  }
}
