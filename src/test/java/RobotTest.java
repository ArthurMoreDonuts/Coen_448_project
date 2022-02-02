import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    public static final String NORTH = "North";
    public static final String SOUTH = "South";
    public static final String EAST = "East";
    public static final String WEST = "West";
    private Robot robot;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void initializeGridWhenSizeLessThanOneTest() {
        robot = new Robot();
        assertFalse(robot.initializeGrid(1));
    }

    @Test
    void initializeGridWhenSizeGreaterThanOneTest() {
        robot = new Robot();
        assertTrue(robot.initializeGrid(2));
        assertEquals(1, robot.getPosition()[0]);
    }

    @Test
    void moveRobotForZeroStepsTest() {
        robot = new Robot();
        assertFalse(robot.moveRobot(0));
    }

    @Test
    void moveRobotSouthExceedingGridSizeTest() {
        robot = new Robot();
        robot.setPointingSouth();
        assertFalse(robot.moveRobot(2));
    }

    @Test
    void moveRobotWestExceedingGridSizeTest() {
        robot = new Robot();
        robot.setPointingWest();
        assertFalse(robot.moveRobot(2));
    }

    @Test
    void moveRobotNorthExceedingGridSizeTest() {
        robot = new Robot();
        robot.setPointingNorth();
        assertFalse(robot.moveRobot(2));
    }

    @Test
    void moveRobotEastExceedingGridSizeTest() {
        robot = new Robot();
        robot.setPointingEast();
        assertFalse(robot.moveRobot(2));
    }

    @Test
    void turnLeftFromNorthTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.turnLeft();
        assertEquals(WEST, String.valueOf(robot.getPointing()));
    }

    @Test
    void turnRightFromNorthTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.turnRight();
        assertEquals(EAST, String.valueOf(robot.getPointing()));
    }

    @Test
    void turnLeftFromEastTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPointingEast();
        robot.turnLeft();
        assertEquals(NORTH, String.valueOf(robot.getPointing()));
    }

    @Test
    void turnRightFromEastTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPointingEast();
        robot.turnRight();
        assertEquals(SOUTH, String.valueOf(robot.getPointing()));
    }

    @Test
    void turnLeftFromSouthTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPointingSouth();
        robot.turnLeft();
        assertEquals(EAST, String.valueOf(robot.getPointing()));
    }

    @Test
    void turnRightFromSouthTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPointingSouth();
        robot.turnRight();
        assertEquals(WEST, String.valueOf(robot.getPointing()));
    }

    @Test
    void turnLeftFromWestTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPointingWest();
        robot.turnLeft();
        assertEquals(SOUTH, String.valueOf(robot.getPointing()));
    }

    @Test
    void turnRightFromWestTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPointingWest();
        robot.turnRight();
        assertEquals(NORTH, String.valueOf(robot.getPointing()));
    }

    @Test
    void moveRobotForwardPointingNorthTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.moveRobot(2));
        assertEquals(1, robot.getPosition()[0]);
        assertEquals(0, robot.getPosition()[1]);
    }

    @Test
    void moveRobotForwardPointingEastTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPointingEast();
        assertTrue(robot.moveRobot(2));
        assertEquals(3, robot.getPosition()[0]);
        assertEquals(2, robot.getPosition()[1]);
    }

    @Test
    void moveRobotForwardPointingSouthTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        assertEquals(3, robot.getPosition()[0]);
        assertEquals(2, robot.getPosition()[1]);
    }

    @Test
    void moveRobotForwardPointingWestTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        assertEquals(3, robot.getPosition()[0]);
        assertEquals(0, robot.getPosition()[1]);
    }

    @Test
    void moveRobotForwardPointingNorthPenDownTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPenDown();
        assertTrue(robot.moveRobot(2));
        assertEquals(1, robot.getPosition()[0]);
        assertEquals(0, robot.getPosition()[1]);
    }

    @Test
    void moveRobotForwardPointingEastPenDownTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPenDown();
        robot.setPointingEast();
        assertTrue(robot.moveRobot(2));
        assertEquals(3, robot.getPosition()[0]);
        assertEquals(2, robot.getPosition()[1]);
    }

    @Test
    void moveRobotForwardPointingSouthPenDownTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPenDown();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        assertEquals(3, robot.getPosition()[0]);
        assertEquals(2, robot.getPosition()[1]);
    }

    @Test
    void moveRobotForwardPointingWestPenDownTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPenDown();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        assertEquals(3, robot.getPosition()[0]);
        assertEquals(0, robot.getPosition()[1]);
    }

    @Test
    void printRobotTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPenDown();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.printRobot();
        assertEquals("Position: 0, 0 – Pen: Down - Facing: West"+System.lineSeparator(), outContent.toString());
    }

    @Test
    void printGridTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        robot.setPenDown();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.turnRight();
        assertTrue(robot.moveRobot(2));
        robot.printGrid();
        assertEquals("3         " + System.lineSeparator() +
                "2 * * *   " + System.lineSeparator() +
                "1 *   *   " + System.lineSeparator() +
                "0 * * *   " + System.lineSeparator() +
                "  0 1 2 3 " + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readEmptyCommandTest() {
        robot = new Robot();
        assertTrue(robot.readCommand(""));
        assertEquals("Please enter a command, press h or H for help!" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readCommandWithoutInitializingTest() {
        robot = new Robot();
        assertTrue(robot.readCommand("P"));
        assertEquals("Initialize the grid first, please!" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readCommandPTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("P"));
        assertEquals("3         " + System.lineSeparator() +
                "2         " + System.lineSeparator() +
                "1         " + System.lineSeparator() +
                "0         " + System.lineSeparator() +
                "  0 1 2 3 " + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readCommandCTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("C"));
        assertEquals("Position: 0, 0 – Pen: Up - Facing: North" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readCommandHTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("H"));
        assertEquals("Type \"I\" or \"i\" followed by a positive integer greater than 1 to initialize the grid" + System.lineSeparator() +
                "Type \"P\" or \"p\" to print the grid, but make sure the grid is already initialized" + System.lineSeparator() +
                "Type \"M\" or \"m\" followed by positive integer greater than zero to move the robot in the direction its facing, but make sure the grid is already initialized" + System.lineSeparator() +
                "Type \"R\" or \"r\" to turn the robot right" + System.lineSeparator() +
                "Type \"L\" or \"l\" to turn the robot left" + System.lineSeparator() +
                "Type \"U\" or \"u\" to lift the robot's pen up" + System.lineSeparator() +
                "Type \"D\" or \"d\" to lower the robot's pen down" + System.lineSeparator() +
                "Type \"C\" or \"c\" to print the robot details" + System.lineSeparator() +
                "Type \"Q\" or \"q\" to quit the program" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readCommandQTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertFalse(robot.readCommand("Q"));
    }

    @Test
    void readCommandUTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("U"));
        assertEquals("Up", String.valueOf(robot.getPen()));
    }

    @Test
    void readCommandDTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("D"));
        assertEquals("Down", String.valueOf(robot.getPen()));
    }

    @Test
    void readCommandRTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("R"));
        assertEquals(EAST, String.valueOf(robot.getPointing()));
    }

    @Test
    void readCommandLTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("L"));
        assertEquals(WEST, String.valueOf(robot.getPointing()));
    }

    @Test
    void readCommandInitWithInvalidValueTest() {
        robot = new Robot();
        assertTrue(robot.readCommand("I1"));
        assertEquals("Invalid Size! Make sure the size is greater than 1" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readCommandMoveWithoutInitTest() {
        robot = new Robot();
        assertTrue(robot.readCommand("M1"));
        assertEquals("Initialize the grid first, please!" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readCommandMoveOutOfGridTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("M5"));
        assertEquals("Invalid move! Make sure the move is within the grid" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readInvalidCommandTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("M"));
        assertEquals("Invalid command, press h or H for help!" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readInvalidCommandWithValueTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("J5"));
        assertEquals("Invalid command, press h or H for help!" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void readInvalidCommandMultipleCommandsTest() {
        robot = new Robot();
        robot.initializeGrid(4);
        assertTrue(robot.readCommand("JM"));
        assertEquals("Invalid command, press h or H for help!" + System.lineSeparator(), outContent.toString());
    }
}
