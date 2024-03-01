package Lab5.Collection;

public class Address {
    public Address(String zipCode) {
        this.zipCode = zipCode;
    }
    private String zipCode; //Длина строки должна быть не меньше 9, Поле может быть null

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return zipCode;
    }
}
