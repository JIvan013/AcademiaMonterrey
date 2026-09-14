package base;

import java.util.LinkedHashSet;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public abstract class BaseTest {

    protected WebDriver browser;
    protected static final String BASE_HOME_URL = "https://www.santander.com.mx/";
    private String primaryWindowHandle;

    @BeforeMethod(alwaysRun = true)
    public void initializeTestEnvironment() {
        launchBrowserSession();
    }

    private void launchBrowserSession() {
        browser = DriverFactory.createChromeDriver();
        browser.get(BASE_HOME_URL);
        primaryWindowHandle = browser.getWindowHandle();
    }

    protected void returnToHomePage() {
        try {
            if (browser == null) {
                launchBrowserSession();
                return;
            }

            Set<String> openWindowHandles = new LinkedHashSet<>(browser.getWindowHandles());

            if (openWindowHandles.isEmpty()) {
                System.out.println("[INFO] No quedan ventanas abiertas. Reiniciando navegador...");
                restartBrowserSession();
                return;
            }

            String targetWindowHandle;

            if (primaryWindowHandle != null && openWindowHandles.contains(primaryWindowHandle)) {
                targetWindowHandle = primaryWindowHandle;
            } else {
                targetWindowHandle = openWindowHandles.iterator().next();
                primaryWindowHandle = targetWindowHandle;
            }

            browser.switchTo().window(targetWindowHandle);

            for (String windowHandle : openWindowHandles) {
                if (!windowHandle.equals(targetWindowHandle)) {
                    try {
                        browser.switchTo().window(windowHandle);
                        browser.close();
                    } catch (WebDriverException ignored) {
                    }
                }
            }

            browser.switchTo().window(targetWindowHandle);
            browser.get(BASE_HOME_URL);

        } catch (WebDriverException exception) {
            System.out.println("[INFO] La ventana de Chrome se perdió: " + exception.getClass().getSimpleName());
            System.out.println("[INFO] Reiniciando navegador...");
            restartBrowserSession();
        }
    }

    protected Set<String> captureOpenWindows() {
        try {
            return new LinkedHashSet<>(browser.getWindowHandles());
        } catch (WebDriverException exception) {
            return new LinkedHashSet<>();
        }
    }

    protected void focusWindowAfterNavigation(Set<String> windowHandlesBeforeAction) {
        Set<String> windowHandlesAfterAction = new LinkedHashSet<>(browser.getWindowHandles());

        if (windowHandlesAfterAction.isEmpty()) {
            throw new WebDriverException("Santander cerró todas las ventanas.");
        }

        for (String windowHandle : windowHandlesAfterAction) {
            if (!windowHandlesBeforeAction.contains(windowHandle)) {
                browser.switchTo().window(windowHandle);
                return;
            }
        }

        try {
            String currentWindowHandle = browser.getWindowHandle();
            if (windowHandlesAfterAction.contains(currentWindowHandle)) {
                return;
            }
        } catch (WebDriverException ignored) {
        }

        browser.switchTo().window(windowHandlesAfterAction.iterator().next());
    }

    protected void restartBrowserSession() {
        try {
            if (browser != null) {
                browser.quit();
            }
        } catch (Exception ignored) {
        }
        launchBrowserSession();
    }

    @AfterMethod(alwaysRun = true)
    public void closeTestEnvironment() {
        if (browser != null) {
            try {
                browser.quit();
            } catch (Exception ignored) {
            }
        }
    }

    public WebDriver getBrowser() {
        return browser;
    }
}
