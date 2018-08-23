#!/usr/bin/python
# -*- coding: utf-8 -*-
import agent
import time
import json
import sys
import random


# Tree structure used by the agent to build task tree
class TaskTree(object):

    def __init__(self, data, children=[]):
        self.children = children
        self.data = data
        self.parent = None
        self.compleated = False


# adds a check to see if node is already in tree.
    def addNode(self, node):
        if(self.findNode(node)):
            return True;
        else:
            return self.addNodePrivate(node)
    
    # Adds a new node to the tree
    def addNodePrivate(self, node):
        for subtask in self.data['SubTasks']:
            if node['NodeName'] == subtask:
                x = TaskTree(node)
                x.children = []
                x.parent = self
                print node['NodeName']
                self.children.append(x)
                return True
        for child in self.children:
            if child.addNode(node):
                return True
        return False

    # Finds a node by name in the tree
    def findNode(self, name):
        if self.data['NodeName'] == name:
            return self
        else:
            for child in self.children:
                tmp = child.findNode(name)
                if tmp != False:
                    return tmp
        return False

    # Sets completed to true
    # Called when the simulator tells the agent a method has finished running
    def setNodeCompleated(self, name):
        if self.data['NodeName'] == name:
            self.compleated = True
            return
        else:
            for child in self.children:
                return child.setNodeCompleated(name)
        return


# Smart Agent class that inherits from Agent.py
class smartAgent(agent.Agent):

    # The root of the task tree
    root = None
    plan =[]
    relationships =[]

    # called by the agent to ask the simulator to start a method
    def ask_to_start_method(self, node):
        if(node != None):
            message = {}
            message['MessageType'] = 'StartMethodMessage'
            message['Message'] = {}
            message['Message']['MsgSender'] = self.name
            message['Message']['MsgDest'] = 'SIMULATOR'
            message['Message']['MethodName'] = node.data['NodeName']
            self.s.send(bytes(json.dumps(message) + '\n'))
            self.requested_method = node

    
# builds a plan
    def buildPlan(self):
    
        initPlan = list(self.unavailable_methods)

        while len(initPlan) >=1:
            index = -1
            max = 0
            bestNode = None

            for ip in initPlan:
                quality = self.eval_quality_distrubution(ip)
                if quality > max:
                    max = quality
                    bestNode = ip
            
            self.plan.append(bestNode)
            node = self.root.findNode(bestNode.data['NodeName'])
            if node.parent.data['QAF'] == 'OR':
                for n in node.parent.children:
                    if n in initPlan:
                        initPlan.remove(n)
    
    
# finds the best method to run and returns it
    def run_method(self):

        if(len(self.plan) > 0):
            tmp = self.plan.pop(0)
            return tmp

        return None

# gets the initial tree from the simulator and creates tree
    def initial_tree_message(self, message):
        nodesToAdd = []
        for node in message['Message']['Nodes']:
            if node['NodeName'] == message['Root']:
                self.root = TaskTree(node)
            else:
                nodesToAdd.append(node)

        while len(nodesToAdd) > 0:
            exit = False
            for node in nodesToAdd:
                if self.root.addNode(node):
                    nodesToAdd.remove(node)
                    treeNode = self.root.findNode(node['NodeName'])
                    if treeNode.data['NodeType'] == 'Method' \
                        and treeNode.data['AgentName'] == self.name:
                        self.unavailable_methods.append(treeNode)
                    exit = True
                    break
            if not exit:
                break
        for relation in message['Message']['Relationships']:
            self.relationships.append(relation) 
            
        self.buildPlan();   
            
# next tick of the simulation has occurred
    def next_tick_message(self, message):

        for m in self.available_methods:
            if m.compleated:
                self.available_methods.remove(m)
                print 'REMOVING'

        if self.available_methods and self.running_method is None \
            and self.requested_method is None:
            self.ask_to_start_method(self.run_method())

        if len(self.available_methods) == 0 and int(message['Message'
                ]['Tick']) % 5 == 0:
            for m in self.unavailable_methods:
                method = {}
                method['MessageType'] = 'AskMethodStatusMessage'
                method['Message'] = {}
                method['Message']['MsgSender'] = self.name
                method['Message']['MsgDest'] = 'SIMULATOR'
                method['Message']['MethodName'] = m.data['NodeName']
                self.s.send(bytes(json.dumps(method) + '\n'))

