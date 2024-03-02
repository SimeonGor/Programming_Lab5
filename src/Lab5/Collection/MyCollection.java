package Lab5.Collection;

import Lab5.Exceptions.InvalidArgument;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class MyCollection {
    transient Comparator<Organization> comparator = new Comparator<Organization>() {
        @Override
        public int compare(Organization o1, Organization o2) {
            if (o1.getAnnualTurnover() - o2.getAnnualTurnover() < 0) {
                return -1;
            }
            else {
               if (o1.getName().compareTo(o2.getName()) <= 0) {
                   return 0;
               }
               else  {
                   return 1;
               }
            }
        }
    };

    private boolean order = true; // true - in ascending order, false - in descending order
    private LocalDate creationDate;
//    private LocalDate modifiedDate;
    private long maxId;
    private final Vector<Organization> collection;

    public void setCreationDate() {
        creationDate = LocalDate.now();
    }

    public void setMaxId() {
        if (collection.isEmpty()) {
            maxId = -1;
            return;
        }
        maxId = Collections.max(collection, (o1, o2) -> Long.compare(o1.getId(), o2.getId())).getId();
    }

    public long genId() {
        return maxId + 1;
    }

    public boolean isOrdered() {
        return order;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

//    public LocalDate getModifiedDate() {
//        return modifiedDate;
//    }

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
        sort();
    }

    public void clear() {
        collection.clear();
//        modifiedDate = LocalDate.now();
        maxId = -1;
    }

    public void replace(long id, Organization el) throws InvalidArgument {
        boolean isFounded = collection.removeIf((o1) -> o1.getId() == id);

        if (!isFounded) {
            throw new InvalidArgument(String.format("There is no item with id %s in the collection", id));
        }
        else {
            collection.add(el);
            sort();
        }
    }

    public Comparator<Organization> getComparator() {
        return comparator;
    }

    public void removeByID(long id) {
        collection.removeIf(t -> t.getId() == id);
//        modifiedDate = LocalDate.now();
    }

    public void removeAt(int index) throws IndexOutOfBoundsException {
        collection.remove(index);
//        modifiedDate = LocalDate.now();
    }

    public int size() {
       return collection.size();
    }

    public void sort() {
        collection.sort(comparator);
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
        reverse();
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
        reverse();
        return result.substring(0, result.length() - 1);
    }
}
