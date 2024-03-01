package Lab5.Collection;

import Lab5.Exceptions.InvalidArgument;

public class Coordinates {
    private int x; //Максимальное значение поля: 199
    private long y;

    public String getXRestriction() {
        return "Maximum field value: 199";
    }

    public void setX(int x) throws InvalidArgument {
        if (x > 199) {
            throw new InvalidArgument(getXRestriction());
        }
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
