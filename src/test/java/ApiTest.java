import io.restassured.common.mapper.TypeRef;
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

    @Test
    public void testApiUsers() {
        System.out.println("ApiTest.testApi");
        List< Map<String, Object> > list = get("https://gorest.co.in/public/v2/users").as(new TypeRef< List< Map<String, Object> > >(){});

        Assert.assertTrue(list.size() > 0);
        Assert.assertEquals(list.size(), 10);

        for (Map<String, Object> map : list) {
            softAssert.assertTrue(String.valueOf(map.get("id")).startsWith("51145"));
        }

        softAssert.assertAll();
    }

    @Test
    void testApiUser(){
        System.out.println("ApiTest.testApiUser");
        Map<String, Object> map = get("https://gorest.co.in/public/v2/users/5114515").as(new TypeRef< Map<String, Object> >(){});
        Assert.assertEquals(map.get("id"), 5114515);
        Assert.assertEquals(map.get("name"), "Hiranya Devar");
        Assert.assertEquals(map.get("email"), "devar_hiranya@ryan.test");
        Assert.assertEquals(map.get("gender"), "male");
        Assert.assertEquals(map.get("status"), "active");

    }
}
