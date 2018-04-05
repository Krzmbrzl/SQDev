//	{
//		[str getPos (_x select 0), getPos (_x select 0), "mil_dot", "ColorYellow", ""] call ENGIMA_CIVILIANS_SetDebugMarkerAllClients;
//	} foreach _playerBuildingsTemp;

CHECK_FALSE(isNil "_type", Invalid eventType!, {})

DEBUG_EXEC(EVENT_LOG(fired - %1, str _type))

ASL_Is_Supported_Cargo = {
	params ["_vehicle","_cargo"];
	private ["_canSling"];
	_canSling = false;
	if(not isNull _vehicle && not isNull _cargo) then {
		{
			if(_vehicle isKindOf (_x select 0)) then {
				if(_cargo isKindOf (_x select 2)) then {
					if( (toUpper (_x select 1)) == "CAN_SLING" ) then {
						_canSling = true;
					} else {
						_canSling = false;
					};
				};
			};
		} forEach (missionNamespace getVariable ["ASL_SLING_RULES_OVERRIDE",ASL_SLING_RULES]);
	};
	_canSling;
};