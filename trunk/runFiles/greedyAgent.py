import agent
import time
import json
import sys
import random

class GreedyAgent(agent.Agent):

    methods_todo =[]
    #sim_agents = ["Agent_Green"]

    def run_method(self):


        print("\n Methods avaiable")
        print(str(self.available_methods))
        print("")

               
        index =0
        max =0;
        
        for m in range(len(self.available_methods)):
            if self.eval_quality_distrubution(self.available_methods[m][2]) > max:
                max = self.eval_quality_distrubution(self.available_methods[m][2])
                index=m

        print("\n\n\n")
        print index
        print max
        print("\n\n\n")
        return self.available_methods[index][0]



    def next_tick_message(self, message):
        if self.available_methods and self.running_method is None and self.requested_method is None:
            self.ask_to_start_method(self.run_method())
            


    def confirm_method_start_message(self, message):
        if message['Message']['Started']:
            self.running_method = self.requested_method
                      
        else:
            for i in range(len(self.available_methods)):
                if self.available_methods[i][0] == message['Message']['MethodName']:
                    tmp = self.available_methods.pop(i)
                    self.methods_todo.append(tmp)
                    print("\n\n ADDING METHOD TO BACKLOG \n\n")
                    break
        self.requested_method = None

    def agent_to_agent_message(self, message):
        i=0
        while i < len(self.methods_todo):
            tmp = self.methods_todo.pop(0)
            self.available_methods.append(tmp)
            print("\n\n ADDING METHOD BACK IN\n\n")

            
    def notify_method_completed_message(self, message):
        self.running_method = None      
        print("Removing " + message['Message']['MethodName'] + " from avaiable Methods")
        for i in range(len(self.available_methods)):
            if self.available_methods[i][0] == message['Message']['MethodName']:
                self.available_methods.pop(i)
                break
        for i in range(len(self.sim_agents)):
            method = {}
            method["MessageType"] = "AgentToAgentMessage"
            method["Message"] = {}
            method["Message"]["MsgSender"] = self.name
            method["Message"]["MsgDest"] = self.sim_agents[i]
            method["Message"]["Content"] = {}
            method["Message"]["Content"]["Content1"] = "Compleated a method"
            self.s.send(bytes(json.dumps(method)+"\n"))

agent1 = GreedyAgent(sys.argv[1],'localhost',9876)

