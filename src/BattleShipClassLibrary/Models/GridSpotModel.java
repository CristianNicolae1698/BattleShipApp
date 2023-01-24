package BattleShipClassLibrary.Models;

public class GridSpotModel {
    private String SpotLetter;
    private int SpotNumber;
    private GridSpotStatus Status=GridSpotStatus.Empty;

    public String getSpotLetter() {
        return SpotLetter;
    }

    public void setSpotLetter(String spotLetter) {
        SpotLetter = spotLetter;
    }

    public int getSpotNumber() {
        return SpotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        SpotNumber = spotNumber;
    }

    public GridSpotStatus getStatus() {
        return Status;
    }

    public void setStatus(GridSpotStatus status) {
        Status = status;
    }
}
