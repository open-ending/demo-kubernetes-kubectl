pipeline {
    agent {
        kubernetes {
            inheritFrom 'jenkins-agent-pod'
        }
    }
    stages {
        stage('checkstyle') {
            steps{
                container('gradle-6-jdk11') {
                    sh './gradlew clean :app:check'
                }
            }
        }
        stage('test') {
            steps{
                container('gradle-6-jdk11') {
                    sh './gradlew clean :app:test'
                }
            }
        }
        stage('build') {
            steps{
                container('gradle-6-jdk11') {
                    sh './gradlew clean :app:build'
                }
            }
        }
    }
}