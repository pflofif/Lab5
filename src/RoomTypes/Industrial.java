
package RoomTypes;

public class Industrial extends Property {
    private final double maxLoadCapacity; // In kilograms
    private final String sectorType;

    public Industrial(String id, String address, double size, double rentalPrice, double maxLoadCapacity, String sectorType) {
        super(id, address, size, rentalPrice);
        this.maxLoadCapacity = maxLoadCapacity;
        this.sectorType = sectorType;
    }

    @Override
    public double computeRentalOffer() {
        // Example calculation, might require adjustment
        return getRentalPrice() * (getMaxLoadCapacity() > 10000 ? 1.25 : 1.1);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", maxLoadCapacity=%.2f, sectorType=%s", getMaxLoadCapacity(), getSectorType());
    }

    public double getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    public String getSectorType() {
        return sectorType;
    }
}
