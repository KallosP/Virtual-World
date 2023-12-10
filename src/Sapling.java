import processing.core.PImage;

import java.util.List;

public class Sapling extends Plant implements TransformEntity{

    public static final String SAPLING_KEY = "sapling";
    public static final int SAPLING_HEALTH = 0;
    public static final int SAPLING_NUM_PROPERTIES = 1;
    public static final double SAPLING_ACTION_ANIMATION_PERIOD = 1.000; // have to be in sync since grows and gains health at same time
    public static final int SAPLING_HEALTH_LIMIT = 5;

    public Sapling (String id, Point position, List<PImage> images, int health){
        super(id, position, images, SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, health);
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.health <= 0) {
            Entity stump = new Stump(Stump.STUMP_KEY + "_" + this.id,
                    this.position, imageStore.getImageList(Stump.STUMP_KEY));

            world.removeEntity( scheduler,this);

            world.addEntity(stump);

            return true;
        } else if (this.health >= SAPLING_HEALTH_LIMIT) {
            ActiveEntity tree = new Tree(Tree.TREE_KEY + "_" + this.id, this.position,
                    this.position.getNumFromRange(Tree.TREE_ACTION_MAX, Tree.TREE_ACTION_MIN),
                    this.position.getNumFromRange(Tree.TREE_ANIMATION_MAX, Tree.TREE_ANIMATION_MIN),
                    this.position.getIntFromRange(Tree.TREE_HEALTH_MAX, Tree.TREE_HEALTH_MIN),
                    imageStore.getImageList(Tree.TREE_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        health++;
        if (!this.transform( world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), SAPLING_ACTION_ANIMATION_PERIOD);
        }
    }
}
