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
            input{
                messages "Enter which environment to deploy"
                ok "environment selected"
                parameters{
                    choice (name:'ENV',choices:['PROD','DEV','UAT'],description:'Thanks')
                }
            }
            steps{
                echo "Deploying the application to ${ENV}"
            }
        }
    }
}
