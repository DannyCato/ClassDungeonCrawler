package edu.rit.swen262.domain;

public class Gold extends Item {

    private int count;

    public Gold(int count) {
        super("Gold", "This item is a form of currency.", null);
        this.count = count;
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
