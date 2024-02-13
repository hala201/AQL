parser grammar AQLParser;

options { tokenVocab=AQLLexer; }

program         : statement+ ;
statement       : request | loop | log | set | onElse;
loop            : FOR_EACH VARIABLE IN (getReq | dynamicVar | VARIABLE) OPEN_BRACE statement+ CLOSE_BRACE ;
set             : SET (request | value) AS VARIABLE ;
log             : LOG (value | dynamicVar | VARIABLE) ;
onElse          : ON condition OPEN_BRACE program CLOSE_BRACE (ELSE OPEN_BRACE program CLOSE_BRACE)? ;
withBlock       : WITH OPEN_BRACE params CLOSE_BRACE ;

request         : (getReq | postReq | putReq | delReq) ;
getReq          : GET       dynamicURI withBlock? (onElse)? ;
postReq         : POST      dynamicURI withBlock? (onElse)? ;
putReq          : PUT       dynamicURI withBlock? (onElse)? ;
delReq          : DELETE    dynamicURI withBlock? (onElse)? ;

condition       : (value | dynamicVar | VARIABLE) (EQUAL | NOT_EQUAL | LESS | GREATER | LESS_EQUAL | GREATER_EQUAL | ERROR | SUCCESS) (value | dynamicVar | VARIABLE) ;
params          : param (COMMA param)* ;
// NOTE: assuming we only allow JSON within WITH block
param           : string COLON (value | string) ;
dynamicURI      : URI (dynamicVar URI_TAIL*)* ;

dynamicVar      : VARIABLE DOT VARIABLE | OPEN_BRACE VARIABLE DOT VARIABLE CLOSE_BRACE;
string          : QUOTEDOUBLE | QUOTESINGLE;
value           : NUMBER | string ;
