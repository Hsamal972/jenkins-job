def incrementVersion() {
    echo 'incrementing the app version'
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

def dockerImage() {
    echo "Building the application"
    withCredentials([usernamePassword(credentialsId:'hub-docker-repo',usernameVariable:'USER',passwordVariable:'PASS')]) {
        sh "docker build -t $IMAGE_NAME ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push $IMAGE_NAME"
    }
}

def deployApp() {
    echo 'deploying the application...'
} 

return this
