#!/usr/bin/env groovy
@Library('jenkins-shared-library')
def gv
pipeline {
    agent any
    environment {
        ENV = "DEV"
    }
    tools {
        maven 'maven-3.6'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("setting up auto versioning") {
            steps {
                script {
                    gv.incrementVersion()
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo "Building jar in ${ENV}"
                    buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    gv.dockerImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }   
}
