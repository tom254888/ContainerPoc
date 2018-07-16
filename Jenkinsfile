pipeline{
    agent any
    Stages{
        Stage("Compile Stage"){
            Steps{
                withMaven(maven:"maven_3_5_4"){
                    sh 'mvn clean compile'
                }
            }
        }
    }
    Stages{
        Stage("Testing Stage"){
            Steps{
                withMaven(maven:"maven_3_5_4"){
                    sh 'mvn test'
                }
            }
        }
    }
    Stages{
        Stage("Deployment Stage"){
            Steps{
                withMaven(maven:"maven_3_5_4"){
                    sh 'mvn deploy'
                }
            }
        }
    }    
}
