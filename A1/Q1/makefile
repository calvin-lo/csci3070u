JC = javac
JVM = java
JFLAGS = -Xmx2G

FILE=
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES =	mergeSort.java quickSort.java heapSort.java

#MAIN = mergeSort
#MAIN = quickSort
MAIN = heapSort

default: classes

classes: $(CLASSES:.java=.class)
run: classes
	@$(foreach i, 1 2 3 4 5 6 7 8 9 10, ($(foreach a, 1 2 3 4 5 6 7 8 9 10 , $(JVM) $(JFLAGS) $(MAIN);));)
clean:
	$(RM) *.class