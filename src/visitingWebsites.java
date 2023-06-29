import java.awt.Dimension;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class visitingWebsites {
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void before() {
		driver.get("https://hatem-hatamleh.github.io/Selenium-html/");
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void controlScreen() {

		driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		Dimension iphonXR = new Dimension(414, 896);
//		driver.manage().window().setSize(iphonXR);
		String url = driver.getCurrentUrl();
//		System.out.println(url);
//		getPageSource
		// driver.quit();//close all tabs
		// driver.close();//close only the first one

		// navigate methods
//		driver.navigate().to("https://google.com");
//		driver.navigate().forward();
//		driver.navigate().refresh();
//		driver.navigate().back();

	}

	@Test(enabled = false)
	public void findElementBy() {
		driver.findElement(By.xpath("//a[@class = 'button locators']")).click();

		// a way to get element that have auto-generated attribute
		/*
		 * find elements by: Id,tagVame,class,name,linkText,partialLinkText,xPath
		 */
		// NOW for xPath divided into many ways:

		// 1-absolute path ===> "//tagneame[@atribute = 'value']"
		String text = driver.findElement(By.xpath("//*[@class = 'button course web']")).getText();
		System.out.println(text);

		// 2- general tagName using * ==> "//*[@attribute = 'value']"
		text = driver.findElement(By.xpath("//*[@class = 'button course mobile']")).getText();
		System.out.println(text);

		// 3- contains ==> "//*[contains(@attribute , 'value')]"
		text = driver.findElement(By.xpath("//*[contains(@class , 'mobile')]")).getText();
		System.out.println(text);

		// 4-by text ==> "//*[text()='text of the target element']"
		text = driver.findElement(By.xpath("//*[text()='Postman']")).getText();
		System.out.println(text); // we found the elements by text
		// text with contains ==>
		text = driver.findElement(By.xpath("//*[contains(text() , 'man')]")).getText();
		System.out.println(text);

		// 5- data test ID == a unique id we assign inside the codes
		text = driver.findElement(By.xpath("//*[@data-testid = 'cypress']")).getText();
		System.out.println(text);
		text = driver.findElement(By.xpath("//*[contains(@data-testid , 'rest')]")).getText();
		System.out.println(text);

		// css selectors
		System.out.println("----using css selectors ------");
		// class
		text = driver.findElement(By.cssSelector(".web")).getText();
		System.out.println(text);
		// id
		text = driver.findElement(By.cssSelector("#selenium")).getText();
		System.out.println(text);
		// data using []
		text = driver.findElement(By.cssSelector("[data-testid=rest]")).getText();
		System.out.println(text);
		// any tagName using *square braces* []
		text = driver.findElement(By.cssSelector("[name = playwright]")).getText();
		System.out.println(text); // VIP NOTE == using square braces means you don't need the quotations

		// we can simply copy cssSelector for any element
		// also for the xPath

		// developer tools : use the console to check the xPath,cssSelector
		// xPath => $x("xPath")
		// cssSelector => $("cssSelectors")

		// 📌travel between elements with the cssSelector
		// use > for direct children , or leave a space
		// use . for classes , # for ID ,سهل للمبرمجين بالفرونت اللي بعرفو سي اس اس
		// nth of type , .class li:nth-of-type(1); index starts from 1

		// to make your code readable , use the By class as the following :
		By buttonPath = By.cssSelector("[data-testid=rest]");
		System.out.println(driver.findElement(buttonPath).getText());
		// important selenium classes :
		// By , webElelement ,

		WebElement button = driver.findElement(buttonPath);
		System.out.println("here is the webElement="+ button.getText());
		// ** findElements == note-1) use .get() to retrieve a single element , index
		// starts with 1 ;
		// note-2) list of web elements
		List<WebElement> x = driver.findElements(buttonPath);

	}

	@Test(enabled = false)
	public void interactWithBrowser_section5() {
//the followings are methods for webelements 

		// click
		driver.findElement(By.className("actions")).click();
		// send keys
		driver.findElement(By.name("firstName")).sendKeys("Razan Rzonte ");
		driver.findElement(By.name("lastName")).sendKeys("roro ");
		WebElement button = driver.findElement(By.cssSelector(".button.actions-button"));
		// check box & radio button

		// drop down == tag name is select , in selenium a special class for select
		// options

		// we can choose using (index,value,text)
		// first get the select
		WebElement DropDown = driver.findElement(By.id("level"));
		Select coursesDropDown = new Select(DropDown);
		// we access methods for SELECT now
		coursesDropDown.selectByIndex(1);

///what returns boolean (isDisplayed,isEnabled,isSelected) each one has conditions

		// isDisplayed ==> needs the element to be in the code , and check the
		// visibility
		// ==> returns True/False
		// because (findElement) gives you an error if it can't find the element,
		Boolean displayed = driver.findElement(By.id("level")).isDisplayed();
		System.out.println("displayed= " + displayed);

		// isEnabled ==> true or false if DisAbled
		Boolean enabled = driver.findElement(By.id("level")).isEnabled();
		System.out.println("enabled=" + enabled);

		// choose radio
		WebElement radio = driver.findElement(By.id("web"));
		radio.click();
		// isSelected
		boolean checkRadio = radio.isSelected();
		System.out.println("checkRadio =" + checkRadio);

// Get things:
//		getAttribute,getTagName,getCssValue,getAccessibleName,getAriaRole = String
//		getLocation = Point + methodsString(y,x)
//		getSize = Dimension + methodsString(height,width)
//		getRect = Rectangle + methodsString(x,y,height,width)

		// getAttribute returns a string with the value inside the attribute
		System.out.println("getAttribute type for radio = " + radio.getAttribute("type"));
		System.out.println("getTageName of radio = " + radio.getTagName());
		System.out.println("getCssValue fontFamily = " + button.getCssValue("font-family"));
		// getCssValure to make assertions for some css effects when clicking

		// getLocation
		System.out.println("getLocationY = " + button.getLocation().y);
		// getSize
		System.out.println("getSizeWidth = " + button.getSize().width);
		// getRect
		System.out.println("getRectWidthY = " + button.getRect().width + "/" + button.getRect().y);
		// getAccessibleName search for aria-label
		System.out.println("getAccessibleName = " + button.getAccessibleName());
		// getAriaRole
		System.out.println("getAriaRole for button= " + button.getAriaRole());

// using class Actions in Selenium to do :
//	(double click, right click,move to element,Drag & drop )

		// Double click
		driver.navigate().to("https://skill-test.net/mouse-double-click");
		WebElement Dbutton = driver.findElement(By.id("clicker"));
		Actions action = new Actions(driver);
		action.doubleClick(Dbutton).perform();

		// right click
		driver.navigate().to("https://skill-test.net/right-click-test");
		WebElement Rbutton = driver.findElement(By.id("clicker"));
		action.contextClick(Rbutton).perform();

		// Hover over element
		driver.navigate().to("https://tympanus.net/Tutorials/CaptionHoverEffects/index4.html");
		WebElement img = driver.findElement(By.xpath("//*[@alt = 'img04']"));
		action.moveToElement(img).perform();

		// Drag & Drop
		driver.navigate().to("https://demoqa.com/droppable/");
		WebElement item = driver.findElement(By.id("draggable"));
		WebElement droparea = driver.findElement(By.id("droppable"));
		Actions act = new Actions(driver);

		act.dragAndDrop(item, droparea).perform();
//		action.clickAndHold(item).moveToElement(droparea).release().build().perform();
	}

	@Test(enabled = false)
	public void multiWindows() {
		driver.findElement(By.className("windows")).click();
		// windowHandel , windowHandles returns a id STRING
		String parent = driver.getWindowHandle();
		System.out.println("first tap ID = " + driver.getWindowHandle());
		List<WebElement> buttons = driver.findElements(By.cssSelector(".button "));
		for (WebElement button : buttons) {
			button.click();
		}

		// VIP NOTE: use Set<string> for taps
		Set<String> taps = driver.getWindowHandles();
//		String[] Taps = taps.toArray(new String[taps.size()]);
//		//Switch to window
//		driver.switchTo().window(Taps[1]);

		for (String tap : taps) {
			driver.switchTo().window(tap);
			if (driver.getTitle().contains("LinkedIn")) {
				// Different between Close & quite
				driver.close();
				break;
			}

		} // always after closing a window , specify which one the driver opens
		driver.switchTo().window(parent);
	}

	@Test (enabled= false)
	public void frames() {
		
		driver.findElement(By.className("frames")).click();
		
//to access elements inside a frame ,first we use driver.switchTo().frame()	
		//inside frame() we use (name OR ID // webElement // index)
		driver.switchTo().frame("qacart");//using ID
		driver.findElement(By.name("firstName")).sendKeys("Rzoonteee");

//		Go to the parentFrame
		driver.switchTo().parentFrame();
//		use WebElement inside frame() method
		WebElement frame = driver.findElement(By.id("qacart"));//use this when the frame doesn't have an ID or Name
		driver.switchTo().frame(frame);//using WebElement
		driver.findElement(By.cssSelector("#web")).click();
		
		//nested frames ==> needs two steps
		driver.switchTo().parentFrame();
		driver.switchTo().frame("menu");
		driver.switchTo().frame("links");
		System.out.println(driver.findElement(By.cssSelector(".locators")).getText());
		driver.switchTo().parentFrame();
		driver.switchTo().parentFrame();
		System.out.println(driver.findElement(By.cssSelector(".frame-container p")).getText());
	}

	@Test (enabled=false)
	public void waits() throws InterruptedException {
		driver.findElement(By.className("waits")).click();
		//Threrad.sleep() ==> we will never use it because it consumes time
//						  ==> it's a JAVA code to stop executing the codes for seconds
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector(".button.primary")).click();


		
		//Implicit wait, when selenium can't find an element,before giving an error, it will repeat searching till specified duration finish
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector(".button.primary")).click();
// Disadvantage ==> it doesn't check the visibilty or enbaled 
//				==> only checks the dome exsistance 
//		        ==> it waits all elements to be existed
//		        ==> Don't mix (implicit or explicit)
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(100));
		By secondaryBtn = By.className("secondary");
		By cover = By.cssSelector(".cover");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(cover));
		wait.until(ExpectedConditions.elementToBeClickable(secondaryBtn)).click();
		
 	}
	
	@Test 
	public void alerts() throws InterruptedException {
		driver.findElement(By.className("alerts")).click();
		driver.findElement(By.cssSelector(".alert")).click();
		Thread.sleep(1000);

//		get the alert text
		System.out.println(driver.switchTo().alert().getText());
//		access the alert and accept
		driver.switchTo().alert().accept();
//		send keys  
		driver.findElement(By.cssSelector(".prompt")).click();
		driver.switchTo().alert().sendKeys("Hi rzonte");
		
		
	}
	
	
	@AfterTest
	public void after() {
//		driver.quit();
	}

}
