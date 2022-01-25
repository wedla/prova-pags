pipeline {
    agent any
    stages {
        stage("Checkout Codebase") {
            steps {
                cleanWs()
                checkout scm: [$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/wedla/prova-pags']]]
            }
        }
    }
}