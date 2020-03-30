import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BonusMemberTest {

    private Personals ole;
    private LocalDate localDate;

    @BeforeEach
    void setUp() {
        ole = new Personals("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        localDate = LocalDate.of(2012, 10 , 20);
    }

    @Test
    void invalidInputToConstructor() {
        try {
            BonusMember bonusMember = new SilverMember(123, null, localDate , 0);
            fail();
        }
        catch (IllegalArgumentException e) {
            //Test is a success as you should not be able to create user with null as a parameter
        }
    }

    @Test
    void invalidPasswordThrowsIllegalArgument() {
        try {
            BonusMember bonusMember = new SilverMember(123, ole, localDate, 0);
            bonusMember.okPassword("");
            fail();
        } catch (IllegalArgumentException e ) {
            //Test is a success
        }
    }

    @Test
    void negativePointsThrowIllegalArgument() {
        try {
            BonusMember bonusMember = new SilverMember(123, ole, localDate, 0);
            bonusMember.registerPoints(-10);
            fail();
        }
        catch (IllegalArgumentException e) {
            //Test is a success
        }
    }

}