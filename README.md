# Virtual-World

A simulated 2D world with a user-triggered event and various entities that have unique roles, actions, and animations. 

## Entities/Rules
| Image | Type | Role |
| ----------- | ----------- | ----------- |
| ![dude3](https://github.com/KallosP/Virtual-World/blob/main/images/dude3.png?raw=true) ![poisoned_dude3](https://github.com/KallosP/Virtual-World/blob/main/images/poisoned_dude3.png?raw=true) ![speed_dude3](https://github.com/KallosP/Virtual-World/blob/main/images/speed_dude3.png?raw=true)| DudeFull | *Attempts to navigate to Slime if present.* Is a wood chopping entity who has already collected as much wood as they can carry, navigates to the nearest House to drop off collected wood, transforms into a DudeNotFull once task is completed. |
| ![dude3](https://github.com/KallosP/Virtual-World/blob/main/images/dude3.png?raw=true) ![poisoned_dude3](https://github.com/KallosP/Virtual-World/blob/main/images/poisoned_dude3.png?raw=true) ![speed_dude3](https://github.com/KallosP/Virtual-World/blob/main/images/speed_dude3.png?raw=true)| DudeNotFull | *Attempts to navigate to Slime if present.* Navigates to nearest Tree or Sapling in search of something to chop down, will search until it has reached its resource limit moving to multiple Trees or Saplings if necessary, transforms into a DudeFull once it hits its resource limit. |
| ![slime2](https://github.com/KallosP/Virtual-World/blob/main/images/slime2.png?raw=true) | Slime | Navigates to nearest Tree, becomes hidden upon successfully reaching destination. |
| ![house](https://github.com/KallosP/Virtual-World/blob/main/images/house.png?raw=true) | House | Remains static. Does not animate or complete any actions. Is the destination for DudeFull entities. |
| ![tree0](https://github.com/KallosP/Virtual-World/blob/main/images/tree0.png?raw=true) | Tree | Animates and has health, if its health is depleted, transforms into a Stump. |
| ![stump](https://github.com/KallosP/Virtual-World/blob/main/images/stump.png?raw=true) | Stump | Does not animate or complete any actions. Acts as a destination for Fairies. |
| ![sapling4](https://github.com/KallosP/Virtual-World/blob/main/images/sapling4.png?raw=true) | Sapling | Animate and grow into Trees once they hit their designated health limit. DudeNotFull entities can upset a Sapling's growth by chopping it down and depleting its health. |
| ![fairy7](https://github.com/KallosP/Virtual-World/blob/main/images/fairy7.png?raw=true) | Fairy | Navigates toward the nearest Stump and turns it into a Sapling. |
| ![water0](https://github.com/KallosP/Virtual-World/blob/main/images/water0.png?raw=true) | Obstacle | Tiles which entities cannot move through. |
| ![flowers](https://github.com/KallosP/Virtual-World/blob/main/images/flowers.png?raw=true) ![grass](https://github.com/KallosP/Virtual-World/blob/main/images/grass.png?raw=true) ![dirt_vert_right](https://github.com/KallosP/Virtual-World/blob/main/images/dirt_vert_right.png?raw=true) ![bridge](https://github.com/KallosP/Virtual-World/blob/main/images/bridge.png?raw=true) ![poisoned_grass](https://github.com/KallosP/Virtual-World/blob/main/images/poisoned_grass.png?raw=true)| Background | Tiles which entities can move through. |
