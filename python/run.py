from __future__ import annotations

import asyncio
import os
import sys
from importlib.metadata import version as pkg_version
from apialerts import ApiAlerts, Event

async def main() -> None:
    api_key = os.environ.get('APIALERTS_API_KEY', '')
    if not api_key:
        print('Error: APIALERTS_API_KEY is not set', file=sys.stderr)
        sys.exit(1)

    version = pkg_version('apialerts')

    ApiAlerts.configure(api_key)

    result = await ApiAlerts.send_async(Event(
        message=f'Published smoke - Python {version}',
        channel='testing',
        event='ci.sdk.smoke.python',
        title='Published Package OK',
        tags=['smoke', 'python', 'published'],
        link='https://github.com/apialerts/apialerts-python',
        data={'language': 'python', 'package_version': version},
    ))
    if not result.success:
        print(f'FAIL: {result.error}', file=sys.stderr)
        sys.exit(1)
    print(f'OK: python {version} sent to {result.workspace} ({result.channel})')


if __name__ == '__main__':
    asyncio.run(main())
