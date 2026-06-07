using System.Reflection;
using APIAlerts;

var apiKey = Environment.GetEnvironmentVariable("APIALERTS_API_KEY") ?? "";
if (string.IsNullOrEmpty(apiKey))
{
    Console.Error.WriteLine("Error: APIALERTS_API_KEY is not set");
    Environment.Exit(1);
}

var version = typeof(ApiAlerts).Assembly
    .GetCustomAttribute<AssemblyInformationalVersionAttribute>()?.InformationalVersion
    ?? typeof(ApiAlerts).Assembly.GetName().Version?.ToString()
    ?? "unknown";
version = version.Split('+')[0];

ApiAlerts.Configure(apiKey);

var result = await ApiAlerts.SendAsync(new Event
{
    Message = $"Published smoke - C# {version}",
    Channel = "testing",
    EventKey = "ci.sdk.smoke.csharp",
    Title = "Published Package OK",
    Tags = new[] { "smoke", "csharp", "published" },
    Link = "https://github.com/apialerts/apialerts-csharp",
    Data = new { language = "csharp", package_version = version },
});

if (!result.Success)
{
    Console.Error.WriteLine($"FAIL: {result.Error}");
    Environment.Exit(1);
}

Console.WriteLine($"OK: csharp {version} sent to {result.Workspace} ({result.Channel})");
