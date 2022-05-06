def incrementVersion {
    echo 'incrementing the app version'
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
}

def deployApp() {
    echo 'deploying the application...'
} 

return this
