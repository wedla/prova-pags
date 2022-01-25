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
                sh "ls"
                dir("pags_api_status_code") {
                    sh "docker build -t my_app ."
                    sh "docker run --name app -p -d 8081:8081 my_app"
                }
            }
        }
        stage("Run tests") {
            steps {
                sh "ls"
                dir("pags_api_tests") {
                    sh "docker build -t my_tests ."
                    sh "docker run -e BASE_URL http://app:8081/status/ my_tests"
                }
            }
        }
        
    }
}