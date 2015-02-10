###FileLog is a class that extends android.util.Log with file based logging:
Every log message is logged via android.util.Log and also written to a text file on the device.


#####How to use:

- set WRITE_EXTERNAL_STORAGE permission in your app's AndroidManifest.xml file<br>
  `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />`

- add FileLog.java to you app project

- call FileLog.open( ... ) to open a log file, set the logging priority and the maximum file size<br>
  e.g. in the onCreate() method of the Application object

- log message priorities are the same as used by android.util.Log

- log function signatures FileLog.v(...), FileLog.d(...), etc. are identical to those provided by android.util.Log


