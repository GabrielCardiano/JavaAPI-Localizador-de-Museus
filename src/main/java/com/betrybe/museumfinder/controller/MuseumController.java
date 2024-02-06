package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Museum controller.
 */
@RestController
@RequestMapping(path = "/museums")
public class MuseumController {

  private MuseumServiceInterface service;

  /**
   * Instantiates a new Museum controller.
   *
   * @param service the service
   */
  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   * Create museum response entity.
   *
   * @param museumDto the museum dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto museumDto) {
    Museum museum = ModelDtoConverter.dtoToModel(museumDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(service.createMuseum(museum));
  }

  /**
   * Gets closest museum.
   *
   * @param lat         the lat
   * @param lng         the lng
   * @param max_dist_km the max dist km
   * @return the closest museum
   */
  @GetMapping("/closest")
  public ResponseEntity<Museum> getClosestMuseum(@RequestParam String lat, String lng,
      String max_dist_km) {
    Double latitude = Double.parseDouble(lat);
    Double longitude = Double.parseDouble(lng);
    Double distanceInKm = Double.parseDouble(max_dist_km);

    Coordinate coordinate = new Coordinate(latitude, longitude);

    return ResponseEntity.status(HttpStatus.OK)
        .body(service.getClosestMuseum(coordinate, distanceInKm));
  }
}
