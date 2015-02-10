package com.example.filelogsampleapp.logging;

import android.util.Log;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.File;

public class FileLogTest extends TestCase
{
    // VERBOSE < DEBUG < INFO < WARN < ERROR

    protected void setUp( )
    {
        FileLog.open( "sdcard/logFile.log", Log.ERROR, 1000000 );
        FileLog.delete( );
    }

    protected void tearDown( )
    {
    }

    public void testV( ) throws Exception
    {
        Assert.assertTrue( writeLogMessage( Log.VERBOSE, Log.VERBOSE, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.DEBUG, Log.VERBOSE, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.INFO, Log.VERBOSE, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.WARN, Log.VERBOSE, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.ERROR, Log.VERBOSE, "testtag", "test message" ) );
    }

    public void testD( ) throws Exception
    {
        Assert.assertTrue( writeLogMessage( Log.VERBOSE, Log.DEBUG, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.DEBUG, Log.DEBUG, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.INFO, Log.DEBUG, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.WARN, Log.DEBUG, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.ERROR, Log.DEBUG, "testtag", "test message" ) );
    }

    public void testI( ) throws Exception
    {
        Assert.assertTrue( writeLogMessage( Log.VERBOSE, Log.INFO, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.DEBUG, Log.INFO, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.INFO, Log.INFO, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.WARN, Log.INFO, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.ERROR, Log.INFO, "testtag", "test message" ) );
    }

    public void testW( ) throws Exception
    {
        Assert.assertTrue( writeLogMessage( Log.VERBOSE, Log.WARN, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.DEBUG, Log.WARN, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.INFO, Log.WARN, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.WARN, Log.WARN, "testtag", "test message" ) );
        Assert.assertFalse( writeLogMessage( Log.ERROR, Log.WARN, "testtag", "test message" ) );
    }

    public void testE( ) throws Exception
    {
        Assert.assertTrue( writeLogMessage( Log.VERBOSE, Log.ERROR, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.DEBUG, Log.ERROR, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.INFO, Log.ERROR, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.WARN, Log.ERROR, "testtag", "test message" ) );
        Assert.assertTrue( writeLogMessage( Log.ERROR, Log.ERROR, "testtag", "test message" ) );
    }

    private boolean writeLogMessage( int filePriority, int msgPriority, String tag, String message )
    {
        boolean messageWritten = false;

        FileLog.open( "sdcard/logFile.log", filePriority, 100000 );
        FileLog.delete( );

        FileLog.open( "sdcard/logFile.log", filePriority, 100000 );

        switch ( msgPriority )
        {
            case Log.VERBOSE:
                FileLog.v( tag, message );
                break;
            case Log.DEBUG:
                FileLog.d( tag, message );
                break;
            case Log.INFO:
                FileLog.i( tag, message );
                break;
            case Log.WARN:
                FileLog.w( tag, message );
                break;
            case Log.ERROR:
                FileLog.e( tag, message );
                break;
        }

        File f = new File( "sdcard/logFile.log" );
        messageWritten = ( f.length( ) > 0 );

        FileLog.close( );

        return messageWritten;
    }

    public void testLogFileRollover( ) throws Exception
    {
        FileLog.open( "sdcard/logFile.log", Log.INFO, 100000 );
        FileLog.delete( );

        FileLog.open( "sdcard/logFile.log", Log.INFO, 100000 );
        for ( int i = 0; i < 10000; i++ )
        {
            FileLog.w( "tag", "message");
        }
        FileLog.close();

        FileLog.open( "sdcard/logFile.log", Log.INFO, 100000 );
        File oldLogFile = new File( "sdcard/logFile.log.old" );
        Assert.assertTrue( oldLogFile.exists() );
        File f = new File( "sdcard/logFile.log" );
        Assert.assertTrue( f.exists() );
        FileLog.close();

        FileLog.open( "sdcard/logFile.log", Log.INFO, 100000 );
        for ( int i = 0; i < 10000; i++ )
        {
            FileLog.w( "tag", "message");
        }
        FileLog.close();

        FileLog.open( "sdcard/logFile.log", Log.INFO, 100000 );
        oldLogFile = new File( "sdcard/logFile.log.old" );
        Assert.assertTrue( oldLogFile.exists() );
        f = new File( "sdcard/logFile.log" );
        Assert.assertTrue( f.exists() );
    }

    public void testNoOpen( ) throws Exception
    {
        FileLog.e( "testtag", "file not opened" );
    }
}
