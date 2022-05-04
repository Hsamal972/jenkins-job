pipeline{
    agent any
    tools {
        maven 'maven-3.6'
    }
    stages{
        stage("Build jar") {
            steps{
                script {
                    echo "Building the application"
                    sh 'mvn package'
                }
            }
        }
        stage("Build image") {
            steps{
                script {
                    echo "Building the application"
                    withCredentials([usernamePassword(credentialsId:'hub-docker-repo',usernameVariable:'USER',passwordVariable:'PASS')]) {
                        sh 'docker build -t doomedmonk13/test1:jma-2.0 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push doomedmonk13/test1:jma-2.0'
                    }    
                }
            }
        }
        stage("Test") {
            steps{
                script {
                    echo "Deploying the application"
                }
            }
        }
    }
}
