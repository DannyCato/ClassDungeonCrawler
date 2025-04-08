package edu.rit.swen262.domain;

public interface Attackable {
    public void setMediator(GameMediator mediator);

    public String takeDamage(int damage);
}
