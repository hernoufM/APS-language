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



package aps1.parser;



//#line 2 "yacc/aps1Grammaire.y"
import java.io.*;
import java.util.ArrayList;
import aps0.ast.statement.*;
import aps0.ast.declaration.*;
import aps0.ast.expression.*;
import aps0.ast.type.*;
import aps0.ast.*;
import aps1.ast.statement.*;
import aps1.ast.declaration.*;
import aps1.ast.type.*;
import aps1.ast.*;
//#line 29 "Parser.java"




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
public final static short VOID=285;
public final static short IF_STAT=286;
public final static short SET=287;
public final static short VAR=288;
public final static short PROC=289;
public final static short CALL=290;
public final static short WHILE=291;
public final static short NUM=292;
public final static short IDENT=293;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    2,    2,    2,    2,    2,    3,
    3,    3,    3,    3,    3,    4,    4,    4,    4,    5,
    5,    6,    7,    7,    8,    8,    8,    8,    8,    8,
    8,    8,   11,   11,   11,   11,   11,   11,   11,   11,
   11,    9,    9,   10,
};
final static short yylen[] = {                            2,
    4,    1,    3,    3,    2,    3,    4,    3,    3,    4,
    7,    8,    3,    6,    7,    1,    1,    5,    1,    1,
    3,    3,    1,    3,    1,    1,    1,    1,    6,    4,
    4,    4,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    2,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   26,
   27,   25,   28,    5,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   16,   17,   19,    0,    0,
    0,    0,    0,    0,    0,   33,   34,   35,   36,   37,
   38,   41,   40,   39,    0,    0,    0,    0,    6,   13,
    0,    0,    0,    9,    8,    1,    4,    3,    0,    0,
   10,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    7,    0,    0,   43,    0,    0,    0,    0,   22,   24,
   31,    0,   32,   30,   44,    0,    0,   21,    0,    0,
    0,    0,    0,   14,   18,    0,   11,   29,   15,   12,
};
final static short yydgoto[] = {                          2,
   12,   13,   14,   69,   70,   43,   44,   63,   64,   58,
   56,
};
final static short yysindex[] = {                      -254,
 -206,    0, -285, -270, -241, -241, -283, -282, -262, -280,
 -241, -243, -236, -233, -250, -256, -250, -239, -235,    0,
    0,    0,    0,    0, -198, -241, -250, -228, -197, -241,
 -198, -193, -206, -206, -250,    0,    0,    0, -241, -250,
 -180, -177, -173, -168, -241,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -241, -241, -206, -198,    0,    0,
 -166, -239, -241,    0,    0,    0,    0,    0, -171, -170,
    0, -164, -239, -250, -239, -241, -241, -165, -163, -160,
    0, -239, -159,    0, -250, -250, -239, -158,    0,    0,
    0, -241,    0,    0,    0, -156, -198,    0, -157, -154,
 -241, -155, -198,    0,    0, -241,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -152,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -151,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -205,    0,    0,    0,    0,    0, -153,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    5,    0,    0,  -10,   24,    0,  -12,   -5,   23,  -29,
    0,
};
final static int YYTABLESIZE=111;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         24,
   25,   65,    1,   16,   39,   31,   41,   15,   35,   26,
   27,   28,   30,   55,   32,   18,   60,   19,   36,   37,
   59,   18,   17,   19,   33,   20,   21,   34,   81,   72,
   29,   20,   21,   71,   38,   45,   40,   67,   68,   77,
   46,   47,   48,   49,   50,   51,   52,   53,   54,   83,
   22,   23,   42,   42,   42,   42,   22,   23,   57,   62,
   88,   80,   90,   89,   61,    3,    4,  104,    5,   96,
   91,   92,   66,  109,  100,   99,   73,   78,   79,    6,
    7,    8,    9,   10,   11,   84,  102,   74,   75,   76,
   82,   85,   87,   86,   93,  107,   94,   95,   97,  101,
  110,  103,  105,  106,  108,    2,   23,    0,   98,    0,
   20,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          5,
    6,   31,  257,  274,   15,   11,   17,  293,  259,  293,
  293,  274,  293,   19,  258,  257,   27,  259,  269,  270,
   26,  257,  293,  259,  261,  267,  268,  261,   58,   40,
  293,  267,  268,   39,  285,  271,  293,   33,   34,   45,
  276,  277,  278,  279,  280,  281,  282,  283,  284,   62,
  292,  293,  258,  293,  260,  261,  292,  293,  257,  257,
   73,   57,   75,   74,  293,  272,  273,   97,  275,   82,
   76,   77,  266,  103,   87,   86,  257,   55,   56,  286,
  287,  288,  289,  290,  291,   63,   92,  265,  262,  258,
  257,  263,  257,  264,  260,  101,  260,  258,  258,  258,
  106,  258,  260,  258,  260,  258,  258,   -1,   85,   -1,
  264,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=293;
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
"ADD","SUB","MUL","DIV","AND","OR","LT","EQ","NOT","VOID","IF_STAT","SET","VAR",
"PROC","CALL","WHILE","NUM","IDENT",
};
final static String yyrule[] = {
"$accept : progr",
"progr : LBAR cmds RBAR EOF",
"cmds : stat",
"cmds : dec SEMCOL cmds",
"cmds : stat SEMCOL cmds",
"stat : ECHO expr",
"stat : SET IDENT expr",
"stat : IF_STAT expr block block",
"stat : WHILE expr block",
"stat : CALL IDENT exprs",
"dec : CONST IDENT type expr",
"dec : FUN IDENT type LBAR args RBAR expr",
"dec : FUN REC IDENT type LBAR args RBAR expr",
"dec : VAR IDENT type",
"dec : PROC IDENT LBAR args RBAR block",
"dec : PROC REC IDENT LBAR args RBAR block",
"type : BOOL",
"type : INT",
"type : LPAR types ARROW type RPAR",
"type : VOID",
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
"block : LBAR cmds RBAR",
};

