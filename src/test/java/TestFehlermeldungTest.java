
import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;


public class TestFehlermeldungTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  String password;

   public TestFehlermeldungTest() {
         System.setProperty("webdriver.chrome.driver", "C:\\Users\\Eva\\Desktop\\Semester 2\\TestWerkZeuge\\chromedriver.exe");
  }
  
  
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://s1.demo.opensourcecms.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void TestFehlermeldungTest() throws Exception {
    
    //User Erstellen
    System.out.println("User wird erstellt");
    
  driver.get(baseUrl + "/wordpress/wp-login.php?loggedout=true");
     
    driver.findElement(By.id("user_login")).sendKeys("opensourcecms");
    driver.findElement(By.id("user_pass")).sendKeys("opensourcecms");
    driver.findElement(By.id("wp-submit")).click();
    
     	Thread.sleep(3000);
    
    //Eingeloggt
    driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-admin/users.php");
    driver.findElement(By.cssSelector("a.page-title-action")).click();
    driver.findElement(By.id("user_login")).sendKeys("blogtwz2018");
    driver.findElement(By.id("email")).sendKeys("blogtwz2018@gmail.com");
    driver.findElement(By.id("first_name")).sendKeys("blog");
    driver.findElement(By.id("last_name")).sendKeys("twz2018");
    driver.findElement(By.id("url")).sendKeys("http://127.0.0.1/wordpress/");
    driver.findElement(By.xpath("//form[@id='createuser']/table/tbody/tr[6]/td/button")).click();



    Thread.sleep(5000);
    
    new Select(driver.findElement(By.id("role"))).selectByVisibleText("Contributor");
    driver.findElement(By.id("createusersub")).click();
    
    Thread.sleep(5000);
    try {
      assertEquals(driver.findElement(By.id("message")).getText(), "New user created. Edit user\nDismiss this notice.");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
   
     driver.findElement(By.xpath("//a[contains(text(),'blogtwz2018')]")).click();

       Thread.sleep(5000);
 
    driver.findElement(By.xpath("//tr[@id='password']/td/button")).click();
     Thread.sleep(5000);
    
    driver.findElement(By.id("pass1-text")).sendKeys("T");
    driver.findElement(By.id("pass1-text")).sendKeys("e");
    driver.findElement(By.id("pass1-text")).sendKeys("s");
    driver.findElement(By.id("pass1-text")).sendKeys("t");
    Thread.sleep(5000);
    driver.findElement(By.cssSelector("input[name=\"pw_weak\"]")).click();
    driver.findElement(By.id("submit")).click();
    try {
      assertEquals(driver.findElement(By.cssSelector("#message > p > strong")).getText(), "User updated.");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    
   
    Thread.sleep(5000);
    
   driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php?loggedout=true");
    driver.findElement(By.id("user_login")).clear();
        Thread.sleep(3000);
    driver.findElement(By.id("user_login")).sendKeys("blogtwz2018@gmail.com");
    driver.findElement(By.id("user_pass")).clear();
        Thread.sleep(3000);
    driver.findElement(By.id("user_pass")).sendKeys("Test");
    driver.findElement(By.id("wp-submit")).click();
    
      	Thread.sleep(3000);
        
   
    System.out.println("eingeloggt");
    Thread.sleep(3000);
    
    
    driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-admin/users.php");
    Thread.sleep(3000);
    try {
      assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "You need a higher level of permission.");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
     Thread.sleep(5000);
     
     
    driver.get(baseUrl + "/wordpress/wp-login.php?loggedout=true");
     
    driver.findElement(By.id("user_login")).sendKeys("opensourcecms");
    driver.findElement(By.id("user_pass")).sendKeys("opensourcecms");
    driver.findElement(By.id("wp-submit")).click();
         Thread.sleep(5000);
  
     driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-admin/users.php");
     
   
     driver.findElement(By.name("users[]")).click();
 Thread.sleep(5000);
  
    
        new Select(driver.findElement(By.id("bulk-action-selector-top"))).selectByVisibleText("Delete");
    driver.findElement(By.id("doaction")).click();
     Thread.sleep(5000);
  driver.findElement(By.id("delete_option0")).click();
   Thread.sleep(2000);
    driver.findElement(By.id("submit")).click();
    
    try {
      assertEquals("User deleted.", driver.findElement(By.cssSelector("#message > p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    
  }

  
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
