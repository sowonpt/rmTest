package utils;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class VerifyUtils {

    static Logger LOGGER = Logger.getLogger(VerifyUtils.class);

    public static void contains(String expected, String actual) {
        boolean result = false;
        LOGGER.info("Verifying the expected data : " + expected + " to be visible in actual value : " + actual);
        if ((!actual.replaceAll("\\s+", "").equals("") && expected.replaceAll("\\s+", "").equals("")) ||
                (actual.replaceAll("\\s+", "").equals("") && !expected.replaceAll("\\s+", "").equals(""))) {
            Assert.fail(expected.replaceAll("\\s+", "") + " is not contained actual value " + actual.replaceAll("\\s+", ""));
        } else {
            if (actual.replaceAll("\\s+", "").contains(expected.replaceAll("\\s+", ""))) {
                return;
            } else if (actual.replaceAll("\\s+", "").equals(expected.replaceAll("\\s+", ""))) {
                return;
            } else if (expected.replaceAll("\\s+", "").contains(actual.replaceAll("\\s+", ""))) {
                return;
            } else
                Assert.fail(expected.replaceAll("\\s+", "") + " is not containing actual value " + actual.replaceAll("\\s+", ""));
        }

    }

    public static boolean containsBoolean(String expected, String actual) {
        boolean result = false;
        LOGGER.info("Verifying the expected data : " + expected + " to be visible in actual value : " + actual);
        if ((!actual.replaceAll("\\s+", "").equals("") && expected.replaceAll("\\s+", "").equals("")) ||
                (actual.replaceAll("\\s+", "").equals("") && !expected.replaceAll("\\s+", "").equals(""))) {
            Assert.fail(expected.replaceAll("\\s+", "") + " is not contained actual value " + actual.replaceAll("\\s+", ""));
            return false;
        } else {
            if (actual.replaceAll("\\s+", "").contains(expected.replaceAll("\\s+", ""))) {
                return true;
            } else if (actual.replaceAll("\\s+", "").equals(expected.replaceAll("\\s+", ""))) {
                return true;
            } else if (expected.replaceAll("\\s+", "").contains(actual.replaceAll("\\s+", ""))) {
                return true;
            } else {
                Assert.fail(expected.replaceAll("\\s+", "") + " is not containing actual value " + actual.replaceAll("\\s+", ""));
                return false;
            }
        }

    }


    public static void True(boolean condition) {
        LOGGER.info("Verifying the value to be True");
        Assert.assertTrue("The condition is false but expecting true - ", condition);
    }

    public static void False(boolean condition) {
        LOGGER.info("Verifying the value to be false");
        Assert.assertFalse("The condition is true but expecting false - ", condition);
    }

    public static void equals(String expected, String actual) {
        LOGGER.info("Verifying the expected data :" + expected + " to be visible in actual value :" + actual);
        Assert.assertEquals("the expected value is " + expected + " but actual is " + actual, expected.replaceAll("\\s+", ""), actual.replaceAll("\\s+", ""));
    }

    public static void equals(List<String> expected, List<String> actual) {
        LOGGER.info("Verifying the expected data :" + expected + " to be visible in actual value :" + actual);
        Assert.assertEquals("the expected value is " + expected + "but actual is" + actual, expected, actual);
    }

    public static void fail(String message) {
        Assert.fail(message);
    }

    public static void equals(boolean exp, boolean act) {
        Assert.assertEquals("Checked the condition to be equal", exp, act);
    }

    public static void equals(int expected, int actual) {
        LOGGER.info("Verifying the expected data : " + expected + " to be visible in actual value : " + actual);
        Assert.assertEquals("the expected value is " + expected + " but actual is " + actual + " ", expected, actual);
    }

    public static void notEquals(String expected, String actual) {
        LOGGER.info("Verifying the expected data : " + expected + " to be not visible in actual value : " + actual);
        Assert.assertNotSame("the expected value is " + expected + " but actual is " + actual + " ", expected, actual);
    }

    public static void dateTimeBefore(Date expectedDateTime, Date actualDateTime) {
        LOGGER.info("Verifying the expected date/time :" + expectedDateTime + " is before actual date/time :" + actualDateTime);
        Assert.assertTrue("the expected date/time is " + expectedDateTime + " is not before actual date/time " + actualDateTime, expectedDateTime.before(actualDateTime));
    }

    public static void dateTimeAfter(Date expectedDateTime, Date actualDateTime) {
        LOGGER.info("Verifying the expected date/time :" + expectedDateTime + " is after actual date/time :" + actualDateTime);
        Assert.assertTrue("the expected date/time is " + expectedDateTime + " is not after actual date/time " + actualDateTime, expectedDateTime.after(actualDateTime));
    }

    public static void dateTimeEquals(Date expectedDateTime, Date actualDateTime) {
        LOGGER.info("Verifying the expected date/time :" + expectedDateTime + " is after actual date/time :" + actualDateTime);
        Assert.assertTrue("the expected date/time is " + expectedDateTime + " is not after actual date/time " + actualDateTime, expectedDateTime.compareTo(actualDateTime) == 0);
    }

    public static void dateTimeBeforeOrEquals(Date expectedDateTime, Date actualDateTime) {
        LOGGER.info("Verifying the expected date/time :" + expectedDateTime + " is before actual date/time :" + actualDateTime);
        Assert.assertTrue("the expected date/time is " + expectedDateTime + " is not before actual date/time " + actualDateTime, expectedDateTime.before(actualDateTime) || expectedDateTime.equals(actualDateTime));
    }

    public static void dateTimeAfterOrEquals(Date expectedDateTime, Date actualDateTime) {
        LOGGER.info("Verifying the expected date/time :" + expectedDateTime + " is after actual date/time :" + actualDateTime);
        Assert.assertTrue("the expected date/time is " + expectedDateTime + " is not after actual date/time " + actualDateTime, expectedDateTime.after(actualDateTime) || expectedDateTime.equals(actualDateTime));
    }

    public static void dateTimeWithinRange(Date actualDateTime, int startMinutes, int endMinutes) {
        Calendar cal = Calendar.getInstance();
        Date startDateTime = new Date();
        cal.setTime(startDateTime);
        cal.add(Calendar.MINUTE, startMinutes);
        startDateTime = cal.getTime();

        Date endDateTime = new Date();
        cal.setTime(endDateTime);
        cal.add(Calendar.MINUTE, endMinutes);
        endDateTime = cal.getTime();

        LOGGER.info("Verifying the date/time :" + actualDateTime + " is within the minute time range between :" + startDateTime + " and " +endDateTime);

        Assert.assertTrue("the actual date/time is " + actualDateTime + " is not within the range of date/time " + startDateTime + ":" + endDateTime, startDateTime.before(actualDateTime) && endDateTime.after(actualDateTime));
    }
}
