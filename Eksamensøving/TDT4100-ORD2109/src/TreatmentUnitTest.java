import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Used to test TreatmentUnit
 */
public class TreatmentUnitTest {
	
	
	private TreatmentUnit treatmentUnit;
	
	
	@Before
	public void setUp() {
	}
	
	
	@Test
	public void testAddDoctorsPatient() {
		treatmentUnit = new TreatmentUnit();
		final Doctor doctor1 = new Doctor("flu"); // new doctor can treat "flu"
		final Doctor doctor2 = new Doctor("nosebleed", "pneumonia"); // new doctor can treat "noseblead" and "pneumonia"
		treatmentUnit.addDoctor(doctor1);
		treatmentUnit.addDoctor(doctor2);
		// Test that both doctors are available.
		assertTrue(doctor1.isAvailable());
		assertTrue(doctor2.isAvailable());
		
		final Patient patient = new Patient();
		patient.addConditions("flu", "nosebleed"); // patient has conditions "flu" and "noseblead"
		// 2e) start sequence diagram
		treatmentUnit.addPatient(patient);
		// Test that only one of the doctors are available:
		
		Doctor patientDoctor = treatmentUnit.getDoctor(patient);
		patientDoctor.treat(patient);
		treatmentUnit.treatmentFinished(patientDoctor);
		// 2e) end sequence diagram
		// Test that the previous doctor is available and that a
		// new doctor has been assigned to the patient.
  
		
		patientDoctor = treatmentUnit.getDoctor(patient);
		patientDoctor.treat(patient);
		treatmentUnit.treatmentFinished(patientDoctor);
		// Test that both doctors are available:
	}
}