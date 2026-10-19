
package com.mycompany.prog5121poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageID() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertTrue(message.checkMessageID());
        assertEquals(9, message.getMessageID().length());
    }

    @Test
    public void testInternationalPhoneNumber() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testMessageHash() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals("00:0:HITONIGHT", message.getMessageHash());
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage("send")
        );
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "08575975889",
                "Hi Keegan, did you receive the payment?"
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage("disregard")
        );
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message successfully stored.",
                message.storeMessage()
        );
    }
}
