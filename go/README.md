# Go - published package smoke test

Tiny consumer of the **published** `github.com/apialerts/apialerts-go` module. It configures the SDK and sends one event, proving the published module installs, imports, and works end to end against the live API.

## Install

```bash
go get github.com/apialerts/apialerts-go
```

## Run

```bash
APIALERTS_API_KEY=<test-workspace-key> go run .
```

## Pinned version

The version under test is pinned in [`go.mod`](go.mod):

```
require github.com/apialerts/apialerts-go v1.2.0
```

On release, bump that to the version you just published and run `go mod tidy`, then dispatch the **Published Package Smoke Tests** workflow.
