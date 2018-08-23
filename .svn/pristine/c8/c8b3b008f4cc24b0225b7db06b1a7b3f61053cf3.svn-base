import socket
import time

def recvline():
    line = ''
    char = s.recv(1)
    line += char
    while char != '\n':
        char = s.recv(1)
        line += char
    return line

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
s.connect(('localhost',9876)) 
choice = 0


print "SENDING REGESTRATION MESSAGE"
s.send('{"MessageType":"AgentRegistrationMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR"}}\n')
time.sleep(1)
print recvline()
print recvline()
print recvline()
print "\n"
print "ASKING TO START METHOD 1"

s.send('{"MessageType":"StartMethodMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR","MethodName":"Method1"}}\n') 
print recvline()
time.sleep(1)
print "ASKING TO START METHOD 2"
s.send('{"MessageType":"StartMethodMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR","MethodName":"Method2"}}\n') 
print recvline()
time.sleep(1)
print "ASKING STATUS OF METHOD 1"
s.send('{"MessageType":"AskMethodStatusMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR","MethodName":"Method1"}}\n')
time.sleep(1)
print recvline()
print "ASKING STATUS OF METHOD 2"
s.send('{"MessageType":"AskMethodStatusMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR","MethodName":"Method2"}}\n')
time.sleep(1)
print recvline() 

print "WAITING FOR SIMULATION TO COMPLETE"
print recvline()
s.close()
print "EXIT"
