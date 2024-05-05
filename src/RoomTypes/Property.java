package RoomTypes;

public abstract class Property {
    protected String type = getClass().getName();
    private final String id;
    private final String address;
    private final double size;  // In square meters
    private final double rentalPrice;

    public Property(String id, String address, double size, double rentalPrice) {
        this.id = id;
        this.address = address;
        this.size = size;
        this.rentalPrice = rentalPrice;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getSize() {
        return size;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    @Override
    public String toString() {
        return String.format("Property{id='%s', address='%s', size=%.2f, rentalPrice=%.2f}", id, address, size, rentalPrice);
    }

    public abstract double computeRentalOffer();
}

