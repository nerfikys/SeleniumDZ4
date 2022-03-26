pipeline {
    parameters {
        choice choices: ['remoteChrome', 'remoteOpera'], name: 'TYPE_BROWSER'
        string defaultValue: '@Test', name: 'TAG'
    }
    agent any
    stages {
        stage('Build') {
            steps {
                bat "mvn clean compile"
            }
        }
        stage('Run Tests') {
            steps {
                bat "mvn test -Dcucumber.filter.tags=${TAG} -Dtype.browser=${TYPE_BROWSER}"
            }
        }
        stage('Allure Report Generation') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/reoprts/allure-results']]
            }
        }

    }
}