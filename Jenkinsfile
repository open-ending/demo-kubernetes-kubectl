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
        stage('SonarScan') {
            steps{
                withSonarQubeEnv('demo-sonarqube-service') {
                  sh "./gradlew sonar"
                }
            }
        }
        stage('Build') {
            steps{
                sh './gradlew clean :app:build'
            }
        }
        stage('Dockerize') {
            steps{
                docker.withRegistry("https://hub.docker.com/", "ca4509c6-1d1c-4d68-9ec8-ff03c3875eec") {
                    def customImage = docker.build("shadowpluto/demo-app:0.0.1")
                    customImage.push()
                    sh "docker rmi shadowpluto/demo-app:0.0.1"
                }
            }
        }
    }
}