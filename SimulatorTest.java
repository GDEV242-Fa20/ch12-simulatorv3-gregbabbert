

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SimulatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SimulatorTest
{
    /**
     * Default constructor for test class SimulatorTest
     */
    public SimulatorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void simulate()
    {
        Simulator simulato1 = new Simulator();
        simulato1.simulate(500);
    }

    @Test
    public void setOne()
    {
        Simulator simulato1 = new Simulator();
        simulato1.simulateOneStep();
    }

    @Test
    public void simulateLong()
    {
        Simulator simulato1 = new Simulator();
        simulato1.runLongSimulation();
    }
}



