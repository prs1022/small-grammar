grammar gscript;

@header {
package com.grammar.antlr;
}

script: statement* EOF;

statement:  expr_semicolon ';'? # stmtExpr
          | if_                 # stmtIf
          | while_              # stmtWhile
          | for_                # stmtFor
          | for_in              # stmtFor
          | switch_             # stmtSwitch
          | block               # stmtBlock
          | ';'                 # stmtSemicolon
          | BREAK ';'           # stmtBreak
         ;
 
if_ : IF '(' expr_semicolon ')' true_block=statement
      ( ELSE false_block=statement ) ?
    ;

while_ : WHILE '(' expr_semicolon ')' statement
    ;

for_ : FOR '(' e1=expr_semicolon? ';' e2=expr_semicolon? ';' e3=expr_semicolon? ')' statement
    ;

for_in : FOR '(' VAR? IDENTIFIER IN expr_semicolon? ')' statement
    ;

switch_ : SWITCH '(' expr ')'
          '{'
            case_+
            default_?
          '}'
        ;

case_ : CASE expr ':' statement*
      ;

default_: DEFAULT ':' statement*
      ;

block: '{' statement* '}';

expr : IDENTIFIER                             # exprId
     | constant                               # exprConst
     | array                                  # exprArray
     | map                                    # exprMap
     | '(' expr_semicolon  ')'                # exprParenthesis
     | expr '(' expr_list? ')'                # exprInvoke
     | expr '.' IDENTIFIER                    # exprDot
     | expr '[' expr_semicolon ']'            # exprBrackets
     | var_with_member op=INC_DEC             # exprSelfIncDecPost
     | op=INC_DEC var_with_member             # exprSelfIncDecPre
     | op=(PLUS_MINUS | UNARY) expr           # exprUnary
     | TYPEOF expr                            # exprTypeof
     | DELETE var_with_member                 # exprDelete
     | <assoc=right> base=expr '**' expo=expr # exprPow
     | expr op=MUL_DIV_MOD expr               # exprMulDivMod
     | expr op=PLUS_MINUS expr                # exprPlusMinus
     | expr op=SHIFT expr                     # exprShift
     | expr op=COMPARE_OP expr                # exprCompare
     | expr op=IN expr                        # exprIn
     | expr op=EQ_NEQ expr                    # exprEqNeq
     | expr op='&' expr                       # exprBitAnd
     | expr op='^' expr                       # exprBitXor
     | expr op='|' expr                       # exprBitOr
     | expr op='&&' expr                      # exprLogicalAnd
     | expr op='||' expr                      # exprLogicalOr
     | <assoc=right> cond_expr=expr '?' true_expr=expr ':' false_expr=expr   # exprConditional
     | <assoc=right> lval ASSIGN_OP expr      # exprAssign
     ;

constant: INT | HEX | FLOAT | STRING | UNDEFINED | NULL_ | TRUE | FALSE;
array: '[' expr_list? ']';
map: '{'
             (field (',' field)* ','?)?
     '}';

field: (IDENTIFIER | STRING) ':' expr;

expr_semicolon : expr | expr_semicolon ',' expr;
expr_list: expr (',' expr)* ','?;

var_with_member: IDENTIFIER | var_with_member member;

member: '[' expr_semicolon ']'   # memberBrackets
      | '.' IDENTIFIER           # memberDot
      ;

lval: var_with_member;

fragment Digit: [0-9];
fragment EscapeChar: 'r'|'n'|'b'|'t'|'f'|'\\';

UNDEFINED: 'undefined';
NULL_: 'null';
VAR: 'var';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
FOR: 'for';
BREAK: 'break';
TRUE: 'true';
FALSE: 'false';
IN: 'in';
SWITCH: 'switch';
CASE: 'case';
DEFAULT: 'default';
DELETE: 'delete';
TYPEOF: 'typeof';

IDENTIFIER: [_a-zA-Z] [_0-9a-zA-Z]*;

INT: Digit+;
HEX: ('0x' | '0X') [0-9a-fA-F]+ ;
FLOAT: (Digit+ '.' Digit* | '.' Digit+) (('e'|'E') [+-]? INT)? | INT ('e'|'E') [+-]? INT;
STRING    : '"' (~[\\\r\n"] | '\\' ('"'|EscapeChar) )*? '"' | '\'' (~[\\\r\n"] | '\\' ('\''|EscapeChar) )*? '\'';
INC_DEC: '++' | '--';
COMPARE_OP: '>' | '>=' | '<' | '<=';
EQ_NEQ: '==' | '!=';
ASSIGN_OP: ([-+*/%&|^] | '>>' | '<<' | '>>>')? '=';
PLUS_MINUS: '+' | '-';
UNARY: '!' | '~';
MUL_DIV_MOD: '*' | '/' | '%';
SHIFT: '<<' | '>>' | '>>>';

LINECOMMENT : '//' ~[\r\n]*        -> channel(HIDDEN);
BLOCKCOMMENT: '/*' .*? '*/'        -> channel(HIDDEN);
SPACES      : [ \t\r\n]+           -> channel(HIDDEN);
