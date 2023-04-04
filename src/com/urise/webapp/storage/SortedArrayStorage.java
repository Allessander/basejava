package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void saveResume(Resume resume, int index) {
        if (size + index < 0) {
            storage[size] = resume;
        } else {
            int realIndex = -index - 1;
            System.arraycopy(storage, realIndex, storage, realIndex + 1, size - realIndex);
            storage[realIndex] = resume;
        }
    }

    protected void deleteResume(int index) {
        if (size - 1 == index) {
            storage[index] = null;
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index -1);
            storage[size - 1] = null;
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}