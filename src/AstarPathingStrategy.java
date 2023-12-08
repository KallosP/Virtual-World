import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AstarPathingStrategy implements PathingStrategy{

    public List<Point> computePath(Point start, Point end, Predicate<Point> canPassThrough, BiPredicate<Point, Point> withinReach, Function<Point, Stream<Point>> potentialNeighbors) {

        // List to store path
        List<Point> path = new LinkedList<>();
        // List to store possible points in path
        List<Point> openList = new LinkedList<>();
        // List to store visited points
        List<Point> closedList = new LinkedList<>();
        // Add the start point
        openList.add(start);

        // Search all points in world until no points left or path is found
        while(!openList.isEmpty()){
            // Initialize current
            Point currPoint = openList.get(0);

            // Update currPoint to be the point with lowest f cost in openList
            for(Point pt : openList){
                if( (pt.g + pt.h) < (currPoint.g + currPoint.h) ){
                    currPoint = pt;
                }
                // Check g-cost next
                else if(pt.g < currPoint.g && ((pt.g + pt.h) == (currPoint.g + currPoint.h))){
                    currPoint = pt;
                }
            }

            // If end is reached return path by backtracking via parents
            if(withinReach.test(currPoint, end)){
                // Ensure current exists and not to add the start point to the path
                while(currPoint != null && !(currPoint.equals(start))) {
                    path.add(0, currPoint);
                    currPoint = currPoint.parent;
                }
                return path;
            }

            // Store all points surrounding currPoint
            List<Point> adjPoints = potentialNeighbors.apply(currPoint)
                    // Filter out points that can't be traversed
                    .filter(canPassThrough)
                    // Filter out points that are in closedList
                    .filter(neighbor -> !(closedList.contains(neighbor)))
                    // Return as list
                    .toList();

            // Manage the costs for the surrounding points
            for(Point pt : adjPoints) {
                // Initialize new potential g cost
                int newG = currPoint.g + 1;
                // Check if the point is already in openList or
                // if the newly caclulated g cost is less than
                // the currently stored g cost
                if(!openList.contains(pt) || newG < pt.g) {
                    // Update to new g cost
                    pt.g = newG;
                    // Update the parent
                    pt.setParent(currPoint);
                    // If not already in openList calculate h cost and add it
                    if(!openList.contains(pt)){
                        pt.h = calcHCost(pt, end);
                        openList.add(pt);
                    }
                }

            }

            // Update openList and closedList
            openList.remove(currPoint);
            closedList.add(currPoint);
        }

        // No path found
        return path;

    }

    // Calculate h-cost
    private int calcHCost(Point pt, Point end) {
        return (int) Math.sqrt( Math.pow((end.getX() - pt.getX()), 2) + Math.pow((end.getY() - pt.getY()), 2));
    }


}
