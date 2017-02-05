package com.example;

public class OutputHelper
{
	IOutputGenerator outputGenerator;

	public void setOutputGenerator(IOutputGenerator outputGenerator){
		this.outputGenerator = outputGenerator;
	}
	
	public void parse()
	{
		outputGenerator.parse();
	}
}