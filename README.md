FileLog is a class that extends android.util.Log with file based logging.

How to use:

- set WRITE_EXTERNAL_STORAGE permission in your app's AndroidManifest.xml file
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

- add FileLog.java to you app project

- call FileLog.open( ... ) to open a log file, set the logging priority and the maximum file size
  e.g. in the onCreate() of the App object

- log message priorities are the same as used by android.util.Log

- log function signatures FileLog.v(...), FileLog.d(...), etc. are identical to those provided by android.util.Log


