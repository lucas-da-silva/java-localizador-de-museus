package com.betrybe.museumfinder.service;

import static com.betrybe.museumfinder.util.CoordinateUtil.isCoordinateValid;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class MuseumService.
 */
@Service
public class MuseumService implements MuseumServiceInterface {

  private MuseumFakeDatabase database;

  @Autowired
  public MuseumService(MuseumFakeDatabase database) {
    this.database = database;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    if (!isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }

    Optional<Museum> museum = database.getClosestMuseum(coordinate, maxDistance);

    if (museum.isEmpty()) {
      throw new MuseumNotFoundException();
    }
    return museum.get();
  }

  @Override
  public Museum createMuseum(Museum museum) {
    if (!isCoordinateValid(museum.getCoordinate())) {
      throw new InvalidCoordinateException();
    }
    return database.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) throws MuseumNotFoundException {
    Optional<Museum> museum = database.getMuseum(id);
    if (museum.isEmpty()) {
      throw new MuseumNotFoundException();
    }
    return museum.get();
  }
}
