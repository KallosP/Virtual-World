import processing.core.PImage;

import java.util.List;

public class Stump extends Entity{

    // REMOVE ALL STATIC calls like below in each class?
    // if you can make it a getter, make it a getter

    // For min requirements:
    // Entity, ActiveEntity, and AnimatedEntity should be interface or abstract
    // House, Stump, and Obstacle are classes
    // Action should be abstrac or interface and Animation and Activity should extend/implement
    public static final String STUMP_KEY = "stump";
    public static final int STUMP_NUM_PROPERTIES = 0;

    public Stump(String id, Point position, List<PImage> images){
        super(id, position, images);
    }

}
