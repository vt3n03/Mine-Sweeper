# Minesweeper Game Program

This program creates a playable Minesweeper game using a linked grid structure. It builds the board, places bombs, calculates the numbers for each cell, and updates the display through a GUI. It also supports recursion for clearing large empty regions and allows users to flag suspected bombs. :contentReference[oaicite:0]{index=0}

# Features

The game uses a two dimensional linked structure where each position stores a single node. Each grid cell has a matching GUICell that appears on the screen. The board stores bombs or empty spaces, and each GUICell stores its row, column, and number indicator. Clicking on a bomb ends the game. Clicking on an empty cell reveals its value or clears a region of zeros.

Supported behaviour

- Creates and displays a Minesweeper board  
- Randomly places bombs with a fixed or random seed  
- Computes all numbers based on surrounding bombs  
- Reveals single cells or clears full empty regions  
- Allows right click to place or remove flags  
- Tracks whether the player is still able to play  
- Throws exceptions for invalid linked grid access  

# How the Program Works

The LinkedGrid class builds the internal structure. It creates a column of linked nodes for each column of the board. Both characters and GUICell objects are stored inside these nodes. The exception class checks for invalid indices.

The BombRandomizer places random bombs on the board based on a fixed density. It picks random positions until the required number of bombs has been placed.

The Game class ties everything together. After placing bombs, it goes through the board and counts how many bombs surround each cell. The processClick method handles left clicks by revealing cells, clearing regions, or ending the game if a bomb is clicked. Right clicks toggle flags. The recursive clearing function reveals all adjoining empty cells until reaching numbered cells.

The GUI class uses Swing to draw the grid. Each GUICell responds to clicks and updates its display through icons, colours, or text. The display changes as the user plays the game.

```vbnet
Test 1 passed
Test 2 passed
Test 3 passed
Test 4 passed
Test 5 passed
Test 6 passed
Test 7 passed
```

