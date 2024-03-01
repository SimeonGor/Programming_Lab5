package Lab5.Collection;

import Lab5.Exceptions.InvalidArgument;

import java.time.LocalDate;

public class Organization {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле может быть null

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Double getAnnualTurnover() {
        return annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameRestriction() {
        return "The string cannot be empty";
    }
    public void setName(String name) throws InvalidArgument {
        if (name.isEmpty()) {
            this.name = name;
        }
        else {
            throw new InvalidArgument(getNameRestriction());
        }
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getAnnualTurnoverRestriction() {
        return "The field value must be greater than 0";
    }

    public void setAnnualTurnover(Double annualTurnover) throws InvalidArgument {
        if (annualTurnover > 0) {
            this.annualTurnover = annualTurnover;
        }
        else {
            throw new InvalidArgument(getAnnualTurnoverRestriction());
        }
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", annualTurnover=" + annualTurnover +
                ", type=" + type +
                ", postalAddress=" + postalAddress +
                '}';
    }
}
