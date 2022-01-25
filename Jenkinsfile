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
                dir("pags_api_status_code") {
                    bat "docker build -t my-image:1 ."
                    bat "docker run -p 8081:8081 my-image:1"
                }
            }
        }
        stage("Test") {
            steps {
                dir("pags_api_tests") {
                    bat "docker build -t my-image:2 ."
                    bat "docker run my-image:2"
                }
            }
        }
    }
}