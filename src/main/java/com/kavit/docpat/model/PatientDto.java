package com.kavit.docpat.model;

import java.io.Serializable;

import com.kavit.docpat.hibernate.pojo.Doctor;

public class PatientDto implements Serializable{
	
	/**
	 * serialVersionUID = 5467158754612371676L;

	 */
	private static final long serialVersionUID = 5467158754612371676L;

	private Integer patId;
	
	private String patName;
	
	private Integer docId;
	private String docName;
	private String spl;
	
	
	//setter and getter
	
	public Integer getPatId() {
		return patId;
	}
	public void setPatId(Integer patId) {
		this.patId = patId;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}

	public Integer getDocId() {
		return docId;
	}
	public void setDoctorId(Integer docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getSpl() {
		return spl;
	}
	public void setSpl(String spl) {
		this.spl = spl;
	}

	
	
	
	
	
	
}
