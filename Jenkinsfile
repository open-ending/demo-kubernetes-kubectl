pipeline {
    agent {
        kubernetes {
            label 'gradle-6-jdk11'
        }
    }
    stages {
        stage('Build') {
            steps{
                sh './gradlew clean :app:check'
            }
        }
    }
}