import agent
import time
import json
import sys
import random

class TaskTree(object):
    def __init__(self, data, children=[]):
        self.children = children
        self.data = data
        self.parent = None
        self.compleated = False
        

    def addNode(self, node):
        for subtask in self.data["SubTasks"]:     
            if(node["NodeName"] == subtask):
                x = TaskTree(node)
                x.children =[]
                x.parent = self
                print(node["NodeName"])
                self.children.append(x)
                return True
        for child in self.children:
            if(child.addNode(node)):
                return True       
        return False
        
    def findNode(self, name):
        if(self.data["NodeName"] == name):
            return self
        else:
            for child in self.children:
                tmp = child.findNode(name)
                if(tmp != False):
                    return tmp
        return False
        
    def setNodeCompleated(self, name):
        if(self.data["NodeName"] == name):
            self.compleated =True
            return
        else:
            for child in self.children:
                return child.setNodeCompleated(name)
        return
         
        

class smartAgent(agent.Agent):

    root = None

    def run_method(self):

        print("\n Methods avaiable")
        print(str(self.available_methods))
        print("")

               
        index =-1
        max =0;
        
        for m in range(len(self.available_methods)):
            if self.eval_quality_distrubution(self.available_methods[m][2]) > max:
                max = self.eval_quality_distrubution(self.available_methods[m][2])
                index=m


        return self.available_methods[index][0]


    def initial_tree_message(self, message):
        nodesToAdd =[]       
        for node in message["Message"]["Nodes"]:
            if node['NodeName'] == message["Root"]:
                self.root = TaskTree(node)
            else:
                nodesToAdd.append(node)

        
        while len(nodesToAdd) > 0:
            exit = False
            for node in nodesToAdd:
                if(self.root.addNode(node)):
                    nodesToAdd.remove(node)
                    exit = True
                    break
            if(not exit):
                break
               
        for node in message["Message"]["Nodes"]:       
            if node['NodeType'] == 'Method' and node['AgentName'] == self.name:
                self.unavailable_methods.append([node['NodeName'], (node['Duration']), (node['Quality'])])
                

    def next_tick_message(self, message):
    
        for m in self.available_methods:
            if(self.root.findNode(m[0]).compleated):
                self.available_methods.remove(m)
                print("REMOVING")
            
        if self.available_methods and self.running_method is None and self.requested_method is None:
            self.ask_to_start_method(self.run_method())

        if(len(self.available_methods) ==0 and int(message['Message']['Tick']) % 5 ==0):
            for m in self.unavailable_methods:
                method = {}
                method["MessageType"] = "AskMethodStatusMessage"
                method["Message"] = {}
                method["Message"]["MsgSender"] = self.name
                method["Message"]["MsgDest"] = "SIMULATOR"
                method["Message"]["MethodName"] = m[0]
                self.s.send(bytes(json.dumps(method)+"\n"))
     
        
        

    def confirm_method_start_message(self, message):
        if message['Message']['Started']:
            self.running_method = self.requested_method
            for i in range(len(self.sim_agents)):
                if( self.sim_agents[i] != myName):
                    outMesssage = {}
                    outMesssage["MessageType"] = "AgentToAgentMessage"
                    outMesssage["Message"] = {}
                    outMesssage["Message"]["MsgSender"] = self.name
                    outMesssage["Message"]["MsgDest"] = self.sim_agents[i]
                    outMesssage["Message"]["Content"] = {}
                    outMesssage["Message"]["Content"]["Load"] = len(self.available_methods)
                    print(self.running_method)
                    outMesssage["Message"]["Content"]["Running"] = self.root.findNode(self.running_method).data
                    outMesssage["Message"]["Content"]["Compleated"] = ""
                    self.s.send(bytes(json.dumps(outMesssage)+"\n"))
                      
        else:
            for i in range(len(self.available_methods)):
                if self.available_methods[i][0] == message['Message']['MethodName']:
                    tmp = self.available_methods.pop(i)
                    self.unavailable_methods.append(tmp)
                    print("\n\n ADDING METHOD TO unavailable_methods \n\n")
                    break
        self.requested_method = None
        
    def confirm_method_Abort_message(self, message):
        self.running_method = None
        self.requested_method = None
        for i in range(len(self.available_methods)):
                if self.available_methods[i][0] == message['Message']['MethodName']:
                    tmp = self.available_methods.pop(i)
                    self.unavailable_methods.append(tmp)
                    print("\n\n ADDING METHOD TO unavailable_methods \n\n")
                    break
   

    def agent_to_agent_message(self, message):
        print("\n")
        print(message)
        print(message['Message']['Content']['Running'])
        if(message['Message']['Content']['Compleated'] == ""):
            if(self.root.addNode(message['Message']['Content']['Running'])):
                print("added node to tree")
                if(message['Message']['MsgSender'] > self.name):
                    node = self.root.findNode(message['Message']['Content']['Running']['NodeName'])
                    if(node.parent.data['QAF'] == 'OR'):
                        for n in node.parent.data['SubTasks']:
                            if(n == self.running_method):
                                print("aborting method: " , self.running_method)
                                self.sendAbortMessage(self.running_method)
                                break
        else:
            self.root.setNodeCompleated(message['Message']['Content']['Compleated'])
            node = self.root.findNode(message['Message']['Content']['Compleated'])
            if(node):
                if(node.parent.data['QAF'] == 'OR'):
                     for n in node.parent.data['SubTasks']:
                        self.root.setNodeCompleated(n)
                        if(n == self.running_method):
                            print("aborting method: " , self.running_method)
                            self.sendAbortMessage(self.running_method)

    
        print("\n")


    def sendAbortMessage(self, name):
        method = {}
        method["MessageType"] = "AbortMethodMessage"
        method["Message"] = {}
        method["Message"]["MsgSender"] = self.name
        method["Message"]["MsgDest"] = "SIMULATOR"
        method["Message"]["MethodName"] = name
        self.s.send(bytes(json.dumps(method)+"\n"))
    
            
    def notify_method_completed_message(self, message):
        self.running_method = None      
