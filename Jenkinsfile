pipeline {
    agent {
        kubernetes {
            label 'jenkins-agent-jnpl'
        }
    }
    stages {
        stage('check style') {
            agent {
                docker {
                    image 'gradle:6.9.1-jdk11'
                }
            }
            steps {
                sh './gradlew clean :app:check'
            }
        }
    }
}
