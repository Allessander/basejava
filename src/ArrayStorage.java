import java.util.Arrays;


public class ArrayStorage {
    private final int capacity = 10_000;
    Resume[] storage = new Resume[capacity];
    int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        int resumeIndex = findIndex(uuid);
        return resumeIndex != -1 ? storage[resumeIndex] : null;
    }

    void delete(String uuid) {
        int resumeIndex = findIndex(uuid);
        if (resumeIndex != -1) {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}