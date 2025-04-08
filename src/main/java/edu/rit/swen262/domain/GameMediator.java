package edu.rit.swen262.domain;

public interface GameMediator {
    public void attackCharacter(Attackable initiator, Attackable reciever);

    public void lootObject(PlayerCharacter player, Lootable lootObject);
}
