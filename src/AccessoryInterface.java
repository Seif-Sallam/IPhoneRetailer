import java.util.ArrayList;

// An interface for all the IPhones to handle their relation with the Accessories
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
