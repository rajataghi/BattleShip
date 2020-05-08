import java.util.Scanner;
import java.util.Random;


public class BattleShipsGame {


    public static void showBattleGrid(int[][] battleGrid)
    {   System.out.print("  ");
        for (int temp=0; temp<battleGrid.length;temp++)
            {
                System.out.print(temp);
            }
            System.out.println();
          for (int i=0;i<battleGrid.length;i++)
          {   System.out.print(i + "|");
              for (int j=0;j<battleGrid[i].length;j++)
              {
                  if(battleGrid[i][j]==1)
                      System.out.print("@");
                 // else if (battleGrid[i][j]==2) See the computer ships.
                  //    System.out.print("$");
                  else if (battleGrid[i][j]==-1)
                       System.out.print("x");
                  else if (battleGrid[i][j]==-2)
                      System.out.print("!");
                  else if (battleGrid[i][j]==-10)
                      System.out.print("-");
                  else
                      System.out.print(" ");


              }
              System.out.println("|" + i);
          }
        System.out.print("  ");
        for (int temp=0; temp<battleGrid.length;temp++)
        {
            System.out.print(temp);
        }
    System.out.println();
    }

    public static boolean isLocationOnGrid(int x, int y) {
        if (x<0 || x>9) {
            System.out.println("The x co-ordinate is not in the ocean!");
            return false;
        }
        if (y<0 || y>9){
            System.out.println("The y co-ordinate is not in the ocean!");
            return false;
        }
        return true;
    }

    public static boolean isLocationAvailable(int[][] battleGrid, int x, int y) {
        if (!isLocationOnGrid(x,y)) {
            return false;
        }
        else if (battleGrid[x][y]==1) {
            System.out.println("You already have a ship here!");
            return false;
        }

        else {
            //System.out.println("Location is available. Ship successfully placed!");
            return true;

        }
    }


    public static void setUserShips(int[][] battleGrid) {
        Scanner input = new Scanner(System.in);
        for (int i = 1; i <= 5; i++) {
            int x = 0;
            int y = 0;
           do {
               System.out.println("Enter X coordinate for ship " + i + ":");
               x = input.nextInt();
               System.out.println("Enter Y coordinate for ship " + i + ":");
               y = input.nextInt();
           } while(!isLocationAvailable(battleGrid, x, y));
        battleGrid[x][y] = 1;
            //showBattleGrid(battleGrid);
        }
        showBattleGrid(battleGrid);
    }

    public static void setComputerShips(int[][] battleGrid)
    {   System.out.println("Computer is deploying ships");
        Random rand = new Random();
        int rand1=0;
        int rand2=0;
        for (int i=1; i<=5; i++)
        {
            do {
                rand1 = rand.nextInt(10);
                rand2 = rand.nextInt(10);

            }  while(!isLocationAvailable(battleGrid,rand1,rand2));

            battleGrid[rand1][rand2] = 2;
            System.out.println(i + " Ship Deployed");
        }
        //showBattleGrid(battleGrid);
        System.out.println("***********************");
    }

    public static boolean wasLocationCheckedBefore(int[][] battleGrid, int x, int y, boolean userorcomputer){

        if (userorcomputer) {
            if (battleGrid[x][y] == -10 || battleGrid[x][y] == -1 || battleGrid[x][y]== -2) {
                System.out.println("These co-ordinates have already been guessed before or there is sunken ship here!");
                return true;
            }
        }
        else if (!userorcomputer) {
            if (battleGrid[x][y] == -20 || battleGrid[x][y] == -1 || battleGrid[x][y]== -2) {
                    System.out.println("Computer tried to hit coordinates that have been guessed before or have sunken ship!");
                    return true;
                }
            }
        else {
            System.out.println("Unknown User. Hacked!");
        }
        return false;
        }

    public static void inputAndCheckUserTurn(int[][] battleGrid,int[] ships) {
        Scanner input = new Scanner(System.in);
        int x = 0;
        int y = 0;

        System.out.println("YOUR TURN");
        do {
            System.out.println("Enter X coordinate:");
            x = input.nextInt();
            System.out.println("Enter Y coordinate:");
            y = input.nextInt();
        } while(!(isLocationOnGrid(x,y)) || (wasLocationCheckedBefore(battleGrid,x,y,true)));

            if (battleGrid[x][y] == 2) {
                battleGrid[x][y] = -2;
                ships[1]--;
                System.out.println("Boom! You sunk the ship!");
            }
            else if(battleGrid[x][y]==1) {
                battleGrid[x][y] = -1;
               ships[0]--;
                System.out.println("Oh no! You sunk your own ship :(");
        }
            else {
                battleGrid[x][y] = -10;
                System.out.println("You missed.");
            }
    }

    public static void inputAndCheckComputerTurn(int[][] battleGrid, int[] ships){
        Random rand = new Random();
        int rand1=0;
        int rand2=0;
        System.out.println("COMPUTER's TURN");
        do{
            rand1 = rand.nextInt(10);
            rand2 = rand.nextInt(10);
        }while(!isLocationOnGrid(rand1,rand2) || wasLocationCheckedBefore(battleGrid,rand1,rand2,false));

        if (battleGrid[rand1][rand2]==1){
            battleGrid[rand1][rand2] = -1;
            ships[0]--;
            System.out.println("The Computer sunk one of your ships!");

        }
        else if(battleGrid[rand1][rand2]==2){
            battleGrid[rand1][rand2] = -2;
            ships[1]--;
            System.out.println("The Computer sunk one of its own ships!");
        }
        else {
            battleGrid[rand1][rand2] = -20;
            System.out.println("Computer missed.");
        }
    }

    public static void playGame(int[][] battleGrid)
    {       int[] ships = {5,5};
        do {
            inputAndCheckUserTurn(battleGrid,ships);
            inputAndCheckComputerTurn(battleGrid,ships);
            showBattleGrid(battleGrid);
            System.out.println("Your Ships: " + ships[0]);
            System.out.println("Computer Ships: " + ships[1]);
        }while (ships[0]>0 && ships[1]>0);

        if (ships[1]<=0)
            System.out.println("Hooray! You win the battle :)");
        else System.out.println("Oh no! you lost the battle :(");

    }

    public static void main(String[] args){

        int[][] battleGrid = new int[10][10];
 /*       for (int row=0; row<battleGrid.length; row++)
        {
            for (int column=0;column<battleGrid[row].length; column++ )
            {
                battleGrid[row][column];
            }
        } */
        showBattleGrid(battleGrid);
        setUserShips(battleGrid);
        setComputerShips(battleGrid);
        System.out.println("It's time to battle!!");
        playGame(battleGrid);



    }
}
