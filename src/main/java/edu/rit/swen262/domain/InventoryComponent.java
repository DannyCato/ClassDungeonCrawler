package edu.rit.swen262.domain;

import edu.rit.swen262.service.InventoryVisitor;

public interface InventoryComponent {
    public String getName();

    public String getDescription();

    public Gold getValue();

    public void accept(InventoryVisitor visitor);
}