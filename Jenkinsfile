pipeline {
    podTemplate(label: 'agent', namespace: 'u2', inheritFrom: 'jenkins-agent-pod', containers: [
        containerTemplate(name: 'gradle6-jdk11', image: 'gradle:6.9.1-jdk11')
    ])
    node(POD_LABEL) {
        stages{
            container('gradle6-jdk11') {
                stage('check style') {
                    steps {
                        sh './gradlew clean :app:check'
                    }
                }
            }
        }
    }
}
