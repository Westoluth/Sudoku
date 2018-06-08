GUISOURCE     = *.java
GUICLASSES    = *.class
SOLVERSOURCE  = sudokusolver/*.java
SOLVERCLASSES = sudokusolver/*.class
JAVASRC       = $(GUISOURCE) $(SOLVERSOURCE)
CLASSES       = $(GUICLASSES) $(SOLVERCLASSES)
MAINCLASS     = SudokuGUI
JARFILE       = Sudoku.jar
SOURCES       = $(JAVASRC) README Makefile

all: $(JARFILE)

$(JARFILE): $(GUICLASSES) $(SOLVERCLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(GUICLASSES) $(SOLVERCLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(GUICLASSES): $(GUISOURCE)
	javac -Xlint $(GUISOURCE)

$(SOLVERCLASSES): $(SOLVERSOURCE)
	javac -Xlint $(SOLVERSOURCE)

clean:
	rm $(CLASSES) $(JARFILE)