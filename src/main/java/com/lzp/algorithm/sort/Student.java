package com.lzp.algorithm.sort;

/**
 * 学生对象类，实现比较接口
 *
 * @author lzp
 * @version v1.0 at 2019/1/15
 */
public class Student implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * 定义比较规则：先比较score，score相同，比较name
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Student other) {
        if (this.score < other.score) {
            return -1;
        } else if (this.score > other.score) {
            return 1;
        } else {
            return this.name.compareTo(other.name);
        }
    }

    @Override
    public String toString() {
        return "Student: " + this.name + " " + Integer.toString(this.score);
    }
}
