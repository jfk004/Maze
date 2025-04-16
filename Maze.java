/*
 * Fahim Kalange 
 * helped by Daniel.V 
 * Friday March 1st 2024
 * A program that navigates a rectangular maze from starting position along the bottom row to an exit along the top row.
 */


package Maze_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {

    // Declares the Variables Start, Finish, North, South, East, West, Wall and Blank 
    public static final char START = 'S';
    public static final char FINISH = 'F';
    public static final char NORTH = '^';
    public static final char SOUTH = 'v';
    public static final char EAST = '>';
    public static final char WEST = '<';
    public static final char WALL = 'X';
    public static final char BLANK = ' ';

    // Declares the Variables mazeHeight, mazeWidth, start Column  and end Column
    public static int mazeHeight;
    public static int mazeWidth;
    public static int startCol;
    public static int endCol;

    public static void main(String[] args) {
        
        // Reads data from Maze.txt
        File mazeFile = new File("Maze.txt");

        // Try catch to read the maze file
        try {
            
            // Declares Scanner object
            Scanner scan = new Scanner(mazeFile);

            // Represents the dimension of the Maze
            // 20 7
            // 20 is width, 7 is height
            mazeWidth = scan.nextInt();
            mazeHeight = scan.nextInt();

            // Moves to the next line after reading the end column
            endCol = scan.nextInt();
            
            // skips over the zero
            scan.nextInt();

            // Moves to the next line after reading the start column
            startCol = scan.nextInt();

            // goes to start of the maze
            scan.nextLine();

            // initialize the maze char[][] array

            char[][] maze = new char[mazeHeight][mazeWidth];

            
            // For loops that iterate over the height and Width  of the Maze
            for (int height = 0; height < mazeHeight; height++) {
                String currentLine = scan.nextLine();               // Assigns the  next line into the String
                for (int width = 0; width < mazeWidth; width++) {
                    maze[height][width] = currentLine.charAt(width);       // Reads a character from the current line and assigns it to width 
                }
            }
            
            // assigns start & finish cells
            maze[0][endCol] = FINISH;
            maze[mazeHeight - 1][startCol] = START;

            display(maze);      // Display method the initialize the start of the maze 
            System.out.println();

            move(maze, mazeHeight - 1, startCol);

            
            scan.close();       // Closes scanner
        } 
        // Catch block that catches the FilenotFoundException
        catch (FileNotFoundException e) {
            System.out.println(e);  // prints out the exception
            System.exit(1); // Ends the program with an IO Error
        }
    }

    // Display Method that prints the state of the maze 
    public static void display(char[][] mazeToPrint) {
        System.out.println();

        // for loop that iterates over the height and width in  the Maze
        for (int height = 0; height < mazeHeight; height++) {
            for (int width = 0; width < mazeWidth; width++) {
                System.out.print(mazeToPrint[height][width]);       // prints out the height and width
            }
            System.out.println();
        }
    }

    // Method to check for the moveable blanks in the maze 
    public static boolean canMoveTo(char[][] maze, int row, int col) {
        
        // If loop to check if  row, col, Height and width are within the boundaries of the maze
        if (row > 0 && col > 0 && row < mazeHeight && col < mazeWidth) {     
            if (maze[row][col] == BLANK || maze[row][col] == FINISH)  {     // Checks if the position to move to is either valid or the finish point
                return true;
            }
        }

        return false;
    }

    // Move Method for movements within the maze
    public static void move(char[][] maze, int row, int col){
        // Base case to check if Finish has been reached 
        if(maze[row - 1][col] == FINISH){
            System.out.println("Solved Maze: "); // Prints out solved maze when the maze is complete 
            display(maze);
            return;
        }

        // Checks if there is a valid move in the directions North, West, East and South
        if(canMoveTo(maze, row - 1, col)){      
            maze[row-1][col] = NORTH;
            move(maze, row - 1 , col);     // recursiive method that calls North
        } 
        else if (canMoveTo(maze, row, col-1)){
            maze[row][col-1] = WEST;
            move(maze, row, col-1);     // Recursiive method that calls West
        }
        else if (canMoveTo(maze, row, col+1)){
            maze[row][col+1] = EAST;
            move(maze, row, col+1);     // Recursive method that calls East
        }
        else if (canMoveTo(maze, row + 1, col)){
            maze[row+1][col] = SOUTH;
            move(maze, row+1, col);     // Recursive method that calls South
        }

        
    }
}


    
