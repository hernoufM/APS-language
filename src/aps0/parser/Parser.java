//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package aps0.parser;



//#line 2 "yacc/aps0Grammaire.y"
import java.io.*;
import java.util.ArrayList;
import aps0.ast.statement.*;
import aps0.ast.declaration.*;
import aps0.ast.expression.*;
import aps0.ast.type.*;
import aps0.ast.*;
//#line 25 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short LBAR=257;
public final static short RBAR=258;
public final static short LPAR=259;
public final static short RPAR=260;
public final static short SEMCOL=261;
public final static short COMMA=262;
public final static short STAR=263;
public final static short ARROW=264;
public final static short TWOPOINTS=265;
public final static short EOF=266;
public final static short TRUE=267;
public final static short FALSE=268;
public final static short BOOL=269;
public final static short INT=270;
public final static short IF=271;
public final static short CONST=272;
public final static short FUN=273;
public final static short REC=274;
public final static short ECHO=275;
public final static short ADD=276;
public final static short SUB=277;
public final static short MUL=278;
public final static short DIV=279;
public final static short AND=280;
public final static short OR=281;
public final static short LT=282;
public final static short EQ=283;
public final static short NOT=284;
public final static short NUM=285;
public final static short IDENT=286;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    2,    3,    3,    3,    4,    4,
    4,    5,    5,    6,    7,    7,    8,    8,    8,    8,
    8,    8,    8,    8,   10,   10,   10,   10,   10,   10,
   10,   10,   10,    9,    9,
};
final static short yylen[] = {                            2,
    4,    1,    3,    3,    2,    4,    7,    8,    1,    1,
    5,    1,    3,    3,    1,    3,    1,    1,    1,    1,
    6,    4,    4,    4,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   18,   19,   17,   20,    5,    0,    0,
    0,    0,    9,   10,    0,    0,    0,    0,    0,    0,
    0,   25,   26,   27,   28,   29,   30,   33,   32,   31,
    0,    0,    1,    4,    3,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   14,   16,   23,    0,   35,   24,   22,   13,    0,
    0,    0,    0,   11,    0,    7,   21,    8,
};
final static short yydgoto[] = {                          2,
    6,    7,    8,   46,   47,   29,   30,   55,   56,   42,
};
final static short yysindex[] = {                      -252,
 -266,    0, -276, -270, -240, -244, -249, -232, -217, -245,
 -217, -242, -246,    0,    0,    0,    0,    0, -219, -266,
 -266, -217,    0,    0, -240, -217, -207, -210, -205, -200,
 -240,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -240, -240,    0,    0,    0, -204, -203,    0, -195, -242,
 -217, -242, -240, -240, -240, -197, -194, -217, -217, -242,
 -193,    0,    0,    0, -240,    0,    0,    0,    0, -192,
 -189, -240, -188,    0, -240,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0, -187,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -185,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -190,    0,    0,    0,    0,
    0,    0,    0,    0, -184,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    3,    0,    0,   -8,   17,    0,    4,   -5,  -40,    0,
};
final static int YYTABLESIZE=76;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         18,
   25,   57,   27,   10,    1,    3,    4,   41,    5,    9,
   12,   20,   13,   19,   66,   11,   12,   49,   13,   48,
   14,   15,   44,   45,   31,   54,   14,   15,   21,   32,
   33,   34,   35,   36,   37,   38,   39,   40,   16,   17,
   26,   22,   62,   28,   16,   17,   43,   64,   65,   50,
   70,   23,   24,   61,   51,   63,   52,   53,   58,   73,
   59,   60,   67,   71,   72,   68,   76,   74,   75,   78,
    2,   77,   15,   12,   69,   34,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          5,
    9,   42,   11,  274,  257,  272,  273,   13,  275,  286,
  257,  261,  259,  258,   55,  286,  257,   26,  259,   25,
  267,  268,   20,   21,  271,   31,  267,  268,  261,  276,
  277,  278,  279,  280,  281,  282,  283,  284,  285,  286,
  286,  259,   51,  286,  285,  286,  266,   53,   54,  257,
   59,  269,  270,   50,  265,   52,  262,  258,  263,   65,
  264,  257,  260,   60,  258,  260,   72,  260,  258,   75,
  258,  260,  258,  264,   58,  260,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=286;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"LBAR","RBAR","LPAR","RPAR","SEMCOL","COMMA","STAR","ARROW",
