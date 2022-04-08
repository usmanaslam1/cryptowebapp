pipeline {
    agent any   
    environment {
        dockerImage=''
        registry='usmanaslam/cryptowebapp'
        registryCredential='DockerHub'

    }
   
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven3.8.5"
    }
    stages {
    
    	stage('Checkout') {
			steps {
			        echo "++++++++++++++++++++++++++++CHECKOUT++++++++++++++++++++++++++++++++++"
				checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/usmanaslam75/cryptowebapp']]])
			}
		}
  		stage('SonarQube Analysis') {
			steps{
				script{
  	  				withSonarQubeEnv() {
			        		echo "++++++++++++++++++++++++++++SONAR QUBE++++++++++++++++++++++++++++++++++"
      						sh "mvn clean verify sonar:sonar -Dsonar.projectKey=Crypto-Web-Application"
    					}
				}
			}
  		}
    
     	stage('Build') {
            steps {
				echo "++++++++++++++++++++++++++++BUILD++++++++++++++++++++++++++++++++++"
                sh "mvn clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.war'
                }
            }
        }
        stage('Docker Image') {
            steps {
                script{
                    dockerImage=docker.build registry
                }
            }
        }
        stage('Upload Docker Image') {
            steps { 
                script{
                   // docker.withRegistry(registryCredential)
                    withDockerRegistry(credentialsId: 'DockerHub'){
                        dockerImage.push()
                    }
                }
            }
        }
        
        
              //TODO fix this step
            
         stage('Deploy container'){
            
            steps{
                
                script{
                    
                     sshagent (credentials: ['AWS_docker']) {
					    sh 'ssh -o StrictHostKeyChecking=no -l ec2-user crypto.usman.cloud sudo docker stop cryptowebapp'
					    sh 'ssh -o StrictHostKeyChecking=no -l ec2-user crypto.usman.cloud sudo docker rm cryptowebapp'
					    sh 'ssh -o StrictHostKeyChecking=no -l ec2-user crypto.usman.cloud sudo docker pull usmanaslam/cryptowebapp'
					    sh 'ssh -o StrictHostKeyChecking=no -l ec2-user crypto.usman.cloud sudo docker run -d --name cryptowebapp -p 8080:8080 usmanaslam/cryptowebapp'
					    			    
  					 }
                }
                
            }
            
        }
    }
}

