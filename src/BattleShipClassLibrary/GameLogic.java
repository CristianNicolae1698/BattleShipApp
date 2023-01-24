package BattleShipClassLibrary;

import BattleShipClassLibrary.Models.GridSpotModel;
import BattleShipClassLibrary.Models.GridSpotStatus;
import BattleShipClassLibrary.Models.PlayerInfoModel;

import java.util.ArrayList;
import java.util.List;
public class GameLogic {
    public static void InitializeGrid(PlayerInfoModel model){
        List<String> letters=new ArrayList<String>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");

        List<Integer> numbers=new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        for(String letter :  letters){
            for(int number: numbers){
                AddGridSpot(model, letter, number);
            }
        }

    }

    public static void AddGridSpot(PlayerInfoModel model, String letter, int number){
        GridSpotModel spot=new GridSpotModel();
        spot.setSpotLetter(letter);
        spot.setSpotNumber(number);
        spot.setStatus(GridSpotStatus.Empty);

        model.getShotGrid().add(spot);
    }

    public static boolean PlaceShip(PlayerInfoModel model, String location)
    {
        boolean output = false;

        String row = GetShotRow(location);
        int column=GetShotColumn(location);

        boolean isValidLocation = ValidateGridLocation(model, row, column);
        boolean isSpotOpen = ValidateShipLocation(model, row, column);

        if (isValidLocation && isSpotOpen) {
            model.getShipLocations();
            GridSpotModel gridSpotModel = new GridSpotModel();
            gridSpotModel.setSpotLetter(row);
            gridSpotModel.setSpotNumber(column);
            gridSpotModel.setStatus(GridSpotStatus.Ship);

            output = true;
            }
        return output;
    }

    private static boolean ValidateShipLocation(PlayerInfoModel model, String row, int column)
    {
        boolean isValidLocation=true;

        for (var ship : model.getShipLocations())
        {
            if (ship.getSpotLetter()==row.toUpperCase() && ship.getSpotNumber()==column)
            {
                isValidLocation = false;
            }
        }

        return isValidLocation;
    }


    public static boolean ValidateShot(PlayerInfoModel player, String row, int column)
    {
        boolean isValidShot = false;

        for (var gridSpot : player.getShotGrid() )
        {
            if (gridSpot.getSpotLetter() == row.toUpperCase() && gridSpot.getSpotNumber() == column)
            {
                if (gridSpot.getStatus() == GridSpotStatus.Empty)
                {
                    isValidShot = true;
                }
            }
        }

        return isValidShot;

    }


    private static boolean ValidateGridLocation(PlayerInfoModel model, String row, int column)
    {
        boolean isValidLocation = false;

        for (var ship : model.getShotGrid())
        {
            if (ship.getSpotLetter() == row.toUpperCase() && ship.getSpotNumber() == column)
            {
                isValidLocation = true;
            }
        }

        return isValidLocation;
    }

    public static int GetShotCount(PlayerInfoModel player)
    {
        int shotCount = 0;

        for (var shot : player.getShotGrid())
        {
            if (shot.getStatus() != GridSpotStatus.Empty)
            {
                shotCount += 1;

            }
        }

        return shotCount;

    }
    public static String GetShotRow(String shot)
    {
        String row = "";


        if (shot.length() != 2)
        {
            System.out.println("This was an invalid shot type.");
        }


        char[] shotArray = shot.toCharArray();

        row = Character.toString(shotArray[0]);


        return row;



    }

    public static Integer GetShotColumn(String shot)
    {

        int column = 0;

        if (shot.length() != 2)
        {
            System.out.println("This was an invalid shot type.");
        }


        char[] shotArray = shot.toCharArray();

        column = Character.getNumericValue(shotArray[1]);


        return column;
    }


    public static boolean PlayerStillActive(PlayerInfoModel player)
    {
        boolean isActive = false;

        for(var ship :player.getShipLocations())
        {
            if (ship.getStatus() != GridSpotStatus.Sunk)
            {
                isActive=true;
            }
        }


        return isActive;

    }

    public static boolean IdentifyShotResult(PlayerInfoModel opponent, String row, int column)
    {
        boolean isAHit = false;

        for (var ship : opponent.getShipLocations())
        {
            if (ship.getSpotLetter() == row.toUpperCase() && ship.getSpotNumber() == column)
            {
                isAHit = true;
                ship.setStatus(GridSpotStatus.Sunk);
            }

        }

        return isAHit;

    }

    public static void MarkShotResult(PlayerInfoModel player, String row, int column, boolean isAHit)
    {


        for (var gridSpot : player.getShotGrid())
        {
            if (gridSpot.getSpotLetter() == row.toUpperCase() && gridSpot.getSpotNumber() == column)
            {
                if (isAHit)
                {
                    gridSpot.setStatus(GridSpotStatus.Hit);;
                }
                else
                {
                    gridSpot.setStatus(GridSpotStatus.Miss);
                }
            }

        }
    }




}










