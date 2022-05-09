def dockerImage() {
    echo "Building the application"
    sh "docker build -t doomedmonk13/test1:jma-${BUILD_NUMBER} ."
    sh "docker push doomedmonk13/test1:jma-${BUILD_NUMBER}"
}

def deployApp() {
    echo 'deploying the application...'
} 

return this
