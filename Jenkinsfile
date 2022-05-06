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
                    echo "Building docker image in ${ENV}"
                    buildImage 'doomedmonk13/test1:jma-3.0'
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
