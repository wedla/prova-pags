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
                sh "docker build -t my_image ."
                sh "docker run -p 8081:8081 my_image"
            }
        }
        
    }
}