package edu.rit.swen262.service;

import edu.rit.swen262.domain.Attackable;
import edu.rit.swen262.domain.GameCharacter;
import edu.rit.swen262.domain.Lootable;
import edu.rit.swen262.domain.PlayerCharacter;

public interface GameMediator {
    public String attackCharacter(GameCharacter initiator, Attackable reciever);

    public void lootObject(PlayerCharacter player, Lootable lootObject);
}
