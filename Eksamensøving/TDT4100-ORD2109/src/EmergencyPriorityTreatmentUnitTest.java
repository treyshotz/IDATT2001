import org.junit.Test;

import javax.print.Doc;

import static org.junit.Assert.*;

public class EmergencyPriorityTreatmentUnitTest {
	
	@Test
	public void testEmergencyPriority() {
		Patient patient1 = new Patient();
		Patient patient2 = new Patient();
		Patient criticalPatient = new Patient();
		patient1.addConditions("flu", "cough");
		patient2.addConditions("aids");
		criticalPatient.addConditions("COVID-19", "flu", "cough", "fever");
		patient1.setPriority(2);
		patient2.setPriority(6);
		criticalPatient.setPriority(9);
		
		Doctor doctor1 = new Doctor("aids");
		Doctor doctor2 = new Doctor("flu");
		Doctor bestDoctor = new Doctor("COVID-19", "flu", "cough");
		doctor1.setPatient(patient1);
		bestDoctor.setPatient(patient2);
		
		EmergencyPriorityTreatmentUnit emergencyPriorityTreatmentUnit = new EmergencyPriorityTreatmentUnit();
		emergencyPriorityTreatmentUnit.addDoctor(doctor1);
		emergencyPriorityTreatmentUnit.addDoctor(doctor2);
		emergencyPriorityTreatmentUnit.addDoctor(bestDoctor);
		emergencyPriorityTreatmentUnit.addPatient(patient1);
		emergencyPriorityTreatmentUnit.addPatient(patient2);
		emergencyPriorityTreatmentUnit.addPatient(criticalPatient);
		emergencyPriorityTreatmentUnit.startTreatment(criticalPatient);
		assertEquals(criticalPatient, bestDoctor.getPatient());
	}
}