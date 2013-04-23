require File.expand_path('../lib/android-pi/version', __FILE__)

Gem::Specification.new do |gem|
  gem.name          = "android-pi"
  gem.version       = Android::PI::VERSION
  gem.platform      = Gem::Platform::RUBY
  gem.authors       = ["Michael Lemley", "David Shah"]
  gem.email         = ["mlemleyap@gmail.com", "david.a.shah@gmail.com"]
  gem.homepage      = "https://github.com/daveshah/pi"
  gem.summary       = %q{Driver that accepts remote json requests and invokes methods inside Android emulator / device.}
  gem.description   = %q{Driver that accepts remote json requests and invokes methods inside Android emulator / device.}

  gem.files         = `git ls-files`.split("\n")
  gem.require_paths = ["lib"]

  gem.executables   = gem.files.grep(%r{^bin/}).map{ |f| File.basename(f) }
  gem.test_files    = gem.files.grep(%r{^(spec|features)/})

  gem.add_development_dependency 'rspec', '>= 2.6.0'
  gem.add_development_dependency 'cucumber'
  gem.add_development_dependency 'require_all'

end
