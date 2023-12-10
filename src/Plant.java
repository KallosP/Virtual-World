import processing.core.PImage;

import java.util.List;

public abstract class Plant extends ActiveEntity{

    protected int health;
    public Plant(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.health = health;
    }

    protected int getHealth(){
        return this.health;
    }

    protected void decrementHealth(){
        this.health--;
    }

}