//#line 189 "yacc/aps1Grammaire.y"
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
//#line 331 "Parser.java"
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
//#line 40 "yacc/aps1Grammaire.y"
{  prog = new AstProgramme((ArrayList<AstCommand>) val_peek(2).obj); 
    yyval.obj = prog;}
break;
case 2:
//#line 44 "yacc/aps1Grammaire.y"
{  ArrayList<AstCommand> l = new ArrayList<AstCommand>(); 
             l.add(0,(AstStatement) val_peek(0).obj);
             yyval.obj = l; }
break;
case 3:
//#line 49 "yacc/aps1Grammaire.y"
{((ArrayList<AstCommand>) val_peek(0).obj).add(0,(AstDeclaration) val_peek(2).obj);
             yyval.obj = val_peek(0).obj; }
break;
case 4:
//#line 53 "yacc/aps1Grammaire.y"
{((ArrayList<AstCommand>) val_peek(0).obj).add(0,(AstStatement) val_peek(2).obj);
             yyval.obj = val_peek(0).obj; }
break;
case 5:
//#line 57 "yacc/aps1Grammaire.y"
{  yyval.obj = new AstEcho((AstExpression) val_peek(0).obj); }
break;
case 6:
//#line 60 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstSet(val_peek(1).sval,(AstExpression) val_peek(0).obj); }
break;
case 7:
//#line 63 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstIfStat((AstExpression) val_peek(2).obj, (AstBlock) val_peek(1).obj, (AstBlock) val_peek(0).obj); }
break;
case 8:
//#line 66 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstWhile((AstExpression) val_peek(1).obj, (AstBlock) val_peek(0).obj); }
break;
case 9:
//#line 69 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstCall(val_peek(1).sval,(ArrayList<AstExpression>) val_peek(0).obj); }
break;
case 10:
//#line 73 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstConst(val_peek(2).sval,(AstType) val_peek(1).obj, (AstExpression) val_peek(0).obj); }
break;
case 11:
//#line 76 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstFun(val_peek(5).sval,(AstType) val_peek(4).obj,(ArrayList<AstArgument>) val_peek(2).obj,(AstExpression) val_peek(0).obj); }
break;
case 12:
//#line 79 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstFunRec(val_peek(5).sval,(AstType) val_peek(4).obj,(ArrayList<AstArgument>) val_peek(2).obj,(AstExpression) val_peek(0).obj); }
break;
case 13:
//#line 82 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstVarDec(val_peek(1).sval,(AstType) val_peek(0).obj);}
break;
case 14:
//#line 85 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstProc(val_peek(4).sval, (ArrayList<AstArgument>) val_peek(2).obj,(AstBlock) val_peek(0).obj); }
break;
case 15:
//#line 88 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstProcRec(val_peek(4).sval, (ArrayList<AstArgument>) val_peek(2).obj,(AstBlock) val_peek(0).obj); }
break;
case 16:
//#line 91 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstBoolType(); }
break;
case 17:
//#line 94 "yacc/aps1Grammaire.y"
{  yyval.obj = new AstIntType(); }
break;
case 18:
//#line 97 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstFuncType((ArrayList<AstType>) val_peek(3).obj,(AstType) val_peek(1).obj); }
break;
case 19:
//#line 100 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstVoidType(); }
break;
case 20:
//#line 103 "yacc/aps1Grammaire.y"
{ ArrayList<AstType> l = new ArrayList<AstType>(); 
             l.add(0,(AstType) val_peek(0).obj);
             yyval.obj = l; }
