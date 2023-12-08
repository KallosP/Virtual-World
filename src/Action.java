/**
 * An action that can be taken by an entity
 */
public abstract class Action {
    protected Entity entity;

    public Action(Entity entity){
        this.entity = entity;
    }

    protected abstract void executeAction(EventScheduler scheduler);

}
