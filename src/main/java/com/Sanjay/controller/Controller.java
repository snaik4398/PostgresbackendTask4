package com.Sanjay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sanjay.Service.PatService;
import com.Sanjay.model.Patient;

@RestController
@RequestMapping("/patient")
public class Controller {

	@Autowired
	private PatService patientService;

	@GetMapping("/a")
	public String home() {
		return "hello";
	}

//	to add single patients
	@PostMapping("/add-patient")
	public ResponseEntity<?> addPat(@RequestBody Patient pat) {
		System.out.println(pat);
		return ResponseEntity.ok(this.patientService.createPat(pat));

	}

	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> getAll(Pageable pageable) {

		List<Patient> patients = this.patientService.getAllPatient();
		int start = (int) pageable.getOffset();
		int end = (int) ((start + pageable.getPageSize()) > patients.size() ? patients.size()
				: (start + pageable.getPageSize()));

		Page<Patient> pagePatient = new PageImpl<Patient>(patients.subList(start, end), pageable, patients.size());

		// List<Patient> patient = pagePatient.getContent();

		Map<String, Object> response = new HashMap<>();
		response.put("patients", patients);
		response.put("currentPage", pagePatient.getNumber());
		response.put("totalItems", pagePatient.getTotalElements());
		response.put("totalPages", pagePatient.getTotalPages());

		return ResponseEntity.ok(response);
	}

	@PutMapping("/update/{patid}")
	public ResponseEntity<Integer> updatePat(@RequestBody Patient pat, @PathVariable("patid") String id) {

		System.out.println("backend code api is calling  :");
		pat.setPatid(id);
		System.out.println("pass value to the Update function ");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		return ResponseEntity.ok().body(this.patientService.update(pat, id));
	}

	@GetMapping("/Search/{data}")
	public ResponseEntity<?> searchOperation(@PathVariable String data) {

		System.out.println(data);
		return ResponseEntity.ok().body(this.patientService.searchingOperation(data));

	}

}
