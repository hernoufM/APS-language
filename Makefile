LEXER = /usr/bin/jflex
PARSER = lib/yacc.linux -J
LEX_FILE = flex/aps1Grammaire.flex
PARS_FILE = yacc/aps1Grammaire.y
JAVAC =  javac
PARSER_DEST = src/aps1/parser
PATH_FILE = paths.txt

all: 
	$(LEXER) $(LEX_FILE) -d $(PARSER_DEST) 
	$(PARSER) -Jpackage=aps1.parser $(PARS_FILE)
	mv *.java $(PARSER_DEST)
	find -name "*.java" > $(PATH_FILE) 
	$(JAVAC) -d bin @$(PATH_FILE)
	rm $(PATH_FILE)
	[ -f "$(PARSER_DEST)/Lexer.java~" ] && rm $(PARSER_DEST)/Lexer.java~ || true

clean:
	rm -r bin/*
