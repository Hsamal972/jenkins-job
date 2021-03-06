pipeline {
    agent any
    environment {
        ENV = "DEV"
    }
    tools {
        maven 'maven-3.6'
    }
    stages {
        stage("build jar") {
            steps {
                script {
                    echo "Building jar in ${ENV}"
                    sh 'mvn clean package -Dmaven.test.skip=true'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "Building the application..."
                    withCredentials([usernamePassword(credentialsId:'dockerhub-username-password',usernameVariable:'USER',passwordVariable:'PASS')]) {
                        sh "docker build -t doomedmonk13/test1:${BUILD_NUMBER} ."
                        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
                        sh "docker push doomedmonk13/test1:${BUILD_NUMBER}"
                    }
                }    
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "Deploying the application..."
                    def dockercmd = 'sudo docker run -d -p 8080:8080 doomedmonk13/test1:43'
                    sshagent(['ec2-instance-ssh-key']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@15.206.90.10 ${dockercmd}"
                    }
                }
            }
        }
    }   
}
