#!groovy
def recentLTS = '2.332.1'
buildPlugin(configurations: [
  [ platform: 'linux', jdk: '8', jenkins: null ],
  [ platform: 'windows', jdk: '11', jenkins: recentLTS ],
  [ platform: 'linux', jdk: '11', jenkins: recentLTS ],
  // Test with Java 17
  [ platform: 'linux', jdk: '17', jenkins: '2.342' ],
])
