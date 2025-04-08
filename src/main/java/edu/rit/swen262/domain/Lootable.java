package edu.rit.swen262.domain;

import edu.rit.swen262.service.GameMediator;

public interface Lootable {
    public void setMediator(GameMediator mediator);

    public void takeLoot();
}
