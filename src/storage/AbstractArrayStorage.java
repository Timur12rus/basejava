package storage;

import model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
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

    protected abstract int getIndex(String uuid);

    protected void printError(String uuid) {
        System.out.println("ERROR! model.Resume with uuid = " + uuid + " not present!");
    }
}
