pipeline {
    agent any
    stages {
        stage("Checkout Codebase") {
            steps {
                cleanWs()
                checkout scm: [$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/wedla/prova-pags']]]
            }
        }
        stage("Build API status code") {
            steps {
                dir("./pags_api_status_code")
                sh "docker build -t my-image:1 ."
                sh "docker run -p 8081:8081 my-image:1"
            }
        }
        stage("Test") {
            steps {
                dir("./pags_api_tests")
                sh "docker build -t my-image:2 ."
                sh "docker run my-image:2"
            }
        }
    }
}