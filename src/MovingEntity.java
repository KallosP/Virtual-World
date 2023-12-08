import processing.core.PImage;

import java.util.List;

public abstract class MovingEntity extends ActiveEntity{

    private PathingStrategy strategy;
    public MovingEntity(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, PathingStrategy strategy) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.strategy = strategy;
    }

    public abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);

    public Point nextPosition(WorldModel world, Point destPos) {

        List<Point> path = strategy.computePath(
                getPosition(), // start point
                destPos, // end point
                p ->  world.withinBounds(p) && !world.isOccupied(p), // canPassThrough
                Point::adjacent, // withinReach
                PathingStrategy.CARDINAL_NEIGHBORS);
//                PathingStrategy.DIAGONAL_CARDINAL_NEIGHBORS);
//                PathingStrategy.DIAGONAL_NEIGHBORS);



        if(path.isEmpty())
            return this.getPosition();
        else
            return path.get(0);

    }


}
