# SpringBatchCSVToDB
  Simple spring batch load the data from csv to database using spring boot.  
  
  Features :  
  Data upload from csv to database.  
  
  Springbatch Concepts:   
  
  Job :   
  1.It is the most important concept in Spring Batch.   
  2.It needs a JobLauncher instance to be executed.     
  3.It can contain one or more steps, which can be executed in sequence or parallel.   
  
  Step :  
  1.It encapsulates an independent and sequential phase of a job.   
  2.It contains exactly a Reader, a Writer and optionally a Processor.   
  
  Reader :   
  1.It represents an abstraction which is responsible for recovering data from a source.   
  2.When it can not retrieve more data, it returns null.    
  3.In setNames(), the given name should match with csv file header value.  
  
  Writer :
  1.It represents the output for a step in chunk oriented processing.    
  2.setSql(), VALUES (:name,:address1,:address2,:city,:country) - parameter should match with DTO variable name. 
  
  Processor :  
  1.It is optional in the chunk oriented processing.  
  2.It represents the business process for each item, and it can return a null, when that item doesn’t need to be written.  
  
  Listeners :  
  1.Bring us the possibility to perform some actions during the execution of a Job and/or Step.    
  2.There are some types of listeners: • StepExecutionListener • ChunkListener • ItemReadListener • ItemProcessListener • ItemWriteListener • SkipListener Spring Batch Framework offers ‘TaskletStep Oriented’ and ‘Chunk Oriented’ processing style.

  Implemented Spring batch using Springboot :   
  Maven dependencies : 
    <dependency>  
		<groupId>org.springframework.boot</groupId> 
		<artifactId>spring-boot-starter-batch</artifactId> 
	</dependency> 
	<dependency> 
		<groupId>org.springframework.boot</groupId> 
		<artifactId>spring-boot-starter-jdbc</artifactId> 
	</dependency> 

