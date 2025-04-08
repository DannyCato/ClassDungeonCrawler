package edu.rit.swen262.domain;

import edu.rit.swen262.service.GameMediator;

public interface Attackable {
    public void setMediator(GameMediator mediator);

    public String takeDamage(int damage);
}
