all:
	javac src/Main.java src/Job.java src/Logger.java src/Scheduler.java
	java -cp src/ Main < inputs/input1.in > outputs/output1.out
	java -cp src/ Main < inputs/input2.in > outputs/output2.out
	java -cp src/ Main < inputs/input3.in > outputs/output3.out
	
