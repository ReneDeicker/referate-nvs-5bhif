package at.htl.zork.model;

import java.util.Objects;

public class ZorkObject {
    private String name;
    private String description;
    private boolean canBeExamined;
    private boolean canBePickedUp;
    private boolean canBeFought;

    public ZorkObject(String name) {
        this.name = name;
        this.canBeExamined = false;
        this.canBePickedUp = false;
        this.canBeFought = false;
    }

    public ZorkObject(String name, String description){
        this(name);
        this.description = description;
        this.canBeExamined = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean canBeExamined() {
        return canBeExamined;
    }

    public void setCanBeExamined(boolean canBeExamined) {
        this.canBeExamined = canBeExamined;
    }

    public boolean canBePickedUp() {
        return canBePickedUp;
    }

    public void setCanBePickedUp(boolean canBePickedUp) {
        this.canBePickedUp = canBePickedUp;
    }

    public boolean canBeFought() {
        return canBeFought;
    }

    public void setCanBeFought(boolean canBeFought) {
        this.canBeFought = canBeFought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZorkObject)) return false;
        ZorkObject that = (ZorkObject) o;
        return name.toLowerCase().equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
