package com.kavit.docpat.patientdaoImpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kavit.docpat.hibernate.pojo.Patient;
import com.kavit.docpat.hibernate.util.HbernateUtil;
import com.kavit.docpat.patientdao.PatientDao;



@Repository
public class PatientDaoImpl implements Serializable,PatientDao{

	private static final long serialVersionUID = 6526356630273305607L;
	
	public boolean savePatient(Patient pat) {
			Session session=null;
			boolean isPatSaved=false;
			try {
				session=HbernateUtil.openSession();
				session.beginTransaction();
				if(pat.getId()==null) {
				session.save(pat.getDoctor());
				session.save(pat);
				}else {
					if(pat.getDoctor().getId()==null) {
						session.save(pat.getDoctor());
					}
					else {
						session.update(pat.getDoctor());
					}
					session.update(pat);
				}
			session.getTransaction().commit();
			isPatSaved=true;
		}catch(Exception ex) {
			session.getTransaction().rollback();
			
		}
		return isPatSaved;
	}
	
	@SuppressWarnings("unchecked")
	public List<Patient> pats() {
		
		List<Patient> patients=null;
		Session session=null;
		try{
			//inner join of criteria
			session=HbernateUtil.openSession();
			Criteria criteria = session.createCriteria(Patient.class, "PAT")
					.createAlias("PAT.doctor", "DOCTOR");
					
			patients =criteria.list();
			
			
			/*here both the approach is done above with criteria  and below with HQL
			 * only emp and address table is used one can add other tables as well
			 * 
			 * */
			
			/*session=HbernateUtil.openSession();
			
			String hql = "FROM Emp EMP "
					+ "JOIN FETCH  EMP.address ADD";
					
			Query query = session.createQuery(hql);
			
			employees = ((List<Emp>) query.list());
*/
		
		}catch(Exception e){
			patients=null;
			e.printStackTrace();
			}
		finally{
			session.close();
		}
		return patients;
		
	}
	
	public Patient getPatById(Integer patId) {
	
		Patient patient=null;
		Session session=null;
		
		try{
			
			session=HbernateUtil.openSession();
			Criteria criteria = session.createCriteria(Patient.class, "PAT")
					.createAlias("PAT.doctor", "DOCTOR");
			criteria.add(Restrictions.eq("PAT.id",patId));
					
			patient =(Patient)criteria.list().get(0);
					
		}catch(Exception e){
			patient=null;
			e.printStackTrace();
			}
		finally{
			session.close();
		}
		return patient;
		
	}
}
