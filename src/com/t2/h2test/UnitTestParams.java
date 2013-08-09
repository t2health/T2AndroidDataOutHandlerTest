package com.t2.h2test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import android.util.Log;

import com.t2.dataouthandler.DataOutHandlerTags;
import com.t2.dataouthandler.DataOutPacket;
import com.t2.dataouthandlertest.Global;
import com.t2.dataouthandlertest.MainActivity;

/**
 * This class is meant to contain all parameters for defining a test case
 *   Both the test case data itself and the pass/fail results criteria
 *   
 * @author scott.coleman
 *
 */
public class UnitTestParams {
	private static final String TAG = UnitTestParams.class.getSimpleName();


	public DataOutPacket mPacketUnderTest;
	public String mTestCase;
	public DataOutPacket mAlternateResultPacket;
	public List<String> mIgnoreList;
	public Boolean mReverseResults;	
	public int mStatus;
	public String mDescription;
	
	/**
	 * Creates a unit test
	 * 
	 * @param packetUnderTest - DataOutPacket to test
	 * @param testCase - Test case number
	 * @param alternateResultPacket - Packet to test against (if null, it compares to packetUnderTest) 
	 * @param ignoreList - Parameters to ignore in the test
	 * @param reverseResults - Pass becomes fail, visa versa
	 * @param description - Description of the test case
	 */
	public UnitTestParams(DataOutPacket packetUnderTest, String testCase, DataOutPacket alternateResultPacket, 
			List<String> ignoreList, Boolean reverseResults, String description) {
		mPacketUnderTest = packetUnderTest; 
		mTestCase = testCase;
		mAlternateResultPacket = alternateResultPacket; 
		mIgnoreList = ignoreList;
		mReverseResults = reverseResults;
		mStatus = Global.UNIT_TEST_WAITING;
		mDescription = description;
	}

	
	
	// ------------------------------------------------------------------
	// Generation of packets for test cases
	// ------------------------------------------------------------------
	
	static public UnitTestParams generatePacketFullGood(int testCaseNum, String description) {

		DataOutPacket packet = new DataOutPacket();

		// Throw in some dummy location data
		packet.add(DataOutHandlerTags.version, "Test Version");
		packet.add(DataOutHandlerTags.ACCEL_Z, (double) 34.5678);
		packet.add(DataOutHandlerTags.ACCEL_Y, (double) 34.5678);
		packet.add(DataOutHandlerTags.ACCEL_X, (double) 34.5678);
		packet.add(DataOutHandlerTags.ORIENT_Z, (double) 34.5678);
		packet.add(DataOutHandlerTags.ORIENT_Y, (double) 34.5678);
		packet.add(DataOutHandlerTags.ORIENT_X, (double) 34.5678);
        packet.add(DataOutHandlerTags.LIGHT, (float) 1.0);
        packet.add(DataOutHandlerTags.PROXIMITY, (float) 1.0);
        packet.add(DataOutHandlerTags.BATTERY_LEVEL, (int) 1);
        packet.add(DataOutHandlerTags.BATTERY_STATUS, (int) 1);
	   	packet.add(DataOutHandlerTags.SCREEN, 1);
	   	packet.add(DataOutHandlerTags.MODEL, "Galaxy S3");
	   	packet.add(DataOutHandlerTags.LOCALE_LANGUAGE, "english");
	   	packet.add(DataOutHandlerTags.LOCALE_COUNTRY, "usa");
		packet.add(DataOutHandlerTags.TEL_CELLID, (int) 1);
		packet.add(DataOutHandlerTags.TEL_MDN, (long) 123);
		packet.add(DataOutHandlerTags.TEL_NETWORK, "verizon");
        packet.add(DataOutHandlerTags.GPS_LON, (double) 34.5678);
        packet.add(DataOutHandlerTags.GPS_LAT, (double) 34.5678);
        packet.add(DataOutHandlerTags.GPS_SPEED, (float) 34.5678);
        packet.add(DataOutHandlerTags.GPS_TIME, (long) 123456);
        packet.add(DataOutHandlerTags.KEYLOCKED, 1);        	

		packet.add(DataOutHandlerTags.BLUETOOTH_ENABLED, 1);			
		packet.add(DataOutHandlerTags.WIFI_ENABLED, 0);			
		packet.add(DataOutHandlerTags.WIFI_CONNECTED_AP, "fred");			
		packet.add(DataOutHandlerTags.CALL_DIR, "in");
		packet.add(DataOutHandlerTags.CALL_REMOTENUM, "2536779838");
		packet.add(DataOutHandlerTags.CALL_DURATION, (int) 1);
    	packet.add(DataOutHandlerTags.SMS_DIR, "out");
		packet.add(DataOutHandlerTags.SMS_REMOTENUM, "2536779838");
		packet.add(DataOutHandlerTags.SMS_LENGTH, (int) 1);
		packet.add(DataOutHandlerTags.MMS_DIR, "in");
		packet.add(DataOutHandlerTags.MMS_REMOTENUM, "2536779838");
		packet.add(DataOutHandlerTags.MMS_LENGTH, (int) 1);
    	packet.add(DataOutHandlerTags.WEBPAGE, "google.com");	    			


		Vector<String> taskVector = new Vector<String>();
		taskVector.add("one");
		taskVector.add("two");
        	
		packet.add(DataOutHandlerTags.TASKS, taskVector);

		Vector<String> bluetoothVector = new Vector<String>();
		bluetoothVector.add("four");
		bluetoothVector.add("five");
		bluetoothVector.add("six");
		
		packet.add(DataOutHandlerTags.BLUETOOTH_PAIREDDEVICES, bluetoothVector);

		Vector<String> wifiVector = new Vector<String>();
		wifiVector.add("seven");
		wifiVector.add("eight");
		wifiVector.add("nine");
		wifiVector.add("ten");
		
		packet.add(DataOutHandlerTags.WIFI_APSCAN, wifiVector);	
		
		
		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);
		
		
		
