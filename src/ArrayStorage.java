import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    Resume[] storage = new Resume[10000];

    // TODO clear() method
    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    // TODO update() method
    public void update(Resume r) {
        // TODO check if resume present
        if (checkResumeIsPresent(r.uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(r.uuid)) {
                    storage[i] = r;
                    break;
                }
            }
        } else {
            printError(r.uuid);
        }
    }

    void save(Resume r) {
        // TODO check if resume not present
        if (!checkResumeIsPresent(r.uuid)) {
            if (size < storage.length) {
                if (storage[size] == null) {
                    storage[size] = r;
                    size++;
                } else {
                    printError(r.uuid);
                }
            }
        } else {
            System.out.println("Storage is FULL!");
        }
    }

    Resume get(String uuid) {
        if (checkResumeIsPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid == uuid) {
                    return storage[i];
                }
            }
        } else {
            printError(uuid);
        }
        return null;
    }

    void delete(String uuid) {
        // TODO check if resume present
        if (checkResumeIsPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if ((storage[i].uuid).equals(uuid) && (uuid != null)) {
                    storage[i] = null;

                    for (int j = i; j < size - 1; j++) {
                        storage[j] = storage[j + 1];
                    }
                    storage[size - 1] = null;
                    if (size > 0) {
                        size--;
                    }
                    break;
                }
            }
        } else {
            printError(uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = Arrays.copyOf(storage, size);
        return result;
    }

    int size() {
        return size;
    }

    private void printError(String uuid) {
        System.out.println("ERROR! Resume with uuid = " + uuid + " not present!");
    }

    private boolean checkResumeIsPresent(String uuid) {
        boolean resumeIsPresent = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resumeIsPresent = true;
                break;
            }
        }
        return resumeIsPresent;
    }
}
