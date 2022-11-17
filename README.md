# Addition and subtraction using abstract classes
This is an assignment from the course 'Java advanced programing', 20554 at the Open University of Israel
Assignment 11, Q2 (semester 2023A).

The game of life is a famous zero player game / math problem by John Conway.
More info can be found in [Wikipedia](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

The purpose of this assignment is to practice the use of abstract classes and functions.

To run the project:
1.   Clone
2.   cd into the cloned directory
3.   run.bat

To re-compile the project yourself:
1. cd into src->game_of_life directory
2. List all the files in the directory: `dir /s /B *.java > sources.txt`
3. Compile: `javac -d . @sources.txt`
4. Copy .fxml file into the newly created folder which contains the .class files
5. Run using the command: `java game_of_life.Game`