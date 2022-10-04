package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage implements Storage {

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            printError(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is FULL!");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.println("model.Resume is already exist!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printError(uuid);
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int searchIndex = getIndex(uuid);
        if (searchIndex == -1) {
            printError(uuid);
        } else {
            storage[searchIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
