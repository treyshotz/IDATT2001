import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A class for managing a set of doctors and the patients they're treating.
 * When doctors or patients arrive, it is made sure that patients are treated as soon as possible.
 */
public class TreatmentUnit {
	
	
	// Internal variables go here: // 1b
	private ArrayList<Doctor> doctors;
	private ArrayList<Patient> patients;
	
	public TreatmentUnit() {
		this.doctors = new ArrayList<>();
		this.patients = new ArrayList<>();
	}
	
	/**
	 * Adds a doctor and makes sure s/he starts treating a patient, if one is waiting.
	 *
	 * @param doctor
	 */
	public void addDoctor(final Doctor doctor) {  // 1b
		doctors.add(doctor);
		startTreatment(doctor);
	}
	
	public Collection<Doctor> getAllDoctors() {
		return doctors;
	}
	
	
	/**
	 * @return the currently available doctors
	 */
	public Collection<Doctor> getAvailableDoctors() {  // 1b
		ArrayList<Doctor> available = new ArrayList<>();
		for (Doctor doctor : doctors) {
			if (doctor.isAvailable())
				available.add(doctor);
		}
		return available;
	}
	
	
	/**
	 * Adds a patient to this treatment unit, and makes sure treatment starts if any doctor is available.
	 * Otherwise the patient is queued for treatment when a doctor becomes available.
	 *
	 * @param patient
	 */
	public void addPatient(final Patient patient) {  // 1b
		patients.add(patient);
		startTreatment(patient);
	}
	
	
	/**
	 * @param pred the predicate that the doctor must satisfy
	 * @return some doctor satisfying the predicate
	 */
	public Doctor getDoctor(final Predicate<Doctor> pred) {  // 1b
		return doctors.stream().filter(pred).findFirst().get();
	}
	
	
	/**
	 * Find the doctor, if any, that treats the provided patient.
	 *
	 * @param patient
	 * @return the doctor treating the provided patient, or null, of the patient isn't currently being treated
	 */
	public Doctor getDoctor(final Patient patient) {  // 1b
		for (Doctor doctor : doctors) {
			if (doctor.getPatient() == (patient))
				return doctor;
		}
		return null;
	}
	
	
	/**
	 * Find all patients that are not currently being treated.
	 *
	 * @return the patients not currently being treated.
	 */
	public Collection<Patient> getWaitingPatients() {  // 1b
		final Collection<Patient> result = new ArrayList<>();
		for (Patient patient : patients) {
			if (getDoctor(patient) == null)
				result.add(patient);
		}
		return result;
	}
	
	
	/**
	 * Finds a waiting patient and sets him/her as the provided doctor's patient.
	 *
	 * @param doctor the doctor for which a patient to treat should be found
	 * @return true if a patient for the provided doctor was found, false
	 * otherwise.
	 */
	public boolean startTreatment(final Doctor doctor) {
		Collection<Patient> waitingPatients = getWaitingPatients();
		for (Patient patient : waitingPatients) {
			if (doctor.canTreat(patient) > 0.0) {
				doctor.setPatient(patient);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Finds an available doctor for the provided patient, and sets that doctor to
	 * treat the patient.
	 *
	 * @param patient the patient for which a treating doctor should be found.
	 * @return true if a doctor for the provided patient was found, false
	 * otherwise.
	 */
	public boolean startTreatment(final Patient patient) {   // 1c
		Collection<Doctor> availableDoctors = getAvailableDoctors();
		
		if (patient == null || availableDoctors == null || availableDoctors.isEmpty()) {
			return false;
		}
		
		for (Doctor doctor : availableDoctors) {
			if (doctor.canTreat(patient) > 0) {
				//Gets the first doctor available
				doctor.setPatient(patient);
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Removes the link between doctor and patient, after treatment is finished.
	 * Since the patient is fully treated, s/he is removed from this treatment
	 * unit.
	 * Also ensure the doctor starts treating another patient.
	 *
	 * @param doctor the doctor that has finished treating his/her patient.
	 */
	public void treatmentFinished(final Doctor doctor) {  // 1c
		//A nullpointerexception will happen if doctor is null. To prevent i decided to use try catch
		try {
			Patient patient = doctor.getPatient();
			doctor.setPatient(null);
			if (patient.RequiresTreatment()) {
				startTreatment(patient);
			} else {
				patients.remove(patient);
			}
			startTreatment(doctor);
		} catch (NullPointerException e) {
			//This should be logged instead...
			e.printStackTrace();
		}
	}
}