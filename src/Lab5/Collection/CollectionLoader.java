package Lab5.Collection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public interface CollectionLoader<T> {
    T load(String path, Class<? extends T> type) throws IOException;
    void save(String path, T collection) throws IOException;
}
