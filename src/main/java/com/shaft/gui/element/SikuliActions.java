package com.shaft.gui.element;

import com.shaft.gui.browser.BrowserFactory;
import org.apache.commons.io.IOUtils;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SikuliActions {

    /**
     * Checks if there is any text in an element, clears it, then types the required
     * string into the target element.
     *
     * @param pathToTargetElementImage relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @param text                     the target text that needs to be typed into the target
     *                                 element
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions type(String pathToTargetElementImage, String text) {
        return type(readImageFromFile(pathToTargetElementImage), text);
    }

    /**
     * Checks if there is any text in an element, clears it, then types the required
     * string into the target element.
     *
     * @param targetElement the image of the desired element in the form of a byte[]
     * @param text          the target text that needs to be typed into the target
     *                      element
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions type(byte[] targetElement, String text) {
        try {
            String elementText = initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).getText();
            if (!elementText.isEmpty()) {
                //clear
                for (char character : elementText.toCharArray()) {
                    initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).type(prepareElementPattern(targetElement), Key.BACKSPACE);
                }
            }
            initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).type(text);
        } catch (IOException | FindFailed rootCauseException) {
            ElementActions.failAction(null, formatTextForReport(text), null, rootCauseException);
        }
        ElementActions.passAction(null, null, formatTextForReport(text), prepareElementAttachment(targetElement));
        return this;
    }

    /**
     * Types the required string into the target element.
     *
     * @param pathToTargetElementImage relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @param text                     the target text that needs to be typed into the target
     *                                 element
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions typeAppend(String pathToTargetElementImage, String text) {
        return typeAppend(readImageFromFile(pathToTargetElementImage), text);
    }

    /**
     * Types the required string into the target element.
     *
     * @param targetElement the image of the desired element in the form of a byte[]
     * @param text          the target text that needs to be typed into the target
     *                      element
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions typeAppend(byte[] targetElement, String text) {
        try {
            initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).type(text);
        } catch (IOException | FindFailed rootCauseException) {
            ElementActions.failAction(null, formatTextForReport(text), null, rootCauseException);
        }
        ElementActions.passAction(null, null, formatTextForReport(text), prepareElementAttachment(targetElement));
        return this;
    }

    /**
     * Checks if there is any text in an element, clears it, then types the required
     * string into the target element.
     *
     * @param pathToTargetElementImage relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @param text                     the target text that needs to be typed into the target
     *                                 element
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions typeSecure(String pathToTargetElementImage, String text) {
        return typeSecure(readImageFromFile(pathToTargetElementImage), text);
    }

    /**
     * Checks if there is any text in an element, clears it, then types the required
     * string into the target element.
     *
     * @param targetElement the image of the desired element in the form of a byte[]
     * @param text          the target text that needs to be typed into the target
     *                      element
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions typeSecure(byte[] targetElement, String text) {
        try {
            String elementText = initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).getText();
            if (!elementText.isEmpty()) {
                //clear
                for (char character : elementText.toCharArray()) {
                    initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).type(prepareElementPattern(targetElement), Key.BACKSPACE);
                }
            }
            initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).type(text);
        } catch (IOException | FindFailed rootCauseException) {
            ElementActions.failAction(null, formatTextForReport(text), null, rootCauseException);
        }
        ElementActions.passAction(null, null, formatTextForReport(text).replaceAll(".", "•"), prepareElementAttachment(targetElement));
        return this;
    }

    /**
     * Clicks on a certain element using SikuliX
     *
     * @param pathToTargetElementImage relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions click(String pathToTargetElementImage) {
        return click(readImageFromFile(pathToTargetElementImage));
    }

    /**
     * Clicks on a certain element using SikuliX
     *
     * @param targetElement the image of the desired element in the form of a byte[]
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions click(byte[] targetElement) {
        String elementText = "";
        try {
            elementText = initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).getText().replace("\n", "").trim();
            initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).click();
        } catch (IOException | FindFailed rootCauseException) {
            if (!elementText.isEmpty()) {
                ElementActions.failAction(null, elementText, null, rootCauseException);
            } else {
                ElementActions.failAction(null, null, rootCauseException);
            }
        }
        if (!elementText.isEmpty()) {
            ElementActions.passAction(null, null, elementText, prepareElementAttachment(targetElement));
        } else {
            ElementActions.passAction(null, null, prepareElementAttachment(targetElement));
        }
        return this;
    }

    /**
     * Retrieves text from the target element and returns it as a string value.
     *
     * @param pathToTargetElementImage relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @return the text value of the target element
     */
    public String getText(String pathToTargetElementImage) {
        return getText(readImageFromFile(pathToTargetElementImage));
    }

    /**
     * Retrieves text from the target element and returns it as a string value.
     *
     * @param targetElement the image of the desired element in the form of a byte[]
     * @return the text value of the target element
     */
    public String getText(byte[] targetElement) {
        String elementText = "";
        try {
            elementText = initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).getText().replace("\n", "").trim();
        } catch (IOException | FindFailed rootCauseException) {
            if (!elementText.isEmpty()) {
                ElementActions.failAction(null, elementText, null, rootCauseException);
            } else {
                ElementActions.failAction(null, null, rootCauseException);
            }
        }
        if (!elementText.isEmpty()) {
            ElementActions.passAction(null, null, elementText, prepareElementAttachment(targetElement));
        } else {
            ElementActions.passAction(null, null, prepareElementAttachment(targetElement));
        }
        return elementText;
    }

    /**
     * Hovers over target element.
     *
     * @param pathToTargetElementImage relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions hover(String pathToTargetElementImage) {
        return hover(readImageFromFile(pathToTargetElementImage));
    }

    /**
     * Hovers over target element.
     *
     * @param targetElement the image of the desired element in the form of a byte[]
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions hover(byte[] targetElement) {
        String elementText = "";
        try {
            elementText = initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).getText().replace("\n", "").trim();
            initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).hover(prepareElementPattern(targetElement));
        } catch (IOException | FindFailed rootCauseException) {
            if (!elementText.isEmpty()) {
                ElementActions.failAction(null, elementText, null, rootCauseException);
            } else {
                ElementActions.failAction(null, null, rootCauseException);
            }
        }
        if (!elementText.isEmpty()) {
            ElementActions.passAction(null, null, elementText, prepareElementAttachment(targetElement));
        } else {
            ElementActions.passAction(null, null, prepareElementAttachment(targetElement));
        }
        return this;
    }

    /**
     * Double-clicks on an element using SikuliX
     *
     * @param pathToTargetElementImage relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions doubleClick(String pathToTargetElementImage) {
        return doubleClick(readImageFromFile(pathToTargetElementImage));
    }

    /**
     * Double-clicks on an element using SikuliX
     *
     * @param targetElement the image of the desired element in the form of a byte[]
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions doubleClick(byte[] targetElement) {
        String elementText = "";
        try {
            elementText = initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).getText().replace("\n", "").trim();
            initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).doubleClick(prepareElementPattern(targetElement));
        } catch (IOException | FindFailed rootCauseException) {
            if (!elementText.isEmpty()) {
                ElementActions.failAction(null, elementText, null, rootCauseException);
            } else {
                ElementActions.failAction(null, null, rootCauseException);
            }
        }
        if (!elementText.isEmpty()) {
            ElementActions.passAction(null, null, elementText, prepareElementAttachment(targetElement));
        } else {
            ElementActions.passAction(null, null, prepareElementAttachment(targetElement));
        }
        return this;
    }

    /**
     * Right-clicks on an element to trigger the context menu
     *
     * @param pathToTargetElementImage relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions rightClick(String pathToTargetElementImage) {
        return rightClick(readImageFromFile(pathToTargetElementImage));
    }

    /**
     * Right-clicks on an element to trigger the context menu
     *
     * @param targetElement the image of the desired element in the form of a byte[]
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions rightClick(byte[] targetElement) {
        String elementText = "";
        try {
            elementText = initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).getText().replace("\n", "").trim();
            initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).rightClick(prepareElementPattern(targetElement));
        } catch (IOException | FindFailed rootCauseException) {
            if (!elementText.isEmpty()) {
                ElementActions.failAction(null, elementText, null, rootCauseException);
            } else {
                ElementActions.failAction(null, null, rootCauseException);
            }
        }
        if (!elementText.isEmpty()) {
            ElementActions.passAction(null, null, elementText, prepareElementAttachment(targetElement));
        } else {
            ElementActions.passAction(null, null, prepareElementAttachment(targetElement));
        }
        return this;
    }

    /**
     * Drags the draggable element and drops it onto the target element
     *
     * @param pathToDraggableElementImage relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @param pathToTargetElementImage    relative path to the desired element image following this example "src/test/resources/DynamicObjectRepository/" + "sikuli_googleHome_searchBox_text.PNG"
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions dragAndDrop(String pathToDraggableElementImage, String pathToTargetElementImage) {
        return dragAndDrop(readImageFromFile(pathToDraggableElementImage), readImageFromFile(pathToTargetElementImage));
    }

    /**
     * Drags the draggable element and drops it onto the target element
     *
     * @param draggableElement the image of the desired element in the form of a byte[]
     * @param targetElement    the image of the desired element in the form of a byte[]
     * @return a self-reference to be used to chain touch actions
     */
    public SikuliActions dragAndDrop(byte[] draggableElement, byte[] targetElement) {
        String elementText = "";
        try {
            elementText = initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).getText().replace("\n", "").trim();
            initializeSikuliEngineForCurrentScreen().wait(prepareElementPattern(targetElement)).dragDrop(prepareElementPattern(draggableElement), prepareElementPattern(targetElement));
        } catch (IOException | FindFailed rootCauseException) {
            if (!elementText.isEmpty()) {
                ElementActions.failAction(null, elementText, null, rootCauseException);
            } else {
                ElementActions.failAction(null, null, rootCauseException);
            }
        }
        if (!elementText.isEmpty()) {
            ElementActions.passAction(null, null, elementText, prepareElementAttachment(targetElement));
        } else {
            ElementActions.passAction(null, null, prepareElementAttachment(targetElement));
        }
        return this;
    }

    private byte[] readImageFromFile(String pathToTargetElementImage) {
        try {
            return IOUtils.toByteArray(new FileInputStream(pathToTargetElementImage));
        } catch (IOException rootCauseException) {
            ElementActions.failAction(null, "Failed to initialize SikuliAction; couldn't read the target Element Image", null, rootCauseException);
            return new byte[0];
        }
    }

    private Pattern prepareElementPattern(byte[] targetElement) throws IOException {
        Pattern elementPattern = new Pattern();
        ByteArrayInputStream targetElementImage = new ByteArrayInputStream(targetElement);
        elementPattern.setBImage(ImageIO.read(targetElementImage));
        return elementPattern;
    }

    private List<Object> prepareElementAttachment(byte[] targetElement) {
        String testCaseName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String actionName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String screenshotFileName = System.currentTimeMillis() + "_" + testCaseName + "_" + actionName;
        return Arrays.asList("Screenshot", screenshotFileName, new ByteArrayInputStream(targetElement));
    }

    private Screen initializeSikuliEngineForCurrentScreen() {
        if (BrowserFactory.isWebExecution()) {
            JavaScriptWaitManager.waitForLazyLoading();
        }
        Settings.setShowActions(false);
        Settings.ActionLogs = true;
        Settings.InfoLogs = true;
        Settings.DebugLogs = true;
        Settings.LogTime = true;
        Screen myScreen = new Screen();
        myScreen.setAutoWaitTimeout(Double.parseDouble(System.getProperty("defaultElementIdentificationTimeout")));
        return myScreen;
    }

    private String formatTextForReport(String text) {
        return text.replace("\n", "").trim();
    }
}
