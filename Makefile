#Main program source macros
GUISOURCE           = $(wildcard src/sudoku/*.java)
SOLVERSOURCE        = $(wildcard src/sudoku/solver/*.java)
GUICLASSES          = $(patsubst src/sudoku/%.java, bin/sudoku/%.class,$(GUISOURCE))
SOLVERCLASSES       = $(patsubst src/sudoku/solver/%.java, bin/sudoku/solver/%.class,$(SOLVERSOURCE))
MAINSRC             = $(GUISOURCE) $(SOLVERSOURCE)
MAINCLASSES         = $(GUICLASSES) $(SOLVERCLASSES)
GUICLASSPATH        = bin/sudoku/*.class
SOLVERCLASSPATH     = bin/sudoku/solver/*.class
MAINCLASSPATH       = $(GUICLASSPATH) $(SOLVERCLASSPATH)
MAINCLASS           = sudoku.SudokuGUI
MAINJARFILE         = Sudoku.jar
MAINSRCPATH         = src/
MAINBINPATH         = bin/

#Test harness source macros
TESTSOURCE          = $(wildcard test/src/sudokutest/*.java)
TESTCLASSES         = $(patsubst test/src/sudokutest/%.java,test/bin/sudokutest/%.class,$(TESTSOURCE))
TESTCLIENTCLASSPATH = test/bin/sudokutest/*.class
TESTCLASSPATH       = $(TESTCLIENTCLASSPATH)
TESTMAINCLASS       = sudokutest.SudokuTestClient
TESTJARFILE         = SudokuTestClient.jar
TESTSRCPATH         = test/src/
TESTBINPATH         = test/bin/

#Test Harness Dependencies Macros
TESTNGPATH          = test/dependencies/testng.jar
JCOMMANDERPATH      = test/dependencies/jcommander.jar

#General Macros
CLASSESPATH         = $(MAINCLASSPATH) $(TESTCLASSPATH)
BINPATHS            = $(MAINBINPATH) $(TESTBINPATH)
JARFILES            = $(MAINJARFILE) $(TESTJARFILE) 

all: $(MAINJARFILE)

jar: all
	rm $(MAINCLASSPATH)

$(MAINJARFILE): $(MAINCLASSES)
	printf "%s\n" "Main-class: $(MAINCLASS)" > Manifest
	jar cvfm $(MAINJARFILE) Manifest -C $(MAINBINPATH) .
	rm Manifest
	chmod +x $(MAINJARFILE)

$(MAINCLASSES): $(MAINSRC)
	mkdir $(MAINBINPATH)
	javac -Xlint $(MAINSRC) -sourcepath $(MAINSRCPATH) -d bin

test: $(MAINJARFILE) $(TESTJARFILE)

$(TESTJARFILE): $(TESTCLASSES)
	printf "%s\n" "Main-class: $(TESTMAINCLASS)" "Class-Path: $(TESTNGPATH) $(JCOMMANDERPATH)" > Manifest
	jar cvfm $(TESTJARFILE) Manifest -C $(TESTBINPATH) .
	rm Manifest
	chmod +x $(TESTJARFILE)

$(TESTCLASSES): $(TESTSOURCE)
	mkdir $(TESTBINPATH)
	javac -cp "$(TESTNGPATH):$(JCOMMANDERPATH)" -Xlint $(TESTSOURCE) -sourcepath $(TESTSRCPATH) -d $(TESTBINPATH)

.PHONY: clean
clean:
	rm -r -d $(BINPATHS) $(JARFILES) || true