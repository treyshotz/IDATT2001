import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * A doctor has the capacity to treat one patient at a time.
 */
public class Doctor {
	// Internal variables go here:
	private Patient patient;
	private Collection<String> canTreatList;
	
	
	public Doctor(String... conditions) {
		this.canTreatList = Arrays.asList(conditions);
	}
	
	public Doctor(Collection<String> canTreatList) {
		this.canTreatList = canTreatList;
	}
	
	public double canTreat(Patient patient){
		Collection<String> diagnosises = patient.getConditions();
		double nr = 0;
		for (String d: diagnosises) {
			if (this.canTreatList.contains(d))
				nr++;
		}
		
		return nr/diagnosises.size();
	}
	
	public void treat(Patient patient) {
		patient.removeConditions(this.canTreatList);
	}
	
	public Collection<String> getCanTreatList() {
		return canTreatList;
	}
	
	public void setCanTreatList(Collection<String> canTreatList) {
		this.canTreatList = canTreatList;
	}
	
	/**
	 * @return the patient this doctor is treating, or null if s/he isn't currently treating any patient.
	 */
	public Patient getPatient() { // 1a
 		return patient;
	}
	
	
	/**
	 * @return true if this doctor is currently treating a patient, otherwise false.
	 */
	public boolean isAvailable() { // 1a
 		return getPatient() == null;
	}
	
	
	/**
	 * Sets the patient that this doctor is treating, use null to indicate s/he isn't currently treating any patient.
	 * @param patient
	 */
	public void setPatient(final Patient patient) { // 1a
 		this.patient = patient;
	}
}