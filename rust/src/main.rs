use apialerts::{ApiAlertsClient, Event};

// Bump alongside the `apialerts` pin in Cargo.toml on release.
const VERSION: &str = "1.1.0";

#[tokio::main]
async fn main() {
    let api_key = std::env::var("APIALERTS_API_KEY").unwrap_or_default();
    if api_key.is_empty() {
        eprintln!("Error: APIALERTS_API_KEY is not set");
        std::process::exit(1);
    }

    let client = ApiAlertsClient::new(api_key);

    let result = client
        .send_async(
            Event::new(format!("Published smoke - Rust {VERSION}"))
                .channel("testing")
                .event("ci.sdk.smoke.rust")
                .title("Published Package OK")
                .tags(vec!["smoke", "rust", "published"])
                .link("https://github.com/apialerts/apialerts-rust")
                .data(serde_json::json!({ "language": "rust", "package_version": VERSION })),
        )
        .await;

    match result {
        Ok(r) => println!("OK: rust {VERSION} sent to {} ({})", r.workspace, r.channel),
        Err(e) => {
            eprintln!("FAIL: {e}");
            std::process::exit(1);
        }
    }
}
