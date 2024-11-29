parser grammar AQLParser;

options { tokenVocab=AQLLexer; }

program         : statement+ ;
statement       : request | loop | log | set | onElse;
loop            : FOR_EACH VARIABLE IN (getReq | dynamicVar) OPEN_BRACE statement+ CLOSE_BRACE ;
set             : SET (request | value | params ) AS VARIABLE ;
log             : LOG (value | dynamicVar) ;
onElse          : ON condition OPEN_BRACE program CLOSE_BRACE (ELSE OPEN_BRACE program CLOSE_BRACE)? ;
withBlock       : WITH  params ;

request         : (getReq | postReq | putReq | delReq) ;
getReq          : GET       dynamicURI withBlock? ;
postReq         : POST      dynamicURI withBlock? ;
putReq          : PUT       dynamicURI withBlock? ;
delReq          : DELETE    dynamicURI withBlock? ;

condition       : (value | dynamicVar) (EQUAL | NOT_EQUAL | LESS | GREATER | LESS_EQUAL | GREATER_EQUAL) (value | dynamicVar) ;
// params is to restrict a JSON format
params          : OPEN_BRACE param (COMMA param)* CLOSE_BRACE;
param           : string COLON (value | dynamicVar) ;
dynamicURI      : URI (dynamicVar URI_TAIL*)* ;
// let dynamicVar catch both single VARIABLE and VARIABLE.VARIABLE to make it locally deterministic
dynamicVar      : VARIABLE (DOT VARIABLE)? | OPEN_BRACE VARIABLE DOT VARIABLE CLOSE_BRACE;
// no QUOTESINGLE because JSON doesnt support it anyways
string          : QUOTEDOUBLE;
value           : NUMBER | string ;