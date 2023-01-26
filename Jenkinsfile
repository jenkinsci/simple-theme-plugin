#!groovy
def recentLTS = '2.375.2'
buildPlugin(
  // Container agents start faster and are easier to administer
  useContainerAgent: true,
  configurations: [
    [platform: 'linux',   jdk: '11'],
    [platform: 'linux',   jdk: '17', jenkins: recentLTS],
    [platform: 'windows', jdk: '17', jenkins: recentLTS],
  ]
)
