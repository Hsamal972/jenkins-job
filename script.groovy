def buildJar() {
    echo "building the application..."
    sh 'mvn clean package -Dmaven.test.skip'
}


def dockerImage() {
    echo "Building the application..."
    withCredentials([usernamePassword(credentialsId:'dockerhub-username-password',usernameVariable:'USER',passwordVariable:'PASS')]) {
        sh "docker build -t doomedmonk13/test1:${BUILD_NUMBER} ."
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
        sh "docker push doomedmonk13/test1:${BUILD_NUMBER}"
    }
}

def deployApp() {
    echo 'deploying the application...'
} 

return this
