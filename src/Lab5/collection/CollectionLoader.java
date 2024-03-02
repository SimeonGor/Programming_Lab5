package Lab5.collection;

import java.io.IOException;

/**
 * Interface fo collection loader
 * @param <T> type of collection
 */
public interface CollectionLoader<T> {
    T load(String path, Class<? extends T> type) throws IOException;
    void save(String path, T collection) throws IOException;
}
