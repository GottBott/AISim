import agent
import time
import json
import sys
import random

class GreedyAgent(agent.Agent):

    
    def run_method(self):

        print("\n Methods avaiable")
        print(str(self.available_methods))
        print("")
        
        max = -1
        for m in range(len(self.available_methods)):
            if self.available_methods[m][2] > max:
                max = m
                
        x = random.randint(0,max)
        return self.available_methods[x][0]



    def next_tick_message(self, message):
        if self.available_methods and self.running_method is None and self.requested_method is None:
            self.ask_to_start_method(self.run_method())


    def confirm_method_start_message(self, message):
        if message['Message']['Started']:
            self.running_method = self.requested_method
        self.requested_method = None
       # else:
    #            self.ask_to_start_method(self.run_method())
            
           
            

agent1 = GreedyAgent(sys.argv[1],'localhost',9876)

