public class Activity extends Action {

    private WorldModel world;
    private ImageStore imageStore;
    public Activity(Entity entity, WorldModel world, ImageStore imageStore){
        super(entity);
        this.world = world;
        this.imageStore = imageStore;
    }

    @Override
    public void executeAction(EventScheduler scheduler) {

        try{
            ((ActiveEntity)this.entity).executeActivity(this.world, this.imageStore, scheduler);
        } catch(ClassCastException e){
            throw new ClassCastException(String.format("executeActivityAction not supported for %s",
                    this.entity.getKind()));
        }
    }
}
