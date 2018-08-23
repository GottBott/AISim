grammar Ctaems;

@header{package edu.udel.cisc475.aisim.input;import java.util.StringTokenizer;}

@members{String token = "notDone"; 
		 String labelParseString = "notDone";
		 String agentParseString = "notDone";
		 String densityParseString = "notDone";
		 String versionParseString = "notDone";
		 String earliestStartTimeParseString = "notDone";
		 String deadlineParseString = "notDone";
		 String qafParseString = "notDone";
		 String toParseString = "notDone";
		 String fromParseString = "notDone";	 
		 boolean hasEST = false;
		 boolean hasDeadline = false;
		 double[] qualityDistributionArray;
		 double[] durationDistributionArray;
		 double[] qualityPowerArray;
		 double[] durationPowerArray;
		 String[] subtaskArray;
}

labelParse :
    AgentToken {labelParseString = $AgentToken.text;
  				int endIndex = labelParseString.indexOf(')'); 
  				labelParseString = labelParseString.substring(21,endIndex);} |
  	
  	EnablesToken {labelParseString = $EnablesToken.text;
  				  labelParseString = labelParseString.substring(1);
  				  int startIndex = labelParseString.indexOf('(');
  				  int endIndex = labelParseString.indexOf(')');
  				  labelParseString = labelParseString.substring(startIndex,endIndex);
  				  startIndex = labelParseString.indexOf(' ')+1;
  				  labelParseString = labelParseString.substring(startIndex);} |
  	
  	FacilitatesToken {labelParseString = $FacilitatesToken.text;
  				  labelParseString = labelParseString.substring(1);
  				  int startIndex = labelParseString.indexOf('(');
  				  int endIndex = labelParseString.indexOf(')');
  				  labelParseString = labelParseString.substring(startIndex,endIndex);
  				  startIndex = labelParseString.indexOf(' ')+1;
  				  labelParseString = labelParseString.substring(startIndex);} |
  				  
  	TaskToken {labelParseString = $TaskToken.text;
  				  labelParseString = labelParseString.substring(1);
  				  int startIndex = labelParseString.indexOf('(');
  				  int endIndex = labelParseString.indexOf(')');
  				  labelParseString = labelParseString.substring(startIndex,endIndex);
  				  startIndex = labelParseString.indexOf(' ')+1;
  				  labelParseString = labelParseString.substring(startIndex);} |
  				  
  	TaskGroupToken {labelParseString = $TaskGroupToken.text;
  				  labelParseString = labelParseString.substring(1);
  				  int startIndex = labelParseString.indexOf('(');
  				  int endIndex = labelParseString.indexOf(')');
  				  labelParseString = labelParseString.substring(startIndex,endIndex);
  				  startIndex = labelParseString.indexOf(' ')+1;
  				  labelParseString = labelParseString.substring(startIndex);} |
  				  
  	MethodToken {labelParseString = $MethodToken.text;
  				 labelParseString = labelParseString.substring(1);
  				 int startIndex = labelParseString.indexOf('(');
  				 int endIndex = labelParseString.indexOf(')');
  				 labelParseString = labelParseString.substring(startIndex,endIndex);
  				 startIndex = labelParseString.indexOf(' ')+1;
  				 labelParseString = labelParseString.substring(startIndex);};
  
agentParse :
	MethodToken {agentParseString = $MethodToken.text;
		  		 String omitString = "(agent";
				 int startIndex = agentParseString.indexOf(omitString);
				 agentParseString = agentParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = agentParseString.indexOf(')');
				 agentParseString = agentParseString.substring(startIndex,endIndex);};

densityParse :
	MethodToken {densityParseString = $MethodToken.text;
				 String omitString = "(density";				 
				 int startIndex = densityParseString.indexOf(omitString);
				 densityParseString = densityParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = densityParseString.indexOf(')');
				 densityParseString = densityParseString.substring(startIndex,endIndex);};

