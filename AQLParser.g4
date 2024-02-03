parser grammar AQLParser;

options { tokenVocab=AQLLexer; }

program         : statement+ ;
statement       : request | loop | log | set ;
loop            : FOR_EACH (dynamicVar | VARIABLE) IN (request | dynamicVar | VARIABLE) OPEN_BRACE statement+ CLOSE_BRACE ;
// loopBasic       : FOR_EACH dyvariable OPEN_BRACE statement+ CLOSE_BRACE ;
set             : SET (request | value | string) AS VARIABLE ;
log             : LOG (value | string) ;
onElse          : ON condition OPEN_BRACE statement+ CLOSE_BRACE (ELSE OPEN_BRACE statement+ CLOSE_BRACE)? ;
withBlock       : WITH OPEN_BRACE params CLOSE_BRACE ;

// TBD: might need to change (onSuccess | onError)
request         : (getReq | postReq | putReq | delReq) ;
getReq          : GET       dynamicURI withBlock? (onElse)? ;
postReq         : POST      dynamicURI withBlock? (onElse)? ;
putReq          : PUT       dynamicURI withBlock? (onElse)? ;
delReq          : DELETE    dynamicURI withBlock? (onElse)? ;

condition       : value (EQUAL | NOT_EQUAL | LESS | GREATER | LESS_EQUAL | GREATER_EQUAL | ERROR | SUCCESS) value ;
params          : param (COMMA param)* ;
param           : VARIABLE COLON (value | string) ;
dynamicURI      : URI (dynamicVar URI_TAIL*)* ;
// allow both {data.value} and data.value
dynamicVar      : VARIABLE DOT VARIABLE | OPEN_BRACE VARIABLE DOT VARIABLE CLOSE_BRACE; 
//
string          : QUOTEDOUBLE value+ QUOTEDOUBLE | QUOTESINGLE value+ QUOTESINGLE;
value           : NUMBER | dynamicVar | VARIABLE ;