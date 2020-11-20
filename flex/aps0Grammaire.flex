package aps0.parser;

%%

%class Lexer
%public
%unicode
%line
%column
%function next_token
%integer

%byaccj

%{
	private Parser parser;
	
	public Lexer(java.io.Reader r, Parser parser) {
		this(r);
		this.parser = parser;
	}
%}

%eofval{
    return Parser.EOF;
%eofval}

/* regulars expressions */

entier = -?[0-9]+
identifier = [a-z][a-zA-Z0-9]*
eol  = \n | \r | \r\n

%%

/* booleans */
"true" { return Parser.TRUE; }
"false" { return  Parser.FALSE; }

/* operators */
"add"  { return Parser.ADD; }
"sub"  { return Parser.SUB; }
"mul"  { return Parser.MUL; }
"div"  { return Parser.DIV; }
"and"  { return Parser.AND; }
"or"  { return Parser.OR; }
"eq"  { return Parser.EQ; }
"lt"  { return Parser.LT; }
"not" { return Parser.NOT; }

/* types */
"bool" { return Parser.BOOL; }
"int" { return Parser.INT; }

/* parenthesis */
"("  { return Parser.LPAR; }
")"  { return Parser.RPAR; }

/* brackets */
"["  { return Parser.LBAR; }
"]"  { return Parser.RBAR; }

/* semicolon */
";"  { return Parser.SEMCOL; }

/* two-points */
":"  { return Parser.TWOPOINTS; }

/* comma */
","  { return Parser.COMMA; }

/* STAR */
"*"  { return Parser.STAR; }

/* arrow */
"->"  { return Parser.ARROW; }

/* newline */
{eol}+   { /* ignore */ } 

/* if */
"if" { return Parser.IF; }

/* entiers */
{entier}  {parser.yylval = new ParserVal(Integer.parseInt(yytext()));
	       return Parser.NUM; }

/* identifier */
{identifier} { parser.yylval = new ParserVal(yytext());
	           return Parser.IDENT;}

/* for functions */
"CONST"  { return Parser.CONST; }
"FUN"  { return Parser.FUN; }
"REC"  { return Parser.REC; }

/* to print */
"ECHO"  { return Parser.ECHO; }

/* whitespace */
[ \t]+ { }

\b     { System.err.println("Sorry, backspace doesn’t work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character ’"+yytext()+"’"); return -1; }
