pipeline {
    agent {
        kubernetes {
            inheritFrom 'jenkins-agent-pod'
            defaultContainer 'gradle-6-jdk11'
        }
    }
    stages {
        stage('Checkstyle') {
            steps {
                sh './gradlew clean :app:check'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew clean :app:test'
            }
        }
        stage('SonarScan') {
            steps {
                withSonarQubeEnv('demo-sonarqube-service') {
                    sh './gradlew sonar'
                }
            }
        }
        stage('Build') {
            steps {
                sh './gradlew clean :app:build'
            }
        }
        stage('Dockerize') {
            steps {
                container('jnlp') {
                    script {
                        docker.withRegistry('https://registry.hub.docker.com/', 'aeaf941c-a86b-4c8a-bb84-6826c8e3ffa0') {
                            def customImage = docker.build('shadowpluto/demo-app:0.0.1')
                            customImage.push()
                            sh 'docker rmi shadowpluto/demo-app:0.0.1'
                        }
                    }
                }
            }
        }
        stage('DeployQA') {
            steps {
                container('helm-kubectl') {
                    withKubeConfig([namespace: 'demo-app']) {
                        sh "kubectl apply -f app/k8s/deployment-app-demo.yaml"
                    }
                }
            }
        }
    }
}
