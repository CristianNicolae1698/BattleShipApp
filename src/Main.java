import BattleShipClassLibrary.GameLogic;
import BattleShipClassLibrary.Models.GridSpotStatus;
import BattleShipClassLibrary.Models.PlayerInfoModel;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class Main {
    public static void main(String[] args) {


        WelcomeMessage();

        PlayerInfoModel activePlayer=CreatePlayer("Player 1");
        PlayerInfoModel opponent=CreatePlayer("Player 2");
        PlayerInfoModel winner=null;


    }

    private static void IdentifyWinner(PlayerInfoModel winner)
    {
        System.out.println("Congratulations to" + winner.getUserName() + " for winning! ");
        System.out.println(winner.getUserName() + "took" + GameLogic.GetShotCount(winner) + "shots. ");
        ;
    }

    private static void WelcomeMessage()
    {
        System.out.println("Welcome to Battleship Lite");
        System.out.println("created by Cristian Nicolae");
        System.out.println();
    }
    private static void DisplayShotGrid(PlayerInfoModel activePlayer)
    {
        String currentRow = activePlayer.getShotGrid().get(0).getSpotLetter();


        for (var gridSpot : activePlayer.getShotGrid())
        {
            if (gridSpot.getSpotLetter() != currentRow)
            {
                System.out.println();
                currentRow = gridSpot.getSpotLetter();
            }


            if (gridSpot.getStatus() == GridSpotStatus.Empty)
            {
                System.out.println(gridSpot.getSpotLetter() + gridSpot.getSpotNumber());
            }
            else if(gridSpot.getStatus()==GridSpotStatus.Hit)
            {
                System.out.println(" X  ");

            }
            else if(gridSpot.getStatus()==GridSpotStatus.Miss)
            {
                System.out.println(" O  ");

            }
            else
            {
                System.out.println(" ?  ");
            }
        }

        System.out.println();
        System.out.println();

    }

    private static void RecordPlayerShot(PlayerInfoModel activePlayer, PlayerInfoModel opponent)
    {
        boolean isValidShot = false;
        String row = "";
        int column = 0;

        do
        {
            String shot= AskForShot(activePlayer);
            try
            {
                row = GameLogic.GetShotRow(shot);
                column=GameLogic.GetShotColumn(shot);
                isValidShot = GameLogic.ValidateShot(activePlayer, row, column);
            }
            catch (Exception ex)
            {


                isValidShot = false;
            }


            if (isValidShot == false)
            {
                System.out.println("Invalid Shot Location. Please try again.");
            }






        } while (isValidShot == false);

        // Determine shot results

        boolean isAHit = GameLogic.IdentifyShotResult(opponent, row, column);


        // Record results

        GameLogic.MarkShotResult(activePlayer, row,column, isAHit);

        DisplayShotresults(row, column, isAHit);


    }
    private static void DisplayShotresults(String row, int column, boolean isAHit)
    {
        if (isAHit)
        {
            System.out.println(row+column + " is a Hit! ");
        }
        else
        {

            System.out.println(row+column + " is a Miss! ");
        }

        System.out.println();
    }
    private static String AskForShot(PlayerInfoModel player)
    {
        System.out.println(player.getUserName() + ", Please enter your shot selection: ");
        String output = System.console().readLine();
        return output;
    }

    private static PlayerInfoModel CreatePlayer(String playerTitle)
    {
        PlayerInfoModel output = new PlayerInfoModel();
        System.out.println("Player information for " + playerTitle);
        // Ask the user for their name
        output.UserName = AskForUsersName();
        // Load up the shot grid

        GameLogic.InitializeGrid(output);

        // Ask the user for their 5 ship placements

        PlaceShips(output);

        // Clear
        System.out.print("\033[H\033[2J");

        return output;
    }

    private static String AskForUsersName()
    {
        System.out.println("What is your name? ");
        Console cnsl
                = System.console();
        String output=null;
        if(cnsl==null){
            System.out.println("No console available");
        }else {

            output = cnsl.readLine();
        }
        return output;
    }

    private static void PlaceShips(PlayerInfoModel model)
    {
        do
        {
            int number=model.getShipLocations().size()+1;
            System.out.println("Where do you want to place ship number" +number);
            String location=null;
            Console cnsl=null;
            try{
                cnsl=System.console();
                if(cnsl!=null){
                    location= cnsl.readLine();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            boolean isValidLocation = false;

            try
            {
                isValidLocation = GameLogic.PlaceShip(model, location);
            }
            catch (Exception ex)
            {

                System.out.println("Error: " + ex);
            }

            if (isValidLocation == false)
            {
                System.out.println("That was not a valid location. Please try again");
            }


        } while (model.getShipLocations().size() < 5);



    }

}
