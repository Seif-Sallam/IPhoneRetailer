// An abstract class that represents a single IPhone and implements the accessory interface, so that all its children will\
// need to implement it later.
abstract public class IPhone implements AccessoryInterface
{
    protected String modelName;
    protected Storage modelCapacity;
    protected Color modelColor;

    protected int price;

    protected IPhoneType type;
    protected int id;

    IPhone(IPhoneType type, Storage modelCapacity, Color modelColor, int price)
    {
        setType(type);
        setModelName(type.toString());
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

    protected void setType(IPhoneType type) { this.type = type; }

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

    public IPhoneType getType() { return type; }

    @Override
    public String toString()
    {
        return String.format("%s %s %s (%d$)", this.modelName, this.modelCapacity, this.modelColor, this.price);
    }
}
