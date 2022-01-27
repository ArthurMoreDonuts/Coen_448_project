import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Robot implements RobotInterface {

    private int[][] grid;
    private int[] position;
    private state pen;
    private direction pointing;
    private enum direction {East, West, North, South}
    private enum state {Up, Down}
    private boolean isInitialized;

    public Robot (){
        grid = new int[1][1];
        pen = state.Up;
        position = new int[2];
        pointing = direction.North;
        isInitialized = false;
    }

    public void  initialize(int size){
        // TODO Check for valid size
        this.grid = new int[size][size];
        this.position[0] = size - 1;
        this.isInitialized = true;
    }
     public boolean  readCommand(String command){
        //TODO conditions
        String[] detailedCommand = command.split("");
        switch (detailedCommand[0]){
            case "P", "p" -> this.printGrid();
            case "Q", "q" -> {return false;}
            case "U", "u" -> this.setPenUp();
            case "D", "d" -> this.setPenDown();
            case "R", "r" -> this.turnRight();
            case "L", "l" -> this.turnLeft();
            case "C", "c" -> this.printRobot();
            case "I", "i" -> this.initialize(Integer.parseInt(detailedCommand[detailedCommand.length-1]));
            case "M", "m" -> this.moverRobot((Integer.parseInt(detailedCommand[detailedCommand.length-1])));
        }
    return true;
    }

    public boolean moverRobot(int steps){
        // TODO conditions
        if (this.getPen() == state.Down) {
            for (int i = 0; i <=steps; i++) {
                switch (this.pointing) {
                    case East -> grid[this.position[0]][this.position[1] + i] = 1;
                    case North -> grid[this.position[0] - i][this.position[1]] = 1;
                    case West -> grid[this.position[0]][this.position[1] - i] = 1;
                    case South -> grid[this.position[0] + i][this.position[1]] = 1;
                }
            }

        }
        switch (this.pointing) {
            case South -> this.position[0] += steps;
            case West -> this.position[1] -= steps;
            case North -> this.position[0] -= steps;
            case East -> this.position[1] += steps;
        }
        return true;

    }
    public void printRobot(){
        int size = this.grid.length;
        System.out.println("Position: "
                + Math.abs(this.position[0] -size+1) +", "
                + this.position[1]
                + " – Pen: " + this.getPen()
                + " - Facing: " + this.getPointing());
    }

    public void printGrid(){

        int size = this.grid.length;
        String LastLine ="  ";

        for (int row = 0; row < size; row ++){

            String Line="" + Math.abs(row-size+1)+ " ";
            LastLine += row + " ";

            for (int column = 0; column < size; column ++){
                if (grid[row][column] == 0)
                    Line += "  ";
                else
                    Line += "* ";
            }

            System.out.println(Line);
        }

        System.out.println(LastLine);
    }
    public static void main(String[] args){
        BufferedReader inFromConsole = new BufferedReader(new InputStreamReader(System.in));
   /* Robot robot = new Robot(9);
    robot.printGrid();
    robot.setPenDown();
    robot.printRobot();
    robot.moverRobot(5);
    robot.printGrid();
    robot.turnRight();
    robot.printRobot();
    robot.moverRobot(5);
    robot.printGrid();
    robot.turnRight();
    robot.printRobot();
    robot.moverRobot(5);
    robot.printGrid();
    robot.turnRight();
    robot.printRobot();
    robot.moverRobot(5);
    robot.printGrid();
    robot.printRobot();
    */
    Robot testRobot = new Robot();
    boolean run = true;
    String input="";
    while (run){
        System.out.print("Enter command :");
        try {
            input = inFromConsole.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        run = testRobot.readCommand(input);
    }
    }
    public int[] getPosition() {
        return position;
    }

    public state getPen() {
        return pen;
    }

    public void setPenUp() {
        this.pen = state.Up;
    }

    public void setPenDown() {
        this.pen = state.Down;
    }

    public direction getPointing() {
        return pointing;
    }

    public void setPointingEast() {
        this.pointing = direction.East;
    }

    public void setPointingWest() {
        this.pointing = direction.West;
    }

    public void setPointingNorth() {
        this.pointing = direction.North;
    }

    public void setPointingSouth() {
        this.pointing = direction.South;
    }

    public void turnRight(){
        switch (this.getPointing()) {
            case North -> {
                this.setPointingEast();
            }
            case East -> {
                this.setPointingSouth();
            }
            case West -> {
                this.setPointingNorth();
            }
            case South -> {
                this.setPointingWest();
            }
        }
    }

    public void turnLeft(){
        switch (this.getPointing()) {
            case North -> {
                this.setPointingWest();
            }
            case East -> {
                this.setPointingNorth();
            }
            case West -> {
                this.setPointingSouth();
            }
            case South -> {
                this.setPointingEast();
            }
        }
    }

}
