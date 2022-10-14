import java.util.Objects;

abstract public class IPhone
{
    protected String modelName;
    protected Storage modelCapacity;
    protected Color modelColor;

    protected int price;

    protected int id;

    IPhone(String modelName, Storage modelCapacity, Color modelColor, int price)
    {
        setModelName(modelName);
        setModelCapacity(modelCapacity);
        setModelColor(modelColor);
        setPrice(price);

        id = IDGenerator.generate();
    }

    protected void setModelColor(Color modelColor) {
        this.modelColor = modelColor;
    }

    protected void setModelCapacity(Storage modelCapacity) {
        this.modelCapacity = modelCapacity;
    }

    protected void setModelName(String modelName) {
        this.modelName = modelName;
    }

    protected void setPrice(int price)
    {
        this.price = price;
    }

    public int getPrice()
    {
        return price;
    }

    public int getId()
    {
        return id;
    }

    public String getModelName()
    {
        return  modelName;
    }

    public Storage getModelCapacity()
    {
        return modelCapacity;
    }

    public Color getModelColor()
    {
        return modelColor;
    }

    @Override
    public String toString()
    {
        return String.format("%s %sGB %s (%d$)", this.modelName, Storage.toString(this.modelCapacity), this.modelColor, this.price);
    }

    public boolean equivalent(IPhone other)
    {
        try{
            return  (this.modelCapacity == other.modelCapacity)
                    && (this.modelColor == other.modelColor)
                    && (this.modelName.contentEquals(other.modelName));
        }
        catch(NullPointerException exception)
        {
            System.err.printf("Exception in Equivalent method: %s", exception.getMessage());
            return false;
        }
    }
}
