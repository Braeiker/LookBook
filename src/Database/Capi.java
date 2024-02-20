package Database;

public class Capi {
    private int id;
    private String dataEntered;
    private String type;
    private String brand;
    private String cut;
    private String price;
    private boolean availability;

    public Capi(int id, String dataEntered, String type, String brand, String cut, boolean availability) {
        this.id = id;
        this.dataEntered = dataEntered;
        this.type = type;
        this.brand = brand;
        this.cut = cut;
        this.price=price;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDataEntered() {
        return dataEntered;
    }

    public void setDataEntered(String dataEntered) {
        this.dataEntered = dataEntered;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}