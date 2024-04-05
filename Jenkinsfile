pipeline {
    agent {
        kubernetes {
            label 'jenkins-agent-jnpl'
        }
    }
    stages {
        stage('check style') {
            steps {
                sh './gradlew clean :app:check'
            }
        }
        stage('test') {
            steps {
                sh './gradlew clean :app:test'
            }
        }
        stage('build') {
            steps {
                sh './gradlew clean :app:build'
            }
        }
    }
}
