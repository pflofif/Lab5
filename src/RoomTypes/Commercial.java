package RoomTypes;

public class Commercial extends Property {
    private final String businessType;
    private final boolean utilitiesIncluded;

    public Commercial(String id, String address, double size, double rentalPrice, String businessType, boolean utilitiesIncluded) {
        super(id, address, size, rentalPrice);
        this.businessType = businessType;
        this.utilitiesIncluded = utilitiesIncluded;
    }

    @Override
    public double computeRentalOffer() {
        // Example calculation, can be refined as needed
        return getRentalPrice() * (isUtilitiesIncluded() ? 1.2 : 1.1);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", businessType=%s, utilitiesIncluded=%b", getBusinessType(), isUtilitiesIncluded());
    }

    public String getBusinessType() {
        return businessType;
    }

    public boolean isUtilitiesIncluded() {
        return utilitiesIncluded;
    }
}
