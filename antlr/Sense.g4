grammar Sense;

@header {
package com.grammar.antlr;
}

sense : nsSentence importSentence* (scriptSentence | primeSentence | macroSentence | productionSentence)* EOF;

nsSentence: 'namespace' namespace ';';
importSentence: 'import' (namespace ('::' ID (',' ID)* )? )  ';';
scriptSentence: FUNCTION ':'  SCRIPT ';';
primeSentence: annotation* criteria? '<' name=ID '>' ':' expr exclusion? ';' ;
macroSentence: annotation* name=ID '(' idList? ')' ':' expr ';';
productionSentence: annotation* name=ID ':' expr exclusion? ';' ;

exclusion: '!' expr;

annotation: '@' ID assigns? ;
assigns: '(' assign ( ',' assign )*  ')';
assign: ID '=' value;

/* define word validation criteria
 */
criteria : '@' '[' catExpr ']';
catExpr :   catUnit ('&' catUnit)+        # catExprAnd
          | catBranch ('|' catBranch)+    # catExprOr
          | catUnit                       # catExprUnit
          ;

catUnit:    cat
          | not='!' catUnit
          | '(' catExpr ')'
          ;
catBranch: catUnit ('&' catUnit)*;

cat : catName=(ID | ID_PATH | '*' | MACROARG) ('{' wordAttrExpr '}')?;

wordAttrExpr: wordAttrUnit ('&' wordAttrUnit)+       # wordAttrExprAnd
            | wordAttrBranch ('|' wordAttrBranch)    # wordAttrExprOr
            | wordAttrUnit                           # wordAttrExprUnit
            ;

wordAttrBranch : wordAttrUnit ('&' wordAttrUnit)*;

wordAttr: attrName=(ID|MACROARG) ('=' attrValue=(MACROARG|STRING))?;

wordAttrUnit : wordAttr | not='!' wordAttrUnit | '(' wordAttrExpr ')';

value : INT | FLOAT | BOOLEAN | STRING | array | dict;
array : '[' (value (',' value)*)? ']';
dict : '{' (dictItem (',' dictItem )* ','? )? '}';
dictItem: key=(ID|STRING) ':' value;

expr         : repUnit repUnit+       #exprSequence
             | branch ('|' branch)+   #exprChoice
             | repUnit                #exprUnit
             ;

unit         : STRING                                             #unitLiteral
             | '^' STRING                                         #unitCharList
             | op=('==' | '!=') STRING                            #unitLookaheadLiteral
             | qualifiedName ('=' alias=ID)?                      #unitReference
             | '[' qualifiedName ( '(' expr (',' expr)* ')')? ']' #unitMacro
             | '<' qualifiedName '>' ('=' alias=ID)?              #unitPrime

             //用"."指代路径上前一个prime,不管什么名字
             | '<*' (qualifiedName|'.') assigns '>'               #unitPrimeAssign

             | '<*' assigns '>'                                   #unitStatementAssign
             | '(' expr ')'                                       #unitParenthesis
             | '.'                                                #unitAnyChar
             | '${' ID ('|' ID)* '}'                              #unitTags
             | MACROARG                                           #unitMacroArg
             | FUNCTION                                           #unitScriptFunction
             | SCRIPT                                             #unitScript
             | REGEX                                              #unitRegex
             | '~'                                                #unitNoskip
//             | '-'                #unitWordBoundary
//             | matchAction        #unitAction
             ;

idList       : ID (',' ID)*;

qualifiedName : (namespace '::')? ID;

namespace    : ID | NAMESPACE;

rep          : '+'                                                                     #repOneOrMore
             | '*'                                                                     #repZeroOrMore
             | '?'                                                                     #repZeroOrOne
             | '&'                                                                     #repPrefer
             | '{' (minCount=INT (sep=',' (maxCount=INT)? ) ? | ',' maxCount=INT)  '}' #repRange
             ;

repUnit      : unit rep?;

branch       : repUnit+;

INT: ('-'|'+')? ('0' | [1-9] [0-9]*) ;
FLOAT  : INT '.' [0-9]+ ( [Ee] INT )?;
BOOLEAN: 'true' | 'false';
STRING    : '"' (~[\\\r\n"] | '\\' ('"'|'r'|'n'|'\\') )*? '"' | '\'' (~[\\\r\n"] | '\\' ('\''|'r'|'n'|'\\') )*? '\'';
FUNCTION  : '~' ID;
ID: [_a-zA-Z\u4e00-\u9fa5] [_0-9a-zA-Z\u4e00-\u9fa5]*;
ID_PATH: ID ('/' ID)+;
MACROARG: '$' ID;
NAMESPACE: ID ('.' ID)+;
LINECOMMENT : '//' ~[\r\n]*        -> channel(HIDDEN);
BLOCKCOMMENT: '/*' .*? '*/'        -> channel(HIDDEN);
SPACES      : [ \t\r\n]+           -> channel(HIDDEN);
SCRIPT      : '<(' .*? ')>';
REGEX       : '/' .*? '/';
