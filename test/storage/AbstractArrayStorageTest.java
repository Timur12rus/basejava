package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {
    private final Storage STORAGE;

    public static final String UUID_1 = "uuid1";
    public static final Resume RESUME_1 = new Resume(UUID_1);

    public static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2);

    public static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3);

    public static final String UUID_4 = "uuid4";
    public static final Resume RESUME_4 = new Resume(UUID_4);

    public static final String UUID_NOT_EXIST = "dummy";

    protected AbstractArrayStorageTest(Storage storage) {
        this.STORAGE = storage;
    }

    @Before
    public void setUp() throws Exception {
        STORAGE.clear();
        STORAGE.save(RESUME_1);
        STORAGE.save(RESUME_2);
        STORAGE.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        STORAGE.clear();
        assertSize(0);
        Resume[] resumes = new Resume[0];
        assertArrayEquals(STORAGE.getAll(), resumes);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        STORAGE.update(newResume);
        assertTrue(newResume == STORAGE.get(UUID_1));
        assertSame(newResume, STORAGE.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        STORAGE.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] result = STORAGE.getAll();
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        assertEquals(expected.length, result.length);
        assertArrayEquals(expected, result);
        assertSize(expected.length);
    }

    @Test
    public void save() throws Exception {
        STORAGE.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        STORAGE.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        STORAGE.clear();
        try {
            for (int i = 4; i <= STORAGE_LIMIT; i++) {
                STORAGE.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        STORAGE.save(new Resume());
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        STORAGE.delete(UUID_1);
        assertSize(2);
        STORAGE.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        STORAGE.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        STORAGE.get(UUID_NOT_EXIST);
    }

    private void assertGet(Resume r) {
        assertEquals(r, STORAGE.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, STORAGE.size());
    }

}