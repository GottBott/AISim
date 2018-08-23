import random


f = open('numbers.txt','w')

for i in range (0,200):
    x = random.randint(0,99999999)
    f.write(str(x)+'\n')

f.close()
