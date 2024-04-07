pipeline {
    agent {
        kubernetes {
            label 'jenkins-agent'
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