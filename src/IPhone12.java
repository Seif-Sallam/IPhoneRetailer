import java.util.ArrayList;
import java.util.HashMap;

// An IPhone12 inherits from IPhone and defines the inherited abstract methods.
public class IPhone12 extends IPhone {
    static HashMap<Color, Integer> casesStock;
    static int chargerStock;
    static final int chargerPrice = 100;
    static final int casePrice = 40;
    // Initializes the data of the accessory stock and defines the iphone type data.
    IPhone12(Storage modelCapacity, Color color, int price)
    {
        super(IPhoneType.IPHONE_12, modelCapacity, color, price);
        if (casesStock == null )
        {
            casesStock = new HashMap<>();
            casesStock.put(Color.Black, 3);
            casesStock.put(Color.White, 4);
            chargerStock = 3;
        }
    }
    // We only buy a case if we have its color
    @Override
    public int buyCase(Color clr) {
        if (casesStock.containsKey(clr))
        {
            if (casesStock.get(clr) > 0)
            {
                int count = casesStock.get(clr);
                casesStock.put(clr, count - 1);
                return casePrice;
            }
            return 0;
        }
        return 0;
    }

    // Airpods are not supported
    @Override
    public int buyAirpods() {
        return 0;
    }

    // We only buy a charger if we have any in the stock
    @Override
    public int buyCharger() {
        if (chargerStock <= 0)
            return 0;

        chargerStock--;
        return chargerPrice;
    }

    // Prints the available stock of accessories
    @Override
    public void printAvailableAcc() {
        System.out.println("Available Accessories for *IPhone 12*");
        System.out.printf("%-20s (%d$) (%s)\n", "MagSafeCharger", chargerPrice, chargerStock == 0? "Out of Stock" : Integer.toString(chargerStock));
        System.out.printf("Cases: %d$ each\n", casePrice);
        for (var cases : casesStock.entrySet())
            System.out.printf("- %-20s (%s)\n", cases.getKey(), cases.getValue() == 0? "Out of Stock" : cases.getValue());
    }

    // Adding a case to the stock (can be of any color).
    @Override
    public void addCase(Color clr) {
        if (casesStock.containsKey(clr))
        {
            int count = casesStock.get(clr);
            casesStock.put(clr, count + 1);
        }
        else {
            casesStock.put(clr, 1);
        }
    }

    // We do not support the airpods
    @Override
    public void addAirpods() {}

    // We add to the charger stock.
    @Override
    public void addCharger() {
        chargerStock++;
    }

    // Returns the available case color
    @Override
    public ArrayList<Color> getCaseColors() {
        return new ArrayList<>(casesStock.keySet());
    }
}
