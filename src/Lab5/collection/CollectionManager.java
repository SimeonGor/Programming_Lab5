package Lab5.collection;


import Lab5.exceptions.InvalidArgument;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Class for managing the collection and its elements
 *
 * @see MyCollection
 * @see Organization
 */

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
        collection.setMaxId();
    }

    /**
     * add new item to the collection
     * @param e item
     */
    public void add(Organization e) {
        collection.add(e);
    }

    public MyCollection getCollection() {
        return collection;
    }

    /**
     * save the collection
     * @throws IOException
     */
    public void saveCollection() throws IOException {
        collectionLoader.save(path, collection);
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public void clear() {
        collection.clear();
    }

    /**
     * remove item with specified id
     * @param id
     * @throws IndexOutOfBoundsException
     */
    public void removeByID(long id) throws IndexOutOfBoundsException {
        collection.removeByID(id);
    }

    /**
     * remove item at index
     * @param index
     */
    public void removeAt(int index) {
        collection.removeAt(index);
    }

    public String getCollectionAsString() {
        return collection.toString();
    }

    /**
     * find minimum item
     * @param comp Predicate
     * @return item
     */
    public Organization minIf(Comparator<Organization> comp) {
        return Collections.min(collection.getCollection(), comp);
    }
    /**
     * find maximum item
     * @param comp Predicate
     * @return item
     */
    public Organization maxIf(Comparator<Organization> comp) {
        return Collections.max(collection.getCollection(), comp);
    }

    public Comparator<Organization> getComparator() {
        return collection.getComparator();
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

    public void replace(long id, Organization el) throws InvalidArgument {
        collection.replace(id, el);
    }

    public String getTypeOfElement() {
        String[] t = collection.getCollection().elementAt(0).getClass().getName().split("\\.");
        return t[t.length - 1];
    }
}
