import driver.MainDriver;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@Slf4j
@Epic("Gmail automation")
@Feature("Draft and send flow")
public class GmailTest extends MainDriver {

    @Test(priority = 1, description = "Login to Gmail")
    @Story("User login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensure user can log in successfully with valid credentials")
    public void loginTest() {
        log.info("Starting login test...");
        gmail.login(testUser);
        boolean isLoggedIn = gmail.isAccountPresent(testUser.getEmail());
        log.info("Login test result: {}", isLoggedIn);
        assertTrue(isLoggedIn, "Login failed!");
    }

    @Test(priority = 2, description = "Check if email draft is present")
    @Story("Draft creation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the draft email is created and listed")
    public void isMailPresentInDraftTest() {
        log.info("Creating draft email...");
        gmail.createDraft(testEmail);
        boolean isDraftPresent = gmail.isDraftPresent(testEmail);
        log.info("Draft presence check result: {}", isDraftPresent);
        assertTrue(isDraftPresent, "Draft email is not present!");
    }

    @Test(priority = 3, description = "Verify contents of draft email")
    @Story("Draft verification")
    @Severity(SeverityLevel.NORMAL)
    @Description("Ensure the content of the draft email matches what was input")
    public void areDraftContentsCorrectTest() {
        log.info("Verifying draft email contents...");
        boolean isDraftCorrect = gmail.isDraftCorrect(testEmail);
        log.info("Draft content verification result: {}", isDraftCorrect);
        assertTrue(isDraftCorrect, "Draft email contents are incorrect!");
    }

    @Test(priority = 4, description = "Check draft disappears after sending")
    @Story("Send email")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensure that after sending, the draft is no longer in Drafts folder")
    public void draftIsAbsentTest() {
        log.info("Sending draft email...");
        gmail.sendDraft(testEmail.getSubject());
        boolean isDraftStillPresent = gmail.isDraftPresent(testEmail);
        log.info("Draft presence after sending result: {}", isDraftStillPresent);
        assertFalse(isDraftStillPresent, "Draft email is still present after sending!");
    }

    @Test(priority = 5, description = "Check email appears in Sent folder")
    @Story("Email delivery")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensure that sent email is present in the Sent folder")
    public void draftInSentTest() {
        log.info("Checking if email is in Sent folder...");
        boolean isSentPresent = gmail.isSentPresent(testEmail);
        log.info("Sent email presence check result: {}", isSentPresent);
        assertTrue(isSentPresent, "Sent email is not found in Sent folder!");
    }
}