qualityDistributionParse :
	MethodToken {String qualityParseString = $MethodToken.text;
				 String omitString = "(quality_distribution";				 
				 int startIndex = qualityParseString.indexOf(omitString);
				 qualityParseString = qualityParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = qualityParseString.indexOf(')');
				 qualityParseString = qualityParseString.substring(startIndex,endIndex);
				 StringTokenizer tokenizer = new StringTokenizer(qualityParseString);
    			 int size = tokenizer.countTokens();
                 qualityDistributionArray = new double[size];
    			 for(int i = 0;i < size;i++)
        		 	qualityDistributionArray[i] = Double.parseDouble((String)tokenizer.nextElement());};

durationDistributionParse :
	MethodToken {String durationParseString = $MethodToken.text;
				 String omitString = "(duration_distribution";				 
				 int startIndex = durationParseString.indexOf(omitString);
				 durationParseString = durationParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = durationParseString.indexOf(')');
				 durationParseString = durationParseString.substring(startIndex,endIndex);
				 StringTokenizer tokenizer = new StringTokenizer(durationParseString);
    			 int size = tokenizer.countTokens();
                 durationDistributionArray = new double[size];
    			 for(int i = 0;i < size;i++)
        		 	durationDistributionArray[i] = Double.parseDouble((String)tokenizer.nextElement());};

versionParse :
	VersionToken{versionParseString = $VersionToken.text;
				 String omitString = "(spec_version";
				 int startIndex = omitString.length()+1;		 
				 int endIndex = versionParseString.indexOf(')');
				 versionParseString = versionParseString.substring(startIndex,endIndex);
				 if(versionParseString.charAt(0)=='"' && versionParseString.charAt(versionParseString.length()-1) =='"'){
				 	versionParseString = versionParseString.substring(1,versionParseString.length()-1);
				 }};
				 
earliestStartTimeParse :
		TaskToken{earliestStartTimeParseString = $TaskToken.text;
					 String omitString = "(earliest_start_time";
					 if($TaskToken.text.contains(omitString)){				 
					 	int startIndex = earliestStartTimeParseString.indexOf(omitString);
					 	earliestStartTimeParseString = earliestStartTimeParseString.substring(startIndex);
					 	startIndex = omitString.length()+1;		 
					 	int endIndex = earliestStartTimeParseString.indexOf(')');
					 	earliestStartTimeParseString = earliestStartTimeParseString.substring(startIndex,endIndex);
					 	hasEST = true;}
					 else
					 	hasEST = false;};
		
deadlineParse :
	TaskToken{deadlineParseString = $TaskToken.text;
			  String omitString = "(deadline";
			  if($TaskToken.text.contains(omitString)){				 
	   	 	  	int startIndex = deadlineParseString.indexOf(omitString);
				deadlineParseString = deadlineParseString.substring(startIndex);
				startIndex = omitString.length()+1;		 
				int endIndex = deadlineParseString.indexOf(')');
				deadlineParseString = deadlineParseString.substring(startIndex,endIndex);
				hasDeadline = true;}
			  else
				hasDeadline = false;};
				
subtaskParse :
	TaskToken{String subtaskParseString = $TaskToken.text;
			  String omitString = "(subtasks";				 
	   	 	  int startIndex = subtaskParseString.indexOf(omitString);
			  subtaskParseString = subtaskParseString.substring(startIndex);
			  startIndex = omitString.length()+1;		 
			  int endIndex = subtaskParseString.indexOf(')');
			  subtaskParseString = subtaskParseString.substring(startIndex,endIndex);
			  StringTokenizer tokenizer = new StringTokenizer(subtaskParseString);
    		  int size = tokenizer.countTokens();
              subtaskArray = new String[size];
    	      for(int i = 0;i < size;i++)
        	  	 subtaskArray[i] = (String)tokenizer.nextElement();} |
        	  	 
	TaskGroupToken{String subtaskParseString = $TaskGroupToken.text;
			  String omitString = "(subtasks";				 
	   	 	  int startIndex = subtaskParseString.indexOf(omitString);
			  subtaskParseString = subtaskParseString.substring(startIndex);
			  startIndex = omitString.length()+1;		 
			  int endIndex = subtaskParseString.indexOf(')');
			  subtaskParseString = subtaskParseString.substring(startIndex,endIndex);
			  StringTokenizer tokenizer = new StringTokenizer(subtaskParseString);
    		  int size = tokenizer.countTokens();
              subtaskArray = new String[size];
    	      for(int i = 0;i < size;i++)
        	  	 subtaskArray[i] = (String)tokenizer.nextElement();};
        	  	 
