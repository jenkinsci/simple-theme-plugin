#!groovy
def recentLTS = '2.361.2'
buildPlugin(configurations: [
  [ platform: 'linux', jdk: '8', jenkins: null ],
  [ platform: 'windows', jdk: '11', jenkins: recentLTS ],
  [ platform: 'linux', jdk: '11', jenkins: recentLTS ],
  [ platform: 'linux', jdk: '17', jenkins: recentLTS ],
])
