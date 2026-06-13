# Ruby - published gem smoke test

Tiny consumer of the **published** `apialerts` RubyGems package. It configures the SDK and sends one event, proving the published gem installs, resolves, and works end to end against the live API.

## Install

```bash
bundle install
```

## Run

```bash
APIALERTS_API_KEY=<test-workspace-key> bundle exec ruby smoke.rb
```

## Pinned version

The version under test is pinned in [`Gemfile`](Gemfile):

```ruby
gem 'apialerts', '1.0.0'
```

On release, bump that pin and dispatch the **Published Package Smoke Tests** workflow.
