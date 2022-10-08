package com.example.alkemychallengejava.utils;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;


public class EmailSender {
    MailjetClient client;
    MailjetRequest request;
    MailjetResponse response;

    public EmailSender() {
        client = new MailjetClient(System.getenv("MAILJET_API_KEY"), System.getenv("MAILJET_SECRET_KEY"), new ClientOptions("v3.1"));
    }

    public void sendEmail(String receiverEmail, String receiverName) throws MailjetSocketTimeoutException, MailjetException {

        request = createSendRequest(receiverEmail,receiverName);

        response = client.post(request);

    }

    private static MailjetRequest createSendRequest(String receiverEmail, String receiverName) {
        return new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "apitestsender01@gmail.com")
                                        .put("Name", "API Disney"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", receiverEmail)
                                                .put("Name", receiverName)))
                                .put(Emailv31.Message.SUBJECT, "Welcome to disney API")
                                .put(Emailv31.Message.TEXTPART, "Greetings user, your registration was successful, now you'll be able to explore the Disney universe!")
                                .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
    }

}
