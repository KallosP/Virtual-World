import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class Dude extends MovingEntity {
    public static final String DUDE_KEY = "dude";
    // 0 = default speed, 1 = fast, 2 = slow
    public static final int DUDE_ACTION_PERIOD = 0;
    // 1 = default period (normal), 0 = slowed state
    public static final int DUDE_ANIMATION_PERIOD = 1;
    public static final int DUDE_LIMIT = 2;
    public static final int DUDE_NUM_PROPERTIES = 3;

    private final double BUFF_STRENGTH_ACTION = 0.3;
    private final double BUFF_STRENGTH_ANIMATION = 0.1;
    private final double DEBUFF_STRENGTH_ACTION = 1.5;
    private final double DEBUFF_STRENGTH_ANIMATION = 0.6;


    public boolean hasRemovedSlime;
    // Store the original images
    private List<PImage> originalImages;

    protected int resourceLimit;


//    private static final PathingStrategy Dude_PATHING = new SingleStepPathingStrategy();
//
    private static final PathingStrategy Dude_PATHING = new AstarPathingStrategy();
    public Dude(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod, Dude_PATHING);
        this.originalImages = new ArrayList<>(images);
    }

    public void tryRemoveSlime(WorldModel world, Entity slime, EventScheduler scheduler, ImageStore imageStore){
        if(moveTo(world, slime, scheduler) && !hasRemovedSlime){
            // Increase Dude's speed
            this.actionPeriod = BUFF_STRENGTH_ACTION;
            this.animationPeriod = BUFF_STRENGTH_ANIMATION;
            // Change this Dude's appearance to "buffed" state
            this.images = new ArrayList<>(imageStore.getImageList("speed_dude")); // Replace the current images with the new list
            // Tracks whether this instance of Dude removed the slime
            hasRemovedSlime = true;
        }
    }

    // Manages the Dude's interaction with poisoned tile or slime if the are present
    public boolean eventInteractionExists(EventScheduler scheduler, WorldModel world, ImageStore imageStore){
        tryPoisonDude(world, imageStore);

        Optional<Entity> targetSlime = world.findNearest(this.position, new ArrayList<>(Arrays.asList(Slime.class)));

        if (targetSlime.isPresent()) {
            tryRemoveSlime(world, targetSlime.get(), scheduler, imageStore);
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.actionPeriod);
            return true;
        }

        return false;

    }

    // If on a poisoned point, poison the Dude
    public void tryPoisonDude(WorldModel world, ImageStore imageStore){
        if(world.getBackgroundCell(this.position).getId().equals("poisoned_point")){
            poisonDude(world, imageStore);
        }
    }

    // Removes all buffs from Dude and applies debuff (slowed)
    public void poisonDude(WorldModel world, ImageStore imageStore){

        // Turn poisoned grass back into regular grass
        if(world.getBackgroundCell(this.position).getId().equals("poisoned_point")){
            world.setBackgroundCell(this.position, new Background("grass", new ArrayList<>(imageStore.getImageList("grass"))));
        }

        System.out.println("CURRENT ACTION: " + this.actionPeriod);
        // Decrease Dude's speed
        this.actionPeriod = DEBUFF_STRENGTH_ACTION;
        this.animationPeriod = DEBUFF_STRENGTH_ANIMATION;
        this.images = new ArrayList<>(imageStore.getImageList("poisoned_dude"));
        hasRemovedSlime = false;
    }


}
