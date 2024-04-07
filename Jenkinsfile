pipeline {
    agent {
        kubernetes {
            inheritFrom 'jenkins-agent-pod'
        }
    }
    stages {
        stage('Build') {
            steps{
                container('gradle-6-jdk11') {
                    sh './gradlew clean :app:check'
                }
            }
        }
    }
}