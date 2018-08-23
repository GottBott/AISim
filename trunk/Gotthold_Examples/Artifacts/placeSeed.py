import sys

seedLine = int(sys.argv[1])
print seedLine
seed = 0

x =0
f = open('numbers.txt','r')
for line in f:
    if(x == seedLine):
        seed = int(line)
        print "here"  
    x = x + 1
    
f.close()

open('config.ini', 'w').close()
f = open('config.ini','w')   
f.write("maxTicks = 500"+'\n')
f.write("tickLength = 50"+'\n')
f.write("seed = "+str(seed)+'\n')
f.close()