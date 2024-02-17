lexer grammar AQLLexer;

FOR_EACH        : 'FOR EACH';
IN              : 'IN';
SET             : 'SET';
AS              : 'AS';
GET             : 'GET';
POST            : 'POST';
PUT             : 'PUT';
DELETE          : 'DELETE';
WITH            : 'WITH';
ON              : 'ON';
ELSE            : 'ELSE';
LOG             : 'LOG';
EQUAL           : '==';
NOT_EQUAL       : '!=';
LESS            : '<';
GREATER         : '>';
LESS_EQUAL      : '<=';
GREATER_EQUAL   : '>=';
OPEN_BRACE      : '{';
CLOSE_BRACE     : '}';
COLON           : ':';
COMMA           : ',';
DOT             : '.';
QUOTEDOUBLE     : '"' (~["\r\n])* '"'; // matches any character except double quote and newlines
URI             : ('https://' | 'http://') ~[{}[\]\r\n\t ]+;
URI_TAIL        : '/' ~[{}[\]\r\n\t ]+; // only acceptable if connected by URI + {...}
VARIABLE        : [a-zA-Z_][a-zA-Z_0-9]*;
NUMBER          : '-'? [0-9]+ ('.' [0-9]+)?;
WS              : [ \t\r\n]+ -> skip;
