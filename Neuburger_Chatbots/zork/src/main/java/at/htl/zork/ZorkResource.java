package at.htl.zork;

import at.htl.zork.model.*;
import at.htl.zork.utils.CreatureState;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Path("/zork")
public class ZorkResource {
    Map<String,Room> rooms;
    Room current;
    Player player;
    List<ZorkObject> environment;

    public ZorkResource(){
        init();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/process")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response process(JsonNode input) {
        if(player.getState()==CreatureState.DEAD){
            return Response.noContent().entity("You are dead. POST to /restart to respawn").build();
        }
        input = input.get("queryResult");
        String command = input.get("intent").get("displayName").asText();
        String response = "I beg your pardon?";
        JsonNode parameters = input.get("parameters");
        if(command.equals("look")){
            response = enterRoom(current.getName());
        }
        else if(command.equals("default welcome intent")){
            response = enterRoom("The Troll Room");
        }
        else if(command.equals("kill")){
            response = kill(parameters.get("Target").asText(),parameters.get("ActionObject").asText());
        }
        else if(command.equals("look-at")){
            response = lookAt(parameters.get("Item").asText());
        }
        else if(command.equals("drop")){
            response = "";
            parameters.get("Item").elements().forEachRemaining(e -> System.out.println(e.asText()));
            Iterator<JsonNode> iterator = parameters.get("Item").elements();
            while (iterator.hasNext()){
                JsonNode item = iterator.next();
                if(player.hasItem(item.asText())){
                    ZorkObject itemObject = player.getInventory()
                            .stream().filter(i -> i.getName().toLowerCase().equals(item.asText())).findFirst().get();
                    player.dropItem(itemObject);
                    current.addContaining(itemObject);
                    fillEnvironment();
                    response += "Dropped.\n";
                }
                else{
                    response += "You don't have a "+item+"!\n";
                }
            }
        }
        else if(command.equals("take")){
            response = "";
            Iterator<JsonNode> iterator = parameters.get("Item").elements();
            while (iterator.hasNext()){
                JsonNode item = iterator.next();
                System.out.println(item);
                if(player.hasItem(item.asText())){
                    response += "You already have the "+item.asText()+"!\n";
                }
                else if(current.getContainings().contains(new ZorkObject(item.asText()))){
                    ZorkObject itemObject = current.getContainings()
                            .stream().filter(i -> i.getName().toLowerCase().equals(item.asText())).findFirst().get();
                    current.removeContaining(itemObject);
                    player.pickupItem(itemObject);
                    response += "Taken.\n";
                    fillEnvironment();
                }
                else{
                    response += "There is no " + item.asText() + " here!\n";
                }
            }
        }
        return Response.ok().entity(response).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/reset")
    public Response reset(){
        init();
        return Response.ok().entity(enterRoom("The Troll Room")).build();
    }

    @GET
    @Path("/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventory(){
        ObjectMapper mapper = new ObjectMapper();
        return Response.ok().entity(mapper.convertValue(player.getInventory(),JsonNode.class)).build();
    }

    @GET
    @Path("/room")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoom(){
        ObjectMapper mapper = new ObjectMapper();
        return Response.ok().entity(mapper.convertValue(current, JsonNode.class)).build();
    }

    private String kill(String target, String item){
        System.out.println(target);
        System.out.println(item);
        String response = "";
        ZorkObject targetObject = new ZorkObject(target);
        if(!isHere(target)){
            return "There is no "+target+" here!";
        }
        targetObject = environment.get(environment.indexOf(targetObject));
        if(!(targetObject instanceof Monster)){
            return "I've known strange people but fighting a "+target+"?!";
        }
        ZorkObject itemObject = new ZorkObject(item);
        if(!player.getInventory().stream().anyMatch(i -> i.getName().toLowerCase().equals(item))){
            return "You don't have that!";
        }
        itemObject = environment.get(environment.indexOf(itemObject));
        if(!(itemObject instanceof Item)||!((Item) itemObject).isWeapon()){
            return "Fighting a" + targetObject.getName() + " with a "+itemObject.getName()+" is suicidal!";
        }

        if(player.getScoreWithItems()>((Monster) targetObject).getPowerLevel()){
            response = ((Monster) targetObject).die();
            current.getContainings().addAll(((Monster) targetObject).getInventory());
            current.removeContaining(targetObject);
            fillEnvironment();
        }
        else if(player.getScoreWithItems()==((Monster) targetObject).getPowerLevel()){
            response = "You parry a strike from the" + targetObject.getName() + " and it tumbles backwards";
        }
        else {
            response = player.die();
        }
        fillEnvironment();
        return response;
    }

    private String lookAt(String target){
        final StringBuilder stringBuilder = new StringBuilder();
        List<ZorkObject> containings = environment
                .stream()
                .filter(c -> c.getName().toLowerCase().equals(target.toLowerCase()))
                .collect(Collectors.toList());
        if(containings.isEmpty()){
            stringBuilder.append("There is no "+target+" here!");
        }
        else if(containings.get(0).canBeExamined()){
            stringBuilder.append(containings.get(0).getDescription());
        }
        else {
            stringBuilder.append("There is nothing special about the "+containings.get(0).getName());
        }
        return stringBuilder.toString();
    }

    private String enterRoom(String target){
        current = rooms.get(target);
        if(current==null){
            return "Error";
        }
        StringBuilder response = new StringBuilder();
        response.append(current.getName()+"\n\n"+current.getDescription()+"\n");
        if(current.getContainings().stream().anyMatch(p -> p instanceof Monster)){
            current.getContainings().stream().filter(p -> p instanceof Monster)
                    .forEach(m -> {
                        response.append(m.getDescription()+"\n");
                    });
            if(player.hasItem("Elvish Sword")){
                response.append("Your sword has begun to glow very brightly\n");
            }
        }
        current.getContainings().stream().filter(p -> p instanceof Item).forEach(i -> {
            response.append("There is a "+i.getName()+" here\n");
        });
        fillEnvironment();
        return response.toString();
    }

    private void fillEnvironment(){
        environment.addAll(player.getInventory());
        current.getContainings()
                .stream()
                .filter(c -> c instanceof Monster)
                .forEach(m -> environment.addAll(((Monster) m).getInventory()));
        environment.addAll(current.getContainings());
    }

    private void init(){
        environment = new LinkedList<>();
        rooms = new HashMap<>();
        player = new Player();
        player.setScore(5);
        Item lantern = new Item("Brass Lantern");
        Item elvenSword = new Item("Elvish Sword");
        elvenSword.setPickupScore(6);
        elvenSword.setWeapon(true);
        player.pickupItem(lantern);
        player.pickupItem(elvenSword);
        Room trollRoom = new Room("The Troll Room","This is a small room with passages to the east and " +
                "south and a forbidding hole leading west. Bloodstains and deep" +
                "scratches (perhaps made by an axe) mar the walls.\n");
        Monster troll = new Monster("Troll",
                "A nasty-looking troll, brandishing a bloody axe, blocks all passages out of the room.");
        Item axe = new Item("Bloody Axe");
        axe.setWeapon(true);
        troll.addItem(axe);
        troll.setPowerLevel(10);
        troll.setDeathMessage("The fatal blow strikes the troll square in the heart:  He dies.\n" +
                "Almost as soon as the troll breathes his last breath, a cloud of sinister black fog envelops him, and when the fog" +
                "lifts, the carcass has disappeared.");
        trollRoom.addContaining(troll);
        rooms.put(trollRoom.getName(),trollRoom);
        current = rooms.get("The Troll Room");
        fillEnvironment();
    }

    private boolean isHere(String target){
        return environment.stream().anyMatch(c -> c.getName().toLowerCase().equals(target));
    }
}