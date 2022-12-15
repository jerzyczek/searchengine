package com.findwise;

import java.util.Objects;

class Entry implements IndexEntry {

    private String id;
    private double score;

    public Entry(String id) {
        this.id = id;
    }

    public Entry(String id, double score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public double getScore() {
        return this.score;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Double.compare(entry.score, score) == 0 && Objects.equals(id, entry.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score);
    }

    @Override
    public String toString() {
        return this.id + " with score "+this.score;
    }
}
