public enum IPhoneType {
    IPHONE_13("IPhone 13"),
    IPHONE_12("IPhone 12"),
    IPHONE_SE("IPhone SE");

    private String modelName;

    IPhoneType(String str)
    {
        this.modelName = str;
    }

    @Override
    public String toString() {
        return modelName;
    }
}
