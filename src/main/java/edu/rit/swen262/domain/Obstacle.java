package edu.rit.swen262.domain;

/**
 * This class just exists to be an Occupant
 */
public class Obstacle implements Occupant, java.io.Serializable{
    private String desc;
    
    public Obstacle(String description) {
        this.desc = description;
    }
    @Override
    public RenderRepresentation render() {
        return RenderRepresentation.OBSTACLE;
    }

    @Override
    public String description() {
        return desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }
    
}