qafParse :
	TaskToken{qafParseString = $TaskToken.text;
		  String omitString = "(qaf";				 
	   	  int startIndex = qafParseString.indexOf(omitString);
		  qafParseString = qafParseString.substring(startIndex);
		  startIndex = omitString.length()+1;		 
		  int endIndex = qafParseString.indexOf(')');
		  qafParseString = qafParseString.substring(startIndex,endIndex);} |
		  
	TaskGroupToken{qafParseString = $TaskGroupToken.text;
		  String omitString = "(qaf";				 
	   	  int startIndex = qafParseString.indexOf(omitString);
		  qafParseString = qafParseString.substring(startIndex);
		  startIndex = omitString.length()+1;		 
		  int endIndex = qafParseString.indexOf(')');
		  qafParseString = qafParseString.substring(startIndex,endIndex);};
		  
fromParse :
	FacilitatesToken{fromParseString = $FacilitatesToken.text;
		  		 String omitString = "(from";
				 int startIndex = fromParseString.indexOf(omitString);
				 fromParseString = fromParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = fromParseString.indexOf(')');
				 fromParseString = fromParseString.substring(startIndex,endIndex);} |
				 
    EnablesToken{fromParseString = $EnablesToken.text;
		  		 String omitString = "(from";
				 int startIndex = fromParseString.indexOf(omitString);
				 fromParseString = fromParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = fromParseString.indexOf(')');
				 fromParseString = fromParseString.substring(startIndex,endIndex);};
				 
toParse :
	FacilitatesToken{toParseString = $FacilitatesToken.text;
		  		 String omitString = "(to";
				 int startIndex = toParseString.indexOf(omitString);
				 toParseString = toParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = toParseString.indexOf(')');
				 toParseString = toParseString.substring(startIndex,endIndex);} |
				 			
	EnablesToken{toParseString = $EnablesToken.text;
		  		 String omitString = "(to";
				 int startIndex = toParseString.indexOf(omitString);
				 toParseString = toParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = toParseString.indexOf(')');
				 toParseString = toParseString.substring(startIndex,endIndex);};
				 
qualityPowerParse :
	FacilitatesToken{String qpParseString = $FacilitatesToken.text;
		  		 String omitString = "(quality_power";
				 int startIndex = qpParseString.indexOf(omitString);
				 qpParseString = qpParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = qpParseString.indexOf(')');
				 qpParseString = qpParseString.substring(startIndex,endIndex);
				 StringTokenizer tokenizer = new StringTokenizer(qpParseString);
    			 int size = tokenizer.countTokens();
                 qualityPowerArray = new double[size];
    			 for(int i = 0;i < size;i++)
        		 	qualityPowerArray[i] = Double.parseDouble((String)tokenizer.nextElement());};
        		 	
