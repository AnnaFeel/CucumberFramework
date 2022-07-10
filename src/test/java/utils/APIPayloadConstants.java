package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload() {
        String createEmployee = "{\n" +
                "    \"emp_firstname\": \"Harley\",\n" +
                "    \"emp_lastname\": \"Kiss\",\n" +
                "    \"emp_middle_name\": \"One\",\n" +
                "    \"emp_gender\": \"M\",\n" +
                "    \"emp_birthday\": \"2000-06-19\",\n" +
                "    \"emp_status\": \"permanent\",\n" +
                "    \"emp_job_title\": \"Hug\"\n" +
                "}";
        return createEmployee;
    }
    // passing the body from json object
    public static String createEmployeePayloadViaJson() {
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Frank");
        obj.put("emp_lastname", "Martin");
        obj.put("emp_middle_name", "FM");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2000-06-19");
        obj.put("emp_status", "employed");
        obj.put("emp_job_title", "SDET");
        return obj.toString();
    }

    public static String createEmployeeDynamic
            (String firstName,String lastName,String middleName,
             String gender,String DoB,
             String status,String jobTitle) {
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname",firstName );
        obj.put("emp_lastname",lastName );
        obj.put("emp_middle_name",middleName );
        obj.put("emp_gender", gender);
        obj.put("emp_birthday",DoB);
        obj.put("emp_status", status);
        obj.put("emp_job_title", jobTitle);
        return obj.toString();
    }
}


