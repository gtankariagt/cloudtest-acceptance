import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;


public class crudOperation {
    RequestSpecification httpRequest;

    @Step("add patient details")
    public void addPatient() {

        Base();

        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "Virender1"); // Cast
        requestParams.put("lastName", "32");
        requestParams.put("contact", "9876543210");
        requestParams.put("hospitalName", "LG1Hospital");
        requestParams.put("result",  "Pass");


        httpRequest.header("Content-Type", "application/json");

// Add the Json to the body of the request
        httpRequest.body(requestParams.toString());
        Response response = httpRequest.post("/addPatient");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Step("Get all patient data")
    public void getAllPatients()  {

        Base();
        Response response = httpRequest.request(Method.GET, "/getAllPatients");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

    }

    @Step("Get patient detail by Id of <ID>")
    public void getPatientDetailByID(int ID)  {
        Base();
        httpRequest.header("Content-Type", "application/json");

        Response response = httpRequest.request(Method.GET, "/getPatient/"+ID);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

    }

    @Step("Update patient detail using <id>")
    public void updatePatient(int ID) {

       Base();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Sehvag"); // Cast
        requestParams.put("age", "32");
        requestParams.put("hospitalName", "LG1Hospital");
        requestParams.put("bloodType", "AB+");
        requestParams.put("result", "FAIL");
        requestParams.put("action", "Amed");

        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParams.toString());
        Response response = httpRequest.post("/updatePatient/+ ID");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    public void Base()
    {
        RestAssured.baseURI = "http://localhost:8080";
        httpRequest = RestAssured.given();

    }

}