#        print("Removing " + message['Message']['MethodName'] + " from avaiable Methods")
        for i in range(len(self.available_methods)):
            if self.available_methods[i][0] == message['Message']['MethodName']:
                self.available_methods.pop(i)
                break
        self.root.setNodeCompleated(message['Message']['MethodName'])
        for i in range(len(self.sim_agents)):
                if( self.sim_agents[i] != myName):
                    outMesssage = {}
                    outMesssage["MessageType"] = "AgentToAgentMessage"
                    outMesssage["Message"] = {}
                    outMesssage["Message"]["MsgSender"] = self.name
                    outMesssage["Message"]["MsgDest"] = self.sim_agents[i]
                    outMesssage["Message"]["Content"] = {}
                    outMesssage["Message"]["Content"]["Load"] = len(self.available_methods)
                    outMesssage["Message"]["Content"]["Running"] = ""
                    outMesssage["Message"]["Content"]["Compleated"] = message['Message']['MethodName']
                    self.s.send(bytes(json.dumps(outMesssage)+"\n"))
        

    def notify_relationship_activation_message(self, message):
        if(message["Message"]["Type"] == "Disables"):
            i=0
            while i < len(self.available_methods):
                if(self.available_methods[i][0] == message["Message"]["Target"]):
                    tmp = self.available_methods.pop(i)
                    self.unavailable_methods.append(tmp)
#                    print("\n\n Removing disabled method\n\n")
                i+=1
    
        elif(message["Message"]["Type"] == "Enables"):
            i=0
            while i < len(self.unavailable_methods):
                if(self.unavailable_methods[i][0] == message["Message"]["Target"]):
                    tmp = self.unavailable_methods.pop(i)
                    self.available_methods.append(tmp)
#                    print("\n\n adding in enabled method\n\n")
                i+=1
             
myName = sys.argv[1]
agent1 = smartAgent(myName,'localhost',9876)

