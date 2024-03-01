package Lab5.Collection;


import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.function.Predicate;

public class CollectionManager {
    /**
     * True if the collection is sorted in ascending order
     */
    private MyCollection collection;
    private final String path;
    private final CollectionLoader<MyCollection> collectionLoader;

    public CollectionManager(String path, CollectionLoader<MyCollection> collectionLoader) throws IOException {
        this.collectionLoader = collectionLoader;
        this.path = path;
        collection = collectionLoader.load(path, MyCollection.class);
        if (collection == null) {
            collection = new MyCollection();
            collection.setCreationDate();
        }
        collection.sort();
    }

    public MyCollection getCollection() {
        return collection;
    }

    public void saveCollection() throws IOException {
        collectionLoader.save(path, collection);
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public void clear() {
        collection.clear();
    }

    public void removeByID(long id) throws IndexOutOfBoundsException {
        collection.removeByID(id);
    }

    public void removeAt(int index) {
        collection.removeAt(index);
    }

    public String getCollectionAsString() {
        return collection.toString();
    }

    public Organization minIf(Comparator<Organization> comp) {
        return Collections.min(collection.getCollection(), comp);
    }

    public MyCollection greaterThan(Predicate<Organization> predicate) {
        MyCollection result = new MyCollection();
        for (var e : collection.getCollection()) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        result.sort();
        return result;
    }

    public String getTypeOfElement() {
        String[] t = collection.getCollection().elementAt(0).getClass().getName().split("\\.");
        return t[t.length - 1];
    }
}
