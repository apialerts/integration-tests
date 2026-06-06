# JS - published package smoke test

Tiny consumer of the **published** `apialerts` npm package. It configures the SDK and sends one event, proving the published package installs, imports, and works end to end against the live API.

## Install

```bash
npm install apialerts
```

## Run

```bash
npm ci
APIALERTS_API_KEY=<test-workspace-key> node index.js
```

`package-lock.json` is committed, so `npm ci` installs the exact locked tree.

## Pinned version

The version under test is pinned in [`package.json`](package.json):

```json
"apialerts": "1.3.2"
```

On release, bump that, run `npm install` to refresh `package-lock.json`, and commit both - then dispatch the **Published Package Smoke Tests** workflow.
