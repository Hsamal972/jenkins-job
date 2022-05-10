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
                    sshagent(['ec2-instance-ssh-key']) {
                        def dockercmd = 'docker run -d -p 8080:8080 doomedmonk13/test1'
                        sh "ssh -o StrictHostKeyChecking ec2-user@13.234.21.249 ${dockercmd}"
                    }
                }
            }
        }
    }   
}
