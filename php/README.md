# PHP - published package smoke test

Tiny consumer of the **published** `apialerts/apialerts` Composer package. It configures the SDK and sends one event, proving the published package installs, autoloads, and works end to end against the live API.

## Install

```bash
composer require apialerts/apialerts
```

## Run

```bash
composer install
APIALERTS_API_KEY=<test-workspace-key> php index.php
```

## Pinned version

The version under test is pinned in [`composer.json`](composer.json):

```json
"apialerts/apialerts": "1.0.0"
```

This won't resolve until `1.0.0` is published to Packagist (the consumer is pre-staged, same as the C#/Rust/Swift staged consumers). On release, bump that pin, run `composer update` to refresh `composer.lock`, and commit both, then dispatch the **Published Package Smoke Tests** workflow.