"TWOPOINTS","EOF","TRUE","FALSE","BOOL","INT","IF","CONST","FUN","REC","ECHO",
"ADD","SUB","MUL","DIV","AND","OR","LT","EQ","NOT","NUM","IDENT",
};
final static String yyrule[] = {
"$accept : progr",
"progr : LBAR cmds RBAR EOF",
"cmds : stat",
"cmds : dec SEMCOL cmds",
"cmds : stat SEMCOL cmds",
"stat : ECHO expr",
"dec : CONST IDENT type expr",
"dec : FUN IDENT type LBAR args RBAR expr",
"dec : FUN REC IDENT type LBAR args RBAR expr",
"type : BOOL",
"type : INT",
"type : LPAR types ARROW type RPAR",
"types : type",
"types : type STAR types",
"arg : IDENT TWOPOINTS type",
"args : arg",
"args : arg COMMA args",
"expr : NUM",
"expr : TRUE",
"expr : FALSE",
"expr : IDENT",
"expr : LPAR IF expr expr expr RPAR",
"expr : LPAR op exprs RPAR",
"expr : LBAR args RBAR expr",
"expr : LPAR expr exprs RPAR",
"op : ADD",
"op : SUB",
"op : MUL",
"op : DIV",
"op : AND",
"op : OR",
"op : NOT",
"op : EQ",
"op : LT",
"exprs : expr",
"exprs : expr exprs",
};

//#line 153 "yacc/aps0Grammaire.y"
  public AstProgramme prog;

  private Lexer lexer;

  private int yylex () {
	  int yyl_return = -1;
	  try {
		yyl_return = lexer.next_token();
	  }catch (IOException e) {
	    System.err.println("IO error :"+e);
	  }
	  return yyl_return;
  }

  public void yyerror (String error) {
  }

  public Parser(Reader r) {
	lexer = new Lexer(r, this);
  }

  public void parse(){
     yyparse();
  }
//#line 289 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 34 "yacc/aps0Grammaire.y"
{  prog = new AstProgramme((ArrayList<AstCommand>) val_peek(2).obj); 
    yyval.obj = prog;}
break;
case 2:
//#line 38 "yacc/aps0Grammaire.y"
{  ArrayList<AstCommand> l = new ArrayList<AstCommand>(); 
             l.add(0,(AstStatement) val_peek(0).obj);
             yyval.obj = l; }
break;
case 3:
//#line 43 "yacc/aps0Grammaire.y"
{((ArrayList<AstCommand>) val_peek(0).obj).add(0,(AstDeclaration) val_peek(2).obj);
             yyval.obj = val_peek(0).obj; }
break;
case 4:
//#line 47 "yacc/aps0Grammaire.y"
{((ArrayList<AstCommand>) val_peek(0).obj).add(0,(AstStatement) val_peek(2).obj);
             yyval.obj = val_peek(0).obj; }
break;
case 5:
//#line 51 "yacc/aps0Grammaire.y"
{  yyval.obj = new AstEcho((AstExpression) val_peek(0).obj); }
break;
case 6:
//#line 54 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstConst(val_peek(2).sval,(AstType) val_peek(1).obj, (AstExpression) val_peek(0).obj); }
break;
case 7:
//#line 57 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstFun(val_peek(5).sval,(AstType) val_peek(4).obj,(ArrayList<AstArgument>) val_peek(2).obj,(AstExpression) val_peek(0).obj); }
break;
case 8:
//#line 60 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstFunRec(val_peek(5).sval,(AstType) val_peek(4).obj,(ArrayList<AstArgument>) val_peek(2).obj,(AstExpression) val_peek(0).obj); }
break;
case 9:
//#line 63 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstBoolType(); }
break;
case 10:
//#line 66 "yacc/aps0Grammaire.y"
{  yyval.obj = new AstIntType(); }
break;
case 11:
//#line 69 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstFuncType((ArrayList<AstType>) val_peek(3).obj,(AstType) val_peek(1).obj); }
break;
case 12:
//#line 72 "yacc/aps0Grammaire.y"
{ ArrayList<AstType> l = new ArrayList<AstType>(); 
             l.add(0,(AstType) val_peek(0).obj);
             yyval.obj = l; }
