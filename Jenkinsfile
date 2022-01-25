pipeline {
    agent any
    stages {
        stage("Checkout Codebase") {
            steps {
                cleanWs()
                checkout scm: [$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[credentialsId: 'Github', url: 'https://github.com/wedla/prova-pags']]]
            }
        }
        stage("Build API status code") {
            steps {
                docker.build("pags_api_status_code")
            }
        }
    }
}