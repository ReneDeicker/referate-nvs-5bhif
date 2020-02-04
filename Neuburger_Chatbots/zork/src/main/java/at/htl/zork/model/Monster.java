package at.htl.zork.model;

import at.htl.zork.utils.CreatureState;

import java.util.LinkedList;
import java.util.List;

public class Monster extends ZorkObject{
    private CreatureState state;
    private int powerLevel;
    private List<ZorkObject> inventory;
    private String deathMessage;

    public Monster(String name){
        super(name);
        setDefaultValues();
    }

    public Monster(String name, String description){
        super(name,description);
        setDefaultValues();
    }

    public Monster(String name, int powerLevel, String deathMessage){
        this(name);
        this.powerLevel = powerLevel;
        this.deathMessage = deathMessage;
    }

    public Monster(String name, String description, int powerLevel, String deathMessage){
        this(name,description);
        this.powerLevel = powerLevel;
        this.deathMessage = deathMessage;
    }

    private void setDefaultValues(){
        inventory = new LinkedList<>();
        this.powerLevel = 0;
        this.state = CreatureState.ALIVE;
        this.deathMessage = "The "+getName()+" dies";
        setCanBePickedUp(false);
        setCanBeFought(true);
    }

    public CreatureState getState() {
        return state;
    }

    public void setState(CreatureState state) {
        this.state = state;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public List<ZorkObject> getInventory() {
        return inventory;
    }

    public void setInventory(List<ZorkObject> inventory) {
        this.inventory = inventory;
    }

    public void addItem(ZorkObject item){
        this.inventory.add(item);
    }

    public String die(){
        this.state = CreatureState.DEAD;
        return this.deathMessage;
    }

    public String getDeathMessage() {
        return deathMessage;
    }

    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
    }
}
