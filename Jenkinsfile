pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat "gradle build"
            }
        }

        stage('Sonar'){
               steps{
                     withSonarQubeEnv('Sonar'){
                          bat "gradle sonar"
                     }
               }
        }

        stage('Build war file'){
              steps{
                   bat "gradle war"
              }
        }

        stage('Deploy on tomcat server'){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://localhost:9090')], contextPath: 'news-management', onFailure: false, war: '**/*.war'
            }
        }
    }
}