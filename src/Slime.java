// P4
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Slime extends MovingEntity{

    public static final String SLIME_KEY = "slime";
    public static final int SLIME_ACTION_PERIOD = 2;
    public static final int SLIME_ANIMATION_PERIOD = 1;
    public static final int SLIME_NUM_PROPERTIES = 2;


    private static final PathingStrategy SLIME_PATHING = new AstarPathingStrategy();
    public Slime(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod, SLIME_PATHING);
        this.animationPeriod = 0.1;
        this.actionPeriod = 1.9;
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        Optional<Entity> slimeTarget = world.findNearest(this.position, new ArrayList<>(Arrays.asList(Tree.class)));

        if (slimeTarget.isPresent() && moveTo(world, slimeTarget.get(), scheduler)) {
            world.removeEntity(scheduler, this);
        } else {
            scheduler.scheduleEvent( this, new Activity(this, world, imageStore), this.actionPeriod);
        }

    }

    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {

        if (this.position.adjacent(target.position)) {
            return true;
        } else {
            Point nextPos = nextPosition( world, target.position);

            if (!this.position.equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }
}
