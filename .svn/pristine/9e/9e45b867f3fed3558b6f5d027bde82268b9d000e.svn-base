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
while(choice != 4):
    print "\n###   USAGE   ###"
    print "1-SendRegistration, 2-AskMethodStatus, 3-StartMethod, 4-QUIT\n"
    choice = input("Enter your choice: ")

    if (choice == 1):
        s.send('{"MessageType":"AgentRegistrationMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR"}}\n')
        time.sleep(1)
        print recvline()
        print recvline()
        print recvline()
        print "\n"
    elif(choice ==2):
        s.send('{"MessageType":"AskMethodStatusMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR","MethodName":"Method1"}}\n')
        time.sleep(1)
        print recvline() 
    elif(choice == 3):
        s.send('{"MessageType":"StartMethodMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR","MethodName":"Method1"}}\n') 
        time.sleep(1)
        print recvline()
    elif(choice == 4):
        print "waiting for simulation to complete"
        print recvline()
        s.close()
        print "EXIT"
    else:
        print "Choice out of bounds: Try Again"
