# Start To Panic

Start To Panic is a turn based Plants Vs Zombies game developed using Java. 
The current version of the application plays the game in a GUI environment 
using point and click input from the player to determine the moves. 
The game has the player facing waves of zombies ensuring that the zombies are unable to reach the player who is to the left of the board. 
Zombies must be destroyed by planting mutant plants who are able to attack, defend and harvest sunlight. This game was made for SYSC 3110 at Carleton University

## Get Started

Run the Controller class to open the gui and start the game. For UML design and documentation can be found in the documentation folder. The user manual with instructions on how to play the game can also be found there.

## Version

Version 3.0 

## Known Issues

During the development phase, it was decided that the game would allow users to undo and redo complete turns rather than one specific action. This was done, as part of our games design, it was determined that players would be able to regain sunpoints when removing plants. Due to this, it would not make sense for balancing purposes to simply add an undo and redo potting option. Hence the player is able to undo and redo complete turns.

## Deliverables

* Source Code
  * src folder
    * Consists of the following Packages
      * entity - Each type of entitiy, plants/zombies
      * game  - game logic
      * level - Level and Tile for levels
      * view - View
      * control - Controllers and Listeners
      * test - All test cases
* Documentation & UML Diagrams
  * Documentation folder
    * Design Documentation
      * DocumentationForChanges.pdf
    * User Manual
      * UserManual.pdf
    * UML Diagrams
      * SequenceDiagramFor....jpg
      * UMLClassDiagram.jpg

## Authors

* Rahul Anilkumar - Worked on Entities, Game Logic, Tests, Readme, User Manual
* Christopher Wang - Worked on Undo/Redo, Level, Entities, Game Logic
* Christophe Tran - Worked on Controllers, Game Logic, Tests, Documentation
* Thomas Leung - Worked on View, Listeners, Tests, UML Diagrams
