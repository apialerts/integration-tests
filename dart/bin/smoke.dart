import 'dart:io';

import 'package:apialerts/apialerts.dart';

Future<void> main() async {
  final apiKey = Platform.environment['APIALERTS_API_KEY'];
  if (apiKey == null || apiKey.isEmpty) {
    stderr.writeln('Error: APIALERTS_API_KEY is not set');
    exit(1);
  }

  ApiAlerts.configure(apiKey);

  final result = await ApiAlerts.sendAsync(const Event(
    message: 'Published smoke - Dart',
    channel: 'testing',
    event: 'ci.sdk.smoke.dart',
    title: 'Published Package OK',
    tags: ['smoke', 'dart', 'published'],
    link: 'https://github.com/apialerts/apialerts-dart',
    data: {'language': 'dart'},
  ));

  if (!result.success) {
    stderr.writeln('FAIL: ${result.error}');
    exit(1);
  }
  stdout.writeln('OK: dart sent to ${result.workspace} (${result.channel})');
}
