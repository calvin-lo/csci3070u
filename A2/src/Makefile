JC = javac
JVM = java

FILE=
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES =	myQueue.java Graph.java B1.java B2.java B3.java B4.java


MAIN1 = B1
MAIN2 = B2
MAIN3 = B3
MAIN4 = B4

default: classes

classes: $(CLASSES:.java=.class)
B1: classes
	$(JVM)  $(MAIN1)
B2: classes
	$(JVM)  $(MAIN2)
B3: classes
	$(JVM)  $(MAIN3)
B4: classes
	$(JVM)  $(MAIN4)
clean:
	$(RM) *.class