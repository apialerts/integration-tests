import com.apialerts.client.ApiAlerts
import com.apialerts.client.Event
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

// Sends one event using the published com.apialerts:client from Maven Central.
fun main() {
    val apiKey = System.getenv("APIALERTS_API_KEY").orEmpty()
    if (apiKey.isBlank()) {
        System.err.println("Error: APIALERTS_API_KEY is not set")
        exitProcess(1)
    }

    val version = System.getProperty("sdkVersion") ?: "unknown"

    ApiAlerts.configure(apiKey)

    val result = runBlocking {
        ApiAlerts.sendAsync(
            Event(
                message = "Published smoke - Kotlin (JVM) $version",
                channel = "testing",
                event = "ci.sdk.smoke.kotlin",
                title = "Published Package OK",
                tags = listOf("smoke", "kotlin", "published"),
                link = "https://github.com/apialerts/apialerts-kotlin",
            ),
        )
    }

    result
        .onSuccess { println("OK: kotlin $version sent to ${it.workspace} (${it.channel})") }
        .onFailure {
            System.err.println("FAIL: ${it.message}")
            exitProcess(1)
        }
}
