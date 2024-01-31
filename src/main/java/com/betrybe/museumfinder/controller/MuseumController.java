package com.betrybe.museumfinder.controller;

import static com.betrybe.museumfinder.util.ModelDtoConverter.dtoToModel;
import static com.betrybe.museumfinder.util.ModelDtoConverter.modelToDto;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MuseumController class define routes for "/museums".
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {

  private MuseumServiceInterface service;

  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<MuseumDto> createMuseum(@RequestBody MuseumCreationDto museumRequisition) {
    Museum museumCreated = service.createMuseum(dtoToModel(museumRequisition));
    return ResponseEntity.status(HttpStatus.CREATED).body(modelToDto(museumCreated));
  }

  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam(name = "lat") Double latitude,
      @RequestParam(name = "lng") Double longitude,
      @RequestParam(name = "max_dist_km") Double maxDistance) {
    Museum museum = service.getClosestMuseum(new Coordinate(latitude, longitude), maxDistance);
    return ResponseEntity.ok(modelToDto(museum));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MuseumDto> getMuseumById(@PathVariable long id) {
    Museum museum = service.getMuseum(id);
    return ResponseEntity.ok(modelToDto(museum));
  }
}
