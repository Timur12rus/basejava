import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    protected final Resume[] storage = new Resume[10000];

    // TODO clear() method
    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    // TODO update() method
    public void update(Resume r) {
        int index = findSearchKey(r.uuid);
        if (index >= 0) {
            storage[index] = r;
        } else {
            printError(r.uuid);
        }
    }

    public void save(Resume r) {
        // TODO check if resume not present
        if (size == storage.length) {
            System.out.println("Storage is FULL!");
        } else if (findSearchKey(r.uuid) >= 0) {
            System.out.println("Resume is already exist!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findSearchKey(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            printError(uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        // TODO check if resume present
        int searchIndex = findSearchKey(uuid);
        if (searchIndex >= 0) {
            storage[searchIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            printError(uuid);
        }
    }


    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private void printError(String uuid) {
        System.out.println("ERROR! Resume with uuid = " + uuid + " not present!");
    }

    private int findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
