#Main program source macros
GUISOURCE              = $(wildcard src/sudoku/*.java)
SOLVERSOURCE           = $(wildcard src/sudoku/solver/*.java)
BOARDSOURCE            = $(wildcard src/sudoku/board/*.java)
SOLVERCONTEXTSOURCE    = $(wildcard src/sudoku/solver/solvercontext/*.java)
RULESSOURCE            = $(wildcard src/sudoku/solver/rules/*.java)
ACTIONSSOURCE          = $(wildcard src/sudoku/solver/actions/*.java)
GUICLASSES             = $(patsubst src/sudoku/%.java, bin/sudoku/%.class,$(GUISOURCE))
SOLVERCLASSES          = $(patsubst src/sudoku/solver/%.java, bin/sudoku/solver/%.class,$(SOLVERSOURCE))
BOARDCLASSES           = $(patsubst src/sudoku/board/%.java, bin/sudoku/board/%.class,$(BOARDSOURCE))
SOLVERCONTEXTCLASSES   = $(patsubst src/sudoku/solver/solvercontext/%.java, bin/sudoku/solver/solvercontext/%.class,$(SOLVERCONTEXTSOURCE))
RULESCLASSES           = $(patsubst src/sudoku/solver/rules/%.java, bin/sudoku/solver/rules/%.class,$(RULESSOURCE))
ACTIONSCLASSES         = $(patsubst src/sudoku/solver/actions/%.java, bin/sudoku/solver/actions/%.class,$(ACTIONSSOURCE))
MAINSRC                = $(GUISOURCE) $(SOLVERSOURCE) $(BOARDSOURCE) $(SOLVERCONTEXTSOURCE) $(RULESSOURCE) $(ACTIONSSOURCE)
MAINCLASSES            = $(GUICLASSES) $(SOLVERCLASSES) $(BOARDCLASSES) $(SOLVERCONTEXTCLASSES) $(RULESCLASSES) $(ACTIONSCLASSES)
GUICLASSPATH           = bin/sudoku/*.class
SOLVERCLASSPATH        = bin/sudoku/solver/*.class
BOARDCLASSPATH         = bin/sudoku/board/*.class
SOLVERCONTEXTCLASSPATH = bin/sudoku/solver/solvercontext/*.class
RULESCLASSPATH         = bin/sudoku/solver/rules/*.class
ACTIONSCLASSPATH       = bin/sudoku/solver/actions/*.class
MAINCLASSPATH          = $(GUICLASSPATH) $(SOLVERCLASSPATH) $(BOARDCLASSPATH) $(SOLVERCONTEXTCLASSPATH) $(RULESCLASSPATH) $(ACTIONSCLASSPATH)
MAINCLASS              = sudoku.SudokuGUI
MAINJARFILE            = Sudoku.jar
MAINSRCPATH            = src/
MAINBINPATH            = bin/
MAINDATAPATH           = data/

#Main program dependencies macros
MAINDEPENDENCIES       = $(LOGBACKCLASSICPATH) $(LOGBACKCOREPATH) $(SLF4JPATH)
LOGBACKCLASSICPATH     = dependencies/logback-classic.jar
LOGBACKCOREPATH        = dependencies/logback-core.jar
SLF4JPATH              = dependencies/slf4j-api.jar

#Test harness source macros
TESTCLIENTSOURCE       = $(wildcard test/src/sudokutest/*.java)
TESTSSOURCE            = $(wildcard test/src/sudokutest/tests/*.java)
TESTCLIENTCLASSES      = $(patsubst test/src/sudokutest/%.java,test/bin/sudokutest/%.class,$(TESTCLIENTSOURCE))
TESTSCLASSES           = $(patsubst test/src/sudokutest/tests/%.java,test/bin/sudokutest/tests/%.class,$(TESTSSOURCE))
TESTSRC                = $(TESTCLIENTSOURCE) $(TESTSSOURCE)
TESTCLASSES            = $(TESTCLIENTCLASSES) $(TESTSCLASSES)
TESTCLIENTCLASSPATH    = test/bin/sudokutest/*.class
TESTSCLASSPATH         = test/bin/sudokutest/tests/*.class
TESTCLASSPATH          = $(TESTCLIENTCLASSPATH)
TESTMAINCLASS          = sudokutest.SudokuTestClient
TESTJARFILE            = SudokuTestClient.jar
TESTSRCPATH            = test/src/
TESTBINPATH            = test/bin/
TESTDATAPATH           = test/data/

#Test harness dependencies macros
TESTDEPENDENCIES       = $(TESTNGPATH) $(JCOMMANDERPATH)
TESTNGPATH             = test/dependencies/testng.jar
JCOMMANDERPATH         = test/dependencies/jcommander.jar

#General macros
CLASSESPATH            = $(MAINCLASSPATH) $(TESTCLASSPATH)
BINPATHS               = $(MAINBINPATH) $(TESTBINPATH)
JARFILES               = $(MAINJARFILE) $(TESTJARFILE) 
TESTOUTPUTPATH         = test-output/
LOGPATH                = logs/
TESTLOGPATH            = test/logs/

all: $(MAINJARFILE)

jar: all
	rm $(MAINCLASSPATH)

$(MAINJARFILE): $(MAINCLASSES)
	printf "%s\n" "Main-class: $(MAINCLASS)" "Class-Path: $(MAINDEPENDENCIES) $(MAINDATAPATH)" > Manifest
	jar cvfm $(MAINJARFILE) Manifest -C $(MAINBINPATH) .
	rm Manifest
	chmod +x $(MAINJARFILE)

$(MAINCLASSES): $(MAINSRC)
	mkdir $(MAINBINPATH) || true
	javac -cp "$(LOGBACKCLASSICPATH):$(LOGBACKCOREPATH):$(SLF4JPATH)" -Xlint $(MAINSRC) -sourcepath $(MAINSRCPATH) -d $(MAINBINPATH)

test: $(MAINJARFILE) $(TESTJARFILE)

$(TESTJARFILE): $(TESTCLASSES)
	printf "%s\n" "Main-class: $(TESTMAINCLASS)" "Class-Path: $(MAINBINPATH) $(MAINDEPENDENCIES) $(TESTDEPENDENCIES) $(TESTDATAPATH)" > Manifest
	jar cvfm $(TESTJARFILE) Manifest -C $(TESTBINPATH) .
	rm Manifest
	chmod +x $(TESTJARFILE)

$(TESTCLASSES): $(TESTSRC)
	mkdir $(TESTBINPATH) || true
	javac -cp "$(MAINBINPATH):$(LOGBACKCLASSICPATH):$(LOGBACKCOREPATH):$(SLF4JPATH):$(TESTNGPATH):$(JCOMMANDERPATH)" -Xlint $(TESTSRC) -sourcepath $(TESTSRCPATH) -d $(TESTBINPATH)

.PHONY: clean
clean:
	rm -r -d $(BINPATHS) $(JARFILES) $(TESTOUTPUTPATH) $(LOGPATH) $(TESTLOGPATH) || true