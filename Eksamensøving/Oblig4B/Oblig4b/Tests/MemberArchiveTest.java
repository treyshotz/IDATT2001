import org.junit.Test;

import static org.junit.Assert.*;

public class MemberArchiveTest {
	
	@Test
	public void testInvalidParametersNewMember() {
		try {
			MemberArchive memberArchive = new MemberArchive();
			memberArchive.newMember(null, null);
		} catch (IllegalArgumentException e) {
			//YE BOIIIIIIIIIIII
		}
	}
	
}