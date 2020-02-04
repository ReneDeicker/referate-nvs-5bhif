package at.htl.zork.model;

public class Item extends ZorkObject {
    private int pickupScore = 0;
    private boolean isWeapon = false;

    public Item(String name){
        super(name);
    }

    public Item(String name, String description){
        super(name, description);
    }

    public int getPickupScore() {
        return pickupScore;
    }

    public void setPickupScore(int pickupScore) {
        this.pickupScore = pickupScore;
    }

    public boolean isWeapon() {
        return isWeapon;
    }

    public void setWeapon(boolean weapon) {
        isWeapon = weapon;
    }
}
