

import socket
import time

#host = "127.0.0.1"
#port = 9876
size = 2048
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
        print s.recv(size)
        print "\n"
    elif(choice ==2):
        s.send('{"MessageType":"AskMethodStatusMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR","MethodName":"Method1"}}\n')
        time.sleep(1)
        print s.recv(size) 
    elif(choice == 3):
        s.send('{"MessageType":"StartMethodMessage","Message":{"MsgSender":"Agent1","MsgDest":"SIMULATOR","MethodName":"Method1"}}\n') 
        print s.recv(size)
        time.sleep(1)
    elif(choice == 4):
        s.close()
        print "EXIT"
    else:
        print "Choice out of bounds: Try Again"

 
    
