package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //to make sure that both will be executed without errors and in a correct order

public class HardCodedExamples {

   String baseURI = RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
   String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTczMzE2ODYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NzM3NDg4NiwidXNlcklkIjoiNDI2MCJ9.5CYWlBNev-YgFsCh0pN_-H4hhXRP_B9zwWIptbKt3lQ";
   static String employee_id;

   @Test

   public void acreateEmployee(){

       RequestSpecification request = given().header("Content-Type", "application/json").
               header("Authorization", token).body("{\n" +
                       "  \"emp_firstname\": \"Harley\",\n" +
                       "  \"emp_lastname\": \"Davidson\",\n" +
                       "  \"emp_middle_name\": \"Feel\",\n" +
                       "  \"emp_gender\": \"M\",\n" +
                       "  \"emp_birthday\": \"2019-01-16\",\n" +
                       "  \"emp_status\": \"Full-time\",\n" +
                       "  \"emp_job_title\": \"Hug Giver\"\n" +
                       "}");

       Response response = request.when().post("/createEmployee.php");
       response.prettyPrint();
       response.then().assertThat().statusCode(201);
       //Hamcrest matchers
       response.then().assertThat().body("Message", equalTo ("Employee Created"));
       response.then().assertThat().body("Employee.emp_firstname", equalTo("Harley"));

       //using jsonPath(), to specify the key in the body so that it returns the value against it

       employee_id = response.jsonPath().getString("Employee.employee_id");
       System.out.println(employee_id);
   }

   @Test
   public void bgetCreatedEmployee(){
       RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
               header("Authorization", token).queryParam("employee_id", employee_id);

       Response response = preparedRequest.when().get("/getOneEmployee.php");
       response.prettyPrint();
       response.then().assertThat().statusCode(200);

       String tempId = response.jsonPath().getString("employee.employee_id");
       System.out.println(employee_id);
       Assert.assertEquals(tempId, employee_id);
   }
   @Test
   public void cupdateEmployee(){
       RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
               header("Authorization", token).body("{\n" +
                       "  \"employee_id\": \"39396A\",\n" +
                       "  \"emp_firstname\": \"Charlie\",\n" +
                       "  \"emp_lastname\": \"Davidson\",\n" +
                       "  \"emp_middle_name\": \"Feel\",\n" +
                       "  \"emp_gender\": \"M\",\n" +
                       "  \"emp_birthday\": \"2017-12-16\",\n" +
                       "  \"emp_status\": \"Temp\",\n" +
                       "  \"emp_job_title\": \"Pro Hug Giver\"\n" +
                       "}");

       Response response = preparedRequest.when().put("/updateEmployee.php");
       response.prettyPrint();
       response.then().assertThat().statusCode(200);
   }

   @Test
   public void dGetUpdatedEmployee(){
       RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
               header("Authorization", token).queryParam("employee_id", employee_id);

       Response response = preparedRequest.when().get("/getOneEmployee.php");
       response.then().assertThat().statusCode(200);
       response.prettyPrint();
   }

   @Test
    public void eGetAllEmployees(){
       RequestSpecification preparedRequest = given().header("Authorization", token).request().
               header("Content-Type", "application/json");

       Response response = preparedRequest.when().get("/getAllEmployees.php");

       //it returns string of response
       String allEmployees = response.prettyPrint();

       //jsonPath() vs jsonPath
       //jsonPath is a class that contains method for converting the values into json object
       //jsonPath() is a method belongs to jsonPath class

       //creating object of jsonPath class
       JsonPath js = new JsonPath(allEmployees);

       //retrieving the total number of employees
       int count = js.getInt("Employees.size()");
       System.out.println(count);

       for (int i=0; i<count; i++){
           String empId = js.getString("Employees["+ i + "].employee_id");
           System.out.println(empId);
       }
   }

}
