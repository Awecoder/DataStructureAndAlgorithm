package com.lzp.structure.hashtable;

/**
 * hashCode / equals
 *
 * @author lzp
 * @version v1.0 at 2019/3/4
 */
public class Student {
    private String name;
    private int grade;
    private int clazz;

    public Student(String name, int grade, int clazz) {
        this.name = name;
        this.grade = grade;
        this.clazz = clazz;
    }

    @Override
    public int hashCode() {
        int B = 31;

        int hash = 0;
        hash = hash * B + name.hashCode();
        hash = hash * B + grade;
        hash = hash * B + clazz;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Student another = (Student) o;
        return this.name.equals(another.name) &&
                this.grade == another.grade &&
                this.clazz == another.clazz;
    }
}
