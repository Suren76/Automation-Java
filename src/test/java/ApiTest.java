import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Header;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class ApiTest {
    SoftAssert softAssert = new SoftAssert();
    protected final static String baseUrl = "https://gorest.co.in";
    private int id = 0;

    @Test(groups = {"apiTests"})
    public void testApiUsers() {
        System.out.println("ApiTest.testApi");
        List< Map<String, Object> > list = get("%s/public/v2/users".formatted(baseUrl)).as(new TypeRef< List< Map<String, Object> > >(){});

        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(list.size(), 10);

        for (Map<String, Object> map : list) {
            System.out.println(map.get("id"));
            softAssert.assertTrue(String.valueOf(map.get("id")).startsWith("51145"));
        }

        softAssert.assertAll();
    }

    @Test(groups = {"apiTest"})
    void addUser() {
        String json = "{\n" +
                "    \"name\":\"Smbulik70006\", \n" +
                "    \"gender\":\"male\", \n" +
                "    \"email\":\"smbulik70006@testmail.com\", \n" +
                "    \"status\":\"active\"\n" +
                "}";

        var response = given().
                header(new Header("Authorization"," Bearer c01cff63bb463aa8ea208d08eae4f01e31f3be301c3c16c813543d4e8c65554e")).
                header(new Header("Accept","application/json")).
                header("Content-Type","application/json").

                body(json)
        .post("%s/public/v2/users".formatted(baseUrl));

        id = (int) response.as(new TypeRef< Map<String, Object> >(){}).get("id");

        System.out.println(response.asPrettyString());
    }

    @Test(groups = {"apiTests"}, dependsOnMethods = {"addUser"})
    void testApiUser() {
        System.out.println("ApiTest.testApiUser");
        Map<String, Object> map = get("%s/public/v2/users/%s".formatted(baseUrl, String.valueOf(id))).as(new TypeRef< Map<String, Object> >(){});
        Assert.assertEquals(map.get("id"), id);
        Assert.assertEquals(map.get("name"), "Smbulik70006");
        Assert.assertEquals(map.get("email"), "smbulik70006@testmail.com");
        Assert.assertEquals(map.get("gender"), "male");
        Assert.assertEquals(map.get("status"), "active");

    }
}
