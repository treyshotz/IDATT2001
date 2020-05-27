import java.util.ArrayList;
import java.util.Collection;

public class EmergencyPriorityTreatmentUnit extends TreatmentUnit {
	
	@Override
	public boolean startTreatment(Patient patient) {
		//1. Må sortere etter alvorlighet.
		//2. Loope gjennom doktorer. Sjekke at at doktoren som sjekkes har en pasient med mindre alvorlighetsgrad
		//3. Hvis doktoren har pasient med mindre alvorlighet så må jeg sjekkke hvor god grad doktoren kan hjelpe
		//4. Sette den beste doktoren til den kritiske pasienten. Den gamle pasienten må da få en ny doktor
		//Denne metoden legger ikke opp til at den patient som allerede blir behandlet av en lege kan ha bedre kompabilitet med en annen
		//Fordi man kun sjekker pasienter som venter
		ArrayList<Doctor> doctors = new ArrayList<>(getAllDoctors());
		//Henter patienter sortert etter alvorlighetsgrad
		int patientPriority = patient.getPriority();
		
		double bestMatch = 0;
		Doctor bestDoctor = null;
		
		for (Doctor doctorList : doctors) {
			if (doctorList.getPatient() != null) {
				if (doctorList.getPatient().getPriority() < patientPriority) {
					if (doctorList.canTreat(patient) > bestMatch) {
						bestMatch = doctorList.canTreat(patient);
						bestDoctor = doctorList;
					}
				}
			}
		}
		Patient oldPatient = null;
		if (bestDoctor == null) {
			return false;
		}
		if (bestDoctor.getPatient() != null) {
			oldPatient = bestDoctor.getPatient();
		}
		bestDoctor.setPatient(patient);
		//If the best doctor is available the patient is null
		if (oldPatient != null) {
			return startTreatment(oldPatient);
		}
		return false;
	}
}
