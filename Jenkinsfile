pipeline {
    agent {
        kubernetes {
            inheritFrom 'jenkins-agent-pod'
            defaultContainer 'gradle-6-jdk11'
        }
    }
    stages {
        stage('checkstyle') {
            steps{
                sh './gradlew clean :app:check'
            }
        }
        stage('test') {
            steps{
                sh './gradlew clean :app:test'
            }
        }
        stage('build') {
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