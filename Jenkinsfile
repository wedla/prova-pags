pipeline {
    agent any
    environment {
        LOG_JUNIT_RESULTS = 'true'
    }
    stages {
        stage("Checkout Codebase") {
            steps {
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
                sh "echo ${env.WORKSPACE}"
                dir("pags_api_tests") {
                    sh "echo ${env.WORKSPACE}"
                    sh "docker build -t my_tests ."
                    sh "docker run --net pags-net -e 'BASE_URL=http://app:8081/status/' -v pags_reports:/target/surefire-reports --name tests my_tests"
                }
                junit("TEST-*.xml")
                influxDbPublisher(selectedTarget: 'junit-test-data')
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