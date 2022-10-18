import java.util.ArrayList;

public interface AccessoryInterface {
    int buyCase(Color clr);

    int buyAirpods();

    int buyCharger();

    void printAvailableAcc();

    void addCase(Color clr);

    void addAirpods();

    void addCharger();

    ArrayList<Color> getCaseColors();
}
