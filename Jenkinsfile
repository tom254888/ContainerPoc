pipeline{
    agent any
    stages{
        stage("Compile Stage"){
            steps{
                withMaven(maven:"maven_3_5_4"){
                    sh 'mvn clean compile'
                }
            }
        }
    }
    stages{
        stage("Testing Stage"){
            steps{
                withMaven(maven:"maven_3_5_4"){
                    sh 'mvn test'
                }
            }
        }
    }
    stages{
        stage("Deployment Stage"){
            steps{
                withMaven(maven:"maven_3_5_4"){
                    sh 'mvn deploy'
                }
            }
        }
    }    
}
