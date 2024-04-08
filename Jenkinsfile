pipeline {
    agent {
        kubernetes {
            inheritFrom 'jenkins-agent-pod'
            defaultContainer 'gradle-6-jdk11'
        }
    }
    stages {
        stage('Checkstyle') {
            steps{
                sh './gradlew clean :app:check'
            }
        }
        stage('Test') {
            steps{
                sh './gradlew clean :app:test'
            }
        }
        stage('Build') {
            steps{
                sh './gradlew clean :app:build'
            }
        }
        stage('Dockerize') {
            steps{
                container('jnlp') {
                    sh 'docker build -t demo-kubernetes-kubectl/jenkins/app-test-demo:latest --no-cache .'
                }
            }
        }
    }
}