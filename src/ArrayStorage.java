import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int MAX_STORAGE_VALUE = 10_000;
    Resume[] storage = new Resume[MAX_STORAGE_VALUE];
    int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        int resumeIndex = findResume(uuid);
        if (resumeIndex != -1) {
            return storage[resumeIndex];
        }
        return null;
    }

    void delete(String uuid) {
        int resumeIndex = findResume(uuid);
        if (resumeIndex != -1) {
            storage[resumeIndex] = null;
        }
        moveLeft(resumeIndex);
        size--;

    }

    void moveLeft(int index) {
        for (int i = index; i < size(); i++) {
            storage[i] = storage[i + 1];
        }
    }

    int findResume(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) return i;
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {
        Resume[] result = Arrays.copyOf(storage, size());
        return result;
    }

    int size() {
        return size;
    }

}