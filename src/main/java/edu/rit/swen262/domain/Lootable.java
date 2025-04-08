package edu.rit.swen262.domain;

import java.util.List;

import edu.rit.swen262.service.GameMediator;

public interface Lootable {
    public void setMediator(GameMediator mediator);

    public List<Item> takeLoot();
}
