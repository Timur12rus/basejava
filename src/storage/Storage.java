package storage;

import model.Resume;

public interface Storage {
    void clear();

    void update(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    void save(Resume r);

    Resume[] getAll();

    int size();


}
