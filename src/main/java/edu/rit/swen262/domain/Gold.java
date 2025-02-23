package edu.rit.swen262.domain;

public class Gold implements Item {

    private int value;
    private int count;
    private String name;
    private String description;

    public Gold(int value, int count) {
        this.name = "Gold";
        this.description = "This item is a form of currency.";
        this.value = value;
        this.count = count;
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

    public Gold getValue() {
        return this;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
