import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Fairy extends MovingEntity{

    public static final String FAIRY_KEY = "fairy";
    public static final int FAIRY_ANIMATION_PERIOD = 0;
    public static final int FAIRY_ACTION_PERIOD = 1;
    public static final int FAIRY_NUM_PROPERTIES = 2;

//    private static final PathingStrategy Fairy_PATHING = new SingleStepPathingStrategy();
//
    private static final PathingStrategy Fairy_PATHING = new AstarPathingStrategy();

    public Fairy(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images) {
        super(id, position, images, actionPeriod, animationPeriod, Fairy_PATHING);
    }

    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.position.adjacent(target.position)) {
            world.removeEntity( scheduler,target);
            return true;
        } else {
            Point nextPos = nextPosition(world, target.position);

            if (!this.position.equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fairyTarget = world.findNearest(this.position, new ArrayList<>(Arrays.asList(Stump.class)));

        // Turn poisoned grass back into regular grass
        if(world.getBackgroundCell(this.position).getId().equals("poisoned_point")){
            world.setBackgroundCell(this.position, new Background("grass", new ArrayList<>(imageStore.getImageList("grass"))));
        }

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().position;

            if (moveTo( world, fairyTarget.get(), scheduler)) {

                ActiveEntity sapling = new Sapling(Sapling.SAPLING_KEY + "_" + fairyTarget.get().id, tgtPos,
                        imageStore.getImageList(Sapling.SAPLING_KEY), 0);

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }


        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.actionPeriod);
    }
}
