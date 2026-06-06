import { readFileSync } from 'node:fs'
import { ApiAlerts } from 'apialerts'

const apiKey = process.env.APIALERTS_API_KEY ?? ''
if (!apiKey) {
    console.error('Error: APIALERTS_API_KEY is not set')
    process.exit(1)
}

const require = createRequire(import.meta.url)
const version = require('apialerts/package.json').version
const version = JSON.parse(
    readFileSync(new URL('./node_modules/apialerts/package.json', import.meta.url), 'utf8'),
).version

ApiAlerts.configure(apiKey)

const result = await ApiAlerts.sendAsync({
    message: `Published smoke - JS ${version}`,
    channel: 'testing',
    event: 'ci.sdk.smoke.js',
    title: 'Published Package OK',
    tags: ['smoke', 'js', 'published'],
    link: 'https://github.com/apialerts/apialerts-js',
    data: { language: 'js', package_version: version },
})

if (!result.success) {
    console.error(`FAIL: ${result.error}`)
    process.exit(1)
}
console.log(`OK: js ${version} sent to ${result.workspace} (${result.channel})`)
