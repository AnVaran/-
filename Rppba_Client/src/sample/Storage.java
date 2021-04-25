package sample;

public class Storage {
    private String Vendor;
    private String Type;
    private String Name;
    private String Shipped;
    private String UnitPrice;

    public Storage(String vendor, String type, String name, String shipped, String unitPrice) {
        Vendor = vendor;
        Type = type;
        Name = name;
        Shipped = shipped;
        UnitPrice = unitPrice;
    }
    public Storage(){}

    public String getVendor() {
        return Vendor;
    }

    public String getType() {
        return Type;
    }

    public String getName() {
        return Name;
    }

    public String getShipped() {
        return Shipped;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setShipped(String shipped) {
        Shipped = shipped;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }
}
