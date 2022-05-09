// def commitVersionToGit() {
//     echo 'commiting version to SCM'
//     withCredentials([usernamePassword(credentialsId: 'github-username-password', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
//         sh 'git add .'
//         sh 'git commit -m "push from jenkins"'
//         sh 'git push origin HEAD:jenkins-jobs'
//     }
// }

def dockerImage() {
    echo "Building the application"
    withCredentials([usernamePassword(credentialsId: 'dockerhub-username-password', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh "docker build -t doomedmonk13/test1:jma-${BUILD_NUMBER} ."
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "echo $USERNAME"
        sh "echo $PASSWORD"
        sh "docker push doomedmonk13/test1:jma-${BUILD_NUMBER}"
    }
}

def deployApp() {
    echo 'deploying the application...'
} 

return this
