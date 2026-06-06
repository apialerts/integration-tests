package main

import (
	"fmt"
	"os"
	"runtime/debug"

	"github.com/apialerts/apialerts-go"
)

func main() {
	apiKey := os.Getenv("APIALERTS_API_KEY")
	if apiKey == "" {
		fmt.Fprintln(os.Stderr, "Error: APIALERTS_API_KEY is not set")
		os.Exit(1)
	}

	version := resolvedVersion()

	apialerts.Configure(apiKey)

	result, err := apialerts.SendAsync(apialerts.Event{
		Message: "Published smoke - Go " + version,
		Channel: "testing",
		Event:   "ci.sdk.smoke.go",
		Title:   "Published Package OK",
		Tags:    []string{"smoke", "go", "published"},
		Link:    "https://github.com/apialerts/apialerts-go",
		Data: map[string]any{
			"language":        "go",
			"package_version": version,
		},
	})
	if err != nil {
		fmt.Fprintln(os.Stderr, "FAIL:", err)
		os.Exit(1)
	}
	fmt.Printf("OK: go %s sent to %s (%s)\n", version, result.Workspace, result.Channel)
}

// resolvedVersion reads the apialerts-go version that was actually resolved into
// this build, so the alert reports the exact published version under test.
func resolvedVersion() string {
	info, ok := debug.ReadBuildInfo()
	if !ok {
		return "unknown"
	}
	for _, dep := range info.Deps {
		if dep.Path == "github.com/apialerts/apialerts-go" {
			return dep.Version
		}
	}
	return "unknown"
}
