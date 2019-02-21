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
      }
    }
    stage ("Run") {
      steps {
        sh "java -jar build/libs/restaurantetic-api-service-0.0.1-SNAPSHOT.jar &"
        input message: "Finished using the api service? (Click \"Proceed\" to continue)"
      }
    }
  }
}