JC = javac
JVM = java
JFLAGS = -Xmx4G

FILE=
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES =	gen.java sorted.java

MAIN = gen
RUN = sorted

default: classes

classes: $(CLASSES:.java=.class)
prepare: classes
	@$(foreach i, 1 2 3 4 5 6 7 8 9 10, ($(foreach a, 1 2 3 4 5 6 7 8 9 10 , $(JVM) $(JFLAGS) $(MAIN);));)

run: classes
	$(JVM) $(JFLAGS) $(RUN)
clean:
	$(RM) *.class