# Rust - published package smoke test

Tiny consumer of the **published** `apialerts` crate from crates.io. It configures the SDK and sends one event, proving the published crate installs, compiles, and works end to end against the live API.

## Run

```bash
APIALERTS_API_KEY=<test-workspace-key> cargo run
```

## Pinned version

The version under test is pinned in [`Cargo.toml`](Cargo.toml):

```toml
apialerts = "1.1.0"
```

> **Not runnable yet.** `apialerts` is not on crates.io until 1.1.0 is published, so the crate will not resolve. On release, bump this pin (and the `VERSION` constant in [`src/main.rs`](src/main.rs)) and dispatch the **Published Package Smoke Tests** workflow.
