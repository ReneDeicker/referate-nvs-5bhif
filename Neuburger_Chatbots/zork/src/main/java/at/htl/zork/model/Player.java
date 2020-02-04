package at.htl.zork.model;

import at.htl.zork.utils.CreatureState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private CreatureState state;
    private int score;
    private List<ZorkObject> inventory;

    public Player(){
        state=CreatureState.ALIVE;
        score = 0;
        inventory = new ArrayList<>();
    }

    public CreatureState getState() {
        return state;
    }

    public void setState(CreatureState state) {
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public int getScoreWithItems(){
        int allScore = getScore();
        for(ZorkObject item: inventory){
            if(item instanceof Item){
                allScore += ((Item) item).getPickupScore();
            }
        }
        return allScore;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<ZorkObject> getInventory() {
        return inventory;
    }

    public void setInventory(List<ZorkObject> inventory) {
        this.inventory = inventory;
    }

    public boolean hasItem(String item){
        return inventory
                .stream()
                .anyMatch(i -> i.getName().toLowerCase().equals(item.toLowerCase()));
    }

    public void pickupItem(ZorkObject item){
        this.inventory.add(item);
    }

    public ZorkObject dropItem(String itemName){
        ZorkObject zorkObject = this.inventory
                .stream()
                .filter(i -> i.getName().toLowerCase().equals(itemName.toLowerCase()))
                .findFirst()
                .get();
        this.inventory.remove(zorkObject);
        return zorkObject;
    }

    public ZorkObject dropItem(ZorkObject item){
        return dropItem(item.getName());
    }

    public String die(){
        state = CreatureState.DEAD;
        return "*** YOU HAVE DIED ***";
    }
}
