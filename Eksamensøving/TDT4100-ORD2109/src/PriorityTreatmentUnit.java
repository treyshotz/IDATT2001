import javax.print.Doc;
import java.util.*;

public class PriorityTreatmentUnit extends TreatmentUnit {
	
	//A patient is rated on a scale from 0 - 10
	//0 being to least critical and 10 the most critical on how critical their illness is
	//The list is then sorted by how critical they are

	public Collection<Patient> getPriorityPatient() {
		Collection<Patient> patients = getWaitingPatients();
		ArrayList<Patient> patientArrayList = new ArrayList<>(patients);
		patientArrayList.sort(Comparator.comparing(Patient::getPriority).reversed());
		patients = patientArrayList;
		return patients;
	}
	
	@Override
	public boolean startTreatment(Doctor doctor) {
		Collection<Patient> patients = getPriorityPatient();
		for (Patient patient : patients) {
			if (doctor.canTreat(patient) > 0.0) {
				doctor.setPatient(patient);
				return true;
			}
		}
		return false;
	}
}
