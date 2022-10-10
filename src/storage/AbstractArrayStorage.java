package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            printError(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            printError(uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int searchIndex = getIndex(uuid);
        if (searchIndex < 0) {
            printError(uuid);
        } else {
            fillDeletedElement(searchIndex);
            storage[size - 1] = null;
            size--;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume is already exist!");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage is FULL!");
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected void printError(String uuid) {
        System.out.println("ERROR! Resume with uuid = " + uuid + " not present!");
    }

    protected abstract int getIndex(String uuid);

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}
