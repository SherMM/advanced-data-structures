Project Description:
--------------------

http://www.utdallas.edu/~rbk/teach/2016s/projects/lp3-6301-2016s.html

Input format:
------------

As specified in project description link. (Level 1 & 2)

Output format:
--------------
As specified in project description link. (Level 1 & 2).


Sample input (level 1):
8 12
1 2 2
1 4 1
2 5 10
2 4 3
5 7 6
3 1 4
3 6 5
4 3 2
7 6 1
4 5 2
4 7 4
4 6 8

Output (level 1):
Dij 20
1 0 -
2 2 1
3 3 4
4 1 1
5 3 4
6 6 7
7 5 4
8 INF -


Sample input (level 2):
7 8
1 2 2
1 3 3
2 4 5
3 4 4
4 5 1
5 1 -7
6 7 -1
7 6 -1

Output (level 2):
7
1 0 1
2 2 1
3 3 1
4 7 2
5 8 2
6 INF 0
7 INF 0

Compilation & Execution:
------------------------

Compiling the two drivers (level 1 & level 2) should compile and prepare
all code for execution.

Compile in the following way:

javac LP3DriverLevelOne.java
javac LP3DriverLevelTwo.java

Execute in the following way:

java LP3DriverLevelOne < "test-case-file.txt"
java LP3DriverLevelTwo < "test-case-file.txt"

                                ****NOTE****
LARGE AND MEDIUM-SIZED TEST CASES WERE NOT INCLUDED WITH THIS SUBMISSION IN
ORDER TO KEEP TOTAL SUBMISSION SIZE TO A MINIMUM. TEST CASES USED WERE MAINLY
THE ONES PROVIDED ON THE COURSE WEBSITE. SMALL TEST CASES, USED DURING THE
DEBUGGING AND IMPLEMENTATION PROCESS WERE INCLUDED IN THIS SUBMISSION AND CAN
BE FOUND IN THE lp3-small DIRECTORY.