durationPowerParse :
	FacilitatesToken{String dpParseString = $FacilitatesToken.text;
		  		 String omitString = "(duration_power";
				 int startIndex = dpParseString.indexOf(omitString);
				 dpParseString = dpParseString.substring(startIndex);
				 startIndex = omitString.length()+1;		 
				 int endIndex = dpParseString.indexOf(')');
				 dpParseString = dpParseString.substring(startIndex,endIndex);
				 StringTokenizer tokenizer = new StringTokenizer(dpParseString);
    			 int size = tokenizer.countTokens();
                 durationPowerArray = new double[size];
    			 for(int i = 0;i < size;i++)
        		 	durationPowerArray[i] = Double.parseDouble((String)tokenizer.nextElement());};

parse returns[String token] :  
  	 VersionToken {System.out.println("parsed :: " + $VersionToken.text);} |
  	 AgentToken {System.out.println("parsed :: " + $AgentToken.text);} |  	 
  	 TaskToken {System.out.println("parsed :: " + $TaskToken.text);} |
  	 TaskGroupToken {System.out.println("parsed :: " + $TaskGroupToken.text);} |
  	 MethodToken {System.out.println("parsed :: " + $MethodToken.text);} |
  	 EnablesToken {System.out.println("parsed :: " + $EnablesToken.text);} |
  	 FacilitatesToken {System.out.println("parsed :: " + $FacilitatesToken.text);} |
  	 Spaces {} |
  	 EOF {System.out.println("End of File"); token = "done";}; 
  
AgentToken :  '(' Spaces? 'spec_agent' Spaces? Label? ')';
  
VersionToken : '(' Spaces? 'spec_version' Spaces? Version? ')';
  
TaskGroupToken :  '(' Spaces? 'spec_task_group' Spaces*? Label? Subtasks? Qaf? ')';
  
TaskToken :  '(' Spaces? 'spec_task' Spaces*? Label? EarliestStartTime? Deadline? Subtasks? Qaf? ')';
  
MethodToken :  '(' Spaces? 'spec_method' Spaces*? Label? Agent? Outcomes? ')';
  
EnablesToken :  '(' Spaces? 'spec_enables' Spaces*? Label? From? To? QualityPower? DurationPower? ')';
  
FacilitatesToken :  '(' Spaces? 'spec_facilitates' Spaces*? Label? From? To? QualityPower? DurationPower? ')';

Spaces:  (' ' | '\t')+;

fragment Label : '(' Spaces? 'label' Spaces? Frag*? ')' Spaces*?;
  
fragment Subtasks : '(' Spaces? 'subtasks' Spaces? Frag*? ')' Spaces*?;
  
fragment Qaf : '(' Spaces? 'qaf' Spaces? Frag*? ')' Spaces*?;
  
fragment Deadline : '(' Spaces? 'deadline' Spaces? Frag*? ')' Spaces*?;
  
fragment EarliestStartTime : '(' Spaces? 'earliest_start_time' Spaces? Frag*? ')' Spaces*?;

fragment From : '(' Spaces? 'from' Spaces? Frag*? ')' Spaces*?;
  
fragment To : '(' Spaces? 'to' Spaces? Frag*? ')' Spaces*?;
  
fragment QualityPower : '(' Spaces? 'quality_power' Spaces? Frag*? ')' Spaces*?;
  
fragment DurationPower : '(' Spaces? 'duration_power' Spaces? Frag*? ')' Spaces*?;
  
fragment Agent : '(' Spaces? 'agent' Spaces? Frag*? ')' Spaces*?;
  
fragment Outcomes : '(' 'outcomes' Spaces*? MethodBody*? Spaces*? ')' Spaces*?;
    
fragment MethodBody : '(' Frag*? Density? QualityDistribution? DurationDistribution? ')' Spaces*?;
    
fragment Density : '(' Spaces? 'density' Spaces? Frag*? ')' Spaces*?;
  
fragment QualityDistribution : '(' Spaces? 'quality_distribution' Spaces? Frag*? ')' Spaces*?;
  
fragment DurationDistribution : '(' Spaces? 'duration_distribution' Spaces? Frag*? ')' Spaces*?;

fragment Version: '"V' Frag*?;
  
fragment Frag: ~('(' | ')');