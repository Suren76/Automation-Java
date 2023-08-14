package BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BaseTest {
    public WebDriver driver;

    @BeforeSuite
    public void setup() {
        WebDriverManager.chromedriver().browserVersion("chrome%s".formatted(getChromeVersion())).setup();
    }

    @BeforeClass
    public void configuringBeforeRun() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterTestEnds() {
        driver.close();
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
