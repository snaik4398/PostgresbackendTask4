package com.Sanjay.Service;

import java.util.List;

import com.Sanjay.model.Patient;

public interface PatService {

	int createPat(Patient pat);

	List<Patient> getAllPatient();


	List<Patient> searchingOperation(String input);

	int update(Patient patient,String id);

}