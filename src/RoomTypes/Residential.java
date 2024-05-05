package RoomTypes;

public class Residential extends Property {
    private int numBedrooms;
    private final boolean hasParking;

    public Residential(String id, String address, double size, double rentalPrice, int numBedrooms, boolean hasParking) {
        super(id, address, size, rentalPrice);
        this.numBedrooms = numBedrooms;
        this.hasParking = hasParking;
    }

    @Override
    public double computeRentalOffer() {
        // Example calculation, can be modified
        return getRentalPrice() * (hasParking ? 1.1 : 1.0);
    }

    public int getNumBedrooms() {
        return numBedrooms;
    }

    public void setNumBedrooms(int numBedrooms) {
        this.numBedrooms = numBedrooms;
    }
}

