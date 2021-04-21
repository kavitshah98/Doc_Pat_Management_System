package com.kavit.docpat.patientdao;

import java.io.Serializable;
import java.util.List;


import com.kavit.docpat.hibernate.pojo.Patient;

public interface PatientDao extends Serializable{

	public boolean savePatient(Patient pat);
	public List<Patient>pats();
	public Patient getPatById(Integer patId);
}