pipeline {
    agent {
        docker {
            image 'maven:3.5.0-jdk-8'
        }
    }

    tools {
        maven '/usr/local/Cellar/maven/3.6.2/'

    }
    stages {
        stage('Build Application') {
            steps {
                sh 'mvn -f pom.xml clean package'
            }
            post {
                success {
                    echo "Now Archiving the Artifacts...."
                    archiveArtifacts artifacts: '**/*.*'
                }
            }
        }

        stage('Create Tomcat Docker Image'){
            steps {
                sh "pwd"
                sh "ls -a"
                sh "docker build ./java-tomcat-sample-docker -t tomcatsamplewebapp:${env.BUILD_ID}"
            }
        }

    }
}


