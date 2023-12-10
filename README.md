# Virtual-World

A simulated 2D world wtih various entities that have their own unique roles. 

## Entities
| Image | Type |
| ----------- | ----------- |
| ![dude3](https://github.com/KallosP/Virtual-World/blob/main/images/dude3.png?raw=true) ![poisoned_dude3](https://github.com/KallosP/Virtual-World/blob/main/images/poisoned_dude3.png?raw=true) ![speed_dude3](https://github.com/KallosP/Virtual-World/blob/main/images/speed_dude3.png?raw=true)| DudeFull/NotFull |
| ![slime2](https://github.com/KallosP/Virtual-World/blob/main/images/slime2.png?raw=true) | Slime |
| ![house](https://github.com/KallosP/Virtual-World/blob/main/images/house.png?raw=true) | House |
| ![tree0](https://github.com/KallosP/Virtual-World/blob/main/images/tree0.png?raw=true) | Tree |
| ![stump](https://github.com/KallosP/Virtual-World/blob/main/images/stump.png?raw=true) | Stump |
| ![sapling4](https://github.com/KallosP/Virtual-World/blob/main/images/sapling4.png?raw=true) | Sapling |
| ![fairy7](https://github.com/KallosP/Virtual-World/blob/main/images/fairy7.png?raw=true) | Fairy |
| ![water0](https://github.com/KallosP/Virtual-World/blob/main/images/water0.png?raw=true) | Obstacle |
| ![flowers](https://github.com/KallosP/Virtual-World/blob/main/images/flowers.png?raw=true) ![grass](https://github.com/KallosP/Virtual-World/blob/main/images/grass.png?raw=true) ![dirt_vert_right](https://github.com/KallosP/Virtual-World/blob/main/images/dirt_vert_right.png?raw=true) ![bridge](https://github.com/KallosP/Virtual-World/blob/main/images/bridge.png?raw=true) ![poisoned_grass](https://github.com/KallosP/Virtual-World/blob/main/images/poisoned_grass.png?raw=true)| Background |

### Roles
- **DudeFull:** Is a wood chopping entity who has already collected as much wood as they can carry, navigates to the nearest House to drop off collected wood, transforms into a DudeNotFull once task is completed.
- **DudeNotFull:** Navigates to nearest Tree or Sapling in search of something to chop down, will search until it has reached its resource limit moving to multiple Trees or Saplings if necessary, transforms into a DudeFull once it hits its resource limit.
- **Dude:** Attempts to navigate to Slime while present.
- **Slime:** Navigates to nearest Tree, becomes hidden upon successfully reaching destination.
- **House:** Remains static. Does not animate or complete any actions. Is the destination for DUDE_FULL entities.
