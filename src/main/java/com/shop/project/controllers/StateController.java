package com.shop.project.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shop.project.dto.CityDTO;
import com.shop.project.dto.StateDTO;
import com.shop.project.services.CityService;
import com.shop.project.services.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateController {

	@Autowired
	private StateService service;

	@Autowired
	private CityService cityService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll() {

		List<StateDTO> listState = service.findAll().stream().map(x -> new StateDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listState);
	}

	@RequestMapping(value = "/{stateId}/cities", method = RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable("stateId") Integer stateId) {
		List<CityDTO> listCities = cityService.findByState(stateId).stream().map(x -> new CityDTO(x))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listCities);

	}
}
