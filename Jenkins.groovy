def mvn = "/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/mvn/bin/mvn"
pipeline {
    parameters {
        choice choices: ['remoteChrome', 'remoteOpera'], name: 'TYPE_BROWSER'
        string defaultValue: '@Test', name: 'TAG'
    }
    agent any
    stages {
        stage('Build') {
            steps {
                sh "${mvn} clean compile"
            }
        }
        stage('Run Tests') {
            steps {
                sh "${mvn} test -Dcucumber.filter.tags=${TAG} -Dtype.browser=${TYPE_BROWSER}"
            }
        }
        stage('Allure Report Generation') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/reports/allure-results']]
            }
        }

    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}