def incrementVersion() {
    echo 'incrementing the app version'
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

def commitVersionToGit() {
    echo 'commiting version to SCM'
    withCredentials([usernamePassword(credentialsId: 'github-username-password', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'git add .'
        sh 'git commit -m "push from jenkins"'
        sh 'git push origin HEAD:jenkins-jobs'
    }
}

def dockerImage() {
    echo "Building the application"
    withCredentials([usernamePassword(credentialsId:'dockerhub-username-password',usernameVariable:'USER',passwordVariable:'PASS')]) {
        sh "docker build -t doomedmonk13/test1:$IMAGE_NAME ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push doomedmonk13/test1:$IMAGE_NAME"
    }
}

def deployApp() {
    echo 'deploying the application...'
} 

return this
