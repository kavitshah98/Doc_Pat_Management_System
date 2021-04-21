package com.kavit.docpat.patientservice;

import java.io.Serializable;
import java.util.List;

import com.kavit.docpat.hibernate.pojo.Patient;


public interface PatientService extends Serializable {
	public boolean savePatient(Patient p);
	public List<Patient>pats();
	public Patient getPatById(Integer patId);
}
