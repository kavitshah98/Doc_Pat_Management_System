package com.kavit.docpat.patientserviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kavit.docpat.hibernate.pojo.Patient;
import com.kavit.docpat.patientdaoImpl.PatientDaoImpl;
import com.kavit.docpat.patientservice.PatientService;

@Service
public class PatientServiceImpl implements Serializable, PatientService {

	private static final long serialVersionUID = -2933162031727467001L;
	
	@Autowired
	PatientDaoImpl patientDaoImpl;
	
	public boolean savePatient(Patient p) {
		boolean isPatSaved=false;
		isPatSaved=patientDaoImpl.savePatient(p);
		return isPatSaved;
	}

	public List<Patient> pats() {
		PatientDaoImpl patientDaoImpl=new PatientDaoImpl();
		return patientDaoImpl.pats();
	}

	public Patient getPatById(Integer patId) {
		return patientDaoImpl.getPatById(patId);
	}

}
