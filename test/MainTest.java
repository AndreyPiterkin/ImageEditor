import org.junit.Test;

/**
 * Tests for the Main.java class, including script reading and command line arguments.
 * NOTE: we don't test main with no arguments since that is interactive mode.
 */
public class MainTest {

  @Test(expected = IllegalArgumentException.class)
  public void testMainInvalidScript() {
    Main.main(new String[]{"-file", "test/invalidScript.txt"});
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMainInvalidArg() {
    Main.main(new String[]{"-invalid", "test/testScript.txt"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMainInvalidNumArgs() {
    Main.main(new String[]{"-file", "test/testScript.txt", "invalid"});
  }

}
