package Lab5.Collection;

public class Coordinates {
    private int x; //Максимальное значение поля: 199
    private long y;

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