break;
case 21:
//#line 108 "yacc/aps1Grammaire.y"
{ ((ArrayList<AstType>) val_peek(0).obj).add(0,(AstType) val_peek(2).obj);
             yyval.obj = val_peek(0).obj; }
break;
case 22:
//#line 112 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstArgument(val_peek(2).sval,(AstType) val_peek(0).obj); }
break;
case 23:
//#line 115 "yacc/aps1Grammaire.y"
{ ArrayList<AstArgument> l = new ArrayList<AstArgument>(); 
             l.add(0,(AstArgument) val_peek(0).obj);
             yyval.obj = l; }
break;
case 24:
//#line 120 "yacc/aps1Grammaire.y"
{ ((ArrayList<AstArgument>) val_peek(0).obj).add(0,(AstArgument) val_peek(2).obj);
             yyval.obj = val_peek(0).obj; }
break;
case 25:
//#line 124 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstNum(val_peek(0).ival); }
break;
case 26:
//#line 127 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstBool(true); }
break;
case 27:
//#line 130 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstBool(false); }
break;
case 28:
//#line 133 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstVar(val_peek(0).sval); }
break;
case 29:
//#line 136 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstIf((AstExpression) val_peek(3).obj,(AstExpression) val_peek(2).obj,(AstExpression) val_peek(1).obj); }
break;
case 30:
//#line 139 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstOp(val_peek(2).sval, (ArrayList<AstExpression>) val_peek(1).obj); }
break;
case 31:
//#line 142 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstLambda((ArrayList<AstArgument>) val_peek(2).obj, (AstExpression) val_peek(0).obj); }
break;
case 32:
//#line 145 "yacc/aps1Grammaire.y"
{ yyval.obj = new AstApplication((AstExpression) val_peek(2).obj,(ArrayList<AstExpression>) val_peek(1).obj); }
break;
case 33:
//#line 148 "yacc/aps1Grammaire.y"
{ yyval.sval = "add"; }
break;
case 34:
//#line 151 "yacc/aps1Grammaire.y"
{ yyval.sval = "sub"; }
break;
case 35:
//#line 154 "yacc/aps1Grammaire.y"
{yyval.sval = "mul"; }
break;
case 36:
//#line 157 "yacc/aps1Grammaire.y"
{ yyval.sval = "div"; }
break;
case 37:
//#line 160 "yacc/aps1Grammaire.y"
{ yyval.sval = "and"; }
break;
case 38:
//#line 163 "yacc/aps1Grammaire.y"
{ yyval.sval = "or"; }
break;
case 39:
//#line 166 "yacc/aps1Grammaire.y"
{ yyval.sval = "not"; }
break;
case 40:
//#line 169 "yacc/aps1Grammaire.y"
{ yyval.sval = "eq"; }
break;
case 41:
//#line 172 "yacc/aps1Grammaire.y"
{ yyval.sval = "lt"; }
break;
case 42:
//#line 175 "yacc/aps1Grammaire.y"
{ ArrayList<AstExpression> l = new ArrayList<AstExpression>(); 
             l.add(0,(AstExpression) val_peek(0).obj);
             yyval.obj = l; }
break;
case 43:
//#line 180 "yacc/aps1Grammaire.y"
{((ArrayList<AstExpression>) val_peek(0).obj).add(0,(AstExpression) val_peek(1).obj);
             yyval.obj = val_peek(0).obj; }
break;
case 44:
//#line 185 "yacc/aps1Grammaire.y"
{ AstBlock b = new AstBlock((ArrayList<AstCommand>) val_peek(1).obj);
		  yyval.obj = b; }
break;
//#line 671 "Parser.java"
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
