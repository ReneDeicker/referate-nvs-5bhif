package at.htl.zork.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Room extends ZorkObject{
    private Map<String, Room> directions;
    private List<ZorkObject> containings;

    public Room(String name, String description){
        super(name,description);
        containings = new LinkedList<>();
        directions = new HashMap<>();
        directions.put("north",null);
        directions.put("east",null);
        directions.put("south",null);
        directions.put("west",null);
        directions.put("northeast",null);
        directions.put("southeast",null);
        directions.put("southwest",null);
        directions.put("northwest",null);
    }

    public Room getDirection(String direction){
        return directions.get(direction);
    }

    public List<ZorkObject> getContainings() {
        return containings;
    }

    public void setContainings(List<ZorkObject> containings) {
        this.containings = containings;
    }

    public void addContaining(ZorkObject item){
        this.containings.add(item);
    }

    public void removeContaining(ZorkObject item){
        this.containings.remove(item);
    }
}
