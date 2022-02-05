

public interface RobotInterface {

    //define robot commands

    public boolean initializeGrid(int size);
    //R4 When the robot is initialized, the pen should be up and the robot should be facing north.
    // CONSTRUCTOR


    //R1 While the pen is down, the robot traces out shapes as it moves.
    //R2 while the pen is up, the robot moves about freely without writing anything.
    //R19 The robot position should be saved whenever the robot moves.
    public boolean moveRobot(int steps);

    //R14 The robot should not accept a movement command when the grid is not initialized
    //R15 The robot should not accept a movement command when the number of steps makes the robot exceed the grid border
    //R20 The robot should accept a set of robot commands that are defined as follows:
    //[U|u] Pen up
    //[D|d] Pen down
    //[R|r] Turn right
    //[L|l] Turn left
    //[M s|m s] Move forward s spaces (s is a non-negative integer)
    //[P|p] Print the N by N array and display the indices
    //[C|c] Print current position of the pen and whether it is up or down and its facing direction
    //[Q|q] Stop the program
    //[I n|i n] Initialize the system: The values of the array floor are zeros and the robot
    //is back to [0, 0], pen up and facing north. n size of the array, an integer greater than zero
    public boolean  readCommand(String command);
    public void printRobot();
    public void printGrid();
    public void turnRight();
    public void turnLeft();
}