		return unitTestParams;
	}
	
	
	static public UnitTestParams generateTestPacketNumericAsStrings(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketNumericAsStrings");
		packet.add(DataOutHandlerTags.ACCEL_X, String.valueOf((double) 11.11111));
		packet.add(DataOutHandlerTags.ACCEL_Y, String.valueOf((double) 22.22222));
		packet.add(DataOutHandlerTags.ACCEL_Z, String.valueOf((double) 33.33333));
		
		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}	
	
	// Check that record is saved (only header data in record)
	static public UnitTestParams generateTestPacketEmpty(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketEmpty");

		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}

	static public UnitTestParams generateTestPacketMinimalVersionOnly(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacket1");

		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}
	
	static public UnitTestParams generateTestPacketLarge(int testCaseNum, String description, int largePacketLength) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketLarge");
		
		char[] array = new char[largePacketLength];
		
		for (int i = 0; i < largePacketLength; i++) {
			int ones = i % 9;
			array[i] = (char) (0x30 + ones);
		}
		
		packet.add("test_field", new String(array));

		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}	

	static public UnitTestParams generateTestPacketNull(int testCaseNum, String description) {

		DataOutPacket packet = null;
		
		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

//		Log.d(TAG, "Test case " + testCase + ": sendTestPacketNull");
//		packet = generateTestPacketNull();				// Should throw null pointer exception (but not crash)
//		try {
//			TestPacket(packet, String.valueOf(testCase), null, null, false);			
//		} catch (Exception e) {
//			
//			if (e.toString().equalsIgnoreCase("java.lang.NullPointerException") ) {
//				Log.d(TAG, "Test Case " + testCase + "           PASSED");
//			}
//			Log.d(TAG, e.toString());
//		}			
//		testCase++;			
		
		return unitTestParams;
	}	
	
	static public UnitTestParams generateTestPacketRepeatedParameters(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketRepeatedParameters");
		packet.add(DataOutHandlerTags.ACCEL_Z, (double) 11.11111);
		packet.add(DataOutHandlerTags.ACCEL_Z, (double) 22.22222);

		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}	

	static public UnitTestParams generateTestPacketEmptyJSONArray(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketEmptyJSONArray");
		Vector<String> taskVector = new Vector<String>();
		packet.add(DataOutHandlerTags.TASKS, taskVector);

		// Now create pass/fail criteria
		List<String> ignoreList;	
		ignoreList = new ArrayList<String>();
		ignoreList.add(DataOutHandlerTags.TASKS);	
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, ignoreList, false, description);

		return unitTestParams;
	}		
	
	// Note, this will pass on Drupal but fail on AWS (which doesn't allow multiple levels
	static public UnitTestParams generateTestPacketJSONArrayTooManyLevels(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketJSONArrayTooManyLevels");
		Vector<Vector> taskVector = new Vector<Vector>();
		Vector<String> innerVector = new Vector<String>();			// One too man levels of nesting
		innerVector.add("one");
		taskVector.add(innerVector);
		packet.add(DataOutHandlerTags.TASKS, taskVector);
		
		
//		// Now create pass/fail criteria
//		DataOutPacket expectedpacket = new DataOutPacket();
//		expectedpacket.add(DataOutHandlerTags.version, "TestPacketJSONArrayTooManyLevels");
//		Vector<String> expectedTaskVector = new Vector<String>();
//		expectedTaskVector.add("one");
//		expectedpacket.add(DataOutHandlerTags.TASKS, expectedTaskVector);			
		
		List<String> ignoreList;
		ignoreList = new ArrayList<String>();
		ignoreList.add("time_stamp");
		ignoreList.add("record_id");		
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, ignoreList, false, description);

		return unitTestParams;
	}		

	static public UnitTestParams generateTestPacketTooLarge(int testCaseNum, String description, int tooLargePacketLength) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketTooLarge");
		
		char[] array = new char[tooLargePacketLength];
		
		for (int i = 0; i < tooLargePacketLength; i++) {
			int ones = i % 9;
			array[i] = (char) (0x30 + ones);
		}
		
		packet.add("test_field", new String(array));
		
		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}		
	

	// This one fails now because of the following
	// when we add a packet it goes to the cache, then is sent to the remote database
	// The database returns with the node id to tell us that it was successfully added.
	// In this case it reuturns the node id even though id doesn't save the unknown tags.
	// We then use the Cache as if it were good (the the remote database doesn't match it
	
	// The fix is, when we receive the node id from the remote database telling us of the 
	// success we need to actually grab the record and reconcile it with the cache.
	static public UnitTestParams generateTestPacketUnknownInconsistentTags(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketUnknownInconsistentTags");
		packet.add("UnknownTag1", "Unknown1");
		packet.add("AIRFLOW", "test@gmail.com");
		packet.add("UnknownTag2", "Unknown2");
		
		// Now create pass/fail criteria
		DataOutPacket packetExpected = new DataOutPacket();
		packetExpected.add(DataOutHandlerTags.version, "TestPacketUnknownInconsistentTags");
		packetExpected.add("AIRFLOW", "test@gmail.com");
		
		List<String> ignoreList = new ArrayList<String>();
		ignoreList.add("time_stamp");
		ignoreList.add("record_id");		
		
		
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), packetExpected, ignoreList, false, description);

		return unitTestParams;
	}		
	
	static public UnitTestParams generateTestPacketParameterTypes(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketParameterTypes");
		// Note that we have to hyjack some tags for this test
		packet.add(DataOutHandlerTags.ACCEL_X, (char) 'a');
		packet.add(DataOutHandlerTags.ACCEL_Y, (byte) 1);
		packet.add(DataOutHandlerTags.ACCEL_Z, (short) 2);
		packet.add(DataOutHandlerTags.ORIENT_X, (int) 3);
		packet.add(DataOutHandlerTags.ORIENT_Y, (long) 4);
		packet.add(DataOutHandlerTags.ORIENT_Y, (float) 5.5);
		packet.add(DataOutHandlerTags.ORIENT_X, (double) 6.6);
		packet.add(DataOutHandlerTags.ORIENT_X, (double) 6.6);
		packet.add(DataOutHandlerTags.GPS_LAT, (char) 7);
		packet.add(DataOutHandlerTags.GPS_LON, "eight");
		
		// Now create pass/fail criteria
		Vector<String> taskVector = new Vector<String>();
		taskVector.add("one");
		taskVector.add("two");
		packet.add(DataOutHandlerTags.TASKS, taskVector);	
		
		
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}		

	static public UnitTestParams generateTestPacketInvalidCharacter(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestTestPacketInvalidCharacter");
		// Note that we have to hyjack some tags for this test
		packet.add(DataOutHandlerTags.ACCEL_Y, "S\uFFFF Se\uFFFFFFFFor"); // This says si senior
		
		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, true, description);

		return unitTestParams;
	}		


	// This passes because we are simply sending all numerics as strings
	static public UnitTestParams generateTestPacketParameterOutOfRange(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket();
		packet.add(DataOutHandlerTags.version, "TestPacketParameterOutOfRange");
		// Note that we have to hyjack some tags for this test
		packet.add(DataOutHandlerTags.ACCEL_X, "99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		
		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}		

	static public UnitTestParams generateTestPacketHabit(int testCaseNum, String description) {
		DataOutPacket packet = new DataOutPacket(DataOutHandlerTags.STRUCTURE_TYPE_HABIT);
		packet.add(DataOutHandlerTags.version, description);
		packet.add(DataOutHandlerTags.HABIT_NAME, "Test habit 1 - name");
		packet.add(DataOutHandlerTags.HABIT_NOTE, "Test habit 1 - note");
		packet.add(DataOutHandlerTags.HABIT_NAME, "Test habit 1");
		
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    	Calendar calendar = GregorianCalendar.getInstance();
    	dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String currentTimeString = dateFormatter.format(calendar.getTime());
		packet.add(DataOutHandlerTags.HABIT_REMINDER_TIME, currentTimeString);
		
		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}		

	static public UnitTestParams generateTestPacketCheckin(int testCaseNum, String description) {

	    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    	Calendar calendar = GregorianCalendar.getInstance();
    	dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timeString = dateFormatter.format(calendar.getTime());
		
		
		DataOutPacket packet = new DataOutPacket(DataOutHandlerTags.STRUCTURE_TYPE_CHECKIN);
		packet.add(DataOutHandlerTags.version, description);
		packet.add(DataOutHandlerTags.CHECKIN_CHECKIN_TIME, timeString);
		packet.add(DataOutHandlerTags.CHECKIN_HABIT_ID, 1);
		
		// Now create pass/fail criteria
		UnitTestParams unitTestParams = new UnitTestParams(packet, String.valueOf(testCaseNum), null, null, false, description);

		return unitTestParams;
	}		
	
	
	
	
	
	
}
