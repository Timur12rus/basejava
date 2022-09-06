/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < 10000; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if ((storage[i].uuid).equals(uuid) && (uuid != null)) {
                storage[i] = null;

                for (int j = i; j < storage.length - 1; j++) {
                    storage[i] = storage[i + 1];
                }
                if (size > 0) {
                    size--;
                }
                storage[storage.length - 1] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] array = new Resume[size];
        for (int i = 0; i < size; i++) {
            array[i] = storage[i];
        }
        return array;
    }

    int size() {
        return size;
    }
}
