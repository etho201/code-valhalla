## Notes

How to structure a game...

1. The application layer should contain all of your helpers and generic subsystems, things like random number generators, text parsers, file access modules, mesh loaders, etc.

1. The game logic layer should implement all of the rules of your game, and be responsible for maintaining canonical state. Basically, when you press the "W" on the keyboard to move forward, the game logic layer should receive MOVE_FORWARD_REQUEST from the UI.

1. The presentation layer should be responsible for two things: getting input, and rendering your world. When it gets input, like the "W" key, it should map that to an action, and send that to the game logic layer to be processed. Then, it should render the world based on whatever the game logic told it to do.
