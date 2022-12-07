package com.Sanjay.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.Sanjay.model.Address;
import com.Sanjay.model.Gender;
import com.Sanjay.model.Patient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class ServiceImplementation implements PatService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int createPat(Patient pat) {
		ObjectMapper mp = new ObjectMapper();
		Integer i = 0;
		try {

			String add = mp.writeValueAsString(pat.getAddress());
//		JsonNode jsonNodeRoot = ObjectMapper.readTree(add);
			String map = mp.writeValueAsString(pat.getExtentionmap());
			System.out.println("********" + add);
			System.out.println("1111********" + map);

			return jdbcTemplate.update(
					"INSERT INTO patient (patient_id,name,dob,contact_no,email_id,gender,address,status,extension) VALUES(?,?,?,?,?,?,?::jsonb,?,?::jsonb)",
					pat.getPatid(), pat.getName(), pat.getDob(), pat.getMobileno(), pat.getEmailId(),
					pat.getGender().toString(), add, pat.getStatus(), map);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 100;

	}
	
//	update of name email and contact number 
	@Override
	  public int update(Patient patient,String id) {
	    return jdbcTemplate.update("UPDATE patient SET name=?, contact_no=?, email_id=? WHERE patient_id=?",patient.getName(),patient.getMobileno(),patient.getEmailId(),id);
	  }
//getting all the patient 
	@Override
	public List<Patient> getAllPatient() {
		String getAllPatientQuery = "SELECT * FROM patient";

		List<Patient> patients = jdbcTemplate.query(getAllPatientQuery, new ResultSetExtractor<List<Patient>>() {

			@SuppressWarnings("unchecked")
			public List<Patient> extractData(ResultSet rs) throws SQLException, DataAccessException {

				ObjectMapper mapper = new ObjectMapper();

				List<Patient> list = new ArrayList<Patient>();
				while (rs.next()) {
					PGobject obj = rs.getObject("address", PGobject.class);
					String addressStr = obj.getValue();
					PGobject obj1 = rs.getObject("extension", PGobject.class);
					String extensionStr = obj1.getValue();
					String k=rs.getString("gender");

					Patient patient = new Patient();
					patient.setPatid(rs.getString("patient_id"));
					patient.setName(rs.getString("name"));
					patient.setDob(rs.getString("dob"));
					patient.setMobileno(getAllPatientQuery);
					patient.setEmailId(rs.getString("email_id"));
					patient.setGender(Gender.valueOf(k.toUpperCase()));

					try {
						patient.setAddress(mapper.readValue(addressStr, Address.class));
						patient.setExtensionmap((Map<String, String>) mapper.readValue(extensionStr, Map.class));
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					patient.setStatus(rs.getBoolean("status"));
					// patient.setExtension(rs.("extension"));
					list.add(patient);
				}
				return list;
			}
		});
		return patients;
	}

	
	
	
//searching  with contain type 
	@Override
	public List<Patient> searchingOperation(String input) {
		String getAllPatientQuery ="SELECT * FROM patient WHERE  name ILIKE '%"+input+"%' OR email_id  ILIKE '%"+input+"%' OR patient_id ILIKE '%"+input+"%';";

		List<Patient> patients = jdbcTemplate.query(getAllPatientQuery, new ResultSetExtractor<List<Patient>>() {

			@SuppressWarnings("unchecked")
			public List<Patient> extractData(ResultSet rs) throws SQLException, DataAccessException {

				ObjectMapper mapper = new ObjectMapper();

				List<Patient> list = new ArrayList<Patient>();
				while (rs.next()) {
					PGobject obj = rs.getObject("address", PGobject.class);
					String addressStr = obj.getValue();
					PGobject obj1 = rs.getObject("extension", PGobject.class);
					String extensionStr = obj1.getValue();
					String k=rs.getString("gender");

					Patient patient = new Patient();
					patient.setPatid(rs.getString("patient_id"));
					patient.setName(rs.getString("name"));
					patient.setDob(rs.getString("dob"));
					patient.setMobileno(getAllPatientQuery);
					patient.setEmailId(rs.getString("email_id"));
					patient.setGender(Gender.valueOf(k.toUpperCase()));

					try {
						patient.setAddress(mapper.readValue(addressStr, Address.class));
						patient.setExtensionmap((Map<String, String>) mapper.readValue(extensionStr, Map.class));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					patient.setStatus(rs.getBoolean("status"));
				
					list.add(patient);
				}
				return list;
			}
		});
		return patients;
	}
}