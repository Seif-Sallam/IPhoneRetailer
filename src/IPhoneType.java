public enum IPhoneType {
    IPHONE_13("IPhone13"),
    IPHONE_12("IPhone12"),
    IPHONE_SE("IPhoneSE");

    private final String modelName;

    IPhoneType(String str)
    {
        this.modelName = str;
    }

    @Override
    public String toString() {
        return modelName;
    }
}
