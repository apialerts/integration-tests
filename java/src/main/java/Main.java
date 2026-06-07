import com.apialerts.client.ApiAlerts;
import com.apialerts.client.ApiAlertsClient;
import com.apialerts.client.ApiAlertsJvm;
import com.apialerts.client.EventBuilder;
import com.apialerts.client.SendResult;

import java.util.List;

// Sends one event from a real Java consumer of the published com.apialerts:client,
// exercising the Java surface: EventBuilder, the ApiAlertsJvm.client factory, and
// the sendFuture bridge (sendAsync is a Kotlin suspend fn, unusable from Java).
public class Main {
    public static void main(String[] args) {
        String apiKey = System.getenv("APIALERTS_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Error: APIALERTS_API_KEY is not set");
            System.exit(1);
        }

        ApiAlerts.configure(apiKey);

        // DI path: construct an injectable client and await a send via the bridge.
        ApiAlertsClient client = ApiAlertsJvm.client(apiKey);
        try {
            SendResult result = ApiAlertsJvm.sendFuture(client, new EventBuilder("Published smoke - Java")
                .channel("testing")
                .event("ci.sdk.smoke.java")
                .title("Published Package OK")
                .tags(List.of("smoke", "java", "published"))
                .link("https://github.com/apialerts/apialerts-kotlin")
                .build()).get();
            System.out.println("OK: java sent to " + result.getWorkspace() + " (" + result.getChannel() + ")");
        } catch (Exception e) {
            System.err.println("FAIL: " + e.getMessage());
            System.exit(1);
        }
    }
}
