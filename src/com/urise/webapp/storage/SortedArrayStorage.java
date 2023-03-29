package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = findIndex(r.getUuid()) + 1;
        if (index > 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_CAPACITY) {
            System.out.println("Storage overflow");
        } else {
            if (index == -size) {
                storage[size] = r;
            } else {
                for (int i = size; i > -index; i--) {
                    storage[i] = storage[i - 1];
                }
                storage[-index] = r;
            }
            size++;
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}