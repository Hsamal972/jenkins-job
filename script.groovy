def incrementVersion() {
    echo 'incrementing the app version'
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

def commitVersionToGit() {
    echo 'commiting version to SCM'
    withCredentials([usernamePassword(credentialsId: 'gitlab-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
/*        sh 'git init'
        sh 'git config --global user.name "jenkins"'
        sh 'git config --global user.email "jenkins@jenkins.com"'
        sh 'git config --list'
        sh "git remote set-url origin https://${USER}:${PASS}@gitlab.com/Hsamal972/java-maven-app.git/"
        sh 'git add .'
        sh 'git commit -m "push from jenkins"'
        sh 'git push origin HEAD:jenkins-jobs'   */
        sh 'pwd'
    }
}

def dockerImage() {
    echo "Building the application"
    withCredentials([usernamePassword(credentialsId:'hub-docker-repo',usernameVariable:'USER',passwordVariable:'PASS')]) {
        sh "docker build -t doomedmonk13/test1:$IMAGE_NAME ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push doomedmonk13/test1:$IMAGE_NAME"
    }
}

def deployApp() {
    echo 'deploying the application...'
} 

return this
