_myTestVar1 = 5;
_myTestVar2 = getPos player;

params [
	["_parameter1", nil, [0]],
	["_parameter2", nil, [[], 4]],
	["", nil, []],
	["_parameter4", nil, ["", objNull]]
];

{
	GlobalTestVar = "Hello World";
	
	if (isNil "Miau") then {
		_innerVariable = ["Hello", "There", "You", "Stranger"];
		
		for "_forVar" from 0 to 12 step 3 do {
			for[{_innerForVar = 0},{_innerForVar < 5},{_innerForVar = _innerForVar + 1}] do {
				
			};
		};
	};
} count [];

private ["_private1", "_private2"];

private _private3 = "Ola";
// _commentVar = 5; }
/*
 * CommentVar = "Mammamia";
 * (
 */

hint Implicit1;
[] call Implicit2;

_ifVar = if(Implicit3) then {"One"} else {"Two"};

nil;