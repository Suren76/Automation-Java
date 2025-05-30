package BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BaseTest {
    public WebDriver driver;

    @BeforeSuite
    public void setup() {
        try {
            WebDriverManager.chromedriver().setup();
        } catch (Exception e) {
            System.out.println("WebDriverManager.chromedriver().setup() failed");
            System.out.println("Trying to setup WebDriverManager.chromedriver().browserVersion(\"chrome%s\")".formatted(getChromeVersion()));
            WebDriverManager.chromedriver().browserVersion("chrome%s".formatted(getChromeVersion())).setup();
        }
    }

    @BeforeClass(alwaysRun = true)
    public void configuringBeforeRun() {
        driver = new ChromeDriver();
        System.out.println("BeforeClass");
    }

    @AfterClass(alwaysRun = true)
    public void afterTestEnds() {
        driver.close();
        System.out.println("AfterClass");
    }

    @AfterSuite
    public void TheEnd() {
        driver.quit();
    }

    static String getChromeVersion() {
        String version = "";
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.startsWith("linux")) {
            version = executeCommand("google-chrome --version").split(" ")[2].split("\\.")[0];
        } else if (osName.startsWith("windows")) {
            version = executeCommand("google-chrome.exe --version").split(" ")[2].split("\\.")[0];
        } else if (osName.startsWith("macos")) {
            System.out.println("not exist functionality");
            System.exit(36456);
        }

        return version;
    }

    static private String executeCommand(String command) {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

}
