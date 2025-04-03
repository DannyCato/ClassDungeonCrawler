package edu.rit.swen262.domain;

public class Gold extends Item {

    private int value;
    private int count;

    public Gold(int value, int count) {
        super("Gold", "This item is a form of currency.", null);
        this.value = value; // value of each gold piece
        this.count = count; // NUMBER of gold pieces in stack?
    }

    public int getStack() {
        return value * count;
    }

    public void increaseCount() {
        this.count++;
    }

    public void decreaseCount() {
        this.count--;
        if (this.count < 0) {
            this.count = 0;
        }
    }

    @Override
    public Gold getValue() {
        return this;
    }

    public int getCount() {
        return count;
    }
}
