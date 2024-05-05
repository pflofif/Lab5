package RoomTypes;

public class Cottage extends Property {
    private final double landSize; // In square meters
    private final boolean isFurnished;

    public Cottage(String id, String address, double size, double rentalPrice, double landSize, boolean isFurnished) {
        super(id, address, size, rentalPrice);
        this.landSize = landSize;
        this.isFurnished = isFurnished;
    }

    @Override
    public double computeRentalOffer() {
        // Example calculation, adjust as necessary
        return getRentalPrice() * (isFurnished() ? 1.15 : 1.05);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", landSize=%.2f, isFurnished=%b", getLandSize(), isFurnished());
    }


    public double getLandSize() {
        return landSize;
    }

    public boolean isFurnished() {
        return isFurnished;
    }
}