# received the go ahead to start the simulation
    def start_simulation_message(self, message):
        for i in message['Message']['Agents']:
            self.sim_agents.append(str(i))
            
        for m in self.plan:
            for i in range(len(self.sim_agents)):
                if self.sim_agents[i] != myName:
                    outMesssage = {}
                    outMesssage['MessageType'] = 'AgentToAgentMessage'
                    outMesssage['Message'] = {}
                    outMesssage['Message']['MsgSender'] = self.name
                    outMesssage['Message']['MsgDest'] = \
                        self.sim_agents[i]
                    outMesssage['Message']['Content'] = {}
                    outMesssage['Message']['Content']['Type'] = 'Plan'
                    outMesssage['Message']['Content']['Load'] = \
                        len(self.unavailable_methods)
                    outMesssage['Message']['Content']['Plan'] = m.data
                    self.s.send(bytes(json.dumps(outMesssage) + '\n'))
                    print("sent a plan")
            
        print '#######'
        print self.sim_agents
        print '#######'
        for m in self.unavailable_methods:
            method = {}
            method['MessageType'] = 'AskMethodStatusMessage'
            method['Message'] = {}
            method['Message']['MsgSender'] = self.name
            method['Message']['MsgDest'] = 'SIMULATOR'
            method['Message']['MethodName'] = m.data['NodeName']
            self.s.send(bytes(json.dumps(method) + '\n'))

    def notify_method_status_message(self, message):
        if message['Message']['Enabled']:
            if not message['Message']['Completed']:
                if not message['Message']['Started']:
                    for i in range(len(self.unavailable_methods)):
                        if self.unavailable_methods[i].data['NodeName'] \
                            == message['Message']['MethodName']:
                            self.available_methods.append(self.unavailable_methods[i])
                            self.unavailable_methods.pop(i)
                            break

    def confirm_method_start_message(self, message):
        if message['Message']['Started']:
            self.running_method = self.requested_method
            for i in range(len(self.sim_agents)):
                if self.sim_agents[i] != myName:
                    outMesssage = {}
                    outMesssage['MessageType'] = 'AgentToAgentMessage'
                    outMesssage['Message'] = {}
                    outMesssage['Message']['MsgSender'] = self.name
                    outMesssage['Message']['MsgDest'] = \
                        self.sim_agents[i]
                    outMesssage['Message']['Content'] = {}
                    outMesssage['Message']['Content']['Type'] = 'Running'
                    outMesssage['Message']['Content']['Load'] = \
                        len(self.available_methods)
                    print self.running_method
                    outMesssage['Message']['Content']['Running'] = \
                        self.running_method.data
                    outMesssage['Message']['Content']['Compleated'] = ''
                    self.s.send(bytes(json.dumps(outMesssage) + '\n'))
        else:

            for i in range(len(self.available_methods)):
                if self.available_methods[i].data['NodeName'] \
                    == message['Message']['MethodName']:
                    tmp = self.available_methods.pop(i)
                    self.unavailable_methods.append(tmp)
                    print '''

 ADDING METHOD TO unavailable_methods 

'''
                    break
        self.requested_method = None


# aborts a currently running method after simulator confirms abort request
    def confirm_method_Abort_message(self, message):
        self.running_method = None
        self.requested_method = None
        for i in range(len(self.available_methods)):
            if self.available_methods[i].data['NodeName'] \
                == message['Message']['MethodName']:
                tmp = self.available_methods.pop(i)
                self.unavailable_methods.append(tmp)
                print '''

 ADDING METHOD TO unavailable_methods 

'''
                break

# receives a message from other agents
    def agent_to_agent_message(self, message):
        print '\n'
        print message
        if message['Message']['Content']['Type'] == 'Running':
            if self.root.addNode(message['Message']['Content']['Running'
                                 ]):
                print 'added node to tree'
                node = self.root.findNode(message['Message']['Content'
                        ]['Running']['NodeName'])
                nodeValue = self.eval_quality_distrubution(node)
                print nodeValue
                if node.parent.data['QAF'] == 'OR':
                    for n in node.parent.children:
                        if n == self.running_method:
                            myValue = self.eval_quality_distrubution(n)
                            print '''

'''
                            print ('MyValue: ', myValue, ' OtherValue: '
                                   , nodeValue)
                            print ('MyLoad: ',
                                   len(self.available_methods),
                                   ' OtherLoad: ', message['Message'
                                   ]['Content']['Load'])
                            print '''

'''
                            if nodeValue > myValue:
                                print ('aborting method: ',
                                        self.running_method)
                                self.sendAbortMessage(self.running_method)
                                break
                            elif nodeValue == myValue \
                                and message['Message']['Content']['Load'
                                    ] < len(self.available_methods):
                                print ('aborting method: ',
                                        self.running_method)
                                self.sendAbortMessage(self.running_method)
                                break
                            elif nodeValue == myValue \
                                and message['Message']['Content']['Load'
                                    ] == len(self.available_methods) \
                                and message['Message']['MsgSender'] \
                                > self.name:
                                print ('aborting method: ',
                                        self.running_method)
                                self.sendAbortMessage(self.running_method)
                                break
        elif message['Message']['Content']['Type'] == 'Compleated':

            self.root.setNodeCompleated(message['Message']['Content'
                    ]['Compleated'])
            node = self.root.findNode(message['Message']['Content'
                    ]['Compleated'])
            if node:
                if node.parent.data['QAF'] == 'OR':
                    for n in node.parent.data['SubTasks']:
                        self.root.setNodeCompleated(n)
                        if n == self.running_method:
                            print ('aborting method: ',
                                   self.running_method)
                            self.sendAbortMessage(self.running_method)
                            
        elif message['Message']['Content']['Type'] == 'Plan':
            if self.root.addNode(message['Message']['Content']['Plan'
                                 ]):
                print 'added node to tree'
                node = self.root.findNode(message['Message']['Content'
                        ]['Plan']['NodeName'])
                for r in self.relationships:
                    if(r['RelationshipType'] == 'Disables' and r['Source'] == node.data['NodeName']):
                        target = r['Destination']
                        target = self.root.findNode(target)
                        if(target):
                            if(target.data['AgentName'] == self.name):
                                q1 = self.eval_quality_distrubution(target)
                                q2 = self.eval_quality_distrubution(node)
                                if(q1 > q2):
                                    self.alterPlan(node.data['AgentName'],node.data['NodeName'],"Remove")
                                    
        elif message['Message']['Content']['Type'] == 'RemovePlan':
            print("HHHHHHEEERRRREEE")
            if(self.root.findNode(message['Message']['Content']['Remove']) in self.plan):
                self.plan.remove(self.root.findNode(message['Message']['Content']['Remove']))
                print("\nremoving from plan\n")
        print '\n'


