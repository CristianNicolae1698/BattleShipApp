package BattleShipClassLibrary.Models;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfoModel {

    public String UserName;
    private List<GridSpotModel> ShipLocations=new ArrayList<GridSpotModel>();
    private List<GridSpotModel> ShotGrid=new ArrayList<GridSpotModel>();


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public List<GridSpotModel> getShipLocations() {
        return ShipLocations;
    }

    public void setShipLocations(List<GridSpotModel> shipLocations) {
        ShipLocations = shipLocations;
    }

    public List<GridSpotModel> getShotGrid() {
        return ShotGrid;
    }

    public void setShotGrid(List<GridSpotModel> shotGrid) {
        ShotGrid = shotGrid;
    }
}
