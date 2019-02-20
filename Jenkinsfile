pepeline {
  agent {
    docker {
      image "openjdk:8u181-jdk-stretch"
      args "--name restaurantetic-api-service --network restaurantetic -p 9999:8080"
    }
  }
  stages {
    stage ("Build") {
      steps {
        sh "cd /tmp/"
      }
    }
    stage ("Run") {
      steps {
        sh "npm install -g @angular/cli@6.1.3"
        sh "npm run build:ssr"
        sh "npm run serve:ssr&"
        input message: "Finished using the web site? (Click \"Proceed\" to continue)"
      }
    }
  }
}