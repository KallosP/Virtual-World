import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DudeFull extends Dude {

    public DudeFull(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images){
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
    }

    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {

        if (this.position.adjacent(target.position)) {

            // Remove slime
            if(target instanceof Slime){
                world.removeEntity(scheduler, target);
                return true;
            }

            return true;
        } else {
            Point nextPos = nextPosition( world, target.position);

            if (!this.position.equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }


    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        ActiveEntity dude = new DudeNotFull(this.id, this.position, this.actionPeriod,
                this.animationPeriod, this.resourceLimit, this.images);

        world.removeEntity(scheduler, this);

        world.addEntity(dude);
        dude.scheduleActions(scheduler, world, imageStore);
    }


    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        if(!eventInteractionExists(scheduler, world, imageStore)){
            Optional<Entity> fullTarget = world.findNearest(this.position, new ArrayList<>(Arrays.asList(House.class)));

            if (fullTarget.isPresent() && moveTo(world, fullTarget.get(), scheduler)) {
                // Reset Dude to normal only if poisoned (avoid removing buff if it already applied)
                if(this.images.equals(new ArrayList<>(imageStore.getImageList("poisoned_dude")))){
                    this.images = new ArrayList<>(imageStore.getImageList("dude"));
                    this.actionPeriod = 1.0;
                    this.animationPeriod = 0.2;
                }
                this.transformFull(world, scheduler, imageStore);
            } else {
                scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.actionPeriod);
            }
        }
    }
}
