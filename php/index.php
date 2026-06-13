<?php

declare(strict_types=1);

require __DIR__ . '/vendor/autoload.php';

use ApiAlerts\ApiAlerts;
use ApiAlerts\Event;
use Composer\InstalledVersions;

$apiKey = getenv('APIALERTS_API_KEY');
if ($apiKey === false || $apiKey === '') {
    fwrite(STDERR, "Error: APIALERTS_API_KEY is not set\n");
    exit(1);
}

$version = InstalledVersions::getPrettyVersion('apialerts/apialerts') ?? 'unknown';

ApiAlerts::configure($apiKey);

$result = ApiAlerts::sendAsync(new Event(
    message: "Published smoke - PHP {$version}",
    channel: 'testing',
    event:   'ci.sdk.smoke.php',
    title:   'Published Package OK',
    tags:    ['smoke', 'php', 'published'],
    link:    'https://github.com/apialerts/apialerts-php',
    data:    ['language' => 'php', 'package_version' => $version],
));

if (!$result->success) {
    fwrite(STDERR, "FAIL: {$result->error}\n");
    exit(1);
}

echo "OK: php {$version} sent to {$result->workspace} ({$result->channel})\n";
