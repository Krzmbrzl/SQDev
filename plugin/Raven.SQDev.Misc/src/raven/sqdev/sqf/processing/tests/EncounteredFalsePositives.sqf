//	{
//		[str getPos (_x select 0), getPos (_x select 0), "mil_dot", "ColorYellow", ""] call ENGIMA_CIVILIANS_SetDebugMarkerAllClients;
//	} foreach _playerBuildingsTemp;

CHECK_FALSE(isNil "_type", Invalid eventType!, {})

DEBUG_EXEC(EVENT_LOG(fired - %1, str _type))