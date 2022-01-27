pipeline {
    agent any
    stages {
        stage("Checkout Codebase") {
            steps {
                cleanWs()
                checkout scm: [$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[credentialsId: 'Github', url: 'https://github.com/wedla/prova-pags']]]
            }
        }
        stage("Creating bridge network") {
            steps {
                sh "docker network create pags-net"
            }
        }
        stage("Build API status code") {
            steps {
                sh "ls"
                dir("pags_api_status_code") {
                    sh "docker build -t my_app ."
                    sh "docker run -d --net pags-net --name app -p 8081:8081 my_app"
                }
            }
        }
        stage("Run tests") {
            steps {
                dir("pags_api_tests") {
                    sh "echo `${env.WORKSPACE}`"
                    sh "docker build -t my_tests ."
                    sh "docker run --net pags-net -e 'BASE_URL=http://app:8081/status/' -v "${env.WORKSPACE}":/target/surefire-reports --name tests my_tests"
                }
            }
        }
        stage("Remove docker network and containers") {
            steps {
                sh 'docker stop app tests'
                sh 'docker container rm app tests'
                sh "docker network rm pags-net"
            }
        }
    }
}