pipeline{
    agent any
    stages{
        stage("Build"){
            steps{
                echo "Building the application"
            }
        }
        stage("Test"){
            steps{
                echo "Testing the application"
            }
        }    
        stage("Deploy"){
            input {
                message "Enter which environment to deploy"
                ok "environment selected"
                parameters{
                    choice (name:'ENV1',choices:['PROD','DEV','UAT'],description:'Hello')
                    choice (name:'ENV2',choices:['PROD','DEV','UAT'],description:'World')

                }
            }
            steps{
                echo "Deploying the application to ${ENV1}"
                echo "Deploying the application to ${ENV2}"
            }
        }
    }
}
