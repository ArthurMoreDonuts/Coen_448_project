import java.util.Arrays;

public class Robot implements RobotInterface {

    private int[][] grid;
    private int[] position;
    private state pen;
    private direction pointing;
    private enum direction {East, West, North, South}
    private enum state {Up, Down}

    public Robot (int size){
        // TODO Check for valid size
        grid = new int[size][size];
        pen = state.Up;
        position = new int[2];
        position[0] = size - 1;
        pointing = direction.North;
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
    Robot robot = new Robot(9);
    robot.grid[0][0] = 1;
    robot.grid[1][1] = 1;
    robot.grid[2][2] = 1;
    robot.grid[3][3] = 1;
    robot.grid[4][4] = 1;
    robot.grid[4][0] = 1;
    robot.grid[0][4] = 1;
    robot.printGrid();
    robot.printRobot();
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
