#Main program source macros
GUISOURCE        = $(wildcard *.java)
GUICLASSES       = $(patsubst %.java,%.class,$(GUISOURCE))
SOLVERSOURCE     = $(wildcard sudokusolver/*.java)
SOLVERCLASSES    = $(patsubst sudokusolver/%.java,sudokusolver/%.class,$(SOLVERSOURCE))
JAVASRC          = $(GUISOURCE) $(SOLVERSOURCE)
CLASSES          = $(GUICLASSES) $(SOLVERCLASSES)

#Main Jar Macros
GUICLASSPATH     = *.class
SOLVERCLASSPATH  = sudokusolver/*.class
MAINCLASS        = SudokuGUI
JARFILE          = Sudoku.jar

#Test harness source macros
TESTSOURCE       = $(wildcard sudokutest/*.java)
TESTCLASSES      = $(patsubst sudokutest/%.java,sudokutest/%.class,$(TESTSOURCE))
TESTCLASSPATH    = sudokutest/*.class
TESTMAINCLASS    = sudokutest.SudokuTestClient
TESTJARFILE      = SudokuTestClient.jar

#General Macros
SOURCES          = $(JAVASRC) README Makefile
CLASSESPATH      = $(GUICLASSPATH) $(SOLVERCLASSPATH) $(TESTCLASSPATH)

#Dependencies Macros
TESTNGPATH       = dependencies/testng.jar

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

test: $(TESTJARFILE) $(JARFILE)

$(TESTJARFILE): $(TESTCLASSES)
	printf "%s\n" "Main-class: $(TESTMAINCLASS)" "Class-Path: dependencies/testng.jar dependencies/jcommander.jar" > Manifest
	jar cvfm $(TESTJARFILE) Manifest $(TESTCLASSPATH)
	rm Manifest
	chmod +x $(TESTJARFILE)

$(TESTCLASSES): $(TESTSOURCE)
	javac -cp "dependencies/testng.jar:dependencies/jcommander.jar" -Xlint $(TESTSOURCE)

.PHONY: clean
clean:
	rm $(CLASSESPATH) $(JARFILE) $(TESTJARFILE) || true