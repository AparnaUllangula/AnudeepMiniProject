package BattleShipGameProject;
import java.util.Scanner;
public class BattleShiftGame {   // create class of BattleshiftGame
	public static  int rowsNumber=10;  
	public static int columNumber=10;
	public static int playerShips;
	public static int computerships;
	public static String[][] grid=new String[rowsNumber][columNumber]; // create 2D array of size 10X10
	public static int[][] missedGuesses=new int[rowsNumber][columNumber];// crate 2D array for missed guesses
	public static void main(String[] args) {
		System.out.println("welcome to Battele Ship Game");
		System.out.println("sea is empty");
		CreateOceanMap();   // call ocean map method 
		deployplayerShips(); // call deployplayerShips
		deployComputerShips(); // call deployComputerShips
		do {
			Battle(); // call battle method for start game 
		}
		while(BattleShiftGame.playerShips!=0 && BattleShiftGame.computerships !=0);
        gameOver(); // call game over method when ships of either player or computer 0
	}
public static void  CreateOceanMap() // creat eempty  ocean map 
{	
		System.out.print("  ");
		for(int i=0;i<columNumber;i++)
			System.out.print(i);
		System.out.println();
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				grid[i][j]=" ";
				if(j==0)
					System.out.print(i+"|"+grid[i][j]);
				else if(j==grid[i].length-1)
					System.out.print(grid[i][j]+"|"+i);
				else
					System.out.print(grid[i][j]);
			}
			System.out.print("  ");
			}
	}
		public static void deployplayerShips() 
		{
		Scanner input = new Scanner(System.in);
        System.out.println("\n deploy your ships:");
        BattleShiftGame.playerShips = 5;
        for (int i = 1; i <= BattleShiftGame.playerShips; ) {
            System.out.print("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + i + " ship: ");
            int y = input.nextInt();

            if((x >= 0 && x <rowsNumber) && (y >= 0 && y < columNumber) && (grid[x][y] == " "))
            {
                grid[x][y] =   "@";
                i++;
            }
            else if((x >= 0 && x < rowsNumber) && (y >= 0 && y < columNumber) && grid[x][y] == "@")
                System.out.println("You can not place two or more ships on the same location");
            else if((x < 0 || x >= rowsNumber) || (y < 0 || y >= columNumber))
                System.out.println("You can not  place ships outside the " + rowsNumber + " by " + columNumber + " grid");
        }
        printOceanMap();
    }
		public static void deployComputerShips(){
	        System.out.println("\n Computer is deploying ships");
	        BattleShiftGame.computerships = 5;
	        for (int i = 1; i <= BattleShiftGame.computerships; ) {
	            int x = (int)(Math.random() * 10);
	            int y = (int)(Math.random() * 10);
	            if((x >= 0 && x < rowsNumber) && (y >= 0 && y < columNumber) && (grid[x][y] == " "))
	            {
	                grid[x][y] =   "x";
	                System.out.println(i + ". ship DEPLOYED");
	                i++;
	            }
	        }
	        printOceanMap();
	    }	
		public static void Battle() {
			playerTurn();
			computerturn();
			printOceanMap();
		System.out.println();
		System.out.println("your ships: "+BattleShiftGame.playerShips +" | Computer ships :"+BattleShiftGame.computerships);
		System.out.println();
		}
		public static void playerTurn()
		{
	        System.out.println("\n yout turn");
	        int x = -1, y = -1;
	        do
	        {
	            Scanner input = new Scanner(System.in);
	            System.out.print("Enter X coordinate: ");
	            x = input.nextInt();
	            System.out.print("Enter Y coordinate: ");
	            y = input.nextInt();
	            if ((x >= 0 && x < rowsNumber) && (y >= 0 && y < columNumber)) 
	            {
	                if (grid[x][y] == "x") 
	                {
	                    System.out.println(" You sunk the ship!");
	                    grid[x][y] = "!"; 
	                    --BattleShiftGame.computerships;
	                }
	                else if (grid[x][y] == "@")
	                {
	                    System.out.println("you sunk your own ship :(");
	                    grid[x][y] = "x";
	                    --BattleShiftGame.playerShips;
	                    ++BattleShiftGame.computerships;
	                }
	                else if (grid[x][y] == " ") 
	                {
	                    System.out.println(" you missed");
	                    grid[x][y] = "-";
	                }
	            }
	            else if ((x < 0 || x >= rowsNumber) || (y < 0 || y >= columNumber))  
	                System.out.println("You can not place ships outside the " + rowsNumber + " by " + columNumber + " grid");
	        }while((x < 0 || x >= rowsNumber) || (y < 0 || y >= columNumber));  
	    }
 public static void computerturn() {
	 System.out.println("\n  turn to computer");
     int x = -1, y = -1;
     do {
         x = (int)(Math.random() * 10);
         y = (int)(Math.random() * 10);

         if ((x >= 0 && x < rowsNumber) && (y >= 0 && y < columNumber)) 
         {
             if (grid[x][y] == "@") 
             {
                 System.out.println("The computer sunk one of your ships");
                 grid[x][y] = "x";
                 --BattleShiftGame.playerShips;
                 ++BattleShiftGame.computerships;
             }
             else if (grid[x][y] == "x") {
                 System.out.println("The Computer sunk one of its own ships");
                 grid[x][y] = "!";
             }
             else if (grid[x][y] == " ") {
                 System.out.println("Computer missed");
                 if(missedGuesses[x][y] != 1)
                     missedGuesses[x][y] = 1;
             }
         }
     }while((x < 0 || x >= rowsNumber) || (y < 0 || y >= columNumber)); 
 }

 public static void gameOver(){
     System.out.println("Your ships: " + BattleShiftGame.playerShips + " | Computer ships: " + BattleShiftGame.computerships);
     if(BattleShiftGame.playerShips > 0 && BattleShiftGame.computerships <= 0)
         System.out.println(" You won the battle  Congratulations ");
     else
         System.out.println(" you lost the battle try again");
     System.out.println();
}

 public static void printOceanMap(){
     System.out.println();
     System.out.print("  ");
     for(int i = 0; i < columNumber; i++)
         System.out.print(i);
     System.out.println();
     for(int x = 0; x < grid.length; x++) {
         System.out.print(x + "|");

         for (int y = 0; y < grid[x].length; y++){
             System.out.print(grid[x][y]);
         }

         System.out.println("|" + x);
     }
     System.out.print("  ");
     for(int i = 0; i < columNumber; i++)
         System.out.print(i);
     System.out.println();
 }		
		}
		
	
	


