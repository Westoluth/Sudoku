JAVASRC    = *.java
CLASSES    = *.class
MAINCLASS  = SudokuGUI
JARFILE    = Sudoku.jar
SOURCES    = $(JAVASRC) README Makefile

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)