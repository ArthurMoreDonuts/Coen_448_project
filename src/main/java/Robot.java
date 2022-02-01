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

    public boolean initializeGrid(int size){
        if (size <= 1)
            return false;
        this.grid = new int[size][size];
        this.position[0] = size - 1;
        this.isInitialized = true;
        return true;
    }
     public boolean  readCommand(String command){
        int InputValue;
        command = command.strip();//remove whitespace
        if (command.length() == 0)
            System.out.println("Please enter a command, press h or H for help!");
        else if (command.length() == 1){
            switch (command.substring(0,1)){
                case "P", "p" -> {
                    if (isInitialized) {
                        this.printGrid();
                    }
                    else{
                        System.out.println("Initialize the grid first, please!");
                    }
                }
                case "Q", "q" -> {return false;}
                case "U", "u" -> this.setPenUp();
                case "D", "d" -> this.setPenDown();
                case "R", "r" -> this.turnRight();
                case "L", "l" -> this.turnLeft();
                case "C", "c" -> this.printRobot();
                case "H", "h" -> {
                    System.out.println("Type \"I\" or \"i\" followed by a positive integer greater than 1 to initialize the grid");
                    System.out.println("Type \"P\" or \"p\" to print the grid, but make sure the grid is already initialized");
                    System.out.println("Type \"M\" or \"m\" followed by positive integer greater than zero to move the robot in the direction its facing, but make sure the grid is already initialized");
                    System.out.println("Type \"R\" or \"r\" to turn the robot right");
                    System.out.println("Type \"L\" or \"l\" to turn the robot left");
                    System.out.println("Type \"U\" or \"u\" to lift the robot's pen up");
                    System.out.println("Type \"D\" or \"d\" to lower the robot's pen down");
                    System.out.println("Type \"C\" or \"c\" to print the robot details");
                    System.out.println("Type \"Q\" or \"q\" to quit the program");
                }
                default -> System.out.println("Invalid command, press h or H for help!");
            }
        }
        else {
            if (command.substring(1).matches("[0-9]+")) {
                InputValue = Integer.parseInt(command.substring(1));
                switch (command.substring(0,1)) {
                    case "I", "i" -> {
                        if (!this.initializeGrid(InputValue))
                            System.out.println("Invalid Size! Make sure the size is greater than 1");
                    }
                    case "M", "m" -> {
                        if (isInitialized) {
                            if (!this.moveRobot(InputValue))
                                System.out.println("Invalid move! Make sure the move is within the grid");
                        }
                        else{
                            System.out.println("Initialize the grid first, please!");
                        }
                    }
                    default -> System.out.println("Invalid command, press h or H for help!");
                }
            }else {
                System.out.println("Invalid command, press h or H for help!");
            }


        }
    return true;
    }

    public boolean moveRobot(int steps){
        if (steps <= 0)
            return false;
        switch (this.pointing) {
            case South -> {
               if(this.position[0] + steps >= this.grid.length)
                   return false;
            }
            case West -> {
               if(this.position[1] - steps < 0)
                   return false;
            }
            case North -> {
                if(this.position[0] - steps < 0)
                    return false;
            }
            case East -> {
                if(this.position[1] + steps >= this.grid.length)
                    return false;
            }
        }
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
        System.out.println("# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #");
        System.out.println("#  *       **       *   *****   *       *****   *****   **       **   *****   #");
        System.out.println("#   *     *  *     *    *       *       *       *   *   * *     * *   *       #");
        System.out.println("#    *   *    *   *     ****    *       *       *   *   *  *   *  *   ****    #");
        System.out.println("#     * *      * *      *       *       *       *   *   *   * *   *   *       #");
        System.out.println("#      *        *       *****   *****   *****   *****   *    *    *   *****   #");
        System.out.println("# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #");


        BufferedReader inFromConsole = new BufferedReader(new InputStreamReader(System.in));
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
