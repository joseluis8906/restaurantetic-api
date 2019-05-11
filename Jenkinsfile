pipeline {
  agent {
    docker {
      image "joseluis8906/openjdk:8u181-jdk-stretch"
      args "--name restaurantetic-api --network restaurantetic -p 9999:8080"
    }
  }
  stages {
    stage ("Build") {
      steps {
        sh "cd /app"
        sh "gradle wrapper"
        sh "./gradlew bootJar"
        sh "cp build/libs/restaurantic-api-service-0.0.1-SNAPSHOT.jar /app/app.jar"
      }
    }
    stage ("Run") {
      steps {
        sh "java -jar /app/app.jar &"
        input message: "Finished using the api service? (Click \"Proceed\" to continue)"
      }
    }
  }
}