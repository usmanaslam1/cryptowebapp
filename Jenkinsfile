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
  	   stage('Build') {
            steps {
				echo "++++++++++++++++++++++++++++BUILD++++++++++++++++++++++++++++++++++"
                sh "mvn clean install"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.war'
                }
                
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
        
        stage ("Dynamic Analysis - DAST with OWASP ZAP") {
			steps {
				sh "docker run -t owasp/zap2docker-stable zap-baseline.py -t http://crypto.usman.cloud:8080/ || true"
			}
		
		}
		
    }
    post {
        always {
        	echo '++++++++++ POST ALWAYS ++++++++'
        }
        
        failure {
        	script {                        
        		env.ForEmailPlugin = env.WORKSPACE
        		emailext mimeType: 'text/html',
        		body: "<b>The following build has failed. Please refer to Jenkins Log</b><br>Project: "+ env.JOB_NAME +" <br>Build Number: "+env.BUILD_NUMBER, 
        		recipientProviders: [[$class: 'DevelopersRecipientProvider'], 
				[$class: 'RequesterRecipientProvider']],
        		subject: currentBuild.currentResult + " : " + env.JOB_NAME +"  (Build Number: "+env.BUILD_NUMBER+")"
            }
        }       
        
        success {  
        	script {                        
        		env.ForEmailPlugin = env.WORKSPACE
        		emailext mimeType: 'text/html',
        		body: "<b>The following build was successful</b><br>Project: "+ env.JOB_NAME +" <br>Build Number: "+env.BUILD_NUMBER+"<br><br> Application URL: "+env.BUILD_URL, 
        		recipientProviders: [[$class: 'DevelopersRecipientProvider'], 
				[$class: 'RequesterRecipientProvider']],
        		subject: currentBuild.currentResult + " : " + env.JOB_NAME +"  (Build Number: "+env.BUILD_NUMBER+")"
            }
        }  
         changed {  
             echo 'This will run only if the state of the Pipeline has changed'  
             echo 'For example, if the Pipeline was previously failing but is now successful'  
        }  
     }  
}

