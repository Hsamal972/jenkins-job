library identifier: 'jenkins-shared-library', retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'https://gitlab.com/nanuchi/jenkins-shared-library.git',
     credentialsId: 'gitlab-credentials'   
    ]
)

pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage('test') {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage('build') {
            steps {
                script {   
                    buildDockerImage 'nanajanashia/demo-app:jma-3.0'
                }
            }
        }
        stage('deploy') {
            when {
                expression {
                   BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    echo "Deploying the application..."
                }
            }
        }
    }
}
