import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < 10000; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < 10000; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < 10000; i++) {
            if (storage[i] != null && storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        try {
            int k = 0;
            for (int i = 0; i < 10000; i++) {
                if (uuid == storage[i].uuid) {
                    storage[i] = null;
                    k = i;
                    break;
                }
            }
            for (int i = k; i < storage.length - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[storage.length - 1] = null;
        } catch (NullPointerException e) {

        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = 0;
        for (int i = 0; i < 10000; i++) {
            if (storage[i] != null) {
                size++;
            } else {
                break;
            }
        }
        Resume[] array = new Resume[size];
        for (int i = 0; i < size; i++) {
            array[i] = storage[i];
        }
        return array;
    }

    int size() {
        int resumeCount = 0;
        for (int i = 0; i < 10000; i++) {
            if (storage[i] != null) {
                resumeCount++;
            } else {
                break;
            }
        }
        return resumeCount;
    }
}
