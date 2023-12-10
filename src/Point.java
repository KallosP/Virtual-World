import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * A simple class representing a location in 2D space.
 */
public final class Point {
    private final int x;
    private final int y;
    public int g;
    public int h;
    public Point parent;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = 0;
        this.h = 0;
    }

    public void setParent(Point pt){
        this.parent = pt;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public boolean adjacent( Point p2) {
        return (this.x == p2.x && Math.abs(this.y - p2.y) == 1) ||
                (this.y == p2.y && Math.abs(this.x - p2.x) == 1);
    }

    public  int getIntFromRange(int max, int min) {
        Random rand = new Random();
        return min + rand.nextInt(max-min);
    }

    public double getNumFromRange(double max, double min) {
        Random rand = new Random();
        return min + rand.nextDouble() * (max - min);
    }

    public  Optional<Entity> nearestEntity(List<Entity> entities, Point pos) {
        if (entities.isEmpty()) {
            return Optional.empty();
        } else {
            Entity nearest = entities.get(0);
            int nearestDistance = pos.distanceSquared(nearest.getPosition());

            for (Entity other : entities) {
                int otherDistance = pos.distanceSquared(other.getPosition());

                if (otherDistance < nearestDistance) {
                    nearest = other;
                    nearestDistance = otherDistance;
                }
            }

            return Optional.of(nearest);
        }
    }

    public int distanceSquared( Point p2) {
        int deltaX = this.x - p2.x;
        int deltaY = this.y - p2.y;

        return deltaX * deltaX + deltaY * deltaY;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        return other instanceof Point && ((Point) other).x == this.x && ((Point) other).y == this.y;
    }

    public int hashCode() {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }

    // Returns a random list of points corresponding to grass tiles within a 5x5 square based
    // on the position of currPoint
    public List<Point> chooseRandomNearbyGrassTile(WorldModel world) {
        Random rand = new Random();

        Point currPoint = this;

        // Define the range of points (any tile within the 5x5 tile range around slime at center)
        int minX = currPoint.getX() - 2;
        int maxX = currPoint.getX() + 2;
        int minY = currPoint.getY() - 2;
        int maxY = currPoint.getY() + 2;

        // Generate between 1 to 3 points
        int numPoints = rand.nextInt(3) + 1;
        List<Point> nearbyPoints = new ArrayList<>();

        for (int i = 0; i < numPoints; i++) {
            Point nPoint;
            do{
                int newX = Math.max(minX, Math.min(maxX, rand.nextInt(5) + minX));
                int newY = Math.max(minY, Math.min(maxY, rand.nextInt(5) + minY));
                nPoint = new Point(newX, newY);

                // Ensure the generated points are within the bounds of the grid
            } while(!world.withinBounds(nPoint) || world.isOccupied(nPoint));

            // Exclude non-grass tiles
            if(world.getBackgroundCell(nPoint).getId().equals("grass")){
                nearbyPoints.add(nPoint);
            }

        }

        return nearbyPoints;

    }

}
