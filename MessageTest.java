package chatappplication.testpackages;

import com.mycompany.chatapplication.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message messageApp;

    @BeforeEach
    public void setup() {
        messageApp = new Message();
    }

    @Test
    public void testMessageDataScenario1() {
        assertTrue(messageApp.checkRecipientCell("+27718693002"));
        
        String testMessage = "Hi Mike, can you join us for dinner tonight?";
        assertEquals("Message ready to send.", messageApp.checkMessageLength(testMessage));
        
        messageApp.sentMessage(testMessage, new java.util.Scanner("1")); 
        
        
        assertEquals("00:0:HITONIGHT", messageApp.createMessageHash());
    }

    @Test
    public void testMessageDataScenario2InvalidRecipient() {
        assertFalse(messageApp.checkRecipientCell("08575975889"), 
            "Should return false due to missing required international country format components.");
    }

    @Test
    public void testSentMessageSendChosen() {
        assertEquals("Message successfully sent.", messageApp.selectTransmissionOption(1));
    }

    @Test
    public void testSentMessageDisregardChosen() {
        assertEquals("Press 0 to delete the message.", messageApp.selectTransmissionOption(2));
    }

    @Test
    public void testSentMessageStoreChosen() {
        assertEquals("Message successfully stored.", messageApp.selectTransmissionOption(3));
    }
}