break;
case 13:
//#line 77 "yacc/aps0Grammaire.y"
{ ((ArrayList<AstType>) val_peek(0).obj).add(0,(AstType) val_peek(2).obj);
             yyval.obj = val_peek(0).obj; }
break;
case 14:
//#line 81 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstArgument(val_peek(2).sval,(AstType) val_peek(0).obj); }
break;
case 15:
//#line 84 "yacc/aps0Grammaire.y"
{ ArrayList<AstArgument> l = new ArrayList<AstArgument>(); 
             l.add(0,(AstArgument) val_peek(0).obj);
             yyval.obj = l; }
break;
case 16:
//#line 89 "yacc/aps0Grammaire.y"
{ ((ArrayList<AstArgument>) val_peek(0).obj).add(0,(AstArgument) val_peek(2).obj);
             yyval.obj = val_peek(0).obj; }
break;
case 17:
//#line 93 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstNum(val_peek(0).ival); }
break;
case 18:
//#line 96 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstBool(true); }
break;
case 19:
//#line 99 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstBool(false); }
break;
case 20:
//#line 102 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstVar(val_peek(0).sval); }
break;
case 21:
//#line 105 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstIf((AstExpression) val_peek(3).obj,(AstExpression) val_peek(2).obj,(AstExpression) val_peek(1).obj); }
break;
case 22:
//#line 108 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstOp(val_peek(2).sval, (ArrayList<AstExpression>) val_peek(1).obj); }
break;
case 23:
//#line 111 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstLambda((ArrayList<AstArgument>) val_peek(2).obj, (AstExpression) val_peek(0).obj); }
break;
case 24:
//#line 114 "yacc/aps0Grammaire.y"
{ yyval.obj = new AstApplication((AstExpression) val_peek(2).obj,(ArrayList<AstExpression>) val_peek(1).obj); }
break;
case 25:
//#line 117 "yacc/aps0Grammaire.y"
{ yyval.sval = "add"; }
break;
case 26:
//#line 120 "yacc/aps0Grammaire.y"
{ yyval.sval = "sub"; }
break;
case 27:
//#line 123 "yacc/aps0Grammaire.y"
{yyval.sval = "mul"; }
break;
case 28:
//#line 126 "yacc/aps0Grammaire.y"
{ yyval.sval = "div"; }
break;
case 29:
//#line 129 "yacc/aps0Grammaire.y"
{ yyval.sval = "and"; }
break;
case 30:
//#line 132 "yacc/aps0Grammaire.y"
{ yyval.sval = "or"; }
break;
case 31:
//#line 135 "yacc/aps0Grammaire.y"
{ yyval.sval = "not"; }
break;
case 32:
//#line 138 "yacc/aps0Grammaire.y"
{ yyval.sval = "eq"; }
break;
case 33:
//#line 141 "yacc/aps0Grammaire.y"
{ yyval.sval = "lt"; }
break;
case 34:
//#line 144 "yacc/aps0Grammaire.y"
{ ArrayList<AstExpression> l = new ArrayList<AstExpression>(); 
             l.add(0,(AstExpression) val_peek(0).obj);
             yyval.obj = l; }
break;
case 35:
//#line 149 "yacc/aps0Grammaire.y"
{((ArrayList<AstExpression>) val_peek(0).obj).add(0,(AstExpression) val_peek(1).obj);
             yyval.obj = val_peek(0).obj; }
break;
//#line 592 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
