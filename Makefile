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
TESTCLIENTSOURCE    = $(wildcard test/src/sudokutest/*.java)
TESTSSOURCE         = $(wildcard test/src/sudokutest/tests/*.java)
TESTCLIENTCLASSES   = $(patsubst test/src/sudokutest/%.java,test/bin/sudokutest/%.class,$(TESTCLIENTSOURCE))
TESTSCLASSES        = $(patsubst test/src/sudokutest/tests/%.java,test/bin/sudokutest/tests/%.class,$(TESTSSOURCE))
TESTSRC             = $(TESTCLIENTSOURCE) $(TESTSSOURCE)
TESTCLASSES         = $(TESTCLIENTCLASSES) $(TESTSCLASSES)
TESTCLIENTCLASSPATH = test/bin/sudokutest/*.class
TESTSCLASSPATH      = test/bin/sudokutest/tests/*.class
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
TESTOUTPUTPATH       = test-output/

all: $(MAINJARFILE)

jar: all
	rm $(MAINCLASSPATH)

$(MAINJARFILE): $(MAINCLASSES)
	printf "%s\n" "Main-class: $(MAINCLASS)" > Manifest
	jar cvfm $(MAINJARFILE) Manifest -C $(MAINBINPATH) .
	rm Manifest
	chmod +x $(MAINJARFILE)

$(MAINCLASSES): $(MAINSRC)
	mkdir $(MAINBINPATH) || true
	javac -Xlint $(MAINSRC) -sourcepath $(MAINSRCPATH) -d bin

test: $(MAINJARFILE) $(TESTJARFILE)

$(TESTJARFILE): $(TESTCLASSES)
	printf "%s\n" "Main-class: $(TESTMAINCLASS)" "Class-Path: $(MAINBINPATH) $(TESTNGPATH) $(JCOMMANDERPATH)" > Manifest
	jar cvfm $(TESTJARFILE) Manifest -C $(TESTBINPATH) .
	rm Manifest
	chmod +x $(TESTJARFILE)

$(TESTCLASSES): $(TESTSRC)
	mkdir $(TESTBINPATH) || true
	javac -cp "$(MAINBINPATH):$(TESTNGPATH):$(JCOMMANDERPATH)" -Xlint $(TESTSRC) -sourcepath $(TESTSRCPATH) -d $(TESTBINPATH)

.PHONY: clean
clean:
	rm -r -d $(BINPATHS) $(JARFILES) $(TESTOUTPUTPATH) || true