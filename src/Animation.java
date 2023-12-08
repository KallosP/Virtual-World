public class Animation extends Action{

    private int repeatCount;
    public Animation(Entity entity, int repeatCount) {
        super(entity);
        this.repeatCount = repeatCount;
    }

    @Override
    protected void executeAction(EventScheduler scheduler) {
        this.entity.nextImage();

        if(this.entity.getKind().equals(Stump.class) || this.entity.getKind().equals(House.class)){
            throw new UnsupportedOperationException(
                    String.format("getAnimationPeriod not supported for %s", this.entity.getKind()));
        }

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity, new Animation(this.entity, Math.max(this.repeatCount - 1, 0)),
                    ((AnimatedEntity)this.entity).getAnimationPeriod());
        }

    }
}
