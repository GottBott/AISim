import sys

quality =[]
duration =[]
for line in open(sys.argv[1]):
    words = line.split(' ')
    words = line.split('\t')
    if("Final Duration:" in words):
#        print words[words.index("Final Duration:") +1]
        duration.append(float(words[words.index("Final Duration:") +1].strip('\n')))
        
    if("Final Quality:" in words):
#        print words[words.index("Final Quality:") +1]
        
        quality.append(float(words[words.index("Final Quality:") +1].strip('\n')))

open(sys.argv[1]).close()
f = open(sys.argv[1],'w')
for i in range(len(quality)):   
    f.write(str(quality[i])+ '\n')
f.write("\n\n")
for i in range(len(quality)):   
    f.write(str(duration[i]) + '\n')
f.close()