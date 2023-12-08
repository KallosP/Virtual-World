import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public abstract class Entity {
    public static final int NUM_PROPERTIES = 4;

    protected String id;
    protected Point position;
    protected List<PImage> images;
    protected int imageIndex;

    public Entity(String id, Point position, List<PImage> images){
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }

    public int getImageIndex() {  return imageIndex;  }

    public Object getKind() { return this.getClass(); }

    public String getId() {  return id; }

    public Point getPosition() { return position; }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void nextImage() {
        this.imageIndex = this.imageIndex + 1;
    }

    public PImage getCurrentImage() { return this.images.get(this.imageIndex % this.images.size()); }
    /**
     * Helper method for testing. Preserve this functionality while refactoring.
     */
    public String log(){
        return this.id.isEmpty() ? null :
                String.format("%s %d %d %d", this.id, this.position.getX(),
                        this.position.getY(), this.imageIndex);
    }
}
