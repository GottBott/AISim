#!/bin/bash

###############################################################
##      Test 4
###############################################################

for i in {1..25}
do
   python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_1 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_2 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_3 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_4 &
   java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test4.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/greedyAgent_Test4.txt
   echo one $i
done
python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/greedyAgent_Test4.txt

for i in {1..25}
do
   python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_1 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_2 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_3 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_4 &
   
   java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test4.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent1_Test4.txt
   echo two $i
done
python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent1_Test4.txt

for i in {1..25}
do
   python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_1 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_2 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_3 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_4 &
   java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test4.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent2_Test4.txt
   echo three $i
done
python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent2_Test4.txt

for i in {1..25}
do
   python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_1 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_2 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_3 &
   python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_4 &
   java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test4.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent3_Test4.txt
   echo four $i
done
python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent3_Test4.txt






# ###############################################################
# ##      Test 3
# ###############################################################
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_1 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_2 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_3 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_4 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test3.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/greedyAgent_Test3.txt
#    echo one $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/greedyAgent_Test3.txt
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_1 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_2 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_3 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_4 &
#    
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test3.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent1_Test3.txt
#    echo two $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent1_Test3.txt
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_1 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_2 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_3 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_4 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test3.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent2_Test3.txt
#    echo three $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent2_Test3.txt
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_1 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_2 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_3 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_4 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test3.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent3_Test3.txt
#    echo four $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent3_Test3.txt
# # 



# ###############################################################
# ##      Test 2
# ###############################################################
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_1 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_2 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_3 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_4 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test2.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/greedyAgent_Test2.txt
#    echo one $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/greedyAgent_Test2.txt
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_1 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_2 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_3 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_4 &
#    
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test2.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent1_Test2.txt
#    echo two $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent1_Test2.txt
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_1 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_2 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_3 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_4 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test2.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent2_Test2.txt
#    echo three $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent2_Test2.txt
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_1 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_2 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_3 &
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_4 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test2.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent3_Test2.txt
#    echo four $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent3_Test2.txt


# ###############################################################
# ##      Test 1
# ###############################################################
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/greedyAgent.py Agent_1 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test1.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/greedyAgent_Test1.txt
#    echo one $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/greedyAgent_Test1.txt
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent1.py Agent_1 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test1.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent1_Test1.txt
#    echo two $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent1_Test1.txt
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent2.py Agent_1 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test1.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent2_Test1.txt
#    echo three $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent2_Test1.txt
# 
# for i in {1..25}
# do
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/placeSeed.py $i
#    python /Users/bengotthold/Desktop/SimResults/Artifacts/agents/smartAgent3.py Agent_1 &
#    java -jar AISim.jar /Users/bengotthold/Desktop/SimResults/Artifacts/Tests/Test1.ctaems /Users/bengotthold/Desktop/SimResults/Artifacts >> /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent3_Test1.txt
#    echo four $i
# done
# python /Users/bengotthold/Desktop/SimResults/Artifacts/getResults.py /Users/bengotthold/Desktop/SimResults/Artifacts/results/smartAgent3_Test1.txt