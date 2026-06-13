require 'apialerts'

api_key = ENV['APIALERTS_API_KEY']
if api_key.nil? || api_key.empty?
  warn 'Error: APIALERTS_API_KEY is not set'
  exit 1
end

ApiAlerts.configure(api_key)

result = ApiAlerts.send_async(ApiAlerts::Event.new(
  message: 'Published smoke - Ruby',
  channel: 'testing',
  event:   'ci.sdk.smoke.ruby',
  title:   'Published Package OK',
  tags:    %w[smoke ruby published],
  link:    'https://github.com/apialerts/apialerts-ruby',
  data:    { language: 'ruby' }
))

unless result.success?
  warn "FAIL: #{result.error}"
  exit 1
end

puts "OK: ruby sent to #{result.workspace} (#{result.channel})"
