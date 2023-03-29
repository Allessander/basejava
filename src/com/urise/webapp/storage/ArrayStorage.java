package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (findIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_CAPACITY) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}