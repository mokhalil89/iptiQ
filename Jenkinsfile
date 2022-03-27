pipeline
{
	agent any
    triggers {
           cron('00 00 * * *')
   }
 	stages {

 	    stage ('Fetch Code')
      		{
      			steps
      			{
    	      		checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                    userRemoteConfigs: [[url: 'https://github.com/mokhalil89/iptiQ.git']]])
    	  		}
      		}
  		stage ('Compile')
  		{
  			steps
  			{
	      		'mvn compile'
	  		}
  		}

	  	stage ('Run Test Cases')
	  	{
		  	steps
		  	{
                 'mvn test'
		 	}
	  	}

	   stage ('Send email with report')
	   {
	  		steps
	  		{
		    	echo 'sending email to Recruiting_Services@swissre.com'
		  	}
	  	}
	}
}