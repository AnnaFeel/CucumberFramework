package utils;

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

}
