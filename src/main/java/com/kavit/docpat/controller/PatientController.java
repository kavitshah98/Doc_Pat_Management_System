
package com.kavit.docpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.kavit.docpat.hibernate.pojo.Doctor;
import com.kavit.docpat.hibernate.pojo.Patient;
import com.kavit.docpat.model.PatientDto;
import com.kavit.docpat.patientserviceImpl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {
	
	@Autowired
	PatientServiceImpl patientServiceImpl;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String welcome() {
		return "server is running";
	}
	
	@RequestMapping(value="/saveEmployee",method=RequestMethod.POST)
	public ResponseEntity<?>savePatient(@RequestBody PatientDto patientDto){
		
		Patient p = new Patient();
		Doctor d = new Doctor();
		boolean isPatSaved = false;
		
		try {
			p.setName(patientDto.getPatName());
			p.setId(patientDto.getPatId());
			d.setId(patientDto.getDocId());
			d.setName(patientDto.getDocName());
			d.setSpl(patientDto.getSpl());
			
			p.setDoctor(d);
			
			isPatSaved = patientServiceImpl.savePatient(p);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<String>("Patient Not saved",HttpStatus.EXPECTATION_FAILED);
		}
		if(isPatSaved)
			return new ResponseEntity<String>("Patient Saved",HttpStatus.CREATED);
		else {
			return new ResponseEntity<String>("Patient not saved",HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	@RequestMapping(value="/emps",method=RequestMethod.GET)
	public ResponseEntity<?> pats(){
		HttpStatus httpStatus = null;
		List<Patient>pats = null;
		List<PatientDto> patDtos = new ArrayList<PatientDto>();
		PatientServiceImpl patientServiceImpl = new PatientServiceImpl();
		
		try {
			pats = patientServiceImpl.pats();
			if(pats!=null) {
				httpStatus=HttpStatus.OK;
				for(Patient p:pats) {
					PatientDto pDto = new PatientDto();
					System.out.println(p.getName());
					pDto.setPatId(p.getId());
					pDto.setPatName(p.getName());
					pDto.setDoctorId(p.getDoctor().getId());
					pDto.setDocName(p.getDoctor().getName());
					pDto.setSpl(p.getDoctor().getSpl());
					
					patDtos.add(pDto);
				}
			}else if(pats.size()==0) {
				httpStatus=HttpStatus.EXPECTATION_FAILED;
				return new ResponseEntity<String>("No patients",httpStatus);
			}
		}catch(Exception ex) {
			httpStatus = HttpStatus.EXPECTATION_FAILED;
			return new ResponseEntity<String>("No patients",httpStatus);
		}
		return new ResponseEntity<List<PatientDto>>(patDtos,httpStatus);	
	}
	
	@RequestMapping(value="/getPatById",method=RequestMethod.GET)
	public ResponseEntity<?> getPatById(@RequestBody PatientDto patientDto){
		HttpStatus httpStatus = null;
		Patient pat=new Patient();
		PatientDto pDto=new PatientDto();
		
		try {
			pat = patientServiceImpl.getPatById(patientDto.getPatId());
			
			if(pat!=null) {
				httpStatus = HttpStatus.OK;
				pDto.setPatId(pat.getId());
				pDto.setPatName(pat.getName());
				pDto.setDoctorId(pat.getDoctor().getId());
				pDto.setDocName(pat.getDoctor().getName());
				pDto.setSpl(pat.getDoctor().getSpl());
		
			}
		}catch(Exception ex) {
			
			httpStatus = HttpStatus.EXPECTATION_FAILED;
			return new ResponseEntity<String>("No Patient",httpStatus);
		}
		System.out.println(httpStatus);
		return new ResponseEntity<PatientDto>(pDto,httpStatus);
		
	}
	
	@RequestMapping(value="/pat/{id}", method=RequestMethod.GET)
	public String printenteredid(@PathVariable("id") String patid){
		return "The entered ID in the URL is:"+patid;
	}
	
	
	@RequestMapping(value="/updatepatient/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updatePatient(@PathVariable("id") Integer patid, @RequestBody PatientDto patientDto){
		Patient p = new Patient();
		Doctor d = new Doctor();
		boolean isPatSaved = false;
		try {
			p = patientServiceImpl.getPatById(patid);
			System.out.print(p.getName()+" "+p.getId());
			p.setName(patientDto.getPatName());
			if(patientDto.getDocName()!=null) {
				d.setName(patientDto.getDocName());
				d.setSpl(patientDto.getSpl());
				p.setDoctor(d);
			}
			isPatSaved = patientServiceImpl.savePatient(p);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<String>("Patient could not be updated",HttpStatus.EXPECTATION_FAILED);
		}
		if(isPatSaved) {
			return new ResponseEntity<String>("Patient updated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Patient not updated",HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	
}
