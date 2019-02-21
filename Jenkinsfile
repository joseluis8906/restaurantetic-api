pipeline {
  agent {
    docker {
      image "joseluis8906/openjdk:8u181-jdk-stretch"
      args "--name restaurantetic-api-service --network restaurantetic -p 9999:8080"
    }
  }
  stages {
    stage ("Build") {
      steps {
        sh "cd /app"
        sh "gradle wrapper"
        sh "./gradlew bootJar"
        sh "ls build/libs"
      }
    }
    stage ("Run") {
      steps {
        sh "cd /app"
        //sh "java -jar app.jar &"
        input message: "Finished using the api service? (Click \"Proceed\" to continue)"
      }
    }
  }
}