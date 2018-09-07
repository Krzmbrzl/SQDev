# Current Changelog

| Added: ObjectRTD data type
| Added: Framework for reading PBOs
| Improved: Command update now times out and won't wait forever for URL requests
| Improved: Paths in SQDevFiles are now anonymized and abstracted -> can be shared on mutliple systems
| Improved: Annotations in SQDevFiles can now make use of regular expressions
| Improved: SQDevFileEditor now validates its input and provides error messages (new format only)
| Changed: Errors for missing semicolons will always have length 1
| Rewrote: SQDevFile format specification and handling
| Fixed: Semicolon-error at wrong position if brackets are being used
| Fixed: No error message for missing semicolons if following statement is wrapped in curly or square brackets
| Fixed: Macros with empty parameter will no longer produce an error during linting
| Fixed: Variable names in content-assist are now in the same casing as in the code again
| Fixed: Nular statement in assignments not getting validated
| Fixed: Macros with identical name as SQF commands not getting processed and highlighted properly
| Fixed: Command update having had troubles with certain commands
| Fixed: Progress bar for command update is now persistent after error-recovery
| Fixed: Some files getting corrupted during export
| Fixed: Inaccessible preference-page in certain situations
