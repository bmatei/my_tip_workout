
ECHO=@echo
RM=@rm -vf
RUN_TESTS=java_run_tests.sh
RUN_PACKS=java_packs.sh
RUN_DEPS=java_deps.sh

PACKS=$(shell $(RUN_PACKS) .)
TMP=$(addsuffix /*.java,$(PACKS))
CLEANUP=$(patsubst %.java,%.class,$(TMP))
SRC=$(wildcard $(TMP))
OBJ=$(patsubst %.java,%.class,$(SRC))
$(shell $(RUN_DEPS) > make.d)


.PHONY: all
all: $(OBJ)

.PHONY: dev
dev: OPTFLAGS+=-Xlint:unchecked
dev: all tests

%.class: %.java
	javac $(OPTFLAGS) $<

.PHONY: tests
tests:
	$(RUN_TESTS)


.PHONY: debug
debug:
	$(ECHO) "PACKS='$(PACKS)'"
	$(ECHO) "SRC='$(SRC)'"
	$(ECHO) "OBJ='$(OBJ)'"
	$(ECHO) "TMP='$(TMP)'"
	$(ECHO) "CLEANUP='$(CLEANUP)'"


.PHONY: clean
clean:
	$(RM) $(CLEANUP) make.d

include make.d