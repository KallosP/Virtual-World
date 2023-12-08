import processing.core.PImage;

import java.util.List;

public abstract class AnimatedEntity extends Entity{
    protected double animationPeriod;
    public AnimatedEntity(String id, Point position, List<PImage> images, double animationPeriod) {
        super(id, position, images);
        this.animationPeriod = animationPeriod;
    }

    protected abstract void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);

    public double getAnimationPeriod(){
        return this.animationPeriod;
    }
}
