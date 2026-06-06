# Python - published package smoke test

Tiny consumer of the **published** `apialerts` package from PyPI. It configures the SDK and sends one event, proving the published package installs, imports, and works end to end against the live API.

## Install

```bash
pip install apialerts
```

## Run

```bash
pip install -r requirements.txt
APIALERTS_API_KEY=<test-workspace-key> python run.py
```

## Pinned version

The version under test is pinned in [`requirements.txt`](requirements.txt):

```
apialerts==1.2.0
```

On release, bump that to the version you just published, then dispatch the **Published Package Smoke Tests** workflow.
