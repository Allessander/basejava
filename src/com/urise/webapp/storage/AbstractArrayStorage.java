package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_CAPACITY = 10000;

    protected Resume[] storage = new Resume[STORAGE_CAPACITY];
    protected int size = 0;

    public int size() {
        return size;
    }

    final public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
//            System.out.println();
        } else if (size >= STORAGE_CAPACITY) {
            throw  new StorageException("Storage overflow", resume.getUuid());
//            System.out.println("Storage overflow");
        } else {
            saveResume(resume, index);
            size++;
        }
    }

    final public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
            size--;
        }
    }


    final public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    final public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

    protected abstract int findIndex(String uuid);
}