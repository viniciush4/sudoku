all:
	mkdir -p bin
	rm -f bin/*.class
	javac -d bin src/*.java
	java -cp bin Sudoku
