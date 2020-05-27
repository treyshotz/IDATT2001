import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityTreatmentUnitTest {
	
	@Test
	void testPriorityPatient() {
		PriorityTreatmentUnit priorityTreatmentUnit = new PriorityTreatmentUnit();
		Patient patient1 = new Patient();
		Patient patient2 = new Patient();
		patient1.addConditions("flu");
		patient2.addConditions("aids");
		patient1.setPriority(9);
		patient2.setPriority(2);
		
		priorityTreatmentUnit.addPatient(patient1);
		priorityTreatmentUnit.addPatient(patient2);
		
		Patient criticalPatient = priorityTreatmentUnit.getPriorityPatient().iterator().next();
		assertEquals(criticalPatient, patient1);
	}
}