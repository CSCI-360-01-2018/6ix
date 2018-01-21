# Use Cases

* set internal time
    1. user holds TIME button for 3 seconds, setting device so SET-TIME mode
    1. the display now reads 12:00
        * pressing VOLUME +/- will result in the display reading 24:00
        * this indicates time will now be displayed in military format
    1. pressing TIME again results in a flashing 12:00/24:00 respectively
        * during this period, VOLUME +/- increases/decreases the current minute count, allowing the user to choose a time
        * if standard time was selected in step 2, moving past 12:00 will result in a rollover of the AM/PM counter. Otherwise, the AM/PM counter is disabled
    1. pressing TIME again sets the clock to STANDARD mode

1. set alarm
    * user chooses which alarm to set
    * user chooses the alarm time
    * user chooses standard beep or radio
    * user can set alarm to 'none' to disable

1. snooze
    * requires alarm to be ringing to function
    * pauses alarm for hard coded time, then resumes alarm routine
1. change volume
    * user increases or decreases volume via buttons
1. change radio frequency
    * user increases or decreases radio frequency via analog slider

* choose FM/AM
    1. the user toggles a switch, indicating a desire to use the FM/AM radio frequency bands
        * this can be done at any point, including during radio playback

1. play alarm
    * alarm plays until stopped
1. disable ringing alarm
    * alarm is disabled via button
1. enable radio
    * enable/disable radio play at current frequency with toggle