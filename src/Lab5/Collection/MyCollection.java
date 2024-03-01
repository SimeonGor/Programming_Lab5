package Lab5.Collection;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class MyCollection {
    transient Comparator<Organization> comparator = new Comparator<Organization>() {
        @Override
        public int compare(Organization o1, Organization o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    private boolean order = true; // true - in ascending order, false - in descending order
    private LocalDate creationDate;
    private LocalDate modifiedDate;
    private long maxId;
    private final Vector<Organization> collection;

    public void setCreationDate() {
        creationDate = LocalDate.now();
    }

    public void setMaxId() {
        maxId = Collections.max(collection, new Comparator<Organization>() {
            @Override
            public int compare(Organization o1, Organization o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        }).getId();
    }

    public long genId() {
        return maxId;
    }

    public boolean isOrdered() {
        return order;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public MyCollection() {
        this.collection = new Vector<>();
    }

    public Vector<Organization> getCollection() {
        return collection;
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public void add(Organization e) {
        collection.add(e);
        maxId = Long.max(maxId, e.getId()) + 1;
        if (maxId < 0) maxId = 0;
    }

    public void clear() {
        collection.clear();
        modifiedDate = LocalDate.now();
    }

    public void removeByID(long id) {
        collection.removeIf(t -> t.getId() == id);
        modifiedDate = LocalDate.now();
    }

    public void removeAt(int index) throws IndexOutOfBoundsException {
        collection.remove(index);
        modifiedDate = LocalDate.now();
    }

    public int size() {
       return collection.size();
    }

    public void sort() {
        Collections.sort(collection, comparator);
        order = true;
    }

    public void reverse() {
        Collections.reverse(collection);
        order = !order;
    }

    @Override
    public String toString() {
        String template = "%4s| %-4s| %-10s| %3s| %-6s| %-10s| %-15s| %-10s| %-9s";
        String header = String.format("%4s| %-4s| %-10s| %-11s| %-10s| %-15s| %-10s| %-9s",
                "#",
                "id",
                "name",
                "coordinates",
                "date",
                "annual turnover",
                "type",
                "zip code");

        StringBuilder result = new StringBuilder();
        result.append(header).append("\n");
        int i = 1;
        for (var e : collection) {
            result.append(String.format(template,
                        i++,
                        e.getId(),
                        e.getName(),
                        e.getCoordinates().getX(),
                        e.getCoordinates().getY(),
                        e.getCreationDate(),
                        e.getAnnualTurnover(),
                        e.getType(),
                        e.getPostalAddress()
                        ))
                    .append("\n");
        }
        return result.substring(0, result.length() - 1);
    }
}
