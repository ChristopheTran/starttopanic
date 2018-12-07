# Start To Panic

GitHub Repo: https://github.com/RahulAnilkumar8/starttopanic

Start To Panic is a turn based Plants Vs Zombies game developed using Java. It can also play in real time. 
The current version of the application plays the game in a GUI environment 
using point and click input from the player to determine the moves. 
The game has the player facing waves of zombies ensuring that the zombies are unable to reach the player who is to the left of the board. 
Zombies must be destroyed by planting mutant plants who are able to attack, defend and harvest sunlight. This game was made for SYSC 3110 at Carleton University

## Get Started

Run the Controller class to open the gui and start the game. For UML design and documentation can be found in the documentation folder. The user manual with instructions on how to play the game can also be found there.

## Version

Version 4.0 

## Known Issues

During the development phase, it was decided that the game would allow users to undo and redo complete turns rather than one specific action. This was done, as part of our games design, it was determined that players would be able to regain sunpoints when removing plants. Due to this, it would not make sense for balancing purposes to simply add an undo and redo potting option. Hence the player is able to undo and redo complete turns. Game over pops up twice for real time implemenation.


## Deliverables

* Source Code
  * src folder
    * Consists of the following Packages
      * entity - Each type of entitiy, plants/zombies
      * game  - game logic
      * level - Level and Level Builder for levels
      * view - View
      * control - Controllers and Listeners
      * test - All test cases
* Documentation & UML Diagrams
  * documentation/Milestone4 folder
    * ClassDiagram folder
      * Milestone4ClassDiagram.jpg
    * SequenceDiagram Folder
      *  LevelBuilderSequenceDiagram.jpg
      *  LoadLevelSequenceDiagram.jpg
      *  LoadandSaveGameSequenceDiagram.jpg
      *  RealTimeSequenceDiagram.jpg
    * UserManual.pdf
    * Milestone4_Documentation.pdf
    
## Files included in CULearn Zip folder
 * source code (including tests)
 * UML class diagram
 * UML sequence diagrams
 * User Manual
 * Documentation
 * readme file

## RoadMap
- [x] Create model
- [x] Create a text-based game
- [x] GUI-based version, including MVC
- [x] Unit test for model
- [x] Feature various types of plants and zombies
- [x] Unlimited undo/redo feature
- [x] Unit test for the control and view
- [x] Add Menu bar, cheat
- [x] Save/ Load feature
- [x] Game level builder
- [x] Real time version (if time allows)



## Authors

* Rahul Anilkumar - Worked on Serialization, Game Logic, Tests, User Manual, Documentation
* Christopher Wang - Worked on View Logic, Level XML, Game Logic, Level Logic, Documentation
* Christophe Tran - Worked on Level View, Level Controller, Tests, Documentation
* Thomas Leung - Worked on Real Time Logic, Tests, UML Diagrams, Documentation
