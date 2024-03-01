package Lab5.Collection;

import Lab5.Exceptions.InvalidArgument;

public class Address {
    public Address() {}
    public Address(String zipCode) {
        this.zipCode = zipCode;
    }
    private String zipCode; //Длина строки должна быть не меньше 9, Поле может быть null

    public String getZipCodeRestriction() {
        return "the string must be at least 9 letters long or empty";
    }

    public void setZipCode(String zipCode) throws InvalidArgument {
        if (zipCode.isEmpty()) {
            this.zipCode = null;
            return;
        }
        if (zipCode.length() > 9) {
            this.zipCode = zipCode;
            return;
        }
        throw new InvalidArgument(getZipCodeRestriction());
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        if (zipCode == null)
            return "";
        return zipCode;
    }
}
