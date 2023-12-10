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

## Event
### Description:
1. Clicking any tile in the world triggers the event.
2. When the event is triggered, a slime and 1-3 poisoned tiles attempt to spawn (see requirements below).
3. The only affected entity is the Dude. Their movement speed is either increased or decreased (see cases below).
4. The new entity is a slime and it should have a jumping animation and be constantly moving to find the nearest tree.

### Visualization:
- When the user clicks on any tile: a slime attempts to spawn and 1, 2, or 3 grass tiles (in a 5x5 tile area with the clicked position at the center) are affected (poisoned) by the event.
- Whenever a Dude leaves a poisoned tile, that tile is turned back to normal effectively "transferring" the poison to the Dude.
- Whenever a Fairy moves over a poisoned tile, that tile is "cured" and turned back to normal.
- Only grass tiles can become poisoned (flowers, dirt, bridges, etc. cannot be poisoned).

### Effect:
This event can have two possible effects only on the Dude: 
1. Buff: If a Dude successfully reaches a slime before it hides in a tree, it removes the slime from the world, the Dude changes in behavior by gaining an increase in movement speed, and changes in apperance to a teal color. When there are no longer any slimes in the world, the Dude goes back to chopping wood and keeps its increased movement speed as long as it doesn't step on a poisoned tile.
2. Debuff: If a Dude moves to a tile that is poisoned, then the Dude becomes poisoned. This causes the Dude to change in behavior by decreasing in movement speed and changing in appearance by turning to a red color.

### New Entity:
- Upon triggering the event, a new slime entity attempts to spawn (can only spawn on non-occupied tile). If the tile is occupied, a message is provided in the console with the appropriate message.
- This new entity animates and uses A* pathfinding to locate the nearest tree from its current position. The goal for the slime is to reach the nearest tree without being caught by the Dude. The slime is either removed if it reaches a tree or if a Dude catches it.

## Pathing
- All moving entities use the A* search algorithm as their main pathing strategy. A single-step algorithm is also included if a less effective pathing strategy is desired.
