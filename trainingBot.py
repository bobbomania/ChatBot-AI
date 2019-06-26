import random
from parseFunction import parser

def cfd(tokenisedText):
    
    file = open("trainingData.txt","r")
    
    answersArr = []
    questionsArr = []
    
    processedAnswers = [[]]
    
    lineNb = 0
    index = -1
    
    for line in file.readlines():
        lineNb += 1
        
        if lineNb%2 == 0:
            answersArr.append(line)
        
        else:
            questionsArr.append(line)
        
    frequenciesToken = []

    for token in tokenisedText:
        for question in questionsArr:
            frequency = 0
            
            if token in question:
                
                questionParsed = parser()
                questionParsed.text = question
                frequency = 1/(len(questionParsed.parse())-1)
                frequenciesToken.append(frequency)
    
    for answer in answersArr:
        index += 1
        if len(frequenciesToken) >= index:
            processedAnswers.append([answer,frequenciesToken[index]])
        
        else:
            processedAnswers.append([answer,''])
    
    if len(frequenciesToken) != 0:
        bestAnswer = answersArr[frequenciesToken.index(max(frequenciesToken))]
    
    else:
        bestAnswer = random.choice(answersArr)
        
    return processedAnswers,bestAnswer