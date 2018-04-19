package org.kumpulainen.learningsystem;

import java.util.Date;
import java.util.logging.Logger;

public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class.getName());

    public static boolean validateCourse(String courseCode, String name, int credits, Date startTime) {

        if (courseCode == null || courseCode.isEmpty()) {
            logger.warning("Invalid course code.");
            return false;
        }

        if (name == null || name.isEmpty()) {
            logger.warning("Invalid course name");
            return false;
        }

        if (credits < 0 || credits > 8) {
            logger.warning("Invalid course credit amount");
            return false;
        }

        if (startTime.before(new Date())) {
            logger.warning("Course start time cannot be in the past");
            return false;
        }
        return true;
    }
}