# ask an agent to not do something in its plan
    def alterPlan(self, agentName, methodName, type):
        print("would ask " , agentName, " to ", type, " ", methodName)
        if(type == "Remove"):
            print("YO YO YO YO");
            outMesssage = {}
            outMesssage['MessageType'] = 'AgentToAgentMessage'
            outMesssage['Message'] = {}
            outMesssage['Message']['MsgSender'] = self.name
            outMesssage['Message']['MsgDest'] = agentName
            outMesssage['Message']['Content'] = {}
            outMesssage['Message']['Content']['Type'] = 'RemovePlan'
            outMesssage['Message']['Content']['Remove'] = methodName
            self.s.send(bytes(json.dumps(outMesssage) + '\n'))
        elif(type == "Add"):
            print("");

# ask to abort currently running message
    def sendAbortMessage(self, node):
        method = {}
        method['MessageType'] = 'AbortMethodMessage'
        method['Message'] = {}
        method['Message']['MsgSender'] = self.name
        method['Message']['MsgDest'] = 'SIMULATOR'
        method['Message']['MethodName'] = node.data['NodeName']
        self.s.send(bytes(json.dumps(method) + '\n'))

# simulator tells agent is it finished running a method
    def notify_method_completed_message(self, message):
        self.running_method = None

        for i in range(len(self.available_methods)):
            if self.available_methods[i].data['NodeName'] \
                == message['Message']['MethodName']:
                self.available_methods.pop(i)
                break
        self.root.setNodeCompleated(message['Message']['MethodName'])
        for i in range(len(self.sim_agents)):
            if self.sim_agents[i] != myName:
                outMesssage = {}
                outMesssage['MessageType'] = 'AgentToAgentMessage'
                outMesssage['Message'] = {}
                outMesssage['Message']['MsgSender'] = self.name
                outMesssage['Message']['MsgDest'] = self.sim_agents[i]
                outMesssage['Message']['Content'] = {}
                outMesssage['Message']['Content']['Type'] = 'Compleated'
                outMesssage['Message']['Content']['Load'] = \
                    len(self.available_methods)
                outMesssage['Message']['Content']['Running'] = ''
                outMesssage['Message']['Content']['Compleated'] = \
                    message['Message']['MethodName']
                self.s.send(bytes(json.dumps(outMesssage) + '\n'))

# when a relationship activation message is received
    def notify_relationship_activation_message(self, message):
        if message['Message']['Type'] == 'Disables':
            i = 0
            while i < len(self.available_methods):
                if self.available_methods[i].data['NodeName'] \
                    == message['Message']['Target']:
                    tmp = self.available_methods.pop(i)
                    self.unavailable_methods.append(tmp)

                i += 1
        elif message['Message']['Type'] == 'Enables':

            i = 0
            while i < len(self.unavailable_methods):
                if self.unavailable_methods[i].data['NodeName'] \
                    == message['Message']['Target']:
                    tmp = self.unavailable_methods.pop(i)
                    self.available_methods.append(tmp)

                i += 1

    # This Method takes a node and evaluates the quality distribution of that node.

    def eval_quality_distrubution(self, node):
        dist = str(node.data['Quality'])
        value = 0
        x = ''.join(x for x in dist if x not in '()')
        y = x.split(',')
        i = 0
        while i < len(y) - 1:
            value += float(y[i]) * float(y[i + 1])
            i += 2
        return value


myName = sys.argv[1]
agent1 = smartAgent(myName, 'localhost', 9876)


            