#Main program source macros
GUISOURCE       = $(wildcard *.java)
GUICLASSES      = $(patsubst %.java,%.class,$(GUISOURCE))
SOLVERSOURCE    = $(wildcard sudokusolver/*.java)
SOLVERCLASSES   = $(patsubst sudokusolver/%.java,sudokusolver/%.class,$(SOLVERSOURCE))
JAVASRC         = $(GUISOURCE) $(SOLVERSOURCE)
CLASSES         = $(GUICLASSES) $(SOLVERCLASSES)

#Jar Macros
GUICLASSPATH    = *.class
SOLVERCLASSPATH = sudokusolver/*.class
MAINCLASS       = SudokuGUI
JARFILE         = Sudoku.jar

#General Macros
SOURCES         = $(JAVASRC) README Makefile
CLASSESPATH     = $(GUICLASSPATH) $(SOLVERCLASSPATH)

all: $(JARFILE)

jar: all
	rm $(GUICLASSPATH)

$(JARFILE): $(GUICLASSES) $(SOLVERCLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(GUICLASSPATH) $(SOLVERCLASSPATH)
	rm Manifest
	chmod +x $(JARFILE)

$(GUICLASSES): $(GUISOURCE)
	javac -Xlint $(GUISOURCE)

$(SOLVERCLASSES): $(SOLVERSOURCE)
	javac -Xlint $(SOLVERSOURCE)

.PHONY: clean
clean:
	rm $(CLASSESPATH) $(JARFILE)