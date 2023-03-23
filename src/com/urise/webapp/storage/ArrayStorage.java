package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


public class ArrayStorage {
    private final int capacity = 10_000;
    private final Resume[] storage = new Resume[capacity];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == capacity) {
            System.out.println("Ошибка: массив заполнен!");
        } else if (findIndex(r.toString()) != -1) {
            System.out.printf("Ошибка: резюме %s уже есть в массиве!\n", r.toString());
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int resumeIndex = findIndex(r.toString());
        if (resumeIndex == -1) {
            System.out.printf("Ошибка: резюме %s уже есть в массиве!\n", r.toString());
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = findIndex(uuid);
        if (resumeIndex == -1) {
            System.out.printf("Ошибка: резюме %s не найдено в массиве!\n", uuid);
            return null;
        } else {
            return storage[resumeIndex];
        }
    }

    public void delete(String uuid) {
        int resumeIndex = findIndex(uuid);
        if (resumeIndex == -1) {
            System.out.printf("Ошибка: резюме %s не найдено в массиве!\n", uuid);
        } else {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
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