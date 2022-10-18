import java.util.ArrayList;
import java.util.HashMap;

public class IPhone13 extends IPhone {
    static HashMap<Color, Integer> casesStock;
    static int chargerStock;
    static int airpodsStock;
    static final int airPodsPrice = 150;
    static final int chargerPrice = 100;
    static final int casePrice = 50;
    IPhone13(Storage modelCapacity, Color color, int price)
    {
        super(IPhoneType.IPHONE_13, modelCapacity, color, price);
        if (casesStock == null )
        {
            casesStock = new HashMap<>();
            casesStock.put(Color.Yellow, 1);
            casesStock.put(Color.Green, 1);
            chargerStock = 2;
            airpodsStock = 5;
        }
    }

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

    @Override
    public int buyAirpods() {
        if (airpodsStock <= 0)
            return 0;
        airpodsStock--;
        return airPodsPrice;
    }

    @Override
    public int buyCharger()
    {
        if (chargerStock <= 0)
            return 0;
        chargerStock--;
        return chargerPrice;
    }

    @Override
    public void printAvailableAcc() {
        System.out.println("Available Accessories for *IPhone 13*");
        System.out.printf("%-20s (%d$) (%s)\n", "MagSafeCharger", chargerPrice, chargerStock == 0? "Out of Stock" : Integer.toString(chargerStock));
        System.out.printf("%-20s (%d$) (%s)\n", "Airpods", airPodsPrice, airpodsStock == 0? "Out of Stock" : Integer.toString(airpodsStock));
        System.out.printf("Cases: %d$ each\n", casePrice);
        for (var cases : casesStock.entrySet())
            System.out.printf("- %-20s (%s)\n", cases.getKey(), cases.getValue() == 0? "Out of Stock" : cases.getValue());
    }

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

    @Override
    public void addAirpods() {
        airpodsStock++;
    }

    @Override
    public void addCharger() {
        chargerStock++;
    }

    @Override
    public ArrayList<Color> getCaseColors() {
        return new ArrayList<>(casesStock.keySet());
    }
}
