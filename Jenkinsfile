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

        stage('Deploy to tomcat server'){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://localhost:8081/')], contextPath: null, war: 'build/libs/stage3-module5-task.war'
            }
        }
    }